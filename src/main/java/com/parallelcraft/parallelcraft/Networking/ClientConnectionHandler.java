package com.parallelcraft.parallelcraft.Networking;

import com.parallelcraft.parallelcraft.exceptions.InsufficientDataException;
import com.parallelcraft.parallelcraft.user.GameProfile;
import com.parallelcraft.parallelcraft.util.AdvancedList;
import com.parallelcraft.parallelcraft.util.Tuple;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This is responsible for holding all the network stuff that corresponds to one client
 * 
 * @author extremeCrazyCoder
 */
public class ClientConnectionHandler {
    public static final int INPUT_BUFFER_SIZE = 10240;
    public static final int WRITE_BUFFER_SIZE = 10240;
    public static final int WRITE_HEADER_BUFFER_SIZE = 10;
    private Logger logger = LogManager.getLogger("NettyServerHandler");
    
    private ServerPortManager parent;
    
    private GameProfile clientProfile;
    
    private byte currentProtocol = Protocol.HANDSHAKE;
    private Tuple<Byte, Integer> delayedProtocolChange = null;
    private List<Supplier<? extends PacketIncoming>> packetCacheToServer;
    private Map<Class<? extends PacketOutgoing>, Byte> packetCacheToClient;
    
    private HandshakePacketHandler handshakeHandler;
    
    private SocketChannel clientChannel;
    private Thread clientReadThread;
    private Thread clientWriteThread;
    private ByteBuffer bufRead;
    private ByteBuffer bufWrite;
    private SecretKey sharedEncryptionKey = null;
    private Cipher encryptionCipher = null;
    private Cipher decryptionCipher = null;
    
    AdvancedList<PacketOutgoing> packetQueue = new AdvancedList<>();
    
    public ClientConnectionHandler(ServerPortManager pParent, SocketChannel pClientChannel) throws IOException {
        parent = pParent;
        clientChannel = pClientChannel;
        updatePacketCache();
        handshakeHandler = new HandshakePacketHandler(this);
        
        clientReadThread = new Thread(new Runnable() {
            @Override
            public void run() {
                runRead();
            }
        });
        clientReadThread.start();
        
        clientWriteThread = new Thread(new Runnable() {
            @Override
            public void run() {
                runWrite();
            }
        });
        clientWriteThread.start();
    }
    
    public void runRead() {
        bufRead = ByteBuffer.allocate(INPUT_BUFFER_SIZE);
        //extra buffer so that we can set read / max individually
        ByteBuffer inputBuffer = ByteBuffer.allocate(INPUT_BUFFER_SIZE);
        PacketReadHelper helper = new PacketReadHelper(bufRead);
        PacketReadHelper externalHelp = new PacketReadHelper(inputBuffer);
        
        try {
            int amountLast = -1;
            do { 
                //channel -> read
                amountLast = clientChannel.read(bufRead);

                if(amountLast <= 0) continue;

                bufRead.flip();

                while(! Thread.currentThread().isInterrupted()) {
                    bufRead.mark();
                    int nextLen;
                    try {
                        nextLen = helper.getInteger();
                    } catch(InsufficientDataException e) {
                        //buffer doesn't even contain enogth data to get the length of the next packet
                        bufRead.reset();
                        bufRead.compact();
                        break;
                    }

                    if(bufRead.remaining() < nextLen) {
                        bufRead.reset();
                        bufRead.compact();
                        break;
                    }
                    
                    byte readBytes[] = new byte[nextLen];
                    bufRead.get(readBytes); // no need for offset / len since given by array
                    
                    if(decryptionCipher != null) {
                        readBytes = decryptionCipher.update(readBytes);
                    }
                    inputBuffer.clear();
                    inputBuffer.put(readBytes);
                    inputBuffer.flip();
                    
                    PacketIncoming p = MCPacketDecoder.readNextPacket(this, externalHelp);
                    logger.trace("Received new Packet {}", p);
                    p.getHandler(this).handle(p);
                }
            } while(amountLast > 0 && ! Thread.currentThread().isInterrupted());

            if(amountLast == -1) {
                //client closed connection
                clientChannel.close();

                //TODO unregister
                System.exit(0);
            }
        } catch (IOException ex) {
            //should not happen
            logger.error(ex);
            ex.printStackTrace();
        } catch(InsufficientDataException ex) {
            //error in protocol
            //TODO disconnect client
            logger.debug("InsufficientData??", ex);
            ex.printStackTrace();
            System.exit(0);
        } catch(Exception e) {
            logger.debug(e);
            e.printStackTrace();
            System.exit(0);
        }
    }
    
    public void runWrite() {
        //final buffer used to write to stream
        //we will group multiple small packets to one big using this for the network side
        //OS network implementation will most likely split it up again as needed
        bufWrite = ByteBuffer.allocate(WRITE_BUFFER_SIZE);
        
        //buffer + helper used for encoding the packet itself
        ByteBuffer bufPacketWrite = ByteBuffer.allocate(WRITE_BUFFER_SIZE);
        PacketWriteHelper helperPacket = new PacketWriteHelper(bufPacketWrite);
        //used to cache data that would not fit into this chunk of data
        byte[] tempPacket = null;
        //buffer + helper used to encode the packet headers
        ByteBuffer headerBuffer = ByteBuffer.allocate(WRITE_HEADER_BUFFER_SIZE);
        PacketWriteHelper headerHelper = new PacketWriteHelper(headerBuffer);
        
        try {
            do {
                if(tempPacket != null) {
                    //there is a packet in the buffer that we could not send last time
                    bufWrite.put(tempPacket);
                    tempPacket = null;
                }

                while(! packetQueue.isEmpty()) {
                    PacketOutgoing p = packetQueue.fetchFirst();
                    logger.trace("Writing Packet {}", p);
                    helperPacket.writeByte(packetCacheToClient.get(p.getClass()));
                    p.encode(helperPacket);
                    bufPacketWrite.flip();
                    
                    headerHelper.writeInteger(bufPacketWrite.limit());
                    headerBuffer.flip();
                    
                    tempPacket = new byte[headerBuffer.limit() + bufPacketWrite.limit()];
                    headerBuffer.get(tempPacket, 0, headerBuffer.limit());
                    bufPacketWrite.get(tempPacket, headerBuffer.limit(), bufPacketWrite.limit());
                    
                    //reset all used buffers
                    bufPacketWrite.clear();
                    headerBuffer.clear();
                    
                    if(encryptionCipher != null) {
                        tempPacket = encryptionCipher.update(tempPacket);
                    }
                    //TODO perform compression / encryption

                    if(bufWrite.remaining() >= tempPacket.length) {
                        //packet fits into buffer
                        bufWrite.put(tempPacket);
                        tempPacket = null;
                    } else {
                        break;
                    }
                    
                    if(delayedProtocolChange != null) {
                        delayedProtocolChange.setArg2(delayedProtocolChange.getArg2() - 1);
                        if(delayedProtocolChange.getArg2() == 0) {
                            byte newProt = delayedProtocolChange.getArg1();
                            delayedProtocolChange = null;
                            setProtocol(newProt, false);
                        }
                    }
                }
                
                bufWrite.flip();
                clientChannel.write(bufWrite);
                bufWrite.clear();

                //TODO reduce time & skip if tempPacket != null
                Thread.sleep(1000);
            } while(! Thread.currentThread().isInterrupted());
        } catch(Exception e) {
            logger.debug(e);
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void enableEncrytption(SecretKey encryptionKey) {
        encryptionCipher = MCEncryption.createSharedCipher(Cipher.ENCRYPT_MODE, encryptionKey);
        decryptionCipher = MCEncryption.createSharedCipher(Cipher.DECRYPT_MODE, encryptionKey);
        sharedEncryptionKey = encryptionKey;
    }
    
    private void updatePacketCache() {
        packetCacheToServer = Protocol.getIncomingPacketsForProtocol(currentProtocol);
        packetCacheToClient = Protocol.getOutgoingPacketMapForProtocol(currentProtocol);
    }
    
    public List<Supplier<? extends PacketIncoming>> getPacketCacheToServer() {
        return packetCacheToServer;
    }
    
    public Map<Class<? extends PacketOutgoing>, Byte> getPacketCacheToClient() {
        return packetCacheToClient;
    }
    
    public byte getCurrentProtocol() {
        return currentProtocol;
    }
    
    /**
     * changes the protocol to given type
     * 
     * @param newProtocolType the new type to use
     * @param queued if this is true then this will be performed as part of the write queue
     */
    protected void setProtocol(byte newProtocolType, boolean queued) {
        if(queued && packetQueue.size() > 0) {
            delayedProtocolChange = new Tuple<>(newProtocolType, packetQueue.size());
            return;
        }
        currentProtocol = newProtocolType;
        updatePacketCache();
    }
    
//[21:16:02] [Netty Epoll Server IO #1/INFO]:  IN: [HANDSHAKING:0] net.minecraft.server.v1_16_R2.PacketHandshakingInSetProtocol
//[21:16:02] [Netty Epoll Server IO #1/INFO]:  IN: [LOGIN:0] net.minecraft.server.v1_16_R2.PacketLoginInStart
//[21:16:02] [Netty Epoll Server IO #1/INFO]:  OUT: PACKET: net.minecraft.server.v1_16_R2.PacketLoginOutEncryptionBegin@43109570 / LOGIN
//[21:16:02] [Netty Epoll Server IO #1/INFO]:  IN: [LOGIN:1] net.minecraft.server.v1_16_R2.PacketLoginInEncryptionBegin
//[21:16:02] [User Authenticator #1/INFO]: UUID of player **** is ****
//[21:16:02] [Server thread/INFO]:  OUT: PACKET: net.minecraft.server.v1_16_R2.PacketLoginOutSetCompression@232eaa1d / LOGIN
//[21:16:02] [Server thread/INFO]:  OUT: PACKET: net.minecraft.server.v1_16_R2.PacketLoginOutSuccess@7a968bfc / LOGIN
//[21:16:02] [Server thread/INFO]:  OUT: PACKET: net.minecraft.server.v1_16_R2.PacketPlayOutLogin@7761288 / PLAY
//[21:16:02] [Server thread/INFO]:  OUT: PACKET: net.minecraft.server.v1_16_R2.PacketPlayOutCustomPayload@6f6f0218 / PLAY
//[21:16:02] [Server thread/INFO]:  OUT: PACKET: net.minecraft.server.v1_16_R2.PacketPlayOutServerDifficulty@5746d35 / PLAY
//[21:16:02] [Server thread/INFO]:  OUT: PACKET: net.minecraft.server.v1_16_R2.PacketPlayOutAbilities@71464f35 / PLAY
//[21:16:02] [Server thread/INFO]:  OUT: PACKET: net.minecraft.server.v1_16_R2.PacketPlayOutHeldItemSlot@7c2252d4 / PLAY
//[21:16:02] [Server thread/INFO]:  OUT: PACKET: net.minecraft.server.v1_16_R2.PacketPlayOutRecipeUpdate@760e9f5e / PLAY
//[21:16:02] [Server thread/INFO]:  OUT: PACKET: net.minecraft.server.v1_16_R2.PacketPlayOutTags@34854e8e / PLAY
//[21:16:02] [Server thread/INFO]:  OUT: PACKET: net.minecraft.server.v1_16_R2.PacketPlayOutEntityStatus@6847e8c5 / PLAY
//[21:16:02] [Server thread/INFO]:  OUT: PACKET: net.minecraft.server.v1_16_R2.PacketPlayOutCommands@4d69ccef / PLAY
//[21:16:02] [Server thread/INFO]:  OUT: PACKET: net.minecraft.server.v1_16_R2.PacketPlayOutRecipes@54ecf96f / PLAY
//[21:16:02] [Server thread/INFO]:  OUT: PACKET: net.minecraft.server.v1_16_R2.PacketPlayOutPosition@3f2adaa8 / PLAY
//[21:16:02] [Server thread/INFO]:  OUT: PACKET: net.minecraft.server.v1_16_R2.PacketPlayOutChat@a4aef13 / PLAY
//[21:16:02] [Server thread/INFO]:  OUT: PACKET: PacketPlayOutPlayerInfo{action=ADD_PLAYER, entries=[PlayerInfoData{latency=0, gameMode=SURVIVAL, profile=****, displayName=null}]} / PLAY
//[21:16:02] [Server thread/INFO]:  OUT: PACKET: PacketPlayOutPlayerInfo{action=ADD_PLAYER, entries=[PlayerInfoData{latency=0, gameMode=SURVIVAL, profile=****, displayName=null}]} / PLAY
//[21:16:02] [Server thread/INFO]:  OUT: PACKET: net.minecraft.server.v1_16_R2.PacketPlayOutEntityMetadata@13d0eb4e / PLAY
//[21:16:02] [Server thread/INFO]:  OUT: PACKET: net.minecraft.server.v1_16_R2.PacketPlayOutViewCentre@383d7878 / PLAY
//[21:16:02] [Server thread/INFO]:  OUT: PACKET: net.minecraft.server.v1_16_R2.PacketPlayOutLightUpdate@3896d6a8 / PLAY

    public PacketHandler getHandshakeHandler() {
        return handshakeHandler;
    }
    
    public ServerPortManager getServerPortManager() {
        return parent;
    }
    
    public void sendPacket(PacketOutgoing toSend) {
        packetQueue.add(toSend);
    }
    
    public void setGameProfile(GameProfile pClientProfile) {
        clientProfile = pClientProfile;
    }
}

package com.parallelcraft.Networking;

import com.parallelcraft.Datapack.DatapackHolder;
import com.parallelcraft.Networking.Packets.PacketPlayOutAbilities;
import com.parallelcraft.Networking.Packets.PacketPlayOutEntityStatus;
import com.parallelcraft.Networking.Packets.PacketPlayOutHeldItemSlot;
import com.parallelcraft.Networking.Packets.PacketPlayOutLightUpdate;
import com.parallelcraft.Networking.Packets.PacketPlayOutLogin;
import com.parallelcraft.Networking.Packets.PacketPlayOutMapChunk;
import com.parallelcraft.Networking.Packets.PacketPlayOutPlayerInfo;
import com.parallelcraft.Networking.Packets.PacketPlayOutPosition;
import com.parallelcraft.Networking.Packets.PacketPlayOutServerDifficulty;
import com.parallelcraft.constants.EnumDifficulty;
import com.parallelcraft.constants.EnumGamemode;
import com.parallelcraft.constants.EnumPlayerInfoAction;
import com.parallelcraft.exceptions.InsufficientDataException;
import com.parallelcraft.user.GameProfile;
import com.parallelcraft.util.AdvancedList;
import com.parallelcraft.util.Constants;
import com.parallelcraft.util.Tuple;
import com.parallelcraft.world.Blocks;
import com.parallelcraft.world.Chunk;
import com.parallelcraft.world.ChunkCoordinates;
import com.parallelcraft.world.DimensionManager;
import com.parallelcraft.world.Position;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.logging.Level;
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
    public static final int INPUT_BUFFER_SIZE = 3 * 1024 * 1024;
    public static final int WRITE_BUFFER_SIZE = 3 * 1024 * 1024;
    public static final int WRITE_HEADER_BUFFER_SIZE = 100;
    private Logger logger = LogManager.getLogger("ClientConnectionHandler");
    
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
    
    public ClientConnectionHandler(ServerPortManager parent, SocketChannel clientChannel) throws IOException {
        this.parent = parent;
        this.clientChannel = clientChannel;
        updatePacketCache();
        handshakeHandler = new HandshakePacketHandler(this);
        
        clientReadThread = new Thread(() -> {
            runRead();
        });
        clientReadThread.start();
        
        clientWriteThread = new Thread(() -> {
            runWrite();
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
                        nextLen = helper.readVarLenInteger();
                    } catch(InsufficientDataException e) {
                        //buffer doesn't even contain enogth data to get the length of the next packet
                        bufRead.reset();
                        bufRead.compact();
                        break;
                    }
                    //TODO limit nextLen
                    
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
                    
                    headerHelper.writeVarLenInteger(bufPacketWrite.limit());
                    headerBuffer.flip();
                    
                    tempPacket = new byte[headerBuffer.limit() + bufPacketWrite.limit()];
                    headerBuffer.get(tempPacket, 0, headerBuffer.limit());
                    bufPacketWrite.get(tempPacket, headerBuffer.limit(), bufPacketWrite.limit());
                    
                    //reset all used buffers
                    bufPacketWrite.clear();
                    headerBuffer.clear();
                    
                    if(Constants.CONNECTION_DEBUG) {
                        StringBuilder packetRaw = new StringBuilder();
                        char[] hexChars = "0123456789ABCDEF".toCharArray();
                        for(int i = 0; i < tempPacket.length; i++) {
                            packetRaw.append(hexChars[(tempPacket[i] >> 4) & 0x0F]);
                            packetRaw.append(hexChars[tempPacket[i] & 0x0F]);
                            packetRaw.append(" ");
                        }
                        logger.debug("Outgoing: {}", packetRaw.toString());
                    }
                    
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
                Thread.sleep(100);
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
    
    PacketHandler defHandle = (p) -> {
        logger.debug("Incoming Packet {}", p);
    };
    public PacketHandler getDefaultPacketHandler() {
        return defHandle;
    }
    
    public ServerPortManager getServerPortManager() {
        return parent;
    }
    
    public void sendPacket(PacketOutgoing toSend) {
        packetQueue.add(toSend);
    }
    
    public void setGameProfile(GameProfile clientProfile) {
        this.clientProfile = clientProfile;
    }
    
    public void loginPlay() {
        sendPacket(new PacketPlayOutLogin(5, false, EnumGamemode.CREATIVE, null,
            12345, DimensionManager.getOverworld(), 200,
            10, false, true, true, false));
        
        sendPacket(new PacketPlayOutServerDifficulty(EnumDifficulty.PEACEFUL, true));
//        playerconnection.sendPacket(new PacketPlayOutCustomPayload(PacketPlayOutCustomPayload.BRAND, (new PacketDataSerializer(Unpooled.buffer())).a(this.getServer().getServerModName())));
        sendPacket(new PacketPlayOutAbilities(true, true, true, true, 5, 5));
        sendPacket(new PacketPlayOutHeldItemSlot((byte) 0));
        
//        playerconnection.sendPacket(new PacketPlayOutRecipeUpdate(this.server.getCraftingManager().b()));
//        playerconnection.sendPacket(new PacketPlayOutTags(this.server.getTagRegistry().a((IRegistryCustom) this.registryHolder)));

        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(ClientConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int n = 0;
        for(int i = -10; i < 11; i++) {
            for(int j = -10; j < 11; j++) {
                while(DatapackHolder.BLOCKS.byID(n) == null) {
                    n++;
                }
                Chunk test = new Chunk(DimensionManager.getOverworld(), new ChunkCoordinates(i, j), DatapackHolder.BLOCKS.byID(n), 32);
                sendPacket(new PacketPlayOutMapChunk(test));
                n++;
            }
        }
        sendPacket(new PacketPlayOutPosition(new Position(0, 40, 0), 0, 0, 0, false));
        
        try {
            Thread.sleep(30000);
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(ClientConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

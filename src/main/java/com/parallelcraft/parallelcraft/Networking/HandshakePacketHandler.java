package com.parallelcraft.parallelcraft.Networking;

import com.parallelcraft.parallelcraft.Networking.Packets.PacketHandshakingInSetProtocol;
import com.parallelcraft.parallelcraft.Networking.Packets.PacketLoginInEncryptionBegin;
import com.parallelcraft.parallelcraft.Networking.Packets.PacketLoginInStart;
import com.parallelcraft.parallelcraft.Networking.Packets.PacketLoginOutEncryptionBegin;
import com.parallelcraft.parallelcraft.Networking.Packets.PacketLoginOutSuccess;
import com.parallelcraft.parallelcraft.exceptions.ProtocolException;
import com.parallelcraft.parallelcraft.user.GameProfile;
import com.parallelcraft.parallelcraft.user.UUID;
import com.parallelcraft.parallelcraft.util.BaseConversionHelper;
import com.parallelcraft.parallelcraft.util.Constants;
import javax.crypto.SecretKey;

/**
 * This is responsible for handling everything until the play phase starts
 * Needs to keep track of who is logging in
 * Check against Mojang auth lib
 * Start encryption & compression
 * 
 * @author extremeCrazyCoder
 */
public class HandshakePacketHandler implements PacketHandler {
    ClientConnectionHandler parent;
    enum HandshakeState {
        INITIAL,
        SET_PROTOCOL,
        LOGIN_START,
        ENCRYPTING,
    }
    
    private HandshakeState state = HandshakeState.INITIAL;
    
    private String username;
    private UUID uuid;
    private byte nonce[];
    private SecretKey encryptionKey;
    private GameProfile playerProfile;
    
    public HandshakePacketHandler(ClientConnectionHandler pParent) {
        this.parent = pParent;
    }

    @Override
    public void handle(PacketIncoming p) {
        if(p instanceof PacketHandshakingInSetProtocol) {
            checkState(HandshakeState.INITIAL);
            PacketHandshakingInSetProtocol pConv = (PacketHandshakingInSetProtocol) p;
            
            if(pConv.getClientVersion() < Constants.MC_VERSION_NUM) {
                throw new ProtocolException("Outdated client Please use " + Constants.VERSION);
            } else if(pConv.getClientVersion() > Constants.MC_VERSION_NUM) {
                throw new ProtocolException("Outdated server Please use " + Constants.VERSION);
            }
            //TODO use hostname / port -> maybe check against allowed? only partially possible because of port forwarding
            
            parent.setProtocol(pConv.getProtocol(), false);
            state = HandshakeState.SET_PROTOCOL;
            
        } else if(p instanceof PacketLoginInStart) {
            checkState(HandshakeState.SET_PROTOCOL);
            PacketLoginInStart pConv = (PacketLoginInStart) p;
            
            username = pConv.getUsername();
            
            String authNonce = "";
            nonce = new byte[5];
            parent.getServerPortManager().getRandom().nextBytes(nonce);
            
            parent.sendPacket(new PacketLoginOutEncryptionBegin(authNonce, parent.getServerPortManager().getKeyPair().getPublic(), nonce));
            state = HandshakeState.LOGIN_START;
        } else if(p instanceof PacketLoginInEncryptionBegin) {
            checkState(HandshakeState.LOGIN_START);
            PacketLoginInEncryptionBegin pConv = (PacketLoginInEncryptionBegin) p;
            byte receivedNonce[] = MCEncryption.decrypt(parent.getServerPortManager().getKeyPair().getPrivate(), pConv.getEncryptionNounce());
            
            if(! BaseConversionHelper.toHex(receivedNonce).equals(BaseConversionHelper.toHex(nonce))) {
                throw new ProtocolException("Nonce is not equal got " + BaseConversionHelper.toHex(receivedNonce)
                        + " expected " + BaseConversionHelper.toHex(nonce));
            }
            
            byte byteKey[] = MCEncryption.decrypt(parent.getServerPortManager().getKeyPair().getPrivate(), pConv.getEncryptionKey());
            encryptionKey = MCEncryption.generateSharedKey(byteKey);
            parent.enableEncrytption(encryptionKey);
            
            //TODO uise mojangs authlib with authNonce
            
            uuid = UUID.create(new int[5]);
            playerProfile = GameProfile.create(uuid, username);
            parent.sendPacket(new PacketLoginOutSuccess(playerProfile));
            parent.setGameProfile(playerProfile);
            parent.setProtocol(Protocol.PLAY, true);
            
            state = HandshakeState.ENCRYPTING;
            //TODO set compression
        } else {
            throw new ProtocolException("Unexpected Packet type: '" + p.getClass().getCanonicalName() + "'");
        }
    }
//[21:16:02] [Netty Epoll Server IO #1/INFO]:  OUT: PACKET: net.minecraft.server.v1_16_R2.PacketLoginOutEncryptionBegin@43109570 / LOGIN
//[21:16:02] [Netty Epoll Server IO #1/INFO]:  IN: [LOGIN:1] net.minecraft.server.v1_16_R2.PacketLoginInEncryptionBegin
//[21:16:02] [User Authenticator #1/INFO]: UUID of player **** is ****
//[21:16:02] [Server thread/INFO]:  OUT: PACKET: net.minecraft.server.v1_16_R2.PacketLoginOutSetCompression@232eaa1d / LOGIN
//[21:16:02] [Server thread/INFO]:  OUT: PACKET: net.minecraft.server.v1_16_R2.PacketLoginOutSuccess@7a968bfc / LOGIN
    
    private void checkState(HandshakeState needed) {
        if(state != needed) {
            throw new ProtocolException("Unexpected Packet for state " + this.state);
        }
    }
}

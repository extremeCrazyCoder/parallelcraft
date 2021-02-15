package com.parallelcraft.parallelcraft.Networking.Packets;

import com.parallelcraft.parallelcraft.Networking.MCEncryption;
import com.parallelcraft.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.parallelcraft.Networking.PacketReadHelper;
import com.parallelcraft.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.parallelcraft.exceptions.InsufficientDataException;
import com.parallelcraft.parallelcraft.util.BaseConversionHelper;
import java.security.PublicKey;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketLoginOutEncryptionBegin extends PacketOutgoing {
    /**
     * This looks like it is appended first to the MessageDigest before sending that stuff to mojangs auth lib
     * But it is effectively not evaluated Client side
     */
    private String authNonce;
    private PublicKey serverPubKey;
    private byte[] nonce;
    
    public PacketLoginOutEncryptionBegin(String pAuthNonce, PublicKey pServerKey, byte[] pNonce) {
        authNonce = pAuthNonce;
        serverPubKey = pServerKey;
        nonce = pNonce;
    }
    
    @Override
    public void decode(PacketReadHelper data) throws InsufficientDataException {
        this.authNonce = data.getString(20);
        this.serverPubKey = MCEncryption.construct(data.getByteArray());
        this.nonce = data.getByteArray();
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeString(authNonce);
        data.writeByteArray(MCEncryption.toByteArray(serverPubKey));
        data.writeByteArray(nonce);
    }
    
    public String toString() {
        return "PacketLoginOutEncryptionBegin["
                + "authNonce='" + authNonce + "', "
                + "nonce='" + BaseConversionHelper.toHex(nonce) + "']";
    }
}

package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.MCEncryption;
import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketReadHelper;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.exceptions.InsufficientDataException;
import com.parallelcraft.util.BaseConversionHelper;
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
    
    public PacketLoginOutEncryptionBegin(String authNonce, PublicKey serverPubKey, byte[] nonce) {
        this.authNonce = authNonce;
        this.serverPubKey = serverPubKey;
        this.nonce = nonce;
    }
    
    @Override
    public void decode(PacketReadHelper data) throws InsufficientDataException {
        this.authNonce = data.readVarLenString(20);
        this.serverPubKey = MCEncryption.construct(data.readVarLenByteArray());
        this.nonce = data.readVarLenByteArray();
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeVarLenString(authNonce);
        data.writeByteArray(MCEncryption.toByteArray(serverPubKey));
        data.writeByteArray(nonce);
    }
    
    public String toString() {
        return "PacketLoginOutEncryptionBegin["
                + "authNonce='" + authNonce + "', "
                + "nonce='" + BaseConversionHelper.toHex(nonce) + "']";
    }
}

package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.ClientConnectionHandler;
import com.parallelcraft.Networking.PacketHandler;
import com.parallelcraft.Networking.PacketIncoming;
import com.parallelcraft.Networking.PacketReadHelper;
import com.parallelcraft.exceptions.InsufficientDataException;
import com.parallelcraft.util.BaseConversionHelper;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketLoginInEncryptionBegin extends PacketIncoming {
    private byte[] encryptionKey;
    private byte[] encryptionNounce;
    
    @Override
    public void decode(PacketReadHelper data) throws InsufficientDataException {
        encryptionKey = data.readVarLenByteArray();
        encryptionNounce = data.readVarLenByteArray();
    }

    @Override
    public PacketHandler getHandler(ClientConnectionHandler handle) {
        return handle.getHandshakeHandler();
    }
    
    public String toString() {
        return "PacketLoginInEncryptionBegin["
                + "encryptionKey='" + BaseConversionHelper.toHex(encryptionKey) + "', "
                + "encryptionNounce='" + BaseConversionHelper.toHex(encryptionNounce) + "']";
    }

    public byte[] getEncryptionKey() {
        return encryptionKey;
    }

    public byte[] getEncryptionNounce() {
        return encryptionNounce;
    }
}

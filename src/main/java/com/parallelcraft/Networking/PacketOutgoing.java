package com.parallelcraft.Networking;

import com.parallelcraft.exceptions.InsufficientDataException;

/**
 * Outgoing packets only need the encoding
 * 
 * @author extremeCrazyCoder
 */

public abstract class PacketOutgoing implements Packet {
    public void decode(PacketReadHelper data) throws InsufficientDataException {
        throw new UnsupportedOperationException("Outgoing packet no need for decoding");
    }
}

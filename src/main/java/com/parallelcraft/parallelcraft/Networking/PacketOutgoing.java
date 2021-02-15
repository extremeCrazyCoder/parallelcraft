package com.parallelcraft.parallelcraft.Networking;

import com.parallelcraft.parallelcraft.exceptions.InsufficientDataException;

/**
 *
 * @author extremeCrazyCoder
 */

public abstract class PacketOutgoing implements Packet {
    public void decode(PacketReadHelper data) throws InsufficientDataException {
        throw new UnsupportedOperationException("Outgoing packet no need for decoding");
    }
}

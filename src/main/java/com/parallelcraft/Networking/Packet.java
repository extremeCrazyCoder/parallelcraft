package com.parallelcraft.Networking;

import com.parallelcraft.exceptions.InsufficientDataException;

/**
 * The general Layout that Packets must have
 * 
 * @author extremeCrazyCoder
 */

public interface Packet {
    public void decode(PacketReadHelper data) throws InsufficientDataException;
    public void encode(PacketWriteHelper data);
}

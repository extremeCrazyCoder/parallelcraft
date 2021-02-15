package com.parallelcraft.parallelcraft.Networking;

import com.parallelcraft.parallelcraft.exceptions.InsufficientDataException;

/**
 *
 * @author extremeCrazyCoder
 */

public interface Packet {
    public void decode(PacketReadHelper data) throws InsufficientDataException;
    public void encode(PacketWriteHelper data);
}

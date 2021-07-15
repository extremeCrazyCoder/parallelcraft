package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * Debugging protocol don't know why we should support sending out debug information...
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutCustomPayload  extends PacketOutgoing {
    public PacketPlayOutCustomPayload() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

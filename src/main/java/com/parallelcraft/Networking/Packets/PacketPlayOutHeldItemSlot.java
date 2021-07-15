package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutHeldItemSlot extends PacketOutgoing {
    //TODO use this
    private byte slot;
    
    public PacketPlayOutHeldItemSlot(byte slot) {
        this.slot = slot;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeByte(this.slot);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutHeldItemSlot["
                + "slot='" + slot + "']";
    }
}

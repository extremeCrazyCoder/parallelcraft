package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutPing extends PacketOutgoing {
    //TODO use this
    private int id;
    
    public PacketPlayOutPing(int id) {
        this.id = id;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeInt(this.id);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutPing["
                + "id='" + id + "']";
    }
}

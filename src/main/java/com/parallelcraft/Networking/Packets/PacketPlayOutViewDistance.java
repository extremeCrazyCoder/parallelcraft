package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutViewDistance extends PacketOutgoing {
    //TODO use this
    private int radius;
    
    public PacketPlayOutViewDistance(int radius) {
        this.radius = radius;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeVarLenInteger(radius);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutViewDistance["
                + "radius='" + radius + "']";
    }
}

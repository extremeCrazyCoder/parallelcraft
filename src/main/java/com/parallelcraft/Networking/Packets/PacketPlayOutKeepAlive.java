package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutKeepAlive  extends PacketOutgoing {
    //TODO use this
    //used for getting ping
    private long time;
    
    public PacketPlayOutKeepAlive(long time) {
        this.time = time;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeLong(time);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutKeepAlive["
                + "time='" + time + "']";
    }
}

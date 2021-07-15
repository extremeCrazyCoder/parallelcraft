package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutUpdateTime extends PacketOutgoing {
    //TODO use this
    private long gameTime;
    private long dayTime;
    
    public PacketPlayOutUpdateTime(long gameTime, long dayTime) {
        this.gameTime = gameTime;
        this.dayTime = dayTime;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeLong(this.gameTime);
        data.writeLong(this.dayTime);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutUpdateTime["
                + "gameTime='" + gameTime + "', "
                + "dayTime='" + dayTime + "']";
    }
}

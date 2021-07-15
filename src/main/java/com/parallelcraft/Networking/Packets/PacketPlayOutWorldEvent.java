package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.world.BlockPosition;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutWorldEvent extends PacketOutgoing {
    //TODO use this
    private int type;
    private BlockPosition pos;
    private int packetData;
    private boolean globalEvent;
    
    public PacketPlayOutWorldEvent(int type, BlockPosition pos, int data, boolean globalEvent) {
        this.type = type;
        this.pos = pos;
        this.packetData = data;
        this.globalEvent = globalEvent;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeInt(type);
        data.writeBlockPosition(pos);
        data.writeInt(packetData);
        data.writeBoolean(globalEvent);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutWorldEvent["
                + "type='" + type + "', "
                + "pos='" + pos + "', "
                + "data='" + packetData + "', "
                + "globalEvent='" + globalEvent + "']";
    }
}

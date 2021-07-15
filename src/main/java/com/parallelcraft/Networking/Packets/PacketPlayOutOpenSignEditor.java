package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.world.BlockPosition;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutOpenSignEditor extends PacketOutgoing {
    //TODO use this
    private BlockPosition pos;
    
    public PacketPlayOutOpenSignEditor(BlockPosition pos) {
        this.pos = pos;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeBlockPosition(this.pos);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutOpenSignEditor["
                + "pos='" + pos + "']";
    }
}

package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.world.BlockPosition;
import com.parallelcraft.world.ChunkCoordinates;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutUnloadChunk  extends PacketOutgoing {
    //TODO use this
    private int x;
    private int z;
    
    public PacketPlayOutUnloadChunk(BlockPosition pos) {
        this.x = pos.getX() >> 4;
        this.z = pos.getZ() >> 4;
    }
    
    public PacketPlayOutUnloadChunk(ChunkCoordinates pos) {
        this.x = pos.getX();
        this.z = pos.getZ();
    }
    
    public PacketPlayOutUnloadChunk(int x, int z) {
        this.x = x;
        this.z = z;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeInt(x);
        data.writeInt(z);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutUnloadChunk["
                + "x='" + x + "', "
                + "z='" + z + "']";
    }
}

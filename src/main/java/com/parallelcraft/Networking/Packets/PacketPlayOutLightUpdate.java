package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.world.ChunkCoordinates;
import java.util.BitSet;
import java.util.List;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutLightUpdate extends PacketOutgoing {
    //TODO use this
    private int x;
    private int z;
    private boolean trustEdges;
    private BitSet skyYMask;
    private BitSet blockYMask;
    private BitSet emptySkyYMask;
    private BitSet emptyBlockYMask;
    private List<byte[]> skyUpdates;
    private List<byte[]> blockUpdates;
    
    public PacketPlayOutLightUpdate(ChunkCoordinates pos, boolean trustEdges, BitSet skyYMask, BitSet blockYMask,
            BitSet emptySkyYMask, BitSet emptyBlockYMask, List<byte[]> skyUpdates, List<byte[]> blockUpdates) {
        this.x = pos.getX();
        this.z = pos.getZ();
        this.trustEdges = trustEdges;
        this.skyYMask = skyYMask;
        this.blockYMask = blockYMask;
        this.emptySkyYMask = emptySkyYMask;
        this.emptyBlockYMask = emptyBlockYMask;
        this.skyUpdates = skyUpdates;
        this.blockUpdates = blockUpdates;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeVarLenInteger(this.x);
        data.writeVarLenInteger(this.z);
        data.writeBitSet(this.skyYMask);
        data.writeBitSet(this.blockYMask);
        data.writeBitSet(this.emptySkyYMask);
        data.writeBitSet(this.emptyBlockYMask);
        data.writeByteArrayList(this.skyUpdates);
        data.writeByteArrayList(this.blockUpdates);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutLightUpdate["
                + "x='" + x + "', "
                + "z='" + z + "', "
                + "skyYMask='" + skyYMask + "', "
                + "blockYMask='" + blockYMask + "', "
                + "emptySkyYMask='" + emptySkyYMask + "', "
                + "emptyBlockYMask='" + emptyBlockYMask + "', "
                + "skyUpdates='" + skyUpdates + "', "
                + "blockUpdates='" + blockUpdates + "']";
    }
}

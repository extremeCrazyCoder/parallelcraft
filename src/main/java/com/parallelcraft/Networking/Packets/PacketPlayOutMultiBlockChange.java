package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutMultiBlockChange extends PacketOutgoing {
    //TODO use this
//    private static final int POS_IN_SECTION_BITS = 12;
//    private SectionPosition sectionPos;
//    private short[] positions;
//    private IBlockData[] states;
//    private boolean suppressLightUpdates;
    
    //TODO TDB
    public PacketPlayOutMultiBlockChange() {
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutMultiBlockChange[???]";
    }
}

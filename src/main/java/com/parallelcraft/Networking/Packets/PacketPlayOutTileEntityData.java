package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.world.BlockPosition;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutTileEntityData  extends PacketOutgoing {
    //TODO use this
    private BlockPosition position;
    
    //TODO use enum here
    /**
        1 ... MobSpawner
        2 ... Command
        3 ... Beacon
        4 ... Skull
        5 ... Condult
        6 ... Banner
        7 ... Structure
        8 ... EndGateway
        9 ... Sign
        10... ??
        11... Bed
        12... Jigsaw
        13... Canpfire
    */
    private byte entityType;
//    private NBTDataWrapper nbtData;
    
//    public PacketPlayOutTileEntityData(BlockPosition position, byte entityType, NBTDataWrapper nbtData) {
//        this.position = position;
//        this.entityType = entityType;
//        this.nbtData = nbtData;
//    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeBlockPosition(position);
        data.writeByte(entityType);
//        data.writeNBTData(nbtData);
    }
    
//    @Override
//    public String toString() {
//        return "PacketPlayOutTileEntityData["
//                + "position='" + position + "', "
//                + "entityType='" + entityType + "', "
//                + "nbtData='" + nbtData.toString() + "']";
//    }
}

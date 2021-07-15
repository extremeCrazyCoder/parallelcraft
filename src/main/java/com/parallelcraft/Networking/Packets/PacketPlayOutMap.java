package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutMap extends PacketOutgoing {
    //TODO use this
    private int mapId;
    private byte scale;
    private boolean locked;
//    private List<MapIcon> decorations;
//    private WorldMap.b colorPatch;
    
    //TODO TBD
    public PacketPlayOutMap() {
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeVarLenInteger(mapId);
        data.writeByte(scale);
        data.writeBoolean(locked);
//        data.writeBoolean(this.decorations != null);
//        
//        if(this.decorations != null) {
//            data.writeList(decorations);
//        }
        
//        if(this.colorPatch != null) {
//            data.writeByte(this.colorPatch.w);
//            data.writeByte(this.colorPatch.h);
//            data.writeByte(this.colorPatch.startX);
//            data.writeByte(this.colorPatch.startY);
//            data.writeVarLenIntegerArray(this.colorPatch.data);
//        } else {
//            data.writeByte((byte) 0);
//        }
    }
    
//    @Override
//    public String toString() {
//        return "PacketPlayOutMap["
//                + "mapId='" + mapId + "', "
//                + "scale='" + scale + "', "
//                + "locked='" + locked + "', "
//                + "decorations='" + decorations + "', "
//                + "colorPatch='" + colorPatch + "']";
//    }
}

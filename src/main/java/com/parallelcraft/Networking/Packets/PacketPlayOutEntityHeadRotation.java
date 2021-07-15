package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutEntityHeadRotation extends PacketOutgoing {
    //TODO use this
    private int entityId;
    private byte yHeadRotation;
    
    public PacketPlayOutEntityHeadRotation(int entityId, byte yHeadRotation) {
        this.entityId = entityId;
        this.yHeadRotation = yHeadRotation;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeVarLenInteger(this.entityId);
        data.writeByte(this.yHeadRotation);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutEntityHeadRotation["
                + "entityId='" + entityId + "', "
                + "yHeadRotation='" + yHeadRotation + "']";
    }
}

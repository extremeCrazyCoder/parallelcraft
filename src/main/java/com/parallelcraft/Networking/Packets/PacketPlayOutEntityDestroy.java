package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutEntityDestroy extends PacketOutgoing {
    //TODO use this
    private int entityId;
    
    public PacketPlayOutEntityDestroy(int entityId) {
        this.entityId = entityId;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeVarLenInteger(this.entityId);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutEntityDestroy["
                + "entityId='" + entityId + "']";
    }
}

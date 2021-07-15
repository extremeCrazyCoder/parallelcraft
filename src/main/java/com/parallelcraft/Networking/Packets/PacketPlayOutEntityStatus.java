package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutEntityStatus  extends PacketOutgoing {
    //TODO use this
    private int entityID;
    /**
     * 9 -> Throw / Schlagen / ?
     * 24 - 28 -> Command ?
     * 43 -> bad omen entfernen ?
     * 55 -> swap main / offhand ?
     * 
     * alles mit broadcastEntityEffect
     */
    private byte type;
    
    public PacketPlayOutEntityStatus(int entityID, byte type) {
        this.entityID = entityID;
        this.type = type;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeInt(entityID);
        data.writeByte(type);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutEntityStatus["
                + "entityID='" + entityID + "', "
                + "type='" + type + "']";
    }
}

package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutEntityLook extends PacketOutgoing {
    //TODO use this
    private int entityId;
    private byte yRot;
    private byte xRot;
    private boolean onGround;
    
    public PacketPlayOutEntityLook(int entityId, float yRot, float xRot, boolean onGround) {
        this.entityId = entityId;
        this.yRot = (byte) (yRot * 256.0F / 360.0F);
        this.xRot = (byte) (xRot * 256.0F / 360.0F);
        this.onGround = onGround;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeVarLenInteger(this.entityId);
        data.writeByte(this.yRot);
        data.writeByte(this.xRot);
        data.writeBoolean(this.onGround);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutEntityLook["
                + "entityId='" + entityId + "', "
                + "yRot='" + yRot + "', "
                + "xRot='" + xRot + "', "
                + "onGround='" + onGround + "']";
    }
}

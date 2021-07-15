package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.util.Vector3D;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutRelEntityMoveLook extends PacketOutgoing {
    //TODO use this
    private int entityId;
    private short diffX;
    private short diffY;
    private short diffZ;
    private byte yRot;
    private byte xRot;
    private boolean onGround;
    
    public PacketPlayOutRelEntityMoveLook(int entityId, Vector3D diff, float yRot, float xRot, boolean onGround) {
        this.entityId = entityId;
        this.diffX = (short) (diff.getX() * 4096);
        this.diffY = (short) (diff.getY() * 4096);
        this.diffZ = (short) (diff.getZ() * 4096);
        this.yRot = (byte) (yRot * 256.0F / 360.0F);
        this.xRot = (byte) (xRot * 256.0F / 360.0F);
        this.onGround = onGround;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeVarLenInteger(this.entityId);
        data.writeUnsignedShort(this.diffX);
        data.writeUnsignedShort(this.diffY);
        data.writeUnsignedShort(this.diffZ);
        data.writeByte(this.yRot);
        data.writeByte(this.xRot);
        data.writeBoolean(this.onGround);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutRelEntityMoveLook["
                + "entityId='" + entityId + "', "
                + "diffX='" + diffX + "', "
                + "diffY='" + diffY + "', "
                + "diffZ='" + diffZ + "', "
                + "yRot='" + yRot + "', "
                + "xRot='" + xRot + "', "
                + "onGround='" + onGround + "']";
    }
}

package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.util.Vector3D;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutRelEntityMove extends PacketOutgoing {
    //TODO use this
    private int entityId;
    private short diffX;
    private short diffY;
    private short diffZ;
    private boolean onGround;
    
    public PacketPlayOutRelEntityMove(int entityId, Vector3D diff, boolean onGround) {
        this.entityId = entityId;
        this.diffX = (short) (diff.getX() * 4096);
        this.diffY = (short) (diff.getY() * 4096);
        this.diffZ = (short) (diff.getZ() * 4096);
        this.onGround = onGround;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeVarLenInteger(this.entityId);
        data.writeUnsignedShort(this.diffX);
        data.writeUnsignedShort(this.diffY);
        data.writeUnsignedShort(this.diffZ);
        data.writeBoolean(this.onGround);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutRelEntityMove["
                + "entityId='" + entityId + "', "
                + "diffX='" + diffX + "', "
                + "diffY='" + diffY + "', "
                + "diffZ='" + diffZ + "', "
                + "onGround='" + onGround + "']";
    }
}

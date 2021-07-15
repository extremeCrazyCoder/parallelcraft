package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.constants.EnumAnchor;
import com.parallelcraft.world.Position;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutLookAt extends PacketOutgoing {
    //TODO use this
    private double posX;
    private double posY;
    private double posZ;
    private int entitiy;
    private EnumAnchor fromAnchor;
    private EnumAnchor toAnchor;
    private boolean atEntity;
    
    public PacketPlayOutLookAt(Position pos, int entitiy, EnumAnchor fromAnchor, EnumAnchor toAnchor, boolean atEntity) {
        this.posX = pos.getX();
        this.posY = pos.getY();
        this.posZ = pos.getZ();
        this.entitiy = entitiy;
        this.fromAnchor = fromAnchor;
        this.toAnchor = toAnchor;
        this.atEntity = atEntity;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeEnum(this.fromAnchor);
        data.writeDouble(this.posX);
        data.writeDouble(this.posY);
        data.writeDouble(this.posZ);
        data.writeBoolean(this.atEntity);
        if(this.atEntity) {
            data.writeVarLenInteger(this.entitiy);
            data.writeEnum(this.toAnchor);
        }
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutLookAt["
                + "fromAnchor='" + fromAnchor + "', "
                + "posX='" + posX + "', "
                + "posY='" + posY + "', "
                + "posZ='" + posZ + "', "
                + "atEntity='" + atEntity + "', "
                + "entitiy='" + entitiy + "', "
                + "toAnchor='" + toAnchor + "']";
    }
}

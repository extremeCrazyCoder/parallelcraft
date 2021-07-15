package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.world.Position;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutVehicleMove extends PacketOutgoing {
    //TODO use this
    private double posX;
    private double posY;
    private double posZ;
    private float yRot;
    private float xRot;
    
    public PacketPlayOutVehicleMove(Position newPos, float yRot, float xRot) {
        this.posX = newPos.getX();
        this.posY = newPos.getY();
        this.posZ = newPos.getZ();
        this.yRot = yRot;
        this.xRot = xRot;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeDouble(this.posX);
        data.writeDouble(this.posY);
        data.writeDouble(this.posZ);
        data.writeFloat(this.yRot);
        data.writeFloat(this.xRot);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutVehicleMove["
                + "posX='" + posX + "', "
                + "posY='" + posY + "', "
                + "posZ='" + posZ + "', "
                + "yRot='" + yRot + "', "
                + "xRot='" + xRot + "']";
    }
}

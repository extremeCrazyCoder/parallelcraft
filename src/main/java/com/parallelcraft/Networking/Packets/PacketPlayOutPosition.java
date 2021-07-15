package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.world.Position;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutPosition extends PacketOutgoing {
    //TODO use this
    private double x;
    private double y;
    private double z;
    private float yRot;
    private float xRot;
    
    //currently only absolute is supported
    //there is no benefit from using relative locations
    private byte relativeLocation = 0;
    private int num;
    private boolean dismountVehicle;
    
    public PacketPlayOutPosition(Position pos, float yRot, float xRot, int num, boolean dismountVehicle) {
        this.x = pos.getX();
        this.y = pos.getY();
        this.z = pos.getZ();
        this.yRot = yRot;
        this.xRot = xRot;
        this.num = num;
        this.dismountVehicle = dismountVehicle;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeDouble(this.x);
        data.writeDouble(this.y);
        data.writeDouble(this.z);
        data.writeFloat(this.yRot);
        data.writeFloat(this.xRot);
        data.writeByte(this.relativeLocation);
        data.writeVarLenInteger(this.num);
        data.writeBoolean(this.dismountVehicle);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutPosition["
                + "x='" + x + "', "
                + "y='" + y + "', "
                + "z='" + z + "', "
                + "yRot='" + yRot + "', "
                + "xRot='" + xRot + "', "
                + "relativeLocation='" + relativeLocation + "', "
                + "num='" + num + "', "
                + "dismountVehicle='" + dismountVehicle + "']";
    }
}

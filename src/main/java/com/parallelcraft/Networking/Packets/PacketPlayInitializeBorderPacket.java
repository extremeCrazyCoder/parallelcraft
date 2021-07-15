package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayInitializeBorderPacket extends PacketOutgoing {
    //TODO use this
    private double centerX;
    private double centerZ;
    private double oldSize;
    private double newSize;
    private long lerpTime;
    private int newAbsoluteMaxSize;
    private int warningBlocks;
    private int warningTime;
    
    public PacketPlayInitializeBorderPacket(double centerX, double centerZ, double oldSize, double newSize,
            long lerpTime, int newAbsoluteMaxSize, int warningBlocks, int warningTime) {
        this.centerX = centerX;
        this.centerZ = centerZ;
        this.oldSize = oldSize;
        this.newSize = newSize;
        this.lerpTime = lerpTime;
        this.newAbsoluteMaxSize = newAbsoluteMaxSize;
        this.warningBlocks = warningBlocks;
        this.warningTime = warningTime;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeDouble(centerX);
        data.writeDouble(centerZ);
        data.writeDouble(oldSize);
        data.writeDouble(newSize);
        data.writeVarLenLong(lerpTime);
        data.writeVarLenInteger(newAbsoluteMaxSize);
        data.writeVarLenInteger(warningBlocks);
        data.writeVarLenInteger(warningTime);
    }
    
    @Override
    public String toString() {
        return "PacketPlayInitializeBorderPacket["
                + "centerX='" + centerX + "', "
                + "centerZ='" + centerZ + "', "
                + "oldSize='" + oldSize + "', "
                + "newSize='" + newSize + "', "
                + "lerpTime='" + lerpTime + "', "
                + "newAbsoluteMaxSize='" + newAbsoluteMaxSize + "', "
                + "warningBlocks='" + warningBlocks + "', "
                + "warningTime='" + warningTime + "']";
    }
}

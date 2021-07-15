package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutSpawnEntityExperienceOrb extends PacketOutgoing {
    //TODO use this
    private int id;
    private double xPos;
    private double yPos;
    private double zPos;
    private int experienceAmount;
    
    public PacketPlayOutSpawnEntityExperienceOrb(int id, double xPos, double yPos, double zPos, int experienceAmount) {
        this.id = id;
        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = zPos;
        this.experienceAmount = experienceAmount;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeVarLenInteger(id);
        data.writeDouble(xPos);
        data.writeDouble(yPos);
        data.writeDouble(zPos);
        data.writeUnsignedShort(experienceAmount);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutSpawnEntityExperienceOrb["
                + "id='" + id + "', "
                + "pos='[" + xPos + ", " + yPos + ", " + zPos + "]', "
                + "experienceAmount='" + experienceAmount + "']";
    }
}

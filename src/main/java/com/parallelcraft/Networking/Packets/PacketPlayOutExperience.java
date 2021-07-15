package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutExperience extends PacketOutgoing {
    //TODO use this
    private float experienceProgress;
    private int totalExperience;
    private int experienceLevel;
    
    public PacketPlayOutExperience(float experienceProgress, int totalExperience, int experienceLevel) {
        this.experienceProgress = experienceProgress;
        this.totalExperience = totalExperience;
        this.experienceLevel = experienceLevel;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeFloat(experienceProgress);
        data.writeVarLenInteger(totalExperience);
        data.writeVarLenInteger(experienceLevel);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutExperience["
                + "experienceProgress='" + experienceProgress + "', "
                + "totalExperience='" + totalExperience + "', "
                + "experienceLevel='" + experienceLevel + "']";
    }
}

package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutUpdateHealth extends PacketOutgoing {
    //TODO use this
    private float health;
    private int food;
    private float saturation;
    
    public PacketPlayOutUpdateHealth(float health, int food, float saturation) {
        this.health = health;
        this.food = food;
        this.saturation = saturation;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeFloat(health);
        data.writeVarLenInteger(food);
        data.writeFloat(saturation);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutUpdateHealth["
                + "health='" + health + "', "
                + "food='" + food + "', "
                + "saturation='" + saturation + "']";
    }
}

package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.world.BlockType;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * Sets the cooldown for the given Item type for the player
 * used in vanilla for chorus / enderPearl / Shield
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutSetCooldown  extends PacketOutgoing {
    //TODO use this
    private BlockType item;
    private int time;
    
    public PacketPlayOutSetCooldown(BlockType item, int time) {
        this.item = item;
        this.time = time;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeVarLenInteger(item.getDatabaseID());
        data.writeVarLenInteger(time);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutSetCooldown["
                + "item='" + item + "', "
                + "time='" + time + "']";
    }
}

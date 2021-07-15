package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.world.Block;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutBlockChange  extends PacketOutgoing {
    //TODO use this
    private Block changed;
    
    public PacketPlayOutBlockChange(Block changed) {
        this.changed = changed;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeBlockPosition(changed.getPosition());
        data.writeVarLenInteger(changed.getType().getDatabaseID());
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutBlockChange["
                + "changed='" + changed.toString() + "']";
    }
}

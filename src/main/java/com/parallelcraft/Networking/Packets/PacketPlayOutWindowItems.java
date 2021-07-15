package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.world.Inventory;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutWindowItems extends PacketOutgoing {
    //TODO use this
    private byte windowID;
    private Inventory items;
    
    public PacketPlayOutWindowItems(byte windowID, Inventory items) {
        this.windowID = windowID;
        this.items = items;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeByte(windowID);
        data.writeInventory(items);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutWindowItems["
                + "windowID='" + windowID + "', "
                + "items='" + items + "']";
    }
}

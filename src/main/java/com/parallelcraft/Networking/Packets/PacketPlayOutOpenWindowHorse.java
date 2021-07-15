package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutOpenWindowHorse  extends PacketOutgoing {
    //TODO use this
    private byte windowId;
    private int inventorySize;
    private int horseId;
    
    public PacketPlayOutOpenWindowHorse(byte windowId, int inventorySize, int horseId) {
        this.windowId = windowId;
        this.inventorySize = inventorySize;
        this.horseId = horseId;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeByte(windowId);
        data.writeVarLenInteger(inventorySize);
        data.writeInt(horseId);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutOpenWindowHorse["
                + "windowId='" + windowId + "', "
                + "inventorySize='" + inventorySize + "', "
                + "horseId='" + horseId + "']";
    }
}

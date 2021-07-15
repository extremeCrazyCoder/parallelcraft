package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutWindowData extends PacketOutgoing {
    //TODO use this
    private byte windowID;
    
    //TODO or something like that verify!
    private short containerIndex;
    //TODO check this
    /**
     * Different meaning based on when it is used:
     * Anvil -> the repair cost
     * StoneCutter -> recepie?
     * EnchantTable -> seed for enchantments
     */
    private short propertyInt;
    
    // TODO check if these names are correct
    public PacketPlayOutWindowData(byte windowID, short containerIndex, short propertyInt) {
        this.windowID = windowID;
        this.containerIndex = containerIndex;
        this.propertyInt = propertyInt;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeByte(windowID);
        data.writeUnsignedShort(containerIndex);
        data.writeUnsignedShort(propertyInt);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutWindowData["
                + "windowID='" + windowID + "', "
                + "containerIndex='" + containerIndex + "', "
                + "propertyInt='" + propertyInt + "']";
    }
}

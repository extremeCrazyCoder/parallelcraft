package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutTransaction extends PacketOutgoing {
    //TODO use this
    //TODO outdated 1.16 code
    private byte windowID;
    private short clickID;
    private boolean allowed;
    
    // TODO check if these names are correct
    public PacketPlayOutTransaction(byte windowID, short clickID, boolean allowed) {
        this.windowID = windowID;
        this.clickID = clickID;
        this.allowed = allowed;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeByte(windowID);
        data.writeUnsignedShort(clickID);
        data.writeBoolean(allowed);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutTransaction["
                + "windowID='" + windowID + "', "
                + "clickID='" + clickID + "', "
                + "allowed='" + allowed + "']";
    }
}

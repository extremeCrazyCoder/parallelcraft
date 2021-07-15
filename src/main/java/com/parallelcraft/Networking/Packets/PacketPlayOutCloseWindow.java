package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutCloseWindow extends PacketOutgoing {
    //TODO use this
    private byte windowID;
    
    public PacketPlayOutCloseWindow(byte windowID) {
        this.windowID = windowID;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeByte(windowID);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutCloseWindow["
                + "windowID='" + windowID + "']";
    }
}

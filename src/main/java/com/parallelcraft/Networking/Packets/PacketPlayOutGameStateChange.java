package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutGameStateChange  extends PacketOutgoing {
    //TODO use this
    
    public PacketPlayOutGameStateChange(byte XXX, float YYY) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        throw new UnsupportedOperationException("Not supported yet.");
//        data.writeByte();
//        data.writeFloat();
    }
    
    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet.");
//        return "PacketPlayOutGameStateChange["
//                + "item='" + item + "', "
//                + "time='" + time + "']";
    }
}

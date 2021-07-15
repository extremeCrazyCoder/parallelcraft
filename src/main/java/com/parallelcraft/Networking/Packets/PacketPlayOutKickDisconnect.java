package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutKickDisconnect  extends PacketOutgoing {
    //TODO use this
    
    public PacketPlayOutKickDisconnect() {
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        //TODO write this
        //something with IChatBaseComponent
        //need to write a class structure + serializer for that stuff first
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet.");
//        return "PacketPlayOutKickDisconnect["
//                + "item='" + item + "', "
//                + "time='" + time + "']";
    }
}

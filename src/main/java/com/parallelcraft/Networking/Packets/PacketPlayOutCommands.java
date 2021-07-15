package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutCommands  extends PacketOutgoing {
    //TODO use this
//    private ChatCommands[] availableCommands;
//    
//    public PacketPlayOutCommands(ChatCommands[] availableCommands) {
//        this.availableCommands = availableCommands;
//    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        throw new UnsupportedOperationException("Not supported yet.");
        //TODO transform this pseudocode into usefull code
//        data.writeVarLenInteger(availableCommands.length);
//        for(int i = 0; i < availableCommands.length; i++) {
//            
//        }
    }
    
//    @Override
//    public String toString() {
//        return "PacketPlayOutCommands["
//                + "availableCommands='" + availableCommands + "']";
//    }
}

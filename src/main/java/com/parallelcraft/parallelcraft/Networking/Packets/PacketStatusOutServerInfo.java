package com.parallelcraft.parallelcraft.Networking.Packets;

import com.parallelcraft.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.parallelcraft.Networking.PacketWriteHelper;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketStatusOutServerInfo extends PacketOutgoing {
    
    @Override
    public void encode(PacketWriteHelper data) {
        /**
         * I have no clue why the use gson here and what data is actually transfered with this packet...
         * Planning to implement it without gson
         * Has something to do with ServerPing...
         */
        //TODO write this
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.ClientConnectionHandler;
import com.parallelcraft.Networking.PacketHandler;
import com.parallelcraft.Networking.PacketIncoming;
import com.parallelcraft.Networking.PacketReadHelper;


/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayInCustomPayload extends PacketIncoming {
    //TODO use this
    
    //TODO TBD
    public PacketPlayInCustomPayload() {
    }
    
    @Override
    public void decode(PacketReadHelper data) {
        //TODO TBD
    }

    @Override
    public PacketHandler getHandler(ClientConnectionHandler handle) {
        return handle.getDefaultPacketHandler();
    }
    
    @Override
    public String toString() {
        //TODO TBD
        return "PacketPlayInCustomPayload[???]";
    }
}

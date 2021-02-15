package com.parallelcraft.parallelcraft.Networking.Packets;

import com.parallelcraft.parallelcraft.Networking.ClientConnectionHandler;
import com.parallelcraft.parallelcraft.Networking.PacketHandler;
import com.parallelcraft.parallelcraft.Networking.PacketIncoming;
import com.parallelcraft.parallelcraft.Networking.PacketReadHelper;
import com.parallelcraft.parallelcraft.exceptions.InsufficientDataException;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketStatusInStart extends PacketIncoming {

    @Override
    public void decode(PacketReadHelper data) throws InsufficientDataException {
        //No contents packet alone has the meaning
    }

    @Override
    public PacketHandler getHandler(ClientConnectionHandler handle) {
        //TODO write this
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

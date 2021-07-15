package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.ClientConnectionHandler;
import com.parallelcraft.Networking.PacketHandler;
import com.parallelcraft.Networking.PacketIncoming;
import com.parallelcraft.Networking.PacketReadHelper;
import com.parallelcraft.exceptions.InsufficientDataException;
import com.parallelcraft.exceptions.MalformedDataException;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * Totally useless packet should cause a disconnect when sent from client
 * 
 * @author extremeCrazyCoder
 */
public class PacketLoginInCustomPayload extends PacketIncoming {
    //would normally contain a byte array, but why decode when we know what will happen?
    @Override
    public void decode(PacketReadHelper data) throws InsufficientDataException {
        throw new MalformedDataException("No need for this Packet");
    }

    @Override
    public PacketHandler getHandler(ClientConnectionHandler handle) {
        //can't be decoded thus no need for a handler
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

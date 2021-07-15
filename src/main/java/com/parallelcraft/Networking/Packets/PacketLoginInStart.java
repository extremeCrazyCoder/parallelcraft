package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.ClientConnectionHandler;
import com.parallelcraft.Networking.PacketHandler;
import com.parallelcraft.Networking.PacketIncoming;
import com.parallelcraft.Networking.PacketReadHelper;
import com.parallelcraft.exceptions.InsufficientDataException;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketLoginInStart extends PacketIncoming {
    private String username;
    
    @Override
    public void decode(PacketReadHelper data) throws InsufficientDataException {
        this.username = data.readVarLenString(16);
    }

    @Override
    public PacketHandler getHandler(ClientConnectionHandler handle) {
        return handle.getHandshakeHandler();
    }
    
    public String getUsername() {
        return username;
    }
    
    public String toString() {
        return "PacketLoginInStart["
                + "username='" + username + "']";
    }
}

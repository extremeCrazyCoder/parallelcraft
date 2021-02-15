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
public class PacketLoginInStart extends PacketIncoming {
    private String username;
    
    @Override
    public void decode(PacketReadHelper data) throws InsufficientDataException {
        this.username = data.getString(16);
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

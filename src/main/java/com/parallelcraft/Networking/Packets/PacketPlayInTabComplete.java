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
public class PacketPlayInTabComplete extends PacketIncoming {
    private int id;
    private String command;
    
    public PacketPlayInTabComplete() {
    }
    
    @Override
    public void decode(PacketReadHelper data) throws InsufficientDataException {
        this.id = data.readVarLenInteger();
        this.command = data.readVarLenString(32500);
    }

    @Override
    public PacketHandler getHandler(ClientConnectionHandler handle) {
        return handle.getDefaultPacketHandler();
    }
    
    @Override
    public String toString() {
        return "PacketPlayInTabComplete["
                + "id='" + id + "', "
                + "command='" + command + "']";
    }
}

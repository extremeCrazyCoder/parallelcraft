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
public class PacketPlayInSetCommandMinecart extends PacketIncoming {
    private int entity;
    private String command;
    private boolean trackOutput;
    
    public PacketPlayInSetCommandMinecart() {
    }
    
    @Override
    public void decode(PacketReadHelper data) throws InsufficientDataException {
        this.entity = data.readVarLenInteger();
        this.command = data.readVarLenString(32767);
        this.trackOutput = data.readBoolean();
    }

    @Override
    public PacketHandler getHandler(ClientConnectionHandler handle) {
        return handle.getDefaultPacketHandler();
    }
    
    @Override
    public String toString() {
        return "PacketPlayInSetCommandMinecart["
                + "entity='" + entity + "', "
                + "command='" + command + "', "
                + "trackOutput='" + trackOutput + "']";
    }
}

package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.ClientConnectionHandler;
import com.parallelcraft.Networking.PacketHandler;
import com.parallelcraft.Networking.PacketIncoming;
import com.parallelcraft.Networking.PacketReadHelper;
import com.parallelcraft.constants.EnumPlayerAction;
import com.parallelcraft.exceptions.InsufficientDataException;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayInEntityAction extends PacketIncoming {
    private int id;
    private EnumPlayerAction action;
    private int data;
    
    public PacketPlayInEntityAction() {
    }
    
    @Override
    public void decode(PacketReadHelper data) throws InsufficientDataException {
        this.id = data.readVarLenInteger();
        this.action = data.readEnum(EnumPlayerAction.class);
        this.data = data.readVarLenInteger();
    }

    @Override
    public PacketHandler getHandler(ClientConnectionHandler handle) {
        return handle.getDefaultPacketHandler();
    }
    
    @Override
    public String toString() {
        return "PacketPlayInEntityAction["
                + "id='" + id + "', "
                + "action='" + action + "', "
                + "data='" + data + "']";
    }
}

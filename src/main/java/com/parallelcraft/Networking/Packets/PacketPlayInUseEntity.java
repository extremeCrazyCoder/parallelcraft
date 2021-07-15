package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.ClientConnectionHandler;
import com.parallelcraft.Networking.PacketHandler;
import com.parallelcraft.Networking.PacketIncoming;
import com.parallelcraft.Networking.PacketReadHelper;
import com.parallelcraft.constants.EnumEntityUseAction;
import com.parallelcraft.exceptions.InsufficientDataException;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayInUseEntity extends PacketIncoming {
    private int entityId;
    private EnumEntityUseAction action;
    private boolean usingSecondaryAction;
    
    public PacketPlayInUseEntity() {
    }
    
    @Override
    public void decode(PacketReadHelper data) throws InsufficientDataException {
        this.entityId = data.readVarLenInteger();
        this.action = data.readEnum(EnumEntityUseAction.class);
        //TODO read action
        this.usingSecondaryAction = data.readBoolean();
    }

    @Override
    public PacketHandler getHandler(ClientConnectionHandler handle) {
        return handle.getDefaultPacketHandler();
    }
    
    @Override
    public String toString() {
        return "PacketPlayInUseEntity["
                + "entityId='" + entityId + "', "
                + "action='" + action + "', "
                + "usingSecondaryAction='" + usingSecondaryAction + "']";
    }
}

package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.ClientConnectionHandler;
import com.parallelcraft.Networking.PacketHandler;
import com.parallelcraft.Networking.PacketIncoming;
import com.parallelcraft.Networking.PacketReadHelper;
import com.parallelcraft.constants.EnumDirection;
import com.parallelcraft.constants.EnumPlayerDigType;
import com.parallelcraft.exceptions.InsufficientDataException;
import com.parallelcraft.world.BlockPosition;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayInBlockDig extends PacketIncoming {
    private BlockPosition pos;
    private EnumDirection direction;
    private EnumPlayerDigType digType;
    
    public PacketPlayInBlockDig() {
    }
    
    @Override
    public void decode(PacketReadHelper data) throws InsufficientDataException {
        this.digType = data.readEnum(EnumPlayerDigType.class);
        this.pos = data.readBlockPosition();
        this.direction = data.readEnum(EnumDirection.class);
    }

    @Override
    public PacketHandler getHandler(ClientConnectionHandler handle) {
        return handle.getDefaultPacketHandler();
    }
    
    @Override
    public String toString() {
        return "PacketPlayInBlockDig["
                + "digType='" + digType + "', "
                + "pos='" + pos + "', "
                + "direction='" + direction + "']";
    }
}

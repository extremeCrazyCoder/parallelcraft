package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.ClientConnectionHandler;
import com.parallelcraft.Networking.PacketHandler;
import com.parallelcraft.Networking.PacketIncoming;
import com.parallelcraft.Networking.PacketReadHelper;
import com.parallelcraft.constants.EnumDirection;
import com.parallelcraft.constants.EnumHand;
import com.parallelcraft.exceptions.InsufficientDataException;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayInUseItem extends PacketIncoming {
    private EnumHand hand;
    
    public PacketPlayInUseItem() {
    }
    
    @Override
    public void decode(PacketReadHelper data) throws InsufficientDataException {
        this.hand = data.readEnum(EnumHand.class);
        data.readBlockPosition();
        data.readEnum(EnumDirection.class);
        data.readFloat();
        data.readFloat();
        data.readFloat();
        data.readBoolean();
    }

    @Override
    public PacketHandler getHandler(ClientConnectionHandler handle) {
        return handle.getDefaultPacketHandler();
    }
    
    @Override
    public String toString() {
        return "PacketPlayInUseItem["
                + "movingBlockPosition='" + 0 + "', "
                + "hand='" + hand + "']";
    }
}

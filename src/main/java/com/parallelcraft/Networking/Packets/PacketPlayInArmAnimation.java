package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.ClientConnectionHandler;
import com.parallelcraft.Networking.PacketHandler;
import com.parallelcraft.Networking.PacketIncoming;
import com.parallelcraft.Networking.PacketReadHelper;
import com.parallelcraft.constants.EnumHand;
import com.parallelcraft.exceptions.InsufficientDataException;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayInArmAnimation extends PacketIncoming {
    private EnumHand hand;
    
    public PacketPlayInArmAnimation() {
    }
    
    @Override
    public void decode(PacketReadHelper data) throws InsufficientDataException {
        this.hand = data.readEnum(EnumHand.class);
    }

    @Override
    public PacketHandler getHandler(ClientConnectionHandler handle) {
        return handle.getDefaultPacketHandler();
    }
    
    @Override
    public String toString() {
        return "PacketPlayInArmAnimation["
                + "hand='" + hand + "']";
    }
}

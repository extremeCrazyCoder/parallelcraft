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
public class PacketPlayInBoatMove extends PacketIncoming {
    private boolean left;
    private boolean right;
    
    public PacketPlayInBoatMove() {
    }
    
    @Override
    public void decode(PacketReadHelper data) throws InsufficientDataException {
        this.left = data.readBoolean();
        this.right = data.readBoolean();
    }

    @Override
    public PacketHandler getHandler(ClientConnectionHandler handle) {
        return handle.getDefaultPacketHandler();
    }
    
    @Override
    public String toString() {
        return "PacketPlayInBoatMove["
                + "left='" + left + "', "
                + "right='" + right + "']";
    }
}

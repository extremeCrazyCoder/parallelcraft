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
public class PacketPlayInSteerVehicle extends PacketIncoming {
    private float xDiff;
    private float zDiff;
    private boolean isJumping;
    private boolean isShiftKeyDown;
    
    public PacketPlayInSteerVehicle() {
    }
    
    @Override
    public void decode(PacketReadHelper data) throws InsufficientDataException {
        this.xDiff = data.readFloat();
        this.zDiff = data.readFloat();
        
        byte dat = data.readByte();
        this.isJumping = (dat & 1) > 0;
        this.isShiftKeyDown = (dat & 2) > 0;
    }

    @Override
    public PacketHandler getHandler(ClientConnectionHandler handle) {
        return handle.getDefaultPacketHandler();
    }
    
    @Override
    public String toString() {
        return "PacketPlayInSteerVehicle["
                + "xDiff='" + xDiff + "', "
                + "zDiff='" + zDiff + "', "
                + "isJumping='" + isJumping + "', "
                + "isShiftKeyDown='" + isShiftKeyDown + "']";
    }
}

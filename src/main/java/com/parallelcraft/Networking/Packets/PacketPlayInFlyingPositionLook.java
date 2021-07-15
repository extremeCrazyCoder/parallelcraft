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
public class PacketPlayInFlyingPositionLook extends PacketIncoming {
    private double x;
    private double y;
    private double z;
    private float yRot;
    private float xRot;
    private boolean onGround;
    
    public PacketPlayInFlyingPositionLook() {
    }
    
    @Override
    public void decode(PacketReadHelper data) throws InsufficientDataException {
        this.x = data.readDouble();
        this.y = data.readDouble();
        this.z = data.readDouble();
        this.yRot = data.readFloat();
        this.xRot = data.readFloat();
        this.onGround = data.readBoolean();
    }

    @Override
    public PacketHandler getHandler(ClientConnectionHandler handle) {
        return handle.getDefaultPacketHandler();
    }
    
    @Override
    public String toString() {
        return "PacketPlayInFlyingPositionLook["
                + "x='" + x + "', "
                + "y='" + y + "', "
                + "z='" + z + "', "
                + "yRot='" + yRot + "', "
                + "xRot='" + xRot + "', "
                + "onGround='" + onGround + "']";
    }
}

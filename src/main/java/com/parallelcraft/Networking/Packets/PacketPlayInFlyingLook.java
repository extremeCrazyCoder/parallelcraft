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
public class PacketPlayInFlyingLook extends PacketIncoming {
    private float yRot;
    private float xRot;
    private boolean onGround;
    
    public PacketPlayInFlyingLook() {
    }
    
    @Override
    public void decode(PacketReadHelper data) throws InsufficientDataException {
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
        return "PacketPlayInFlyingLook["
                + "yRot='" + yRot + "', "
                + "xRot='" + xRot + "', "
                + "onGround='" + onGround + "']";
    }
}

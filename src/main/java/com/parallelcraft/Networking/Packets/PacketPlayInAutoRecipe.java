package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.ClientConnectionHandler;
import com.parallelcraft.Networking.PacketHandler;
import com.parallelcraft.Networking.PacketIncoming;
import com.parallelcraft.Networking.PacketReadHelper;
import com.parallelcraft.exceptions.InsufficientDataException;
import com.parallelcraft.util.MinecraftKey;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayInAutoRecipe extends PacketIncoming {
    private int containerId;
    private MinecraftKey recipe;
    private boolean shiftDown;
    
    public PacketPlayInAutoRecipe() {
    }
    
    @Override
    public void decode(PacketReadHelper data) throws InsufficientDataException {
        this.containerId = data.readByte();
        this.recipe = data.readMCKey();
        this.shiftDown = data.readBoolean();
    }

    @Override
    public PacketHandler getHandler(ClientConnectionHandler handle) {
        return handle.getDefaultPacketHandler();
    }
    
    @Override
    public String toString() {
        return "PacketPlayInAutoRecipe["
                + "containerId='" + containerId + "', "
                + "recipe='" + recipe + "', "
                + "shiftDown='" + shiftDown + "']";
    }
}

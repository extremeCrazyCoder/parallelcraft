package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.ClientConnectionHandler;
import com.parallelcraft.Networking.PacketHandler;
import com.parallelcraft.Networking.PacketIncoming;
import com.parallelcraft.Networking.PacketReadHelper;
import com.parallelcraft.exceptions.InsufficientDataException;
import com.parallelcraft.world.BlockPosition;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayInJigsawGenerate extends PacketIncoming {
    private BlockPosition pos;
    private int levels;
    private boolean keepJigsaws;
    
    public PacketPlayInJigsawGenerate() {
    }
    
    @Override
    public void decode(PacketReadHelper data) throws InsufficientDataException {
        this.pos = data.readBlockPosition();
        this.levels = data.readVarLenInteger();
        this.keepJigsaws = data.readBoolean();
    }

    @Override
    public PacketHandler getHandler(ClientConnectionHandler handle) {
        return handle.getDefaultPacketHandler();
    }
    
    @Override
    public String toString() {
        return "PacketPlayInJigsawGenerate["
                + "pos='" + pos + "', "
                + "levels='" + levels + "', "
                + "keepJigsaws='" + keepJigsaws + "']";
    }
}

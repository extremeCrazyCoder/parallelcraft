package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.ClientConnectionHandler;
import com.parallelcraft.Networking.PacketHandler;
import com.parallelcraft.Networking.PacketIncoming;
import com.parallelcraft.Networking.PacketReadHelper;
import com.parallelcraft.constants.EnumBlockJigsawJointType;
import com.parallelcraft.exceptions.InsufficientDataException;
import com.parallelcraft.util.MinecraftKey;
import com.parallelcraft.world.BlockPosition;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayInSetJigsaw extends PacketIncoming {
    private BlockPosition pos;
    private MinecraftKey name;
    private MinecraftKey target;
    private MinecraftKey pool;
    private String finalState;
    private EnumBlockJigsawJointType joint;
    
    public PacketPlayInSetJigsaw() {
    }
    
    @Override
    public void decode(PacketReadHelper data) throws InsufficientDataException {
        this.pos = data.readBlockPosition();
        this.name = data.readMCKey();
        this.target = data.readMCKey();
        this.pool = data.readMCKey();
        this.finalState = data.readVarLenString(32767);
        this.joint = data.readEnum(EnumBlockJigsawJointType.class);
    }

    @Override
    public PacketHandler getHandler(ClientConnectionHandler handle) {
        return handle.getDefaultPacketHandler();
    }
    
    @Override
    public String toString() {
        return "PacketPlayInSetJigsaw["
                + "pos='" + pos + "', "
                + "name='" + name + "', "
                + "target='" + target + "', "
                + "pool='" + pool + "', "
                + "finalState='" + finalState + "', "
                + "joint='" + joint + "']";
    }
}

package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.ClientConnectionHandler;
import com.parallelcraft.Networking.PacketHandler;
import com.parallelcraft.Networking.PacketIncoming;
import com.parallelcraft.Networking.PacketReadHelper;
import com.parallelcraft.constants.EnumBlockCommandBlockActivationType;
import com.parallelcraft.exceptions.InsufficientDataException;
import com.parallelcraft.world.BlockPosition;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayInSetCommandBlock extends PacketIncoming {
    private BlockPosition pos;
    private String command;
    private EnumBlockCommandBlockActivationType mode;
    private boolean trackOutput;
    private boolean conditional;
    private boolean automatic;
    
    public PacketPlayInSetCommandBlock() {
    }
    
    @Override
    public void decode(PacketReadHelper data) throws InsufficientDataException {
        this.pos = data.readBlockPosition();
        this.command = data.readVarLenString(32767);
        this.mode = data.readEnum(EnumBlockCommandBlockActivationType.class);
        
        byte dat = data.readByte();
        this.trackOutput = (dat & 1) > 0;
        this.conditional = (dat & 2) > 0;
        this.automatic = (dat & 4) > 0;
    }

    @Override
    public PacketHandler getHandler(ClientConnectionHandler handle) {
        return handle.getDefaultPacketHandler();
    }
    
    @Override
    public String toString() {
        return "PacketPlayInSetCommandBlock["
                + "pos='" + pos + "', "
                + "command='" + command + "', "
                + "contaimodenerId='" + mode + "', "
                + "trackOutput='" + trackOutput + "', "
                + "conditional='" + conditional + "', "
                + "automatic='" + automatic + "']";
    }
}

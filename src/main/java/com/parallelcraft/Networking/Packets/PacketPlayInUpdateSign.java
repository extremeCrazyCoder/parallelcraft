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
public class PacketPlayInUpdateSign extends PacketIncoming {
    private BlockPosition pos;
    private String[] lines;
    
    public PacketPlayInUpdateSign() {
    }
    
    @Override
    public void decode(PacketReadHelper data) throws InsufficientDataException {
        this.pos = data.readBlockPosition();
        this.lines = new String[4];
        this.lines[0] = data.readVarLenString(384);
        this.lines[1] = data.readVarLenString(384);
        this.lines[2] = data.readVarLenString(384);
        this.lines[3] = data.readVarLenString(384);
    }

    @Override
    public PacketHandler getHandler(ClientConnectionHandler handle) {
        return handle.getDefaultPacketHandler();
    }
    
    @Override
    public String toString() {
        return "PacketPlayInUpdateSign["
                + "pos='" + pos + "', "
                + "line_0='" + lines[0] + "', "
                + "line_1='" + lines[1] + "', "
                + "line_2='" + lines[2] + "', "
                + "line_3='" + lines[3] + "']";
    }
}

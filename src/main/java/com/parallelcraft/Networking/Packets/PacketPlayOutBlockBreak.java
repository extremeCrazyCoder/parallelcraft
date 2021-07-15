package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.constants.EnumPlayerDigType;
import com.parallelcraft.world.Block;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutBlockBreak  extends PacketOutgoing {
    //TODO use this
    private Block toBreak;
    private EnumPlayerDigType digType;
    private boolean allowed;
    
    public PacketPlayOutBlockBreak(Block toBreak, EnumPlayerDigType digType, boolean allowed) {
        this.toBreak = toBreak;
        this.digType = digType;
        this.allowed = allowed;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeBlockPosition(toBreak.getPosition());
        data.writeVarLenInteger(toBreak.getType().getDatabaseID());
        data.writeEnum(digType);
        data.writeBoolean(allowed);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutBlockBreak["
                + "toBreak='" + toBreak.toString() + "', "
                + "digType='" + digType.name() + "', "
                + "allowed='" + allowed + "']";
    }
}

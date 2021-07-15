package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.constants.EnumDifficulty;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutServerDifficulty extends PacketOutgoing {
    //TODO use this
    private EnumDifficulty newDifficultiy;
    private boolean locked;
    
    public PacketPlayOutServerDifficulty(EnumDifficulty newDifficultiy, boolean locked) {
        this.newDifficultiy = newDifficultiy;
        this.locked = locked;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeEnum(newDifficultiy);
        data.writeBoolean(locked);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutServerDifficulty["
                + "newDifficultiy='" + newDifficultiy.name() + "', "
                + "locked='" + locked + "']";
    }
}

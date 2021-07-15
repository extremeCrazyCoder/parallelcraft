package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutPlayerCombatEnd extends PacketOutgoing {
    //TODO use this
    private int killerId;
    private int duration;
    
    public PacketPlayOutPlayerCombatEnd(int killerId, int duration) {
        this.killerId = killerId;
        this.duration = duration;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeVarLenInteger(this.duration);
        data.writeInt(this.killerId);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutPlayerCombatEnd["
                + "duration='" + duration + "', "
                + "killerId='" + killerId + "']";
    }
}

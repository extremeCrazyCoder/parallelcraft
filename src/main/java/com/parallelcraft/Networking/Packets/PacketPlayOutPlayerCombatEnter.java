package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutPlayerCombatEnter extends PacketOutgoing {
    //TODO use this
    
    public PacketPlayOutPlayerCombatEnter() {
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutPlayerCombatEnter[]";
    }
}

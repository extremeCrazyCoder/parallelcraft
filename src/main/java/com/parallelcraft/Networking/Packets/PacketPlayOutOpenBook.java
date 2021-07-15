package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.constants.EnumHand;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutOpenBook extends PacketOutgoing {
    //TODO use this
    private EnumHand hand;
    
    public PacketPlayOutOpenBook(EnumHand hand) {
        this.hand = hand;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeEnum(hand);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutOpenBook["
                + "hand='" + hand + "']";
    }
}

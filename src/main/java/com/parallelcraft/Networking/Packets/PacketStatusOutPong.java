package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketReadHelper;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.exceptions.InsufficientDataException;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketStatusOutPong extends PacketOutgoing {
    
    private long pingTimestamp;
    
    @Override
    public void decode(PacketReadHelper data) throws InsufficientDataException {
        this.pingTimestamp = data.readLong();
    }

    @Override
    public void encode(PacketWriteHelper data) {
        data.writeLong(pingTimestamp);
    }
    
    @Override
    public String toString() {
        return "PacketStatusOutPong["
                + "pingTimestamp='" + pingTimestamp + "']";
    }
}

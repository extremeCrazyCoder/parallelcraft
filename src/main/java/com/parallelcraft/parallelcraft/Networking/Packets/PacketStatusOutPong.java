package com.parallelcraft.parallelcraft.Networking.Packets;

import com.parallelcraft.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.parallelcraft.Networking.PacketReadHelper;
import com.parallelcraft.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.parallelcraft.exceptions.InsufficientDataException;

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
    
    public String toString() {
        return "PacketStatusOutPong["
                + "pingTimestamp='" + pingTimestamp + "']";
    }
}

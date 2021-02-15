package com.parallelcraft.parallelcraft.Networking.Packets;

import com.parallelcraft.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.parallelcraft.exceptions.MalformedDataException;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * Totally useless packet used nowhere and will most likly also cause a diconnect when sent
 * (based on same Packet in opposite direction)
 * 
 * @author extremeCrazyCoder
 */
public class PacketLoginOutCustomPayload extends PacketOutgoing {
    //would normally contain a byte array, but why decode when we know what will happen?
    @Override
    public void encode(PacketWriteHelper data) {
        throw new MalformedDataException("No need for this Packet");
    }
    
    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

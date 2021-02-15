package com.parallelcraft.parallelcraft.Networking;

import com.parallelcraft.parallelcraft.exceptions.InsufficientDataException;
import com.parallelcraft.parallelcraft.exceptions.MalformedDataException;
import java.util.List;
import java.util.function.Supplier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Used for decoding incoming Packets
 * 
 * @author extremeCrazyCoder
 */
public class MCPacketDecoder {
    private static Logger logger = LogManager.getLogger("MCPacketDecoder");
    
    /**
     * Reads the next packet from Buffer
     * @returns the packet
     */
    public static PacketIncoming readNextPacket(ClientConnectionHandler clientState, PacketReadHelper helper) throws InsufficientDataException {
        int packetIndex = helper.getInteger();
        
        List<Supplier<? extends PacketIncoming>> cache = clientState.getPacketCacheToServer();
        if(packetIndex >= cache.size()) {
            throw new MalformedDataException("Packet with id " + packetIndex + " does not exist in protocol " + clientState.getCurrentProtocol());
        }
        
        PacketIncoming generated = cache.get(packetIndex).get();
        generated.decode(helper);
        
        if(helper.hasRemaining()) {
            throw new MalformedDataException("Packet longer than expected " + helper.remaining() + " bytes too much");
        }
        
        return generated;
    }
}

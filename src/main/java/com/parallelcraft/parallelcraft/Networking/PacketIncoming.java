package com.parallelcraft.parallelcraft.Networking;

/**
 *
 * @author extremeCrazyCoder
 */

public abstract class PacketIncoming implements Packet {
    public final void encode(PacketWriteHelper data) {
        throw new UnsupportedOperationException("Incoming packet no need for encoding");
    }
    
    /**
     * get a function that is able to deal with that packet
     * 
     * @param handle the ClientConnctionHandler that called this function and thus knows whom this belongs to
     */
    abstract public PacketHandler getHandler(ClientConnectionHandler handle);
}

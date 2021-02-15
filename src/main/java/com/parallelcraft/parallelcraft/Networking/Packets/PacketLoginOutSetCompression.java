package com.parallelcraft.parallelcraft.Networking.Packets;

import com.parallelcraft.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.parallelcraft.Networking.PacketWriteHelper;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketLoginOutSetCompression extends PacketOutgoing {

    private int minPacketSize;
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeInteger(minPacketSize);
        //TODO implement compression
        // implemented in vanilla using java.util.zip.Inflater
        // packets smaller than 256 should not be compressed if smaller received -> error
        // compressed packets must not be larger than 2097152 bytes (uncompressed)
        // see PacketDecompressor
        
        // use Deflater for compression
        // see https://www.tutorialspoint.com/javazip/javazip_inflater_inflate.htm
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public String toString() {
        return "PacketLoginOutSetCompression["
                + "minPacketSize='" + minPacketSize + "']";
    }
}

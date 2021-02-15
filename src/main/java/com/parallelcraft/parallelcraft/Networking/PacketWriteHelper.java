package com.parallelcraft.parallelcraft.Networking;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Helper class that wraps around a byte buffer for easier read acess
 * Decodes Data types in the Minecraft Packet Style
 * 
 * @author extremeCrazyCoder
 */
public class PacketWriteHelper {
    private final ByteBuffer buf;
    
    private Logger logger = LogManager.getLogger("PacketReadHelper");
    
    public PacketWriteHelper(ByteBuffer pBuf) {
        this.buf = pBuf;
    }
    
    public void writeInteger(int toWrite) {
        int running = toWrite;
        byte cur;
        
        /*
         * Last byte is shown by a low MSB
         * Middle byte is shown by high MSB
         */
        do {
            cur = (byte) (running & 0x7F);
            running = running >> 7;
            if(running > 0) {
                cur |= 0x80;
            }
            buf.put(cur);
        } while (running > 0);
    }
    
    public void writeString(String data) {
        ByteBuffer encoded = StandardCharsets.UTF_8.encode(data);
        writeInteger(encoded.limit());
        
        buf.put(encoded);
    }
    
    /**
     * The next two bytes will be an unsigned short
     */
    public void writeUnsignedShort(int data) {
        buf.put((byte) (data >> 8));
        buf.put((byte) (data & 0xFF));
    }
    
    /**
     * The next byte is an byte
     */
    public void writeByte(byte data) {
        buf.put(data);
    }
    
    /**
     * Writes an int for the lenght and afterwards the data
     */
    public void writeByteArray(byte[] data) {
        writeInteger(data.length);
        
        buf.put(data);
    }
    
    /**
     * the next 8 Bytes are an Long
     */
    public void writeLong(long data) {
        buf.putLong(data);
    }
    
    /**
     * the next 4 Bytes are an Integer
     */
    public void writeInt(int data) {
        buf.putInt(data);
    }
}

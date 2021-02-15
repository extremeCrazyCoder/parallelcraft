package com.parallelcraft.parallelcraft.Networking;

import com.parallelcraft.parallelcraft.exceptions.InsufficientDataException;
import com.parallelcraft.parallelcraft.exceptions.MalformedDataException;
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
public class PacketReadHelper {
    private final ByteBuffer buf;
    
    private Logger logger = LogManager.getLogger("PacketReadHelper");
    
    public PacketReadHelper(ByteBuffer pBuf) {
        this.buf = pBuf;
    }
    
    /**
     * reads the next Integer from the buffer
     * 
     * @throws InsufficientDataException when the buffer does not contain enogh data
     */
    public int getInteger() throws InsufficientDataException {
        int res = 0;
        int byteRead = 0;
        
        byte cur;
        
        /*
         * Last byte is shown by a low MSB
         * Middle byte is shown by high MSB
         */
        do {
            if(! buf.hasRemaining()) {
                throw new InsufficientDataException();
            }
            cur = buf.get();
            res |= (cur & 0x7F) << byteRead * 7;
            byteRead++;
            if(byteRead > 5) {
                throw new MalformedDataException("Int too long");
            }
        } while ((cur & 0x80) == 0x80);
        
        return res;
    }
    
    /**
     * reads the next String from the buffer
     * 
     * @param maxLen the maximum String lenght to be expected
     * memory will be allocated based on that
     * 
     * @throws InsufficientDataException when the buffer does not contain enogh data
     */
    public String getString(int maxLen) throws InsufficientDataException {
        int strlen = getInteger();
        
        if(strlen > maxLen * 4) {
            throw new MalformedDataException("Strlen(" + strlen + ") is bigger than maxlen(" + (maxLen * 4) + ")");
        } else if(strlen < 0) {
            throw new MalformedDataException("Strlen smaller than 0... (" + strlen + ")");
        }
        if(buf.remaining() < strlen) {
            throw new InsufficientDataException();
        }
        
        String data = StandardCharsets.UTF_8.decode(buf.slice(buf.position(), strlen)).toString();
        if(data.length() > maxLen) {
            throw new MalformedDataException("Resulting String is to long: " + data.length() + " instead of " + maxLen);
        }
        buf.position(buf.position() + strlen);
        return data;
    }
    
    /**
     * The next two bytes are an unsigned short
     * 
     * @throws InsufficientDataException when the buffer does not contain enogh data
     */
    public int getUnsignedShort() throws InsufficientDataException {
        if(buf.remaining() < 2) {
            throw new InsufficientDataException();
        }
        
        int res = (((int) buf.get()) & 0xFF) << 8;
        res |= (((int) buf.get()) & 0xFF);
        return res;
    }
    
    /**
     * The next byte is an byte
     * 
     * @throws InsufficientDataException when the buffer does not contain enogh data
     */
    public byte getByte() throws InsufficientDataException {
        if(! buf.hasRemaining()) {
            throw new InsufficientDataException();
        }
        
        return buf.get();
    }
    
    /**
     * Reads an int for the lenght and then the next bytes according to the int
     * Implemented in vanilla using a maximum that is used as max buffer size when called...
     * 
     * @throws InsufficientDataException when the buffer does not contain enogh data
     */
    public byte[] getByteArray() throws InsufficientDataException {
        int len = getInteger();
        
        if(buf.remaining() < len) {
            throw new InsufficientDataException();
        }
        
        byte returnVal[] = new byte[len];
        buf.get(returnVal);
        return returnVal;
    }
    
    /**
     * Reads the next 8 Bytes as an Long
     * 
     * @throws InsufficientDataException when the buffer does not contain enogh data
     */
    public long readLong() throws InsufficientDataException {
        if(buf.remaining() < 8) {
            throw new InsufficientDataException();
        }
        
        return buf.getLong();
    }
    
    /**
     * Reads the next 4 Bytes as an Integer
     * 
     * @throws InsufficientDataException when the buffer does not contain enogh data
     */
    public int readInt() throws InsufficientDataException {
        if(buf.remaining() < 4) {
            throw new InsufficientDataException();
        }
        
        return buf.getInt();
    }

    public boolean hasRemaining() {
        return buf.hasRemaining();
    }

    public int remaining() {
        return buf.remaining();
    }
}

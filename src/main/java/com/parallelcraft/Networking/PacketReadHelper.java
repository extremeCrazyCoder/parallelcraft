package com.parallelcraft.Networking;

import com.parallelcraft.Datapack.DatapackHolder;
import com.parallelcraft.exceptions.InsufficientDataException;
import com.parallelcraft.exceptions.MalformedDataException;
import com.parallelcraft.user.ChatMessage;
import com.parallelcraft.util.MinecraftKey;
import com.parallelcraft.world.BlockPosition;
import com.parallelcraft.world.BlockType;
import com.parallelcraft.world.Blocks;
import com.parallelcraft.world.Inventory;
import com.parallelcraft.world.ItemStack;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Helper class that wraps around a byte buffer for easier read access
 * Decodes Data types in the Minecraft Packet Style
 * 
 * @author extremeCrazyCoder
 */
public class PacketReadHelper {
    private final ByteBuffer buf;
    
    private Logger logger = LogManager.getLogger("PacketReadHelper");
    
    public PacketReadHelper(ByteBuffer buf) {
        this.buf = buf;
    }
    
    public ByteBuffer getInternalBuffer() {
        return this.buf;
    }

    public boolean hasRemaining() {
        return buf.hasRemaining();
    }

    public int remaining() {
        return buf.remaining();
    }
    
    /**
     * Primitive data types
     * 
     */
    
    /**
     * The next two bytes are an unsigned short
     * 
     * @throws InsufficientDataException when the buffer does not contain enogh data
     */
    public int readUnsignedShort() throws InsufficientDataException {
        if(buf.remaining() < 2) {
            throw new InsufficientDataException();
        }
        
        int res = (((int) buf.get()) & 0xFF) << 8;
        res |= (((int) buf.get()) & 0xFF);
        return res;
    }
    
    /**
     * The next two bytes are an signed short
     * 
     * @throws InsufficientDataException when the buffer does not contain enogh data
     */
    public int readSignedShort() throws InsufficientDataException {
        if(buf.remaining() < 2) {
            throw new InsufficientDataException();
        }
        
        return buf.getShort();
    }
    
    /**
     * The next byte is an byte
     * 
     * @throws InsufficientDataException when the buffer does not contain enogh data
     */
    public byte readByte() throws InsufficientDataException {
        if(! buf.hasRemaining()) {
            throw new InsufficientDataException();
        }
        
        return buf.get();
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
    
    /**
     * Reads the next 4 Bytes as an Float
     * 
     * @throws InsufficientDataException when the buffer does not contain enogh data
     */
    public float readFloat() throws InsufficientDataException {
        if(buf.remaining() < 4) {
            throw new InsufficientDataException();
        }
        
        return buf.getFloat();
    }
    
    /**
     * Reads the next 8 Bytes as an Double
     * 
     * @throws InsufficientDataException when the buffer does not contain enogh data
     */
    public double readDouble() throws InsufficientDataException {
        if(buf.remaining() < 8) {
            throw new InsufficientDataException();
        }
        
        return buf.getDouble();
    }
    
    /**
     * Reads the next Byte as an Boolean
     * 
     * @throws InsufficientDataException when the buffer does not contain enogh data
     */
    public boolean readBoolean() throws InsufficientDataException {
        if(buf.remaining() < 1) {
            throw new InsufficientDataException();
        }
        byte data = buf.get();
        
        return data != 0;
    }
    
    
    
    /**
     * Variable Length data
     * standard Java
     */
    
    /**
     * reads the next Integer from the buffer (encoded with variable lenght)
     * 
     * @throws InsufficientDataException when the buffer does not contain enogh data
     */
    public int readVarLenInteger() throws InsufficientDataException {
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
     * reads the next Long from the buffer (encoded with variable lenght)
     * 
     * @throws InsufficientDataException when the buffer does not contain enogh data
     */
    public long readVarLenLong() throws InsufficientDataException {
        long res = 0;
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
            if(byteRead > 10) {
                throw new MalformedDataException("Long too long");
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
    public String readVarLenString(int maxLen) throws InsufficientDataException {
        int strlen = readVarLenInteger();
        
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
     * Reads an int for the lenght and then the next bytes according to the int
     * Implemented in vanilla using a maximum that is used as max buffer size when called...
     * 
     * @throws InsufficientDataException when the buffer does not contain enogh data
     */
    public byte[] readVarLenByteArray() throws InsufficientDataException {
        int len = readVarLenInteger();
        
        if(buf.remaining() < len) {
            throw new InsufficientDataException();
        }
        
        byte returnVal[] = new byte[len];
        buf.get(returnVal);
        return returnVal;
    }
    
    /**
     * Reads an int for the lenght and then the data via readVarLenByteArray
     * 
     * @throws InsufficientDataException when the buffer does not contain enogh data
     */
    public List<byte[]> readVarLenByteArrayList() throws InsufficientDataException {
        int len = readVarLenInteger();
        List<byte[]> result = new ArrayList<>(len);
        
        for(int i = 0; i < len; i++) {
            result.add(readVarLenByteArray());
        }
        return result;
    }
    
    /**
     * Reads an varLenInt for the lenght and then the data via readVarLenInteger
     * 
     * @throws InsufficientDataException when the buffer does not contain enogh data
     */
    public int[] readVarLenIntegerArray() throws InsufficientDataException {
        int len = readVarLenInteger();
        int[] data = new int[len];
        
        if(buf.remaining() < len) {
            throw new InsufficientDataException();
        }
        for(int i = 0; i < len; i++) {
            data[i] = readVarLenInteger();
        }
        return data;
    }
    
    /**
     * Reads an int for the lenght and then the data via readLong
     * 
     * @throws InsufficientDataException when the buffer does not contain enogh data
     */
    public long[] readVarLenLongArray() throws InsufficientDataException {
        int len = readVarLenInteger();
        long[] data = new long[len];
        
        if(buf.remaining() < len) {
            throw new InsufficientDataException();
        }
        for(int i = 0; i < len; i++) {
            data[i] = readLong();
        }
        return data;
    }
    
    
    
    /**
     * Writing of custom classes
     * 
     */
    
    /**
     * Interprets the next VarLenLongArray as BitSet
     * 
     * @throws InsufficientDataException when the buffer does not contain enogh data
     */
    public BitSet readBitSet() throws InsufficientDataException {
        return BitSet.valueOf(readVarLenLongArray());
    }
    
    /**
     * Interprets the next variable integer as enum of given class
     * 
     * @throws InsufficientDataException when the buffer does not contain enogh data
     */
    public <T extends Enum<T>> T readEnum(Class<T> enumClass) throws InsufficientDataException {
        return enumClass.getEnumConstants()[readVarLenInteger()];
    }
    
    /**
     * Interprets the next byte as enum of given class
     * 
     * @throws InsufficientDataException when the buffer does not contain enogh data
     */
    public <T extends Enum<T>> T readEnumAsByte(Class<T> enumClass) throws InsufficientDataException {
        return enumClass.getEnumConstants()[readByte()];
    }
    
    public UUID readUUID() throws InsufficientDataException {
        long msb = readLong();
        long lsb = readLong();
        return new UUID(msb, lsb);
    }
    
    /**
     * Interprets the next long as blockPosition
     * 
     * @throws InsufficientDataException when the buffer does not contain enogh data
     */
    public BlockPosition readBlockPosition() throws InsufficientDataException {
        return new BlockPosition(readLong());
    }
    
    /**
     * Reads an ItemStack from buffer
     * 
     * @throws InsufficientDataException when the buffer does not contain enogh data
     */
    public ItemStack readItemStack() throws InsufficientDataException {
        boolean notEmpty = readBoolean();
        if(notEmpty) {
            BlockType mat = DatapackHolder.BLOCKS.byID(readVarLenInteger());
            short amount = readByte();
            
            //TODO something with NBTTagCompound
            return new ItemStack(mat, amount);
        } else {
            return new ItemStack(Blocks.AIR, (short) 0);
        }
    }
    
    /**
     * Reads an Inventory from buffer
     * 
     * @throws InsufficientDataException when the buffer does not contain enogh data
     */
    public Inventory readInventory() throws InsufficientDataException {
        short len = (short) readUnsignedShort();
        Inventory retval = new Inventory(len);
        for(short i = 0; i < len; i++) {
            retval.put(i, readItemStack());
        }
        return retval;
    }
    
    public ChatMessage readChatMessage() throws InsufficientDataException {
        return ChatMessage.fromSerialized(readVarLenString(262144));
    }
    
    
    public <K, V> Map<K, V> readMap(PacketReadFunction<K> readKey, PacketReadFunction<V> readValue) throws InsufficientDataException {
        int size = readVarLenInteger();
        Map<K, V> result = new HashMap<>();
        
        for(int i = 0; i < size; i++) {
            result.put(readKey.apply(this), readValue.apply(this));
        }
        
        return result;
    }

    public MinecraftKey readMCKey() throws InsufficientDataException {
        return new MinecraftKey(readVarLenString(32767));
    }
    
    public interface PacketReadFunction<T> {
        public T apply(PacketReadHelper reader) throws InsufficientDataException;
    }
}

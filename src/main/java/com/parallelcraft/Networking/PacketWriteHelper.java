package com.parallelcraft.Networking;

import com.parallelcraft.Datapack.DatapackRegistry;
import com.parallelcraft.user.ChatMessage;
import com.parallelcraft.util.MinecraftKey;
import com.parallelcraft.world.BlockPosition;
import com.parallelcraft.world.Inventory;
import com.parallelcraft.world.ItemStack;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.BitSet;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Helper class that wraps around a byte buffer for easier read access
 * Decodes Data types in the Minecraft Packet Style
 * 
 * @author extremeCrazyCoder
 */
public class PacketWriteHelper {
    private final ByteBuffer buf;
    
    private Logger logger = LogManager.getLogger("PacketWriteHelper");
    
    public PacketWriteHelper(ByteBuffer buf) {
        this.buf = buf;
    }
    
    public ByteBuffer getInternalBuffer() {
        return this.buf;
    }
    
    /**
     * Primitive data types
     * 
     */
    
    /**
     * The next two bytes will be an unsigned short
     */
    public void writeUnsignedShort(int data) {
        buf.put((byte) (data >> 8));
        buf.put((byte) (data & 0xFF));
    }
    
    /**
     * The next two bytes will be an signed short
     */
    public void writeSignedShort(short data) {
        buf.putShort(data);
    }
    
    /**
     * The next byte is an byte
     */
    public void writeByte(byte data) {
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
    
    /**
     * the next 4 Bytes are an Float
     */
    public void writeFloat(float data) {
        buf.putFloat(data);
    }
    
    /**
     * the next 8 Bytes are an Double
     */
    public void writeDouble(double data) {
        buf.putDouble(data);
    }
    
    /**
     * the next Byte is an boolean
     */
    public void writeBoolean(boolean data) {
        buf.put((byte) (data?1:0));
    }
    
    
    
    /**
     * Variable Length data
     * standard Java
     */
    
    /**
     * writes an compressed integer
     * 
     * integer can be 1 to 5 Bytes long based on value
     */
    public void writeVarLenInteger(int toWrite) {
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
    
    /**
     * writes an compressed integer
     * 
     * long can be 1 to 10 Bytes long based on value
     */
    public void writeVarLenLong(long toWrite) {
        long running = toWrite;
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
    
    public void writeVarLenString(String data) {
        ByteBuffer encoded = StandardCharsets.UTF_8.encode(data);
        writeVarLenInteger(encoded.limit());
        
        buf.put(encoded);
    }
    
    /**
     * Writes an int for the lenght and afterwards the data
     */
    public void writeByteArray(byte[] data) {
        writeVarLenInteger(data.length);
        
        buf.put(data);
    }
    
    /**
     * Writes an int for the length and afterwards the data with writeVarLenByteArray
     */
    public void writeByteArrayList(List<byte[]> data) {
        //TODO check if this is correct
        writeVarLenInteger(data.size());
        
        for(byte[] array : data) {
            writeByteArray(array);
        }
    }
    
    
    /**
     * Writes an int for the lenght and afterwards the data
     * that is expressed as a bunch of varLenInteger
     */
    public void writeVarLenIntegerArray(int[] data) {
        writeVarLenInteger(data.length);
        
        for(int i = 0; i < data.length; i++) {
            writeVarLenInteger(data[i]);
        }
    }
    
    
    /**
     * Writes an int for the lenght and afterwards the data
     * that is expressed as a bunch of direct long writes
     */
    public void writeLongArray(long[] data) {
        writeVarLenInteger(data.length);
        
        for(int i = 0; i < data.length; i++) {
            writeLong(data[i]);
        }
    }
    
    
    
    /**
     * Writing of custom classes
     * 
     */
    
    /**
     * Writes an long array for the data of given BitSet
     */
    public void writeBitSet(BitSet data) {
        writeLongArray(data.toLongArray());
    }
    
    public void writeEnum(Enum<?> data) {
        if(data == null) {
            writeVarLenInteger(0);
        } else {
            writeVarLenInteger(data.ordinal());
        }
    }
    
    public void writeEnumAsByte(Enum<?> data) {
        writeByte((byte) data.ordinal());
    }
    
    public void writeUUID(UUID data) {
        buf.putLong(data.getMostSignificantBits());
        buf.putLong(data.getLeastSignificantBits());
    }
    
    public void writeBlockPosition(BlockPosition data) {
        writeLong(data.asLong());
    }
    
    public void writeItemStack(ItemStack data) {
        writeBoolean(!data.isEmpty());
        if(!data.isEmpty()) {
            writeVarLenInteger(data.getType().getDatabaseID());
            writeByte((byte) data.getAmount());
            //TODO write NBTTagCompound
        }
    }
    
    public void writeInventory(Inventory data) {
        writeUnsignedShort(data.getSize());
        for(ItemStack stack : data.getItems()) {
            writeItemStack(stack);
        }
    }
    
    public void writeChatMessage(ChatMessage data) {
        writeVarLenString(data.serialize());
    }
    
    public <T> void writeList(Collection<T> input, PacketWriteFunction<T> writeSingle) {
        int size = input.size();
        writeVarLenInteger(size);
        for(T single : input) {
            writeSingle.accept(this, single);
        }
    }

    public void writeMCKey(MinecraftKey data) {
        writeVarLenString(data.asPacketString());
    }
    
    public void writeBuffer(ByteBuffer data) {
        data.flip();
        writeVarLenInteger(data.limit());
        buf.put(data);
    }
    
    public interface PacketWriteFunction<T> {
        public void accept(PacketWriteHelper writer, T data);
    }
}

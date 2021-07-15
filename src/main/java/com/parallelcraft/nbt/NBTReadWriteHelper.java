package com.parallelcraft.nbt;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Helper class that wraps around a byte buffer for easier read / write access
 * Decodes data types in the Minecraft Packet Style
 * 
 * @author extremeCrazyCoder
 */
public class NBTReadWriteHelper {
    private final ByteBuffer buf;
    
    private Logger logger = LogManager.getLogger("NBTReadWriteHelper");

    public NBTReadWriteHelper(ByteBuffer buf) {
        this.buf = buf;
    }
    
    public byte readByte() {
        return buf.get();
    }

    public short readShort() {
        return buf.getShort();
    }

    public int readInteger() {
        return buf.getInt();
    }

    public long readLong() {
        return buf.getLong();
    }

    public float readFloat() {
        return buf.getFloat();
    }

    public double readDouble() {
        return buf.getDouble();
    }

    public byte[] readByteArray() {
        int len = readInteger();
        byte[] res = new byte[len];
        for(int i = 0; i < len; i++) {
            res[i] = buf.get();
        }
        return res;
    }
    
    public String readString() {
        short len = buf.getShort();
        String data = StandardCharsets.UTF_8.decode(buf.slice(buf.position(), len)).toString();
        buf.position(buf.position() + len);
        return data;
    }

    public int[] readIntArray() {
        int len = readInteger();
        int[] res = new int[len];
        for(int i = 0; i < len; i++) {
            res[i] = buf.getInt();
        }
        return res;
    }

    public long[] readLongArray() {
        int len = readInteger();
        long[] res = new long[len];
        for(int i = 0; i < len; i++) {
            res[i] = buf.getLong();
        }
        return res;
    }
    
    
    
    
    public void writeByte(byte data) {
        buf.put(data);
    }

    public void writeShort(short data) {
        buf.putShort(data);
    }

    public void writeInteger(int data) {
        buf.putInt(data);
    }

    public void writeLong(long data) {
        buf.putLong(data);
    }

    public void writeFloat(float data) {
        buf.putFloat(data);
    }

    public void writeDouble(double data) {
        buf.putDouble(data);
    }

    public void writeByteArray(byte[] data) {
        writeInteger(data.length);
        buf.put(data);
    }
    
    public void writeString(String data) {
        ByteBuffer encoded = StandardCharsets.UTF_8.encode(data);
        buf.putShort((short) encoded.limit());
        buf.put(encoded);
    }

    public void writeIntArray(int[] data) {
        writeInteger(data.length);
        for(int i = 0; i < data.length; i++) {
            buf.putInt(data[i]);
        }
    }

    public void writeLongArray(long[] data) {
        writeInteger(data.length);
        for(int i = 0; i < data.length; i++) {
            buf.putLong(data[i]);
        }
    }
}

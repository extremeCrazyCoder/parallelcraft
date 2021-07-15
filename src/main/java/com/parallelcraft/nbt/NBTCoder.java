package com.parallelcraft.nbt;

import com.parallelcraft.Networking.PacketWriteHelper;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Packet responsible for en-/de-coding of NBT-Data
 *
 * @author extremeCrazyCoder
 */
public class NBTCoder {
    private static Logger logger = LogManager.getLogger("NBTCoder");
    
    public static void main(String args[]) throws FileNotFoundException, IOException {
        //TODO remove this
        FileInputStream inFile = new FileInputStream("BIN_1");
        ByteBuffer buffer = ByteBuffer.allocate(1 * 1024 * 1024);
        
        int read = inFile.read(buffer.array());
        buffer.limit(read);
        inFile.close();
        NBTReadWriteHelper helper = new NBTReadWriteHelper(buffer);
        NBTCompoundElement elm = decodeNBTElement(helper);
        System.out.println(elm);
        
        System.out.println(elm.toJSON());
//        ByteBuffer enc = encode(elm);
//        FileOutputStream outFile = new FileOutputStream("PacketPlayOutLogin_bin_new");
//        outFile.write(enc.array(), 0, enc.limit());
//        outFile.close();
    }
    /**
     * Types
     * 1: Byte
     * 2: Short
     * 3: Integer
     * 4: Long
     * 5: float
     * 6: double
     * 7: Byte-Array -> ArrayLänge als Int dann bytes
     * 8: String
     * 9: List -> Datentyp ID -> Integer Länge
     * 10: Element mit Sub-Elementen (keine Liste) -> 00 als Element ende
     * 11: Int-Array -> ArrayLänge als Int
     * 12: Long-Array -> ArrayLänge als Int
     */
    
    //read section
    public static NBTCompoundElement decodeNBTElement(NBTReadWriteHelper helper) {
        byte type = helper.readByte();
        if(type != 10) {
            throw new IllegalArgumentException("Root element needs to be of type compound found " + type);
        }
        String name = helper.readString();
        NBTCompoundElement elm = decodeNBTElementInternal(helper);
        //Disabled because seems useless
//        String typePart = (String) elm.get("type");
//        if((typePart == null && name == null) || !name.equals(typePart)) {
//            throw new IllegalArgumentException("Type needs to be the same as name " + typePart + " / " + name);
//        }
        return elm;
    }
    
    private static NBTCompoundElement decodeNBTElementInternal(NBTReadWriteHelper helper) {
        NBTCompoundElement elm = new NBTCompoundElement();
        byte typeNext = helper.readByte();
        while(typeNext != 0) {
            String key = helper.readString();
            Object value = readTyped(helper, typeNext, key);
            elm.put(key, value);
            typeNext = helper.readByte();
        }
        return elm;
    }
    
    private static Object readTyped(NBTReadWriteHelper helper, byte type, String key) {
        switch(type) {
            case 1:
                return helper.readByte();
            case 2:
                return helper.readShort();
            case 3:
                return helper.readInteger();
            case 4:
                return helper.readLong();
            case 5:
                return helper.readFloat();
            case 6:
                return helper.readDouble();
            case 7:
                return helper.readByteArray();
            case 8:
                return helper.readString();
            case 9:
                List<Object> result = new ArrayList<>();
                byte typeInner = helper.readByte();
                int numElements = helper.readInteger();
                for(int i = 0; i < numElements; i++) {
                    result.add(readTyped(helper, typeInner, key));
                }
                return result;
            case 10:
                NBTCompoundElement elm = decodeNBTElementInternal(helper);
                //Disabled because seems useless
//                String typePart = (String) elm.get("type");
//                if(key.equals("element")) key = null;
//                
//                if((typePart != null && key != null) || !key.equals(typePart)) {
//                    throw new IllegalArgumentException("Type needs to be the same as name " + typePart + " / " + key);
//                }
                return elm;
            case 11:
                return helper.readIntArray();
            case 12:
                return helper.readLongArray();
        }
        
        throw new IllegalArgumentException("Unknown type found " + type);
    }

    public static ByteBuffer encode(NBTCompoundElement toEncode) {
        ByteBuffer buf = ByteBuffer.allocate(2 * 1024 * 1024);
        NBTReadWriteHelper helper = new NBTReadWriteHelper(buf);
        encodeData(toEncode, "", helper);
        buf.flip();
        return buf;
    }

    public static void encode(NBTAble toEncode, PacketWriteHelper data) {
        Object elmEncode = toEncode.toNBTElement();
        NBTReadWriteHelper helper = new NBTReadWriteHelper(data.getInternalBuffer());
        encodeData(elmEncode, "", helper);
    }

    public static void encode(NBTCompoundElement elmEncode, PacketWriteHelper data) {
        NBTReadWriteHelper helper = new NBTReadWriteHelper(data.getInternalBuffer());
        encodeData(elmEncode, "", helper);
    }
    
    private static void encodeData(Object data, String key, NBTReadWriteHelper helper) {
        byte type = getType(data);
        helper.writeByte(type);
        helper.writeString(key);
        writeTyped(data, type, helper);
    }
    
    private static void writeTyped(Object data, byte type, NBTReadWriteHelper helper) {
        switch (type) {
            case 1:
                helper.writeByte((byte) data);
                break;
            case 2:
                helper.writeShort((short) data);
                break;
            case 3:
                helper.writeInteger((int) data);
                break;
            case 4:
                helper.writeLong((long) data);
                break;
            case 5:
                helper.writeFloat((float) data);
                break;
            case 6:
                helper.writeDouble((double) data);
                break;
            case 7:
                helper.writeByteArray((byte[]) data);
                break;
            case 8:
                helper.writeString((String) data);
                break;
            case 9:
                List<?> dataList = (List<?>) data;
                if(dataList.isEmpty()) {
                    helper.writeByte((byte) 1);
                    helper.writeInteger(0);
                    break;
                }
                byte typeInner = getType(dataList.get(0));
                helper.writeByte(typeInner);
                helper.writeInteger(dataList.size());
                for(int i = 0; i < dataList.size(); i++) {
                    writeTyped(dataList.get(i), typeInner, helper);
                }
                break;
            case 10:
                NBTCompoundElement elm = (NBTCompoundElement) data;
                for(Map.Entry<String, Object> entry : elm.allEntries()) {
                    encodeData(entry.getValue(), entry.getKey(), helper);
                }
                //end marking
                helper.writeByte((byte) 0);
                break;
            case 11:
                helper.writeIntArray((int[]) data);
                break;
            case 12:
                helper.writeLongArray((long[]) data);
                break;
        }
    }
    
    private static byte getType(Object data) {
        if(data instanceof Byte) {
            return 1;
        } else if(data instanceof Short) {
            return 2;
        } else if(data instanceof Integer) {
            return 3;
        } else if(data instanceof Long) {
            return 4;
        } else if(data instanceof Float) {
            return 5;
        } else if(data instanceof Double) {
            return 6;
        } else if(data instanceof byte[] || data instanceof Byte[]) {
            return 7;
        } else if(data instanceof String) {
            return 8;
        } else if(data instanceof List) {
            return 9;
        } else if(data instanceof NBTCompoundElement) {
            return 10;
        } else if(data instanceof int[] || data instanceof Integer[]) {
            return 11;
        } else if(data instanceof long[] || data instanceof Long[]) {
            return 12;
        }
        throw new IllegalArgumentException("Unknown type of data to encode " + data.getClass().getCanonicalName() + " data: " + data);
    }
}

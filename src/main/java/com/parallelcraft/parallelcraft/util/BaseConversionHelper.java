package com.parallelcraft.parallelcraft.util;

/**
 * For converting data between different bases
 * 
 * @author extremeCrazyCoder
 */
public class BaseConversionHelper {
    public static final char[] hexChars = "0123456789ABCDEF".toCharArray();
    
    public static String toHex(byte[] data) {
        StringBuilder res = new StringBuilder();
        
        for(int i = 0; i < data.length; i++) {
            res.append(hexChars[(data[i] >> 4) & 0x0F]);
            res.append(hexChars[data[i] & 0x0F]);
        }
        return res.toString();
    }
    
    /**
     * Converts the given int array to hex
     * will use all available bit of the int
     */
    public static String toHex(int[] data) {
        StringBuilder res = new StringBuilder();
        
        for(int i = 0; i < data.length; i++) {
            res.append(hexChars[(data[i] >> 28) & 0x0F]);
            res.append(hexChars[(data[i] >> 24) & 0x0F]);
            res.append(hexChars[(data[i] >> 20) & 0x0F]);
            res.append(hexChars[(data[i] >> 16) & 0x0F]);
            res.append(hexChars[(data[i] >> 12) & 0x0F]);
            res.append(hexChars[(data[i] >> 8) & 0x0F]);
            res.append(hexChars[(data[i] >> 4) & 0x0F]);
            res.append(hexChars[(data[i] >> 0) & 0x0F]);
        }
        return res.toString();
    }
}

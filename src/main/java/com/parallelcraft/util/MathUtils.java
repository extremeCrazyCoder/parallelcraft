package com.parallelcraft.util;

/**
 * Usefull math stuff
 * 
 * @author extremeCrazyCoder
 */
public class MathUtils {
    public static double limit(double input, double min, double max) {
        if(input < min) {
            return min;
        }
        
        if(input > max) {
            return max;
        }
        
        return input;
    }
    
    
    private static final byte DE_BRUJIN_32[] = {
        0,  9,  1, 10, 13, 21,  2, 29, 11, 14, 16, 18, 22, 25,  3, 30,
        8, 12, 20, 28, 15, 17, 24,  7, 19, 27, 23,  6, 26,  5,  4, 31
    };
    
    public static byte ceilLog2(int value) {
        // see https://stackoverflow.com/a/11398748
        value |= value >> 1;
        value |= value >> 2;
        value |= value >> 4;
        value |= value >> 8;
        value |= value >> 16;
        return DE_BRUJIN_32[(int) (value*0x07C4ACDD) >> 27];
    }
}

package com.parallelcraft.parallelcraft.user;

import com.parallelcraft.parallelcraft.util.BaseConversionHelper;

/**
 * Class for holding a UUID
 * 
 * @author extremeCrazyCoder
 */
public class UUID {
    
    public static UUID create(int[] pUUID) {
        return new UUID(pUUID);
    }
    
    
    private final int[] uuid;
    
    private UUID(int[] pUUID) {
        uuid = pUUID;
    }

    public int getInt(int i) {
        return uuid[i];
    }
    
    public String asHex() {
        return BaseConversionHelper.toHex(uuid);
    }
    
    @Override
    public String toString() {
        return asHex();
    }
}

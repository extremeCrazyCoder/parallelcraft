package com.parallelcraft.util;

import com.parallelcraft.nbt.NBTAble;
import java.util.Objects;

/**
 * Used to Store hirachical information about the settings
 * 
 * @author extremeCrazyCoder
 */
public class MinecraftKey implements NBTAble {
    private String path;

    public MinecraftKey(String path) {
        this.path = path;
    }
    
    public String asPacketString() {
        return path;
    }
    
    @Override
    public boolean equals(Object other) {
        if(other instanceof MinecraftKey) {
            return ((MinecraftKey) other).path.equals(path);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.path);
        return hash;
    }
    
    @Override
    public String toString() {
        return "MinecraftKey["
                + "path='" + path + "']";
    }

    @Override
    public String toNBTElement() {
        return path;
    }

    @Override
    public void notifyChange() {}
}

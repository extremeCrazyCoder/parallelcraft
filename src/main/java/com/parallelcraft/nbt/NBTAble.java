package com.parallelcraft.nbt;

/**
 * This class can be transformed into an NBTElement
 * 
 * @author extremeCrazyCoder
 */
public interface NBTAble {
    public Object toNBTElement();
    
    /**
     * This function will be called if something changes
     */
    public void notifyChange();
}

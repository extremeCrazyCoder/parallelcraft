package com.parallelcraft.world;

/**
 * This is one Inventory in the GameWorld
 * Can be:
 *  - Player
 *  - Chest
 *  - Ender
 *  - ...
 * 
 * @author extremeCrazyCoder
 */
public class Inventory {
    private short size;
    private ItemStack[] items;
    
    public Inventory(short size) {
        this.size = size;
        this.items = new ItemStack[size];
    }

    public short getSize() {
        return size;
    }

    public ItemStack[] getItems() {
        return items;
    }
    
    public void put(short index, ItemStack stack) {
        this.items[index] = stack;
    }

    @Override
    public String toString() {
        return "Inventory{" + "size=" + size + ", items=" + items + '}';
    }
}

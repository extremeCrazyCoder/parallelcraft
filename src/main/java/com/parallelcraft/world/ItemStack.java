package com.parallelcraft.world;

/**
 * A single ItemStack inside an Inventory
 *
 * @author extremeCrazyCoder
 */
public class ItemStack {
    //TODO rewrite this to be able to mix Items
    private BlockType type;
    private short amount;

    public ItemStack(BlockType type, short amount) {
        this.type = type;
        this.amount = amount;
    }

    public BlockType getType() {
        return type;
    }

    public short getAmount() {
        return amount;
    }
    
    public boolean isEmpty() {
        return amount == 0;
    }

    @Override
    public String toString() {
        return "ItemStack{" + "type=" + type + ", amount=" + amount + '}';
    }
}

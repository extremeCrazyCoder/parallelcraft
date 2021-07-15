package com.parallelcraft.world;

/**
 * This references a single Block inside the minecraft World
 * 
 * This is basically a wrapper around the EnumMaterial that adds some Functions
 * and maps it to a location
 * 
 * There should be specialisations of this for various types of blocks that need to have a state
 * e.g.
 *   a chest needs to have contents -> specialisation
 *   a water block / crop / ?? needs to tick -> specialisation with overridden tick method
 * //TODO think of a good way to implement event based ticking
 *  -> why tick redstone / hoppers / .. if nothing changes there
 */
public abstract class Block {
    BlockType type;
    BlockPosition pos;
    
    public Block(BlockType type, BlockPosition pos) {
        this.type = type;
        this.pos = pos;
    }
    
    public BlockPosition getPosition() {
        return pos;
    }

    public BlockType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Block["
                + "type='" + type + "', "
                + "pos='" + pos + "']";
    }
}

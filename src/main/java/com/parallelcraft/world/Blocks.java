package com.parallelcraft.world;

import com.parallelcraft.Datapack.DatapackHolder;

/**
 * Static references to the block type singletons
 * 
 * @author extremeCrazyCoder
 */
public class Blocks {
    public static BlockType AIR;
    public static BlockType STONE;
    
    static {
        DatapackHolder.registerLoadListener(() -> {
            DatapackHolder.reflectiveBoot(DatapackHolder.BLOCKS, Blocks.class);
            for(BlockType block : DatapackHolder.BLOCKS.values()) {
                block.afterBoot();
            }
        });
    }
}

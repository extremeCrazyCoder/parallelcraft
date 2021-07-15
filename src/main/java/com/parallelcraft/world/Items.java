package com.parallelcraft.world;

import com.parallelcraft.Datapack.DatapackHolder;

/**
 *
 * @author michi
 */
public class Items {
    
    static {
        DatapackHolder.registerLoadListener(() -> {
            DatapackHolder.reflectiveBoot(DatapackHolder.ITEMS, Items.class);
            for(ItemType item : DatapackHolder.ITEMS.values()) {
                item.afterBoot();
            }
        });
    }
}

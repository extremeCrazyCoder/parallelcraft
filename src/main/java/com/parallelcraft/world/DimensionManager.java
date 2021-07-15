package com.parallelcraft.world;

import com.parallelcraft.Datapack.DatapackHolder;
import com.parallelcraft.nbt.NBTCompoundElement;
import com.parallelcraft.util.MinecraftKey;

/**
 * Manages the available / loaded Dimensions
 * Manages also the available Biomes
 * 
 * @author extremeCrazyCoder
 */
public class DimensionManager {
    public static Dimension overworld;
    
    public static Dimension getOverworld() {
        if(overworld == null) {
            overworld = DatapackHolder.DIMENSIONS.byName(new MinecraftKey("overworld"));
        }
        return overworld;
    }
    
    private static NBTCompoundElement cache = null;
    public static NBTCompoundElement getElm() {
        if(cache == null) {
            cache = DatapackHolder.DIMENSIONS.toNBTElement().merge(DatapackHolder.BIOMES.toNBTElement());
        }
        return cache;
    }
}

package com.parallelcraft.world.biome;

import com.parallelcraft.nbt.NBTAble;
import com.parallelcraft.nbt.NBTCompoundElement;
import com.parallelcraft.util.MinecraftKey;
import org.json.JSONObject;

/**
 * This should be a one to one relation with biomes / biomeSettings
 * 
 * @author extremeCrazyCoder
 */
public class BiomeCaveSoundEffectSettings implements NBTAble {
    private double offset;
    private int tickDelay;
    private MinecraftKey sound;
    private int blockSearchExtent;
    
    public BiomeCaveSoundEffectSettings(JSONObject obj) {
        this.tickDelay = obj.getInt("tick_delay");
        this.offset = obj.getFloat("offset");
        this.sound = new MinecraftKey(obj.getString("sound"));
        this.blockSearchExtent = obj.getInt("block_search_extent");
    }
    
    private NBTCompoundElement cache = null;
    
    @Override
    public NBTCompoundElement toNBTElement() {
        if(cache == null) {
            cache = new NBTCompoundElement()
                .put("offset", offset)
                .put("tick_delay", tickDelay)
                .put("sound", sound)
                .put("block_search_extent", blockSearchExtent);
        }
        return cache;
    }
    
    @Override
    public void notifyChange() {
        cache = null;
    }
}

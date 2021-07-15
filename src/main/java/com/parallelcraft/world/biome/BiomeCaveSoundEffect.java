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
public class BiomeCaveSoundEffect implements NBTAble {
    private float tickChance;
    private MinecraftKey sound;
    
    public BiomeCaveSoundEffect(JSONObject obj) {
        this.tickChance = obj.getFloat("tick_chance");
        this.sound = new MinecraftKey(obj.getString("sound"));
    }
    
    private NBTCompoundElement cache = null;
    
    @Override
    public NBTCompoundElement toNBTElement() {
        if(cache == null) {
            cache = new NBTCompoundElement()
                .put("tick_chance", tickChance)
                .put("sound", sound);
        }
        return cache;
    }
    
    @Override
    public void notifyChange() {
        cache = null;
    }
}

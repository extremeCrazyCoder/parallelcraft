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
public class BiomeMusic implements NBTAble {
    private boolean replaceCurMusic;
    private int maxDelay;
    private int minDelay;
    private MinecraftKey sound;
    
    public BiomeMusic(JSONObject obj) {
        this.replaceCurMusic = obj.getBoolean("replace_current_music");
        this.maxDelay = obj.getInt("max_delay");
        this.minDelay = obj.getInt("min_delay");
        this.sound = new MinecraftKey(obj.getString("sound"));
    }
    
    private NBTCompoundElement cache = null;
    
    @Override
    public NBTCompoundElement toNBTElement() {
        if(cache == null) {
            cache = new NBTCompoundElement()
                .put("replace_current_music", replaceCurMusic)
                .put("max_delay", maxDelay)
                .put("min_delay", minDelay)
                .put("sound", sound);
        }
        return cache;
    }
    
    @Override
    public void notifyChange() {
        cache = null;
    }
}

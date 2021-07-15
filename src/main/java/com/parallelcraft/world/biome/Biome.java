package com.parallelcraft.world.biome;

import com.parallelcraft.constants.EnumGeography;
import com.parallelcraft.constants.EnumPrecipitation;
import com.parallelcraft.nbt.NBTAble;
import com.parallelcraft.nbt.NBTCompoundElement;
import com.parallelcraft.util.MinecraftKey;
import org.json.JSONObject;

/**
 * One available biome 
 * 
 * @author extremeCrazyCoder
 */
public class Biome implements NBTAble {
    private EnumPrecipitation precipitation;
    private float depth;
    private float temperature;
    private float scale;
    private float downfall;
    private EnumGeography category;
    private BiomeEffects effects;
    
    
    public Biome(JSONObject obj) {
        this.precipitation = EnumPrecipitation.valueOf(obj.getString("precipitation").toUpperCase());
        this.depth = obj.getFloat("depth");
        this.temperature = obj.getFloat("temperature");
        this.scale = obj.getFloat("scale");
        this.downfall = obj.getFloat("downfall");
        this.category = EnumGeography.valueOf(obj.getString("category").toUpperCase());
        this.effects = new BiomeEffects(obj.getJSONObject("effects"));
    }
    
    private NBTCompoundElement cache = null;
    
    @Override
    public NBTCompoundElement toNBTElement() {
        if(cache == null) {
            cache = new NBTCompoundElement()
                .put("precipitation", precipitation)
                .put("effects", effects)
                .put("depth", depth)
                .put("temperature", temperature)
                .put("scale", scale)
                .put("downfall", downfall)
                .put("category", category);
        }
        return cache;
    }
    
    @Override
    public void notifyChange() {
        cache = null;
    }
}

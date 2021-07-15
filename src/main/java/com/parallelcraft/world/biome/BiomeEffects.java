package com.parallelcraft.world.biome;

import com.parallelcraft.nbt.NBTAble;
import com.parallelcraft.nbt.NBTCompoundElement;
import com.parallelcraft.util.MinecraftKey;
import org.json.JSONObject;

/**
 * The settings of the effects of one Biome
 * This should be a one to one relation with biomes / biomeSettings
 * 
 * @author extremeCrazyCoder
 */
public class BiomeEffects implements NBTAble {
    private int skyColor;
    private Integer grassColor = null;
    private Integer foliageColor = null;
    private int waterFogColor;
    private int waterColor;
    private int fogColor;
    
    private BiomeParticleEffect particles = null;
    private MinecraftKey sound = null;
    private BiomeCaveSoundEffectSettings caveSoundEffectSettings = null;
    private BiomeCaveSoundEffect caveSoundEffect = null;
    private BiomeMusic music = null;

    
    public BiomeEffects(JSONObject obj) {
        this.skyColor = obj.getInt("sky_color");
        if(obj.has("grass_color")) this.grassColor = obj.getInt("grass_color");
        if(obj.has("foliage_color")) this.foliageColor = obj.getInt("foliage_color");
        this.waterFogColor = obj.getInt("water_fog_color");
        this.fogColor = obj.getInt("fog_color");
        this.waterColor = obj.getInt("water_color");
        
        JSONObject tmp = obj.optJSONObject("particle");
        if(tmp != null) this.particles = new BiomeParticleEffect(tmp);
        if(obj.has("ambient_sound")) this.sound = new MinecraftKey(obj.getString("ambient_sound"));
        tmp = obj.optJSONObject("mood_sound");
        if(tmp != null) this.caveSoundEffectSettings = new BiomeCaveSoundEffectSettings(tmp);
        tmp = obj.optJSONObject("additions_sound");
        if(tmp != null) this.caveSoundEffect = new BiomeCaveSoundEffect(tmp);
        tmp = obj.optJSONObject("music");
        if(tmp != null) this.music = new BiomeMusic(tmp);
    }
    
    private NBTCompoundElement cache = null;
    
    @Override
    public NBTCompoundElement toNBTElement() {
        if(cache == null) {
            cache = new NBTCompoundElement()
                .put("sky_color", skyColor)
                .put("grass_color", grassColor)
                .put("foliage_color", foliageColor)
                .put("water_fog_color", waterFogColor)
                .put("water_color", waterColor)
                .put("fog_color", fogColor)
                .put("particle", particles)
                .put("ambient_sound", sound)
                .put("mood_sound", caveSoundEffectSettings)
                .put("additions_sound", caveSoundEffect)
                .put("music", music);
        }
        return cache;
    }
    
    @Override
    public void notifyChange() {
        cache = null;
    }
}

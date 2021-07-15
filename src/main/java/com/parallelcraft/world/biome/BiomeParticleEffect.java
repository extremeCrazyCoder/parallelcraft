package com.parallelcraft.world.biome;

import com.parallelcraft.Datapack.DatapackHolder;
import com.parallelcraft.nbt.NBTAble;
import com.parallelcraft.nbt.NBTCompoundElement;
import org.json.JSONObject;

/**
 * This should be a one to one relation with biomes / biomeSettings
 * 
 * @author extremeCrazyCoder
 */
public class BiomeParticleEffect implements NBTAble {
    private float probability;
    private int particle;
    
    public BiomeParticleEffect(JSONObject obj) {
        this.probability = obj.getFloat("probability");
        this.particle = obj.getInt("id");
    }
    
    private NBTCompoundElement cache = null;
    
    @Override
    public NBTCompoundElement toNBTElement() {
        if(cache == null) {
            cache = new NBTCompoundElement()
                .put("probability", probability)
                .put("options", new NBTCompoundElement()
                    .put("type", DatapackHolder.PARTICLES.nameById(particle))
                );
        }
        return cache;
    }
    
    @Override
    public void notifyChange() {
        cache = null;
    }
}

package com.parallelcraft.world;

import com.parallelcraft.nbt.NBTAble;
import com.parallelcraft.nbt.NBTCompoundElement;
import com.parallelcraft.util.MinecraftKey;
import org.json.JSONObject;

/**
 * Holds all relevant information about a loaded dimension
 * 
 * @author extremeCrazyCoder
 */
public class Dimension implements NBTAble {
    private DimensionSettings settings;
    
    private int numSections;

    public Dimension(boolean piglinSafe, boolean natural, float ambientLight, MinecraftKey infiniburn,
            boolean respawnAnchorWorks, boolean hasSkylight, boolean bedWorks, MinecraftKey effects, boolean hasRaids,
            float coordinateScale, int logicalHeight, int min_y, boolean hasCeiling, boolean ultrawarm, int height) {
        this.settings = new DimensionSettings(piglinSafe, natural, ambientLight, infiniburn, respawnAnchorWorks,
                hasSkylight, bedWorks, effects, hasRaids, coordinateScale, logicalHeight, min_y, hasCeiling, ultrawarm, height);
        
        numSections = height / ChunkSection.SECTION_SIZE;
        
        if(height % ChunkSection.SECTION_SIZE != 0) {
            throw new IllegalArgumentException("Height needs to be multiple of 16");
        }
    }

    public Dimension(JSONObject obj) {
        this.settings = new DimensionSettings(obj);
        
        numSections = this.settings.getHeight() / ChunkSection.SECTION_SIZE;
        
        if(this.settings.getHeight() % ChunkSection.SECTION_SIZE != 0) {
            throw new IllegalArgumentException("Height needs to be multiple of 16");
        }
    }
    
    public DimensionSettings getSettings() {
        return settings;
    }
    
    public int getHeightSectionCount() {
        return numSections;
    }
    
    
    @Override
    public NBTCompoundElement toNBTElement() {
        return settings.toNBTElement();
    }
    
    @Override
    public void notifyChange() {
    }
    
    @Override
    public String toString() {
        return "Dimension["
                + "settings='" + settings + "']";
    }
}

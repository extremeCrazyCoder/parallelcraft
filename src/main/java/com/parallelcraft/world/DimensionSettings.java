package com.parallelcraft.world;

import com.parallelcraft.nbt.NBTAble;
import com.parallelcraft.nbt.NBTCompoundElement;
import com.parallelcraft.util.MinecraftKey;
import org.json.JSONObject;

/**
 * Holds the settings of a Dimension
 * 
 * @author extremeCrazyCoder
 */
public class DimensionSettings implements NBTAble {
    private boolean piglinSafe;
    private boolean natural;
    private float ambientLight;
    private MinecraftKey infiniburn;
    private boolean respawnAnchorWorks;
    private boolean hasSkylight;
    private boolean bedWorks;
    private MinecraftKey effects;
    private boolean hasRaids;
    private float coordinateScale;
    private int logicalHeight;
    private int min_y;
    private boolean hasCeiling;
    private boolean ultrawarm;
    private int height;

    public DimensionSettings(boolean piglinSafe, boolean natural, float ambientLight, MinecraftKey infiniburn,
            boolean respawnAnchorWorks, boolean hasSkylight, boolean bedWorks, MinecraftKey effects, boolean hasRaids,
            float coordinateScale, int logicalHeight, int min_y, boolean hasCeiling, boolean ultrawarm, int height) {
        this.piglinSafe = piglinSafe;
        this.natural = natural;
        this.ambientLight = ambientLight;
        this.infiniburn = infiniburn;
        this.respawnAnchorWorks = respawnAnchorWorks;
        this.hasSkylight = hasSkylight;
        this.bedWorks = bedWorks;
        this.effects = effects;
        this.hasRaids = hasRaids;
        this.coordinateScale = coordinateScale;
        this.logicalHeight = logicalHeight;
        this.min_y = min_y;
        this.hasCeiling = hasCeiling;
        this.ultrawarm = ultrawarm;
        this.height = height;
    }
    
    public DimensionSettings(JSONObject obj) {
        this.piglinSafe = obj.getBoolean("piglin_safe");
        this.natural = obj.getBoolean("natural");
        this.ambientLight = obj.getFloat("ambient_light");
        this.infiniburn = new MinecraftKey(obj.getString("infiniburn"));
        this.respawnAnchorWorks = obj.getBoolean("respawn_anchor_works");
        this.hasSkylight = obj.getBoolean("has_skylight");
        this.bedWorks = obj.getBoolean("bed_works");
        this.effects = new MinecraftKey(obj.getString("effects"));
        this.hasRaids = obj.getBoolean("has_raids");
        this.coordinateScale = obj.getFloat("coordinate_scale");
        this.logicalHeight = obj.getInt("logical_height");
        this.min_y = obj.getInt("min_y");
        this.hasCeiling = obj.getBoolean("has_ceiling");
        this.ultrawarm = obj.getBoolean("ultrawarm");
        this.height = obj.getInt("height");
    }

    public boolean isPiglinSafe() {
        return piglinSafe;
    }

    public boolean isNatural() {
        return natural;
    }

    public float getAmbientLight() {
        return ambientLight;
    }

    public MinecraftKey getInfiniburn() {
        return infiniburn;
    }

    public boolean isRespawnAnchorWorks() {
        return respawnAnchorWorks;
    }

    public boolean isHasSkylight() {
        return hasSkylight;
    }

    public boolean isBedWorks() {
        return bedWorks;
    }

    public MinecraftKey getEffects() {
        return effects;
    }

    public boolean isHasRaids() {
        return hasRaids;
    }

    public float getCoordinateScale() {
        return coordinateScale;
    }

    public int getLogicalHeight() {
        return logicalHeight;
    }

    public int getMin_y() {
        return min_y;
    }

    public boolean isHasCeiling() {
        return hasCeiling;
    }

    public boolean isUltrawarm() {
        return ultrawarm;
    }

    public int getHeight() {
        return height;
    }

    public NBTCompoundElement getCache() {
        return cache;
    }
    
    
    
    private NBTCompoundElement cache = null;
    
    @Override
    public NBTCompoundElement toNBTElement() {
        if(cache == null) {
            cache = new NBTCompoundElement()
                .put("piglin_safe", piglinSafe)
                .put("natural", natural)
                .put("ambient_light", ambientLight)
                .put("infiniburn", infiniburn)
                .put("respawn_anchor_works", respawnAnchorWorks)
                .put("has_skylight", hasSkylight)
                .put("bed_works", bedWorks)
                .put("effects", effects)
                .put("has_raids", hasSkylight)
                .put("coordinate_scale", coordinateScale)
                .put("logical_height", logicalHeight)
                .put("min_y", min_y)
                .put("has_ceiling", hasCeiling)
                .put("ultrawarm", ultrawarm)
                .put("height", height);
        }
        return cache;
    }
    
    @Override
    public void notifyChange() {
        cache = null;
    }
    
    @Override
    public String toString() {
        return "DimensionSettings["
                + "piglinSafe='" + piglinSafe + "', "
                + "natural='" + natural + "', "
                + "ambientLight='" + ambientLight + "', "
                + "infiniburn='" + infiniburn + "', "
                + "respawnAnchorWorks='" + respawnAnchorWorks + "', "
                + "hasSkylight='" + hasSkylight + "', "
                + "bedWorks='" + bedWorks + "', "
                + "effects='" + effects + "', "
                + "hasRaids='" + hasRaids + "', "
                + "coordinateScale='" + coordinateScale + "', "
                + "logicalHeight='" + logicalHeight + "', "
                + "min_y='" + min_y + "', "
                + "hasCeiling='" + hasCeiling + "', "
                + "ultrawarm='" + ultrawarm + "', "
                + "height='" + height + "']";
    }
}

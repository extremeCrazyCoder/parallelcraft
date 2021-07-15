package com.parallelcraft.Datapack;

import com.parallelcraft.util.MinecraftKey;

/**
 * A key consists of ID and MinecraftKey
 *
 * @author extremeCrazyCoder
 */
public class DatapackKey {
    private int id;
    private MinecraftKey mcKey;

    public DatapackKey(int id, MinecraftKey mcKey) {
        this.id = id;
        this.mcKey = mcKey;
    }

    public int getId() {
        return id;
    }

    public MinecraftKey getName() {
        return mcKey;
    }
    
    @Override
    public String toString() {
        return "DatapackKey["
                + "id='" + id + "', "
                + "mcKey='" + mcKey.toString() + "']";
    }
}

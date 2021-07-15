package com.parallelcraft.Datapack;

import com.parallelcraft.nbt.NBTAble;
import com.parallelcraft.nbt.NBTCompoundElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Stores the Instance -> id mappings for one type of the Datapack
 *
 * @author extremeCrazyCoder
 */
public class DatapackRegistryNBTAble<T extends NBTAble> extends DatapackRegistry<T> implements NBTAble {
    
    public DatapackRegistryNBTAble(Class<T> cls, String packPath) {
        super(cls, packPath);
    }

    private NBTCompoundElement cache = null;
    
    @Override
    public NBTCompoundElement toNBTElement() {
        if(cache == null) {
            List<NBTCompoundElement> contents = new ArrayList<>();
            for(Map.Entry<T, DatapackKey> entry : entrySet()) {
                contents.add(new NBTCompoundElement()
                    .put("id", entry.getValue().getId())
                    .put("name", entry.getValue().getName())
                    .put("element", entry.getKey())
                );
            }
            
            cache = new NBTCompoundElement()
                .put(packPath, new NBTCompoundElement()
                    .put("type", packPath)
                    .put("value", contents)
                );
        }
        return cache;
    }
    
    @Override
    public void notifyChange() {
        cache = null;
    }
}

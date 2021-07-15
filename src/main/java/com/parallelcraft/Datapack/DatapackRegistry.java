package com.parallelcraft.Datapack;

import com.parallelcraft.util.MinecraftKey;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

/**
 * Stores the Instance -> id mappings for one type of the Datapack
 *
 * @author extremeCrazyCoder
 */
public class DatapackRegistry<T> {
    private Logger logger = LogManager.getLogger("DatapackRegistry");
    
    protected String packPath;
    private Class<T> cls;
    private boolean loaded = false;
    
    Map<T, DatapackKey> contents = new HashMap<>();
    Map<MinecraftKey, T> namedHash = new HashMap<>();
    MinecraftKey name[];
    Object data[];
    
    public DatapackRegistry(Class<T> cls, String packPath) {
        this.packPath = packPath;
        this.cls = cls;
    }
    
    public void load(String datapack) {
        try {
            Constructor<T> supplier = cls.getConstructor(JSONObject.class);
            Method idMe = null, nameMe = null;
            try {
                idMe = cls.getDeclaredMethod("setID", int.class);
                idMe.setAccessible(true);
            } catch(NoSuchMethodException ignored) {}
            try {
                nameMe = cls.getDeclaredMethod("setName", MinecraftKey.class);
                nameMe.setAccessible(true);
            } catch(NoSuchMethodException ignored) {}
            Method idMeFin = idMe, nameMeFin = nameMe;

            Datapack.forEachJSONContent(datapack, packPath, (name, obj) -> {
                try {
                    MinecraftKey nameKey = new MinecraftKey(obj.getString("name"));
                    int id = obj.getInt("id");
                    T newInst = supplier.newInstance(obj.getJSONObject("element"));
                    
                    contents.put(newInst, new DatapackKey(id, nameKey));
                    namedHash.put(nameKey, newInst);
                    
                    if(idMeFin != null) idMeFin.invoke(newInst, id);
                    if(nameMeFin != null) nameMeFin.invoke(newInst, nameKey);
                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    throw new RuntimeException(packPath + " Unable to load registry", ex);
                }
            });
            
            int maxId = contents.entrySet().stream().mapToInt((value) -> {
                return value.getValue().getId();
            }).max().getAsInt();
            
            data = new Object[maxId + 1];
            name = new MinecraftKey[maxId + 1];
            
            for(Map.Entry<T, DatapackKey> entry : contents.entrySet()) {
                data[entry.getValue().getId()] = entry.getKey();
                name[entry.getValue().getId()] = entry.getValue().getName();
            }
        } catch(NoSuchMethodException ex) {
            throw new RuntimeException(packPath + " Unable to load registry constructor(JSONObject) not found", ex);
        }
        
        logger.debug("loaded {} entries for {}", contents.size(), packPath);
        loaded = true;
    }
    
    public Set<Map.Entry<T, DatapackKey>> entrySet() {
        return contents.entrySet();
    }
    
    public Set<T> values() {
        return contents.keySet();
    }
    
    public Set<MinecraftKey> names() {
        return namedHash.keySet();
    }
    
    public MinecraftKey getName(T searchFor) {
        return contents.get(searchFor).getName();
    }
    
    public T byName(MinecraftKey data) {
        return namedHash.get(data);
    }

    public MinecraftKey nameById(int id) {
        return name[id];
    }

    public T byID(int id) {
        return (T) data[id];
    }
    
    @Override
    public String toString() {
        return "DatapackRegistry["
                + "packPath='" + packPath + "', "
                + "class='" + cls.getCanonicalName() + "']";
    }
}

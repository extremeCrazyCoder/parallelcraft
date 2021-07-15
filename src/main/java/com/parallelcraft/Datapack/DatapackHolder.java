package com.parallelcraft.Datapack;

import com.parallelcraft.nbt.NBTAble;
import com.parallelcraft.util.MinecraftKey;
import com.parallelcraft.world.BlockType;
import com.parallelcraft.world.Dimension;
import com.parallelcraft.world.ItemType;
import com.parallelcraft.world.Particle;
import com.parallelcraft.world.biome.Biome;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Stores the Instance -> id mappings for the Datapack
 *
 * @author extremeCrazyCoder
 */
public class DatapackHolder {
    private static Logger logger = LogManager.getLogger("ClientConnectionHandler");
    private static List<DatapackRegistry<?>> ALL_REGISTRIES = new ArrayList<>();
    private static List<Runnable> LOAD_LISTENERS = new ArrayList<>();
    
    public static DatapackRegistryNBTAble<Biome> BIOMES = createNBT(Biome.class, "worldgen/biome");
    public static DatapackRegistryNBTAble<Dimension> DIMENSIONS = createNBT(Dimension.class, "dimension_type");
    public static DatapackRegistry<Particle> PARTICLES = create(Particle.class, "world/particles");
    public static DatapackRegistry<BlockType> BLOCKS = create(BlockType.class, "world/blocks");
    public static DatapackRegistry<ItemType> ITEMS = create(ItemType.class, "world/items");
    
    
    private static <T> DatapackRegistry<T> create(Class<T> cls, String packPath) {
        DatapackRegistry<T> created = new DatapackRegistry<>(cls, packPath);
        ALL_REGISTRIES.add(created);
        return created;
    }
    
    private static <T extends NBTAble> DatapackRegistryNBTAble<T> createNBT(Class<T> cls, String packPath) {
        DatapackRegistryNBTAble<T> created = new DatapackRegistryNBTAble<>(cls, packPath);
        ALL_REGISTRIES.add(created);
        return created;
    }
    
    public static void loadAll(String datapack) {
        for(DatapackRegistry<?> registry : ALL_REGISTRIES) {
            registry.load(datapack);
        }
        
        for(Runnable listener : LOAD_LISTENERS) {
            listener.run();
        }
    }
    
    public static void registerLoadListener(Runnable toRegister) {
        LOAD_LISTENERS.add(toRegister);
    }
    
    /**
     * Uses reflection in order to fill the class with data from the registry
     * @param registry
     * @param toFill 
     */
    public static void reflectiveBoot(DatapackRegistry<?> registry, Class<?> toFill) {
        try {
            for(Field f : toFill.getFields()) {
                Object val = registry.byName(new MinecraftKey(f.getName().toLowerCase()));
                if(val == null) {
                    throw new IllegalArgumentException("Unable to find name:" + f.getName() + " in " + registry);
                }
                f.set(null, val);
            }
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            logger.fatal("Unable to boot {} with {}", toFill.getCanonicalName(), registry.packPath);
            throw new RuntimeException(ex);
        }
    }
}

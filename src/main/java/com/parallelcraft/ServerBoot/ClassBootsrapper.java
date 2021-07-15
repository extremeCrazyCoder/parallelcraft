package com.parallelcraft.ServerBoot;

import com.parallelcraft.world.Blocks;
import com.parallelcraft.world.DimensionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Inits all static stuff
 * 
 * @author extremeCrazyCoder
 */
public class ClassBootsrapper {
    private static Logger logger = LogManager.getLogger("ClassBootsrapper");
    
    public static void boot() {
        try {
            Class.forName(Blocks.class.getCanonicalName());
            Class.forName(DimensionManager.class.getCanonicalName());
        } catch (ClassNotFoundException ex) {
            logger.fatal("Unable too boot Classes", ex);
            throw new RuntimeException(ex);
        }
    }
}

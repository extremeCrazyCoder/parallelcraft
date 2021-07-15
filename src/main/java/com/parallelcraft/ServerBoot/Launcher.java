package com.parallelcraft.ServerBoot;

import com.parallelcraft.Networking.ServerPortManager;
import com.parallelcraft.Datapack.Datapack;
import com.parallelcraft.Datapack.DatapackHolder;
import com.parallelcraft.world.Blocks;

/**
 * This should be the main Launcher for the server
 * Boots all Services
 * 
 * @author extremeCrazyCoder
 */
public class Launcher {
    public static void main(String[] args) {
        ClassBootsrapper.boot();
        DatapackHolder.loadAll(Datapack.DEFAULT_DATAPACK);
        ServerPortManager.getSingleton().boot("localhost", 25565);
    }
}

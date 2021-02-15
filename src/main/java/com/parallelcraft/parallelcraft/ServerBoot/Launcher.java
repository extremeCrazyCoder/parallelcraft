package com.parallelcraft.parallelcraft.ServerBoot;

import com.parallelcraft.parallelcraft.Networking.ServerPortManager;

/**
 * This should be the main Launcher for the server
 * Boots all Services
 * 
 * @author extremeCrazyCoder
 */
public class Launcher {
    public static void main(String[] args) {
        ServerPortManager.getSingleton().boot("localhost", 25565);
    }
}

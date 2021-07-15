package com.parallelcraft.world;

/**
 * The coordinates of a chunk in the game world
 * 
 * @author extremeCrazyCoder
 */
public class ChunkCoordinates {
    private int posX;
    private int posZ;
    
    public ChunkCoordinates(int posX, int posZ) {
        this.posX = posX;
        this.posZ = posZ;
    }
    
    public int getX() {
        return posX;
    }
    
    public int getZ() {
        return posZ;
    }

    @Override
    public String toString() {
        return "ChunkCoordinates{" + "posX=" + posX + ", posZ=" + posZ + '}';
    }
}

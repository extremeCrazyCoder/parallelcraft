package com.parallelcraft.world;

/**
 * This class contains a position of a single Block
 * 
 */
//TODO think if we should do this world bound?? (have world ref in here)
public class BlockPosition {
    private static final byte BITS_X = 26;
    private static final byte BITS_Z = 26;
    private static final byte BITS_Y = 64 - BITS_X - BITS_Z;
    private static final long maxX = (1L << BITS_X) - 1;
    private static final long maxY = (1L << BITS_Y) - 1;
    private static final long maxZ = (1L << BITS_Z) - 1;
    
    private int x;
    private int y;
    private int z;
    
    public BlockPosition(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    /**
     * creates a block position from its long representation
     */
    public BlockPosition(long data) {
        this.y = (int) (data & maxY);
        data = data >> BITS_Y;
        this.z = (int) (data & maxZ);
        data = data >> BITS_Z;
        this.x = (int) (data & maxX);
    }
    
    public long asLong() {
        long retval = 0;
        retval = (retval << BITS_X) + (this.x & maxX);
        retval = (retval << BITS_Z) + (this.z & maxZ);
        retval = (retval << BITS_Y) + (this.y & maxY);
        return retval;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "BlockPosition{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
    }
}

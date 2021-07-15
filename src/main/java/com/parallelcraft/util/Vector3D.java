package com.parallelcraft.util;

/**
 * Class to store a Vector
 *
 * @author extremeCrazyCoder
 */
public class Vector3D {
    private float x;
    private float y;
    private float z;

    public Vector3D(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }
    
    @Override
    public String toString() {
        return "GameProfile["
                + "x='" + x + "', "
                + "y='" + y + "', "
                + "z='" + z + "']";
    }
}

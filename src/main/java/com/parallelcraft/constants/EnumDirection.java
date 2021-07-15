package com.parallelcraft.constants;

/**
 * Enum of the directions
 * 
 * @author extremeCrazyCoder
 */
public enum EnumDirection {
    DOWN(0, 1, -1),
    UP(1, 0, -1),
    NORTH(2, 3, 0),
    SOUTH(3, 2, 1),
    WEST(4, 5, 2),
    EAST(5, 4, 3);
    
    
    private byte id;
    private byte opposite;
    private byte flatID;
    
    private EnumDirection(int id, int opposite, int flatID) {
        this.id = (byte) id;
        this.opposite = (byte) opposite;
        this.flatID = (byte) flatID;
    }
    
    public byte getID() {
        return id;
    }
    
    public byte getOpposite() {
        return opposite;
    }
    
    public byte getFlatID() {
        return flatID;
    }
}

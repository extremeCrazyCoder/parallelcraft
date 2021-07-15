package com.parallelcraft.constants;

/**
 * List of possible animations
 * 
 * @author extremeCrazyCoder
 */
public enum EnumAnimation {
    SWING_HAND_SECOND((byte) 0),
    WAKEUP((byte) 2),
    SWING_HAND_MAIN((byte) 3),
    DAMAGE_TAKEN_CRIT((byte) 4), //TODO check this
    DAMAGE_TAKEN((byte) 5); //TODO check this
    
    private final byte animationID;
    private EnumAnimation(byte id) {
        animationID = id;
    }
    
    public byte getAnimationId() {
        return animationID;
    }
}

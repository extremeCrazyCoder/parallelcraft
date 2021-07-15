package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutAbilities extends PacketOutgoing {
    //TODO use this
    private boolean instabuild;
    private boolean canFly;
    private boolean isFlying;
    private boolean invulnerable;
    private float flyingSpeed;
    private float walkingSpeed;
    
    public PacketPlayOutAbilities(boolean instabuild, boolean canFly, boolean isFlying, boolean invulnerable,
            float flyingSpeed, float walkingSpeed) {
        this.instabuild = instabuild;
        this.canFly = canFly;
        this.isFlying = isFlying;
        this.invulnerable = invulnerable;
        this.flyingSpeed = flyingSpeed;
        this.walkingSpeed = walkingSpeed;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        byte bDat = 0;
        bDat = (byte) ((bDat << 1) + (instabuild? 1 : 0));
        bDat = (byte) ((bDat << 1) + (canFly? 1 : 0));
        bDat = (byte) ((bDat << 1) + (isFlying? 1 : 0));
        bDat = (byte) ((bDat << 1) + (invulnerable? 1 : 0));
        data.writeByte(bDat);
        data.writeFloat(this.flyingSpeed);
        data.writeFloat(this.walkingSpeed);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutAbilities["
                + "instabuild='" + instabuild + "', "
                + "canFly='" + canFly + "', "
                + "isFlying='" + isFlying + "', "
                + "invulnerable='" + invulnerable + "', "
                + "flyingSpeed='" + flyingSpeed + "', "
                + "walkingSpeed='" + walkingSpeed + "']";
    }
}

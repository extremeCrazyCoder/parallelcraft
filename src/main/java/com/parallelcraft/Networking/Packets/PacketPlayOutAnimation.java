package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.constants.EnumAnimation;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutAnimation  extends PacketOutgoing {
    //TODO use this
    private int entityId;
    private byte animationId;
    
    public PacketPlayOutAnimation(int entityId, EnumAnimation animationId) {
        this.entityId = entityId;
        this.animationId = animationId.getAnimationId();
    }
    
    public PacketPlayOutAnimation(int entityId, byte animationId) {
        this.entityId = entityId;
        this.animationId = animationId;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeVarLenInteger(entityId);
        data.writeByte(animationId);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutAnimation["
                + "entityId='" + entityId + "', "
                + "animationId='" + animationId + "']";
    }
}

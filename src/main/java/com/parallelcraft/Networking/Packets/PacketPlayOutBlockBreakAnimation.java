package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.world.BlockPosition;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutBlockBreakAnimation  extends PacketOutgoing {
    //TODO use this
    private int breakingEntityId;
    private BlockPosition animationAt;
    //TODO check this
    private byte breakProgress; //range maybe 0 to 10
    
    public PacketPlayOutBlockBreakAnimation(int breakingEntityId, BlockPosition animationAt, byte breakProgress) {
        this.breakingEntityId = breakingEntityId;
        this.animationAt = animationAt;
        this.breakProgress = breakProgress;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeVarLenInteger(breakingEntityId);
        data.writeBlockPosition(animationAt);
        data.writeByte(breakProgress);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutBlockBreakAnimation["
                + "breakingEntityId='" + breakingEntityId + "', "
                + "animationAt='" + animationAt.toString() + "', "
                + "breakProgress='" + breakProgress + "']";
    }
}

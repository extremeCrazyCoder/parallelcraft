package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.world.BlockPosition;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutAddVibrationSignalPacket extends PacketOutgoing {
    //TODO use this
    private BlockPosition startPos;
    private BlockPosition targetPos;
    private int arrivalInTicks;
    
    public PacketPlayOutAddVibrationSignalPacket(BlockPosition startPos, BlockPosition targetPos, int arrivalInTicks) {
        this.startPos = startPos;
        this.targetPos = targetPos;
        this.arrivalInTicks = arrivalInTicks;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeBlockPosition(startPos);
        data.writeBlockPosition(targetPos);
        data.writeVarLenInteger(arrivalInTicks);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutAddVibrationSignalPacket["
                + "startPos='" + startPos + "', "
                + "targetPos='" + targetPos + "', "
                + "arrivalInTicks='" + arrivalInTicks + "']";
    }
}

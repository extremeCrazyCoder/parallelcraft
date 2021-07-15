package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.world.Block;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutBlockAction  extends PacketOutgoing {
    //TODO use this
    private Block animated;
    private byte animationID; 
    private byte aditionalInfo;
    
    /**
     * Note: 0, 0
     * Piston {0: extend, 1: ??, 2: ??}, direction
     * Bell 1, direction
     * Chest 1, viewingCount = num Players looking currently into
     * EnderChest 1, viewingCount = num Players looking currently into
     * EndGateway 1, 0
     * MobSpawner 1, 0
     * ShulkerBox 1, viewingCount = num Players looking currently into
     */
    public PacketPlayOutBlockAction(Block animated, byte animationID, byte aditionalInfo) {
        this.animated = animated;
        this.animationID = animationID;
        this.aditionalInfo = aditionalInfo;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeBlockPosition(animated.getPosition());
        data.writeByte(animationID);
        data.writeByte(aditionalInfo);
        data.writeVarLenInteger(animated.getType().getDatabaseID());
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutBlockAction["
                + "animated='" + animated.toString() + "', "
                + "animationID='" + animationID + "', "
                + "aditionalInfo='" + aditionalInfo + "']";
    }
}

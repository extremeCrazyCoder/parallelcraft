package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.constants.EnumGamemode;
import com.parallelcraft.world.Dimension;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutRespawn extends PacketOutgoing {
    //TODO use this
    private Dimension dimension;
    private long seed; //is generated somewhat from the server seed replace with random value wtf mc biomeZoomSeed
    private EnumGamemode gameType;
    private EnumGamemode previousGameType;
    private boolean isDebug;
    private boolean isFlat;
    private boolean keepAllPlayerData;
    
    //TODO TBD
    public PacketPlayOutRespawn() {
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        //TODO encoding
//        data.writeMCKey(dimension.getKey());
//        dimension.writeAsPacket(data);
//        data.writeLong(this.seed);
//        data.writeEnumAsByte(gameType);
//        data.writeEnumAsByte(previousGameType);
//        data.writeBoolean(this.isDebug);
//        data.writeBoolean(this.isFlat);
//        data.writeBoolean(this.keepAllPlayerData);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutRespawn["
                + "dimension='" + dimension + "', "
                + "seed='" + seed + "', "
                + "gameType='" + gameType + "', "
                + "previousGameType='" + previousGameType + "', "
                + "isDebug='" + isDebug + "', "
                + "isFlat='" + isFlat + "', "
                + "keepAllPlayerData='" + keepAllPlayerData + "']";
    }
}

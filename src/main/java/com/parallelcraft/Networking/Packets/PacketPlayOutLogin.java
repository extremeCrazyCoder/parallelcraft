package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Datapack.DatapackHolder;
import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.constants.EnumGamemode;
import com.parallelcraft.nbt.NBTCoder;
import com.parallelcraft.util.MinecraftKey;
import com.parallelcraft.world.Dimension;
import com.parallelcraft.world.DimensionManager;
import java.util.Set;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutLogin extends PacketOutgoing {
    //TODO use this
    private int playerId;
    private boolean hardcore;
    private EnumGamemode gameMode;
    private EnumGamemode previousGameMode;
    private long seed; //is generated somewhat from the server seed replace with random value wtf mc biomeZoomSeed
    private Set<MinecraftKey> levels;
    private Dimension currentDimension;
    private int maxPlayers;
    private int chunkRadius;
    private boolean reducedDebugInfo;
    private boolean showDeathScreen;
    private boolean isDebug;
    private boolean isFlat;

    public PacketPlayOutLogin(int playerId, boolean hardcore, EnumGamemode gameMode, EnumGamemode previousGameMode,
            long seed, Dimension currentDimension, int maxPlayers,
            int chunkRadius, boolean reducedDebugInfo, boolean showDeathScreen, boolean isDebug, boolean isFlat) {
        this.playerId = playerId;
        this.hardcore = hardcore;
        this.gameMode = gameMode;
        this.previousGameMode = previousGameMode;
        this.seed = seed;
        this.levels = DatapackHolder.DIMENSIONS.names();
        this.currentDimension = currentDimension;
        this.maxPlayers = maxPlayers;
        this.chunkRadius = chunkRadius;
        this.reducedDebugInfo = reducedDebugInfo;
        this.showDeathScreen = showDeathScreen;
        this.isDebug = isDebug;
        this.isFlat = isFlat;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeInt(playerId);
        data.writeBoolean(hardcore);
        data.writeEnum(gameMode);
        data.writeEnum(previousGameMode);
        data.writeList(levels, (writer, dat) -> {
            writer.writeMCKey(dat);
        });
        NBTCoder.encode(DimensionManager.getElm(), data);
        NBTCoder.encode(currentDimension.getSettings(), data);
        data.writeMCKey(DatapackHolder.DIMENSIONS.getName(currentDimension));
        data.writeLong(this.seed);
        data.writeVarLenInteger(this.maxPlayers);
        data.writeVarLenInteger(this.chunkRadius);
        data.writeBoolean(this.reducedDebugInfo);
        data.writeBoolean(this.showDeathScreen);
        data.writeBoolean(this.isDebug);
        data.writeBoolean(this.isFlat);
        
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutLogin["
                + "playerId='" + playerId + "', "
                + "hardcore='" + hardcore + "', "
                + "gameType='" + gameMode + "', "
                + "previousGameType='" + previousGameMode + "', "
                + "levels='" + levels + "', "
                + "currentDimension='" + currentDimension + "', "
                + "seed='" + seed + "', "
                + "maxPlayers='" + maxPlayers + "', "
                + "chunkRadius='" + chunkRadius + "', "
                + "reducedDebugInfo='" + reducedDebugInfo + "', "
                + "showDeathScreen='" + showDeathScreen + "', "
                + "isDebug='" + isDebug + "', "
                + "isFlat='" + isFlat + "']";
    }
}

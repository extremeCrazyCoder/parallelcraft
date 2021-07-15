package com.parallelcraft.user;

import com.parallelcraft.constants.EnumGamemode;

/**
 * Stores data about a Player used in the tab menue??
 * 
 * @author extremeCrazyCoder
 */
public class PlayerInfoData {
    private int latency;
    private EnumGamemode gameMode;
    private GameProfile profile;
    private ChatMessage displayName;
    
    public PlayerInfoData(GameProfile profile, int latency, EnumGamemode gameMode, ChatMessage displayName) {
        this.profile = profile;
        this.gameMode = gameMode;
        this.profile = profile;
        this.displayName = displayName;
    }
    
    public GameProfile getProfile() {
        return profile;
    }

    public int getLatency() {
        return latency;
    }

    public EnumGamemode getGameMode() {
        return gameMode;
    }

    public ChatMessage getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return "PlayerInfoData{" +
                "latency=" + latency +
                ", gameMode=" + gameMode +
                ", profile=" + profile +
                ", displayName=" + displayName +
            '}';
    }
}

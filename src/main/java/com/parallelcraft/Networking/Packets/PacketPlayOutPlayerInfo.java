package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.constants.EnumPlayerInfoAction;
import com.parallelcraft.user.PlayerInfoData;
import java.util.List;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutPlayerInfo extends PacketOutgoing {
    //TODO use this
    private EnumPlayerInfoAction action;
    private List<PlayerInfoData> entries;
    
    public PacketPlayOutPlayerInfo(EnumPlayerInfoAction action, List<PlayerInfoData> entries) {
        this.action = action;
        this.entries = entries;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeEnum(this.action);
        switch(this.action) {
            case ADD_PLAYER:
                data.writeList(this.entries, (write, dat) -> {
                    write.writeUUID(dat.getProfile().getUUID());
                    write.writeVarLenString(dat.getProfile().getUsername());
                    write.writeList(dat.getProfile().getPropertiesAsList(), (writeInner, dataInner) -> {
                        writeInner.writeVarLenString(dataInner.getName());
                        writeInner.writeVarLenString(dataInner.getValue());
                        writeInner.writeBoolean(dataInner.hasSignature());
                        if(dataInner.hasSignature()) {
                            writeInner.writeVarLenString(dataInner.getSignature());
                        }
                    });
                    
                    write.writeEnum(dat.getGameMode());
                    write.writeVarLenInteger(dat.getLatency());
                    write.writeChatMessage(dat.getDisplayName());
                });
            
            case UPDATE_GAME_MODE:
                data.writeList(this.entries, (write, dat) -> {
                    write.writeUUID(dat.getProfile().getUUID());
                    write.writeEnum(dat.getGameMode());
                });
            
            case UPDATE_LATENCY:
                data.writeList(this.entries, (write, dat) -> {
                    write.writeUUID(dat.getProfile().getUUID());
                    write.writeVarLenInteger(dat.getLatency());
                });
            
            case UPDATE_DISPLAY_NAME:
                data.writeList(this.entries, (write, dat) -> {
                    write.writeUUID(dat.getProfile().getUUID());
                    write.writeChatMessage(dat.getDisplayName());
                });
            
            case REMOVE_PLAYER:
                data.writeList(this.entries, (write, dat) -> {
                    write.writeUUID(dat.getProfile().getUUID());
                });
        }
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutPlayerInfo["
                + "action='" + action + "', "
                + "entries='" + entries + "']";
    }
}

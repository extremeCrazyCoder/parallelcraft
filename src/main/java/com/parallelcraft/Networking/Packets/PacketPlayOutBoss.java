package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.constants.EnumBossBattleBarColor;
import com.parallelcraft.constants.EnumBossBattleBarStyle;
import com.parallelcraft.user.ChatMessage;
import java.util.UUID;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutBoss  extends PacketOutgoing {
    //TODO use this
    private UUID bossUUID;
    private PacketType changeType;
    private ChatMessage name;
    private float percentage;
    private EnumBossBattleBarColor barColor;
    private EnumBossBattleBarStyle barStyle;
    private boolean darkenSky;
    private boolean playMusik;
    private boolean createFog;
    
    public PacketPlayOutBoss(UUID bossUUID, PacketType changeType, ChatMessage name, float percentage, EnumBossBattleBarColor barColor,
            EnumBossBattleBarStyle barStyle, boolean darkenSky, boolean playMusik, boolean createFog) {
        this.bossUUID = bossUUID;
        this.changeType = changeType;
        this.name = name;
        this.percentage = percentage;
        this.barColor = barColor;
        this.barStyle = barStyle;
        this.darkenSky = darkenSky;
        this.playMusik = playMusik;
        this.createFog = createFog;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeUUID(bossUUID);
        data.writeEnum(changeType);
        switch (changeType) {
            case ADD:
                data.writeChatMessage(name);
                data.writeFloat(percentage);
                data.writeEnum(barColor);
                data.writeEnum(barStyle);
                data.writeByte(this.encodeFlags());
                break;
            
            case REMOVE:
                break;
            
            case UPDATE_PERCENTAGE:
                data.writeFloat(percentage);
                break;
            
            case UPDATE_NAME:
                data.writeChatMessage(name);
                break;
            
            case UPDATE_STYLE:
                data.writeEnum(barColor);
                data.writeEnum(barStyle);
                break;
            
            case UPDATE_PROPERTIES:
                data.writeByte(this.encodeFlags());
        }
    }
    
    private byte encodeFlags() {
        byte retval = 0;
        retval |= darkenSky?1:0;
        retval |= playMusik?2:0;
        retval |= createFog?4:0;
        return retval;
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutBoss["
                + "bossUUID='" + bossUUID.toString() + "', "
                + "changeType='" + changeType.name() + "', "
                + "name='" + name.toString() + "', "
                + "percentage='" + percentage + "', "
                + "barColor='" + barColor.name() + "', "
                + "barStyle='" + barStyle.name() + "', "
                + "darkenSky='" + darkenSky + "', "
                + "playMusik='" + playMusik + "', "
                + "createFog='" + createFog + "']";
    }

    public static enum PacketType {
        ADD,
        REMOVE,
        UPDATE_PERCENTAGE,
        UPDATE_NAME,
        UPDATE_STYLE,
        UPDATE_PROPERTIES
    }
}

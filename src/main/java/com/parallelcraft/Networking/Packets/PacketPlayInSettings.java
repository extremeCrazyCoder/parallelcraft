package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.ClientConnectionHandler;
import com.parallelcraft.Networking.PacketHandler;
import com.parallelcraft.Networking.PacketIncoming;
import com.parallelcraft.Networking.PacketReadHelper;
import com.parallelcraft.constants.EnumChatVisibility;
import com.parallelcraft.constants.EnumMainHand;
import com.parallelcraft.exceptions.InsufficientDataException;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayInSettings extends PacketIncoming {
    private String language;
    private int viewDistance;
    private EnumChatVisibility chatVisibility;
    private boolean chatColors;
    private int modelCustomisation;
    private EnumMainHand mainHand;
    private boolean textFilteringEnabled;
    
    public PacketPlayInSettings() {
    }
    
    @Override
    public void decode(PacketReadHelper data) throws InsufficientDataException {
        this.language = data.readVarLenString(16);
        this.viewDistance = data.readByte();
        this.chatVisibility = data.readEnum(EnumChatVisibility.class);
        this.chatColors = data.readBoolean();
        this.modelCustomisation = ((int) data.readByte()) & 0xFF;
        this.mainHand = data.readEnum(EnumMainHand.class);
        this.textFilteringEnabled = data.readBoolean();
    }

    @Override
    public PacketHandler getHandler(ClientConnectionHandler handle) {
        return handle.getDefaultPacketHandler();
    }
    
    @Override
    public String toString() {
        return "PacketPlayInSettings["
                + "language='" + language + "', "
                + "viewDistance='" + viewDistance + "', "
                + "chatVisibility='" + chatVisibility + "', "
                + "chatColors='" + chatColors + "', "
                + "modelCustomisation='" + modelCustomisation + "', "
                + "mainHand='" + mainHand + "', "
                + "textFilteringEnabled='" + textFilteringEnabled + "']";
    }
}

package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.user.ChatMessage;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutOpenWindow extends PacketOutgoing {
    //TODO use this
    private int windowId;
    private int containerId;
    private ChatMessage title;
    
    public PacketPlayOutOpenWindow(int windowId, int containerId, ChatMessage title) {
        this.windowId = windowId;
        this.containerId = containerId;
        this.title = title;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeVarLenInteger(this.windowId);
        data.writeVarLenInteger(this.containerId);
        data.writeChatMessage(this.title);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutOpenWindow["
                + "windowId='" + windowId + "', "
                + "containerId='" + containerId + "', "
                + "title='" + title + "']";
    }
}

package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.constants.EnumChatMessageType;
import com.parallelcraft.user.ChatMessage;
import java.util.UUID;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutChat extends PacketOutgoing {
    //TODO use this
    private ChatMessage message;
    private EnumChatMessageType type;
    private UUID sender;
    
    public PacketPlayOutChat(ChatMessage message, EnumChatMessageType type, UUID sender) {
        this.message = message;
        this.type = type;
        this.sender = sender;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeChatMessage(message);
        data.writeEnumAsByte(type);
        data.writeUUID(sender);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutChat["
                + "message='" + message.toString() + "', "
                + "type='" + type.name() + "', "
                + "sender='" + sender.toString() + "']";
    }
}

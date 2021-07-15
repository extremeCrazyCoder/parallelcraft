package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.user.ChatMessage;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutResourcePackSend extends PacketOutgoing {
    //TODO use this

    public static final int MAX_HASH_LENGTH = 40;
    public String url;
    public String hash;
    public boolean required;
    public ChatMessage message;
    
    //TODO TBD
    public PacketPlayOutResourcePackSend() {
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeVarLenString(this.url);
        data.writeVarLenString(this.hash);
        data.writeBoolean(this.required);
        data.writeChatMessage(this.message);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutResourcePackSend["
                + "url='" + url + "', "
                + "hash='" + hash + "', "
                + "required='" + required + "', "
                + "message='" + message + "']";
    }
}

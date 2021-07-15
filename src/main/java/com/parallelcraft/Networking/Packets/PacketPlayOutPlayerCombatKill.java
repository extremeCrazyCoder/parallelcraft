package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.user.ChatMessage;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutPlayerCombatKill extends PacketOutgoing {
    //TODO use this
    private int playerId;
    private int killerId;
    private ChatMessage message;
    
    public PacketPlayOutPlayerCombatKill(int playerId, int killerId, ChatMessage message) {
        this.playerId = playerId;
        this.killerId = killerId;
        this.message = message;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeVarLenInteger(this.playerId);
        data.writeInt(this.killerId);
        data.writeChatMessage(this.message);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutPlayerCombatKill["
                + "playerId='" + playerId + "', "
                + "killerId='" + killerId + "', "
                + "message='" + message + "']";
    }
}

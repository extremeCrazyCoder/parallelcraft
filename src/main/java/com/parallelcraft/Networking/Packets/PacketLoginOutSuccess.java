package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketReadHelper;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.exceptions.InsufficientDataException;
import com.parallelcraft.user.GameProfile;
import java.util.UUID;
/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketLoginOutSuccess extends PacketOutgoing{
    private GameProfile clientProf;
    
    public PacketLoginOutSuccess(GameProfile clientProf) {
        this.clientProf = clientProf;
    }

    @Override
    public void decode(PacketReadHelper data) throws InsufficientDataException {
        UUID uuid = data.readUUID();
        String username = data.readVarLenString(16);
        this.clientProf = GameProfile.create(uuid, username);
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeUUID(clientProf.getUUID());
        data.writeVarLenString(clientProf.getUsername());
    }
    
    public String toString() {
        return "PacketLoginOutSuccess["
                + "clientProf='" + clientProf.toString() + "']";
    }
}

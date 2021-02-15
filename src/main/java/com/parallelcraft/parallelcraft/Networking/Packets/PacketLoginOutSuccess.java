package com.parallelcraft.parallelcraft.Networking.Packets;

import com.parallelcraft.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.parallelcraft.Networking.PacketReadHelper;
import com.parallelcraft.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.parallelcraft.exceptions.InsufficientDataException;
import com.parallelcraft.parallelcraft.user.GameProfile;
import com.parallelcraft.parallelcraft.user.UUID;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketLoginOutSuccess extends PacketOutgoing{
    private GameProfile clientProf;
    
    public PacketLoginOutSuccess(GameProfile pClientProfile) {
        this.clientProf = pClientProfile;
    }

    @Override
    public void decode(PacketReadHelper data) throws InsufficientDataException {
        int[] uidInt = new int[4];
        
        for(int i = 0; i < uidInt.length; i++) {
            uidInt[i] = data.readInt();
        }
        
        UUID uuid = UUID.create(uidInt);
        String username = data.getString(16);
        this.clientProf = GameProfile.create(uuid, username);
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        UUID uuid = clientProf.getUUID();
        data.writeInt(uuid.getInt(0));
        data.writeInt(uuid.getInt(1));
        data.writeInt(uuid.getInt(2));
        data.writeInt(uuid.getInt(3));
        data.writeString(clientProf.getUsername());
    }
    
    public String toString() {
        return "PacketLoginOutSuccess["
                + "clientProf='" + clientProf.toString() + "']";
    }
}

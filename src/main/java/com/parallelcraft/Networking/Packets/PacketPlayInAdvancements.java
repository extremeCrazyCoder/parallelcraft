package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.ClientConnectionHandler;
import com.parallelcraft.Networking.PacketHandler;
import com.parallelcraft.Networking.PacketIncoming;
import com.parallelcraft.Networking.PacketReadHelper;
import com.parallelcraft.constants.EnumAdvancementsStatus;
import com.parallelcraft.exceptions.InsufficientDataException;
import com.parallelcraft.util.MinecraftKey;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayInAdvancements extends PacketIncoming {
    private EnumAdvancementsStatus type;
    private MinecraftKey tab;
    
    public PacketPlayInAdvancements() {
    }
    
    @Override
    public void decode(PacketReadHelper data) throws InsufficientDataException {
        this.type = data.readEnum(EnumAdvancementsStatus.class);
        if(this.type == EnumAdvancementsStatus.OPENED_TAB) {
            this.tab = data.readMCKey();
        }
    }

    @Override
    public PacketHandler getHandler(ClientConnectionHandler handle) {
        return handle.getDefaultPacketHandler();
    }
    
    @Override
    public String toString() {
        return "PacketPlayInAdvancements["
                + "type='" + type + "', "
                + "tab='" + tab + "']";
    }
}

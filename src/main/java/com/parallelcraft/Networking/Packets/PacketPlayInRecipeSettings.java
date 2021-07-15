package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.ClientConnectionHandler;
import com.parallelcraft.Networking.PacketHandler;
import com.parallelcraft.Networking.PacketIncoming;
import com.parallelcraft.Networking.PacketReadHelper;
import com.parallelcraft.constants.EnumRecipeBookType;
import com.parallelcraft.exceptions.InsufficientDataException;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayInRecipeSettings extends PacketIncoming {
    private EnumRecipeBookType bookType;
    private boolean isOpen;
    private boolean isFiltering;
    
    public PacketPlayInRecipeSettings() {
    }
    
    @Override
    public void decode(PacketReadHelper data) throws InsufficientDataException {
        this.bookType = data.readEnum(EnumRecipeBookType.class);
        this.isOpen = data.readBoolean();
        this.isFiltering = data.readBoolean();
    }

    @Override
    public PacketHandler getHandler(ClientConnectionHandler handle) {
        return handle.getDefaultPacketHandler();
    }
    
    @Override
    public String toString() {
        return "PacketPlayInRecipeSettings["
                + "bookType='" + bookType + "', "
                + "isOpen='" + isOpen + "', "
                + "isFiltering='" + isFiltering + "']";
    }
}

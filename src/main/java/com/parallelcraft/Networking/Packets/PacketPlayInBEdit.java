package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.ClientConnectionHandler;
import com.parallelcraft.Networking.PacketHandler;
import com.parallelcraft.Networking.PacketIncoming;
import com.parallelcraft.Networking.PacketReadHelper;
import com.parallelcraft.exceptions.InsufficientDataException;
import com.parallelcraft.world.ItemStack;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayInBEdit extends PacketIncoming {
    private ItemStack book;
    private boolean signing;
    private int slot;
    
    public PacketPlayInBEdit() {
    }
    
    @Override
    public void decode(PacketReadHelper data) throws InsufficientDataException {
        this.book = data.readItemStack();
        this.signing = data.readBoolean();
        this.slot = data.readVarLenInteger();
    }

    @Override
    public PacketHandler getHandler(ClientConnectionHandler handle) {
        return handle.getDefaultPacketHandler();
    }
    
    @Override
    public String toString() {
        return "PacketPlayInBEdit["
                + "book='" + book + "', "
                + "signing='" + signing + "', "
                + "slot='" + slot + "']";
    }
}

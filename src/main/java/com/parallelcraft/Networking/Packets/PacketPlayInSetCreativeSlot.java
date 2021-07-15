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
public class PacketPlayInSetCreativeSlot extends PacketIncoming {
    private int slotNum;
    private ItemStack stack;
    
    public PacketPlayInSetCreativeSlot() {
    }
    
    @Override
    public void decode(PacketReadHelper data) throws InsufficientDataException {
        this.slotNum = data.readSignedShort();
        this.stack = data.readItemStack();
    }

    @Override
    public PacketHandler getHandler(ClientConnectionHandler handle) {
        return handle.getDefaultPacketHandler();
    }
    
    @Override
    public String toString() {
        return "PacketPlayInSetCreativeSlot["
                + "slotNum='" + slotNum + "', "
                + "stack='" + stack + "']";
    }
}

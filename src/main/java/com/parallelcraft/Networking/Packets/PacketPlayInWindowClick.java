package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.ClientConnectionHandler;
import com.parallelcraft.Networking.PacketHandler;
import com.parallelcraft.Networking.PacketIncoming;
import com.parallelcraft.Networking.PacketReadHelper;
import com.parallelcraft.constants.EnumInventoryClickType;
import com.parallelcraft.exceptions.InsufficientDataException;
import com.parallelcraft.world.ItemStack;
import java.util.Map;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayInWindowClick extends PacketIncoming {
    private int containerId;
    private int slotNum;
    private int buttonNum;
    private EnumInventoryClickType clickType;
    private Map<Integer, ItemStack> changedSlots;
    private ItemStack carriedItem;
    
    public PacketPlayInWindowClick() {
    }
    
    @Override
    public void decode(PacketReadHelper data) throws InsufficientDataException {
        this.containerId = data.readByte();
        this.slotNum = data.readSignedShort();
        this.buttonNum = data.readByte();
        this.clickType = data.readEnum(EnumInventoryClickType.class);
        this.changedSlots = data.readMap(
                (reader) -> {return reader.readSignedShort();},
                (reader) -> {return reader.readItemStack();}
        );
        this.carriedItem = data.readItemStack();
    }

    @Override
    public PacketHandler getHandler(ClientConnectionHandler handle) {
        return handle.getDefaultPacketHandler();
    }
    
    @Override
    public String toString() {
        return "PacketPlayInWindowClick["
                + "containerId='" + containerId + "', "
                + "slotNum='" + slotNum + "', "
                + "buttonNum='" + buttonNum + "', "
                + "clickType='" + clickType + "', "
                + "changedSlots='" + changedSlots + "', "
                + "carriedItem='" + carriedItem + "']";
    }
}

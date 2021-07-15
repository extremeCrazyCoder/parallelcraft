package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.world.ItemStack;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutSetSlot  extends PacketOutgoing {
    //TODO use this
    
    /*
     * -1 / -1 for carried item
     * -2 /  * for Player
     */
    private byte windowId;
    private short slotId;
    private ItemStack contents;
    
    public PacketPlayOutSetSlot(byte windowId, short slotId, ItemStack contents) {
        this.windowId = windowId;
        this.slotId = slotId;
        this.contents = contents;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeByte(windowId);
        data.writeUnsignedShort(slotId);
        data.writeItemStack(contents);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutSetSlot["
                + "windowId='" + windowId + "', "
                + "slotId='" + slotId + "', "
                + "contents='" + contents + "']";
    }
}

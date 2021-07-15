package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.util.MinecraftKey;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutAutoRecipe extends PacketOutgoing {
    //TODO use this
    private byte containerId;
    private MinecraftKey recipe;
    
//    public PacketPlayOutAutoRecipe(byte containerId, IRecipe<?> irecipe) {
//        this.containerId = containerId;
//        this.recipe = irecipe.getKey();
//    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeByte(this.containerId);
        data.writeMCKey(this.recipe);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutAutoRecipe["
                + "containerId='" + containerId + "', "
                + "recipe='" + recipe + "']";
    }
}

package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.util.MinecraftKey;
import java.util.List;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutRecipes extends PacketOutgoing {
    //TODO use this
    private Type packetType;
    private List<MinecraftKey> recipes;
    private List<MinecraftKey> highlighted;
    //bunch of booleans very inefficient; no idea what that exactly is
//    private RecipeBookSettings bookSettings;
    
    //TODO TBD
    public PacketPlayOutRecipes() {
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeEnum(packetType);
//        data.writeBoolean(this.bookSettings);
        data.writeList(this.recipes, (writer, dat) -> {
            writer.writeMCKey(dat);
        });
        if(packetType == Type.INIT) {
            data.writeList(this.highlighted, (writer, dat) -> {
                writer.writeMCKey(dat);
            });
        }
    }
    
//    @Override
//    public String toString() {
//        return "PacketPlayOutRecipes["
//                + "packetType='" + packetType + "', "
//                + "bookSettings='" + bookSettings + "', "
//                + "recipes='" + recipes + "', "
//                + "highlighted='" + highlighted + "']";
//    }
    
    public enum Type {
        INIT, ADD, REMOVE;
    }
}

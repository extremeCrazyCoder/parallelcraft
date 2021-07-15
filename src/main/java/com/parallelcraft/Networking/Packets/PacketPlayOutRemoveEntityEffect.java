package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.constants.EnumMobEffect;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutRemoveEntityEffect extends PacketOutgoing {
    //TODO use this
    private int entityId;
    private EnumMobEffect effect;
    
    public PacketPlayOutRemoveEntityEffect(int entityId, EnumMobEffect effect) {
        this.entityId = entityId;
        this.effect = effect;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeVarLenInteger(this.entityId);
        data.writeEnumAsByte(this.effect);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutRemoveEntityEffect["
                + "entityId='" + entityId + "', "
                + "effect='" + effect + "']";
    }
}

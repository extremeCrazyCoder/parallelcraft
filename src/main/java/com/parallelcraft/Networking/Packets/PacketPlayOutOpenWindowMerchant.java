package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutOpenWindowMerchant extends PacketOutgoing {
    //TODO use this
    private int containerId;
//    private MerchantRecipeList offers; //Maybe change To List of Trades?
    private int villagerLevel;
    private int villagerXp;
    private boolean showProgress;
    private boolean canRestock;
    
//    public PacketPlayOutOpenWindowMerchant(int containerId, MerchantRecipeList offers, int villagerLevel, int villagerXp,
//            boolean showProgress, boolean canRestock) {
//        this.containerId = containerId;
//        this.offers = offers;
//        this.villagerLevel = villagerLevel;
//        this.villagerXp = villagerXp;
//        this.showProgress = showProgress;
//        this.canRestock = canRestock;
//    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeVarLenInteger(this.containerId);
//        data.writeList(this.offers);
        data.writeVarLenInteger(this.villagerLevel);
        data.writeVarLenInteger(this.villagerXp);
        data.writeBoolean(this.showProgress);
        data.writeBoolean(this.canRestock);
    }
    
//    @Override
//    public String toString() {
//        return "PacketPlayOutOpenWindowMerchant["
//                + "containerId='" + containerId + "', "
//                + "offers='" + offers + "', "
//                + "villagerLevel='" + villagerLevel + "', "
//                + "villagerXp='" + villagerXp + "', "
//                + "showProgress='" + showProgress + "', "
//                + "canRestock='" + canRestock + "']";
//    }
}

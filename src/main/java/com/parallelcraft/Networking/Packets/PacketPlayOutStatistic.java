package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutStatistic  extends PacketOutgoing {
    //TODO use this
//    Map<Statistic<?>, Integer> mapData;
    
    public PacketPlayOutStatistic() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        throw new UnsupportedOperationException("Not supported yet.");
        //TODO transform this pseudocode into usefull code
//        data.writeVarLenInteger(mapData.size());
//        
//        for(Map.Entry<Statistic<?>, Integer> entry : mapData.entrySet()) {
//            Statistic<?> statistic = entry.getKey();
//            
//            data.writeVarLenInteger(0); //somethin with 
//            data.writeVarLenInteger(0);
//            data.writeVarLenInteger(0);
//        }
    }
    
//    @Override
//    public String toString() {
//        return "PacketPlayOutStatistic["
//                + "id='" + id + "', "
//                + "uuid='" + uuid + "', "
//                + "entityType='" + entityType + "', "
//                + "pos='[" + xPos + ", " + yPos + ", " + zPos + "]', "
//                + "pitch='" + pitch + "', "
//                + "yaw='" + yaw + "', "
//                + "motion='[" + xMotion + ", " + yMotion + ", " + zMotion + "]', "
//                + "subID='" + subID + "']";
//    }
}

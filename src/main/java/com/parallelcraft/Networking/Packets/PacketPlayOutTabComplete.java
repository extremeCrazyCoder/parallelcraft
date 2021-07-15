package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutTabComplete  extends PacketOutgoing {
    //TODO use this
//    private int requestID;
//    private TabCompleteSuggestions suggestions;
//    
//    public PacketPlayOutTabComplete(int requestID,TabCompleteSuggestions suggestions) {
//        this.requestID = requestID;
//        this.suggestions = suggestions;
//    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        throw new UnsupportedOperationException("Not supported yet.");
        //TODO transform this pseudocode into usefull code
//        data.writeVarLenInteger(requestID);
//        data.writeVarLenInteger(suggestions.getRange().getStart());
//        data.writeVarLenInteger(suggestions.getRange().getLenght());
//        data.writeVarLenInteger(suggestions.getList().size());
//        
//        for(TablCompleteSuggestion suggestion : suggestions.getList()) {
//            data.writeVarLenString(suggestion.getText());
//            data.writeBoolean(suggestion.getTooltip() != null);
//            if(suggestion.getTooltip() != null) {
//                data.writeChatMessage(suggestion.getTooltip());
//            }
//        }
    }
    
//    @Override
//    public String toString() {
//        return "PacketPlayOutTabComplete["
//                + "requestID='" + requestID + "', "
//                + "suggestions='" + suggestions.toString() + "']";
//    }
}

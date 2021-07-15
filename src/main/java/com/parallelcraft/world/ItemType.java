package com.parallelcraft.world;

import com.parallelcraft.Datapack.DatapackHolder;
import org.json.JSONObject;

/**
 * The type of an item. 
 *
 * @author extremeCrazyCoder
 */
public class ItemType {
    private int maxStackSize;
    private boolean isFireResistant;
    private float maxDamage;
    private int craftingRemainingItemInt;
    private ItemType craftingRemainingItem;
    
    private int id = -1;
    
    public ItemType(JSONObject obj) {
        this.maxStackSize = obj.getInt("maxStackSize");
        this.isFireResistant = obj.getBoolean("isFireResistant");
        this.maxDamage = obj.getFloat("maxDamage");
        this.craftingRemainingItemInt = obj.getInt("craftingRemainingItem");
    }
    
    //will be called reflective during datapack loading
    private void setID(int id) {
        this.id = id;
    }
    
    public void afterBoot() {
        this.craftingRemainingItem = DatapackHolder.ITEMS.byID(this.craftingRemainingItemInt);
    }
    
    public int getDatabaseID() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ItemType["
                + "maxStackSize='" + maxStackSize + "', "
                + "isFireResistant='" + isFireResistant + "', "
                + "maxDamage='" + maxDamage + "', "
                + "craftingRemainingItem='" + craftingRemainingItem + "']";
    }
}

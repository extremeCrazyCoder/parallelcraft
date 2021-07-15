package com.parallelcraft.world;

import com.parallelcraft.Datapack.DatapackHolder;
import org.json.JSONObject;

/**
 * This references a type of block
 * 
 * This class should implement all important things about that block
 * 
 */
public class BlockType {
    private float jumpFactor;
    private float speedFactor;
    private float friction;
    private float explosionResistance;
    private float destroyTime;
    private boolean hasCollision;
    
    private ItemType asItem;
    private int asItemId;
    
    private int id = -1;
    
    //TODO sound
    
    public BlockType(JSONObject obj) {
        this.jumpFactor = obj.getFloat("jumpFactor");
        this.speedFactor = obj.getFloat("speedFactor");
        this.friction = obj.getFloat("friction");
        this.explosionResistance = obj.getFloat("explosionResistance");
        this.destroyTime = obj.getFloat("destroyTime");
        this.hasCollision = obj.getBoolean("hasCollision");
        this.asItemId = obj.getInt("asItem");
    }
    
    //will be called reflective during datapack loading
    private void setID(int id) {
        this.id = id;
    }
    
    public void afterBoot() {
        this.asItem = DatapackHolder.ITEMS.byID(this.asItemId);
    }
    
    public int getDatabaseID() {
        return this.id;
    }

    @Override
    public String toString() {
        return "BlockType["
                + "jumpFactor='" + jumpFactor + "', "
                + "speedFactor='" + speedFactor + "', "
                + "friction='" + friction + "', "
                + "explosionResistance='" + explosionResistance + "', "
                + "destroyTime='" + destroyTime + "', "
                + "hasCollision='" + hasCollision + "']";
    }
}

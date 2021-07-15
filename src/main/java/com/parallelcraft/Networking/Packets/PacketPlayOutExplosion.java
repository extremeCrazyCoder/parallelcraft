package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.util.Vector3D;
import com.parallelcraft.world.BlockPosition;
import java.util.List;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutExplosion  extends PacketOutgoing {
    //TODO use this
    private float xPos;
    private float yPos;
    private float zPos;
    private float power;
    private List<BlockPosition> affectedBlocks;
    private float forceX;
    private float forceY;
    private float forceZ;
    
    public PacketPlayOutExplosion(Vector3D position, float power, List<BlockPosition> affectedBlocks, Vector3D force) {
        this.xPos = position.getX();
        this.yPos = position.getY();
        this.zPos = position.getZ();
        this.power = power;
        this.affectedBlocks = affectedBlocks; //TODO deepclone this?
        this.forceX = force.getX();
        this.forceY = force.getY();
        this.forceZ = force.getZ();
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeFloat(xPos);
        data.writeFloat(yPos);
        data.writeFloat(zPos);
        data.writeFloat(power);
        
        data.writeInt(affectedBlocks.size());
        int flooredX = (int) xPos;
        int flooredY = (int) yPos;
        int flooredZ = (int) zPos;
        for(BlockPosition b : affectedBlocks) {
            data.writeByte((byte) (b.getX() - flooredX));
            data.writeByte((byte) (b.getY() - flooredY));
            data.writeByte((byte) (b.getZ() - flooredZ));
        }
        
        data.writeFloat(forceX);
        data.writeFloat(forceY);
        data.writeFloat(forceZ);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutExplosion["
                + "pos='[x='" + xPos + "', y='" + yPos + "', z='" + zPos + "']', "
                + "power='" + power + "', "
                + "affectedBlocks='" + affectedBlocks.toString() + "', "
                + "force='[x='" + forceX + "', y='" + forceY + "', z='" + forceZ + "']']";
    }
}

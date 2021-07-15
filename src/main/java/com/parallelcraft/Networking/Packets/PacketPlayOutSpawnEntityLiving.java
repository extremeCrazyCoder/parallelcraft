package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.util.MathUtils;
import com.parallelcraft.util.Vector3D;
import java.util.UUID;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutSpawnEntityLiving extends PacketOutgoing {
    //TODO use this
    private int id;
    private UUID uuid;
    private int entityType;
    private double xPos;
    private double yPos;
    private double zPos;
    
    /**
     * Movement speed
     * The given Vec3D is limited to the range from -3.9 to 3.9
     * afterwards multiplied with 8000
     */
    private int xMotion;
    private int yMotion;
    private int zMotion;
    
    private byte yaw; //in range 0 to 255
    private byte pitch; //in range 0 to 255 -> deg * 256 / 360
    private byte headRotation;
    
    public PacketPlayOutSpawnEntityLiving(int id, UUID uuid, double xPos, double yPos, double zPos, float pitch, float yaw, float headRotation,
            int entityType, Vector3D movement) {
        this.id = id;
        this.uuid = uuid;
        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = zPos;
        this.pitch = (byte) (pitch * 256 / 360);
        this.yaw = (byte) (yaw * 256 / 360);
        this.headRotation = (byte) (headRotation * 256 / 360);
        this.entityType = entityType;
        
        this.xMotion = (int) (MathUtils.limit(movement.getX(), -3.9D, 3.9D) * 8000);
        this.yMotion = (int) (MathUtils.limit(movement.getY(), -3.9D, 3.9D) * 8000);
        this.zMotion = (int) (MathUtils.limit(movement.getZ(), -3.9D, 3.9D) * 8000);
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeVarLenInteger(id);
        data.writeUUID(uuid);
        data.writeVarLenInteger(entityType);
        data.writeDouble(xPos);
        data.writeDouble(yPos);
        data.writeDouble(zPos);
        data.writeByte(yaw);
        data.writeByte(pitch);
        data.writeByte(headRotation);
        data.writeUnsignedShort(xMotion);
        data.writeUnsignedShort(yMotion);
        data.writeUnsignedShort(zMotion);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutSpawnEntityLiving["
                + "id='" + id + "', "
                + "uuid='" + uuid + "', "
                + "entityType='" + entityType + "', "
                + "pos='[" + xPos + ", " + yPos + ", " + zPos + "]', "
                + "pitch='" + pitch + "', "
                + "yaw='" + yaw + "', "
                + "headRotation='" + headRotation + "', "
                + "motion='[" + xMotion + ", " + yMotion + ", " + zMotion + "]']";
    }
}

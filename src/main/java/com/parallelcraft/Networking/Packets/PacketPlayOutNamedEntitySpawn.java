package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;
import java.util.UUID;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutNamedEntitySpawn extends PacketOutgoing {
    //TODO use this
    private int id;
    private UUID uuid;
    private double xPos;
    private double yPos;
    private double zPos;
    private byte yaw; //in range 0 to 255
    private byte pitch; //in range 0 to 255 -> deg * 256 / 360
    
    public PacketPlayOutNamedEntitySpawn(int id, UUID uuid, double xPos, double yPos, double zPos, float pitch, float yaw) {
        this.id = id;
        this.uuid = uuid;
        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = zPos;
        this.pitch = (byte) (pitch * 256 / 360);
        this.yaw = (byte) (yaw * 256 / 360);
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeVarLenInteger(id);
        data.writeUUID(uuid);
        data.writeDouble(xPos);
        data.writeDouble(yPos);
        data.writeDouble(zPos);
        data.writeByte(yaw);
        data.writeByte(pitch);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutNamedEntitySpawn["
                + "id='" + id + "', "
                + "uuid='" + uuid + "', "
                + "pos='[" + xPos + ", " + yPos + ", " + zPos + "]', "
                + "pitch='" + pitch + "', "
                + "yaw='" + yaw + "']";
    }
}

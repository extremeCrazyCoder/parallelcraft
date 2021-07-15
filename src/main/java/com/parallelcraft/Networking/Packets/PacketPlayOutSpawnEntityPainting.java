package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.constants.EnumDirection;
import com.parallelcraft.world.BlockPosition;
import java.util.UUID;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutSpawnEntityPainting extends PacketOutgoing {
    //TODO use this
    private int id;
    private UUID uuid;
    private BlockPosition position;
    private byte direction;
    private int motive;
    
    public PacketPlayOutSpawnEntityPainting(int id, UUID uuid, BlockPosition position, EnumDirection direction, int motive) {
        this.id = id;
        this.uuid = uuid;
        this.position = position;
        this.direction = direction.getFlatID();
        this.motive = motive;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeVarLenInteger(id);
        data.writeUUID(uuid);
        data.writeVarLenInteger(motive);
        data.writeBlockPosition(position);
        data.writeByte(direction);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutSpawnEntityPainting["
                + "id='" + id + "', "
                + "uuid='" + uuid + "', "
                + "position='[" + position.toString() + "]', "
                + "direction='" + direction + "', "
                + "motive='" + motive + "']";
    }
}

package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.nbt.NBTCoder;
import com.parallelcraft.nbt.NBTCompoundElement;
import com.parallelcraft.world.Chunk;
import com.parallelcraft.world.ChunkSection;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutMapChunk  extends PacketOutgoing {
    public static final int TMP_BUFFER_SIZE = 2 * 1024 * 1024;
    
    //TODO use this
    private int posX;
    private int posZ;
    private NBTCompoundElement heightMaps;
    private ChunkSection sections[];
    private int[] biomeIndizes;
    private List<NBTCompoundElement> blockEntitiesTags; //should be list of NBTTags empty for now
    
    //TODO biomeHeight = wrong
    public PacketPlayOutMapChunk(Chunk toSend) {
        this.posX = toSend.getPos().getX();
        this.posZ = toSend.getPos().getZ();
        this.heightMaps = toSend.getHeightMaps();
        this.biomeIndizes = toSend.biomesAsArray();
        blockEntitiesTags = new ArrayList<>();
        sections = toSend.getSections();
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        BitSet availableSections = new BitSet(sections.length);
        ByteBuffer temp = ByteBuffer.allocate(TMP_BUFFER_SIZE);
        PacketWriteHelper helperTmp = new PacketWriteHelper(temp);
        for(int i = 0; i < sections.length; i++) {
            if(sections[i] != ChunkSection.EMPTY_SECTION) {
                availableSections.set(i);
                sections[i].writeAsPacket(helperTmp);
            }
        }
        
        data.writeInt(posX);
        data.writeInt(posZ);
        data.writeBitSet(availableSections);
        NBTCoder.encode(heightMaps, data);
        data.writeVarLenIntegerArray(biomeIndizes); //1024 = 4 * 4 * 64
        data.writeBuffer(temp);
        data.writeList(blockEntitiesTags, (write, dat) -> {
            //TODO write
//            write.writeVarLenInteger(dat);
        });
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutMapChunk["
                + "posX='" + posX + "', "
                + "posZ='" + posZ + "', "
                + "heightMaps='" + heightMaps + "', "
                + "sections='" + sections + "', "
                + "biomeIndizes='" + biomeIndizes + "', "
                + "blockEntitiesTags='" + blockEntitiesTags + "']";
    }
}

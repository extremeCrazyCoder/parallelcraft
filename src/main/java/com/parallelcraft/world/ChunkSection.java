package com.parallelcraft.world;

import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.util.MathUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * A 16 x 16 x 16 Part of the game world
 * 
 * @author extremeCrazyCoder
 */
public class ChunkSection {
    public static final int SECTION_SIZE = 16;
    public static final int SECTION_SIZE_FULL = SECTION_SIZE * SECTION_SIZE * SECTION_SIZE;
    public static final ChunkSection EMPTY_SECTION = new ChunkSection(Blocks.AIR);
    private static final byte LONG_SIZE = 64;
    
    private short nonEmptyBlockCount = 0;
    private List<BlockType> blockIndex = new ArrayList<>();
    
    private int indexBits = 0;
    private int valuesPerLong = 0;
    private int bitAndWith = 0;
    private long[] bitArray;
    
    public ChunkSection(BlockType fillWith) {
        blockIndex.add(fillWith);
        bitArray = new long[1];
        autoResize();
        nonEmptyBlockCount = SECTION_SIZE_FULL;
    }
    
    public void writeAsPacket(PacketWriteHelper helper) {
        helper.writeSignedShort(nonEmptyBlockCount);
        helper.writeByte((byte) indexBits);
        helper.writeList(blockIndex, ((writer, data) -> {
            writer.writeVarLenInteger(data.getDatabaseID());
        }));
        
        helper.writeLongArray(bitArray);
    }
    
    /**
     * Needs to be called always after adding to the index (before writing the data)
     * Needs to be called always after removing from the index (after writing the data)
     */
    private void autoResize() {
        //TODO make this thread save / maybe async?
        byte newIndexBits = MathUtils.ceilLog2(blockIndex.size());
        if(newIndexBits != indexBits) {
            int newValuesPerLong = LONG_SIZE / newIndexBits;
            int newBitAndWith = (1 << newIndexBits) - 1;
            long[] newArray = new long[(int) Math.floor(((float) SECTION_SIZE_FULL) / newValuesPerLong)];
            
            for(int i = 0; i < SECTION_SIZE_FULL; i++) {
                int val = (int) (bitArray[i / valuesPerLong] >> (indexBits * (i % valuesPerLong)) ) & bitAndWith;
                newArray[i / newValuesPerLong] |= val << (newIndexBits * (i % newValuesPerLong));
            }
            
            this.indexBits = newIndexBits;
            this.bitAndWith = newBitAndWith;
            this.valuesPerLong = newValuesPerLong;
            this.bitArray = newArray;
        }
    }
}

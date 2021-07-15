package com.parallelcraft.world;

import com.parallelcraft.nbt.NBTCompoundElement;
import java.util.Arrays;

/**
 * A single chunk in the game world
 * 
 * @author extremeCrazyCoder
 */
public class Chunk {
    private ChunkCoordinates pos;
    private ChunkSection sections[];
    private Dimension dim;
    
    /**
     * Fill is based on sections
     */
    public Chunk(Dimension dim, ChunkCoordinates pos, BlockType fillWith, int upTo) {
        this(dim, pos);
        
        for(int i = 0; i < upTo / ChunkSection.SECTION_SIZE; i++) {
            sections[i] = new ChunkSection(fillWith);
        }
    }
    
    public Chunk(Dimension dim, ChunkCoordinates pos) {
        this.dim = dim;
        this.pos = pos;
        this.sections = new ChunkSection[dim.getHeightSectionCount()];
        
        for(int i = 0; i < sections.length; i++) {
            sections[i] = ChunkSection.EMPTY_SECTION;
        }
    }
    
    //TODO this
    public int[] biomesAsArray() {
        //chunk = 16 * 16 * 256
        //biomes = 4 * 4 * 64
        int[] data = new int[1024];
        Arrays.fill(data, 0);
        return data;
    }
    
    public NBTCompoundElement getHeightMaps() {
        return new NBTCompoundElement();
    }
    
    public ChunkCoordinates getPos() {
        return pos;
    }
    
    public Dimension getDimension() {
        return dim;
    }
    
    public ChunkSection[] getSections() {
        return sections;
    }

    @Override
    public String toString() {
        return "Chunk{" + "pos=" + pos + '}';
    }
}

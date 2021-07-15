package com.parallelcraft.world;

import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.constants.EnumParticleType;
import org.json.JSONObject;

/**
 * A single particle in the minecraft world
 * 
 * @author extremeCrazyCoder
 */
public class Particle {
    private EnumParticleType type;
    
    public Particle(JSONObject obj) {
        
    }
    
    public EnumParticleType getType() {
        return type;
    }

    public void writePacketData(PacketWriteHelper writer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

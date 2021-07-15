package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.util.Vector3D;
import com.parallelcraft.world.Particle;
import com.parallelcraft.world.Position;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutWorldParticles extends PacketOutgoing {
    //TODO use this
    private Position pos;
    private Vector3D dist;
    private float maxSpeed;
    private int count;
    private boolean overrideLimiter;
    private Particle particle;
    
    public PacketPlayOutWorldParticles(Position pos, Vector3D dist, float maxSpeed, int count,
            boolean overrideLimiter, Particle particle) {
        this.pos = pos;
        this.dist = dist;
        this.maxSpeed = maxSpeed;
        this.count = count;
        this.overrideLimiter = overrideLimiter;
        this.particle = particle;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeEnum(particle.getType());
        data.writeBoolean(this.overrideLimiter);
        data.writeDouble(this.pos.getX());
        data.writeDouble(this.pos.getY());
        data.writeDouble(this.pos.getZ());
        data.writeFloat(this.dist.getX());
        data.writeFloat(this.dist.getY());
        data.writeFloat(this.dist.getZ());
        data.writeFloat(this.maxSpeed);
        data.writeInt(this.count);
        this.particle.writePacketData(data);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutWorldParticles["
                + "pos='" + pos + "', "
                + "dist='" + dist + "', "
                + "maxSpeed='" + maxSpeed + "', "
                + "count='" + count + "', "
                + "overrideLimiter='" + overrideLimiter + "', "
                + "particle='" + particle + "']";
    }
}

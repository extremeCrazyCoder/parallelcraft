package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.PacketOutgoing;
import com.parallelcraft.Networking.PacketWriteHelper;
import com.parallelcraft.constants.EnumSoundCategory;
import com.parallelcraft.util.Vector3D;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketPlayOutCustomSoundEffect  extends PacketOutgoing {
    //TODO use this
    private String soundEvent;
    private EnumSoundCategory type;
    private int sourceLocationX;
    private int sourceLocationY;
    private int sourceLocationZ;
    
    /*
     * Volume for this sound based on center (not the actual volume the client will hear
     */
    private float volume;
    
    /**
     * Speed the sound is played back with (e.g. 0.5 for half speed)
     */
    private float pitch;
    
    //TODO try sending wrong Events what happens for client??
    public PacketPlayOutCustomSoundEffect(String soundEvent, EnumSoundCategory type, Vector3D sourceLocation, float volume, float pitch) {
        this.soundEvent = soundEvent;
        this.type = type;
        this.sourceLocationX = (int) (sourceLocation.getX() * 8);
        this.sourceLocationY = (int) (sourceLocation.getY() * 8);
        this.sourceLocationZ = (int) (sourceLocation.getZ() * 8);
        this.volume = volume;
        this.pitch = pitch;
    }
    
    @Override
    public void encode(PacketWriteHelper data) {
        data.writeVarLenString(soundEvent);
        data.writeEnum(type);
        data.writeInt(sourceLocationX);
        data.writeInt(sourceLocationY);
        data.writeInt(sourceLocationZ);
        data.writeFloat(volume);
        data.writeFloat(pitch);
    }
    
    @Override
    public String toString() {
        return "PacketPlayOutCustomSoundEffect["
                + "soundEvent='" + soundEvent + "', "
                + "type='" + type + "', "
                + "sourceLocation='[x='" + sourceLocationX + "', y='" + sourceLocationY + "', z='" + sourceLocationZ + "']', "
                + "volume='" + volume + "', "
                + "pitch='" + pitch + "']";
    }
}

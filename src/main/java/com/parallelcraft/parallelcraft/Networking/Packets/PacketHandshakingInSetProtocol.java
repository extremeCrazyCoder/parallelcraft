package com.parallelcraft.parallelcraft.Networking.Packets;

import com.parallelcraft.parallelcraft.Networking.ClientConnectionHandler;
import com.parallelcraft.parallelcraft.Networking.PacketHandler;
import com.parallelcraft.parallelcraft.Networking.PacketIncoming;
import com.parallelcraft.parallelcraft.Networking.PacketReadHelper;
import com.parallelcraft.parallelcraft.exceptions.InsufficientDataException;

/**
 * Using Packet names of vanilla MC in order to be easier for Coders to switch from vanilla to this
 * 
 * @author extremeCrazyCoder
 */
public class PacketHandshakingInSetProtocol extends PacketIncoming {
    private int clientVersion;
    private String hostname;
    private int port;
    private byte protocol;
    
    @Override
    public void decode(PacketReadHelper data) throws InsufficientDataException {
        this.clientVersion = data.getInteger();
        this.hostname = data.getString(255);
        this.port = data.getUnsignedShort();
        this.protocol = data.getByte();
    }

    @Override
    public PacketHandler getHandler(ClientConnectionHandler handle) {
        return handle.getHandshakeHandler();
    }
    
    public String toString() {
        return "PacketHandshakingInSetProtocol["
                + "version='" + clientVersion + "', "
                + "hostname='" + hostname + "', "
                + "port='" + port + "', "
                + "protocol='" + protocol + "']";
    }

    public int getClientVersion() {
        return clientVersion;
    }

    public String getHostname() {
        return hostname;
    }

    public int getPort() {
        return port;
    }

    public byte getProtocol() {
        return protocol;
    }
}

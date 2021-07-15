package com.parallelcraft.Networking.Packets;

import com.parallelcraft.Networking.ClientConnectionHandler;
import com.parallelcraft.Networking.PacketHandler;
import com.parallelcraft.Networking.PacketIncoming;
import com.parallelcraft.Networking.PacketReadHelper;
import com.parallelcraft.exceptions.InsufficientDataException;

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
        this.clientVersion = data.readVarLenInteger();
        this.hostname = data.readVarLenString(255);
        this.port = data.readUnsignedShort();
        this.protocol = data.readByte();
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

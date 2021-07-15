package com.parallelcraft.Networking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * 
 * @author extremeCrazyCoder
 */
public class ServerPortManager implements Runnable {
    private static ServerPortManager SINGLETON = null;
    public static ServerPortManager getSingleton() {
        //TODO remove singleton from here could be nice to support multiple "servers"
        if(SINGLETON == null) {
            SINGLETON = new ServerPortManager();
        }
        return SINGLETON;
    }
    
    private int port;
    private String hostname;
    //TODO make this multi thread safe
    private List<ClientConnectionHandler> connectedClients = new ArrayList<>();
    
    private Thread mainPortThread = null;
    private ServerSocketChannel serverSock = null;
    private Selector serverSelect = null;
    
    private Logger logger = LogManager.getLogger("ServerPortManager");
    
    private KeyPair serverKey = null;
    private SecureRandom random;
    
    public void boot(String hostname, int port) {
        this.port = port;
        this.hostname = hostname;
        
        mainPortThread = new Thread(this);
        mainPortThread.start();
        
        
        // creating the object of KeyPairGenerator 
        KeyPairGenerator kpg;
        try {
            kpg = KeyPairGenerator.getInstance("RSA");
        } catch(NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        // initializing with 1024 
        kpg.initialize(1024); 

        // getting key pairs 
        // using genKeyPair() method 
        serverKey = kpg.genKeyPair();
        
        try {
            random = SecureRandom.getInstanceStrong();
        } catch(NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        //see https://github.com/teocci/NioSocketCodeSample/blob/master/src/com/github/teocci/nio/socket/nio/NonBlockingWRServer.java
        logger.debug("Booting server channel...");
        try {
            serverSock = ServerSocketChannel.open();
            serverSock.configureBlocking(false);
            serverSock.socket().bind(new InetSocketAddress(hostname, port));
            
            serverSelect = Selector.open();
            serverSock.register(serverSelect, SelectionKey.OP_ACCEPT);
            
            while(! Thread.currentThread().isInterrupted()) {
                serverSelect.select(5000);
                
                for(SelectionKey key : serverSelect.selectedKeys()) {
                    if(! key.isValid()) {
                        continue;
                    }
                    
                    if(key.isAcceptable()) {
                        if(serverSock != key.channel()) {
                            throw new IllegalStateException("Server Sock is not key channel");
                        }
                        SocketChannel channel = serverSock.accept();
                        if(channel != null) {
                            channel.configureBlocking(true);

                            ClientConnectionHandler clientConn = new ClientConnectionHandler(this, channel);
                            connectedClients.add(clientConn);
                        }
                    }
                }
                
            }
        } catch (IOException ex) {
            logger.error(ex);
        } finally {
            if(serverSock != null) {
                try {
                    serverSock.close();
                } catch (IOException ex) {
                    logger.error(ex);
                }
            }
        }
    }
    
    public KeyPair getKeyPair() {
        return serverKey;
    }
    
    public SecureRandom getRandom() {
        return random;
    }
}

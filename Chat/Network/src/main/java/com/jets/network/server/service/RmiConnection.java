package com.jets.network.server.service;

import java.io.FileReader;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;

/**
 * used to connect to the rmi server
 * @author Mohamed Ali
 */
class RmiConnection {
	private static RmiConnection rmiConnection;
	private Registry registry;
    private static final String HOST="host";
    private static final String PORT="port";
    private static final String FILE="/rmi.properties";
    
    private RmiConnection(){
    	try {
    		Properties properties=new Properties();
        	properties.load(new FileReader(FILE));
        	String host=properties.getProperty(HOST);
        	int port=Integer.parseInt(properties.getProperty(PORT));
        	registry = LocateRegistry.createRegistry(port);
        }
    	catch (IOException ex) {
    		ex.printStackTrace();
    	}
    }
    
    public synchronized static RmiConnection getInstance(){
        if(rmiConnection==null) {
        	rmiConnection=new RmiConnection();
        }
        return rmiConnection;
    }
    
    public Registry getRegistry() {
    	return registry;
    }
}

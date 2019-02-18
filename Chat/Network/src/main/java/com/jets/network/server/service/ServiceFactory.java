package com.jets.network.server.service;

import java.io.FileReader;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.registry.Registry;
import java.util.Properties;
import java.util.Set;

import com.jets.network.common.RmiConnection;
import com.jets.network.exception.NoSuchServiceException;

/**
 * create services when the server starts
 * @author Mohamed Ali
 */
public class ServiceFactory {
	
	private static ServiceFactory serviceFactory;
	private static final String DEFAULT_FILE="/services.properties";
    
    private ServiceFactory(){
    }
    
    public synchronized static ServiceFactory getInstance(){
        if(serviceFactory==null)
        	serviceFactory=new ServiceFactory();
        return serviceFactory;
    }
    
    public void startServices(String file) {
    	try {
    		Properties properties=new Properties();
        	properties.load(new FileReader(file));
        	Set<String> serviceNames=properties.stringPropertyNames();
        	Registry registry=RmiConnection.getInstance().getRegistry();
        	for(String serviceName:serviceNames) {
        		String serviceClass=properties.getProperty(serviceName);
        		Object service=Class.forName(serviceClass).newInstance();
        		if(service instanceof Remote) {
        			registry.rebind(serviceName, (Remote)service);
        		}
        		else {
        			throw new NoSuchServiceException("not a Remote object");
        		}
        	}
        }
    	catch (IOException ex) {
    		ex.printStackTrace();
    	}
    	catch (ClassNotFoundException ex) {
    		throw new NoSuchServiceException("no such service class");
    	}
    	catch (IllegalAccessException ex) {
    		ex.printStackTrace();
		}
    	catch (InstantiationException ex) {
    		ex.printStackTrace();
    	}
    }
}

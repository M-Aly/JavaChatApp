package com.jets.network.server.service;

import java.io.FileReader;
import java.io.IOException;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.jets.network.exception.NoSuchServiceException;

/**
 * create services when the server starts
 * @author Mohamed Ali
 */
public class ServiceFactory {
	
	private static ServiceFactory serviceFactory;
	private static final String DEFAULT_FILE="C:/services.properties";
	private Map<String,Remote> serviceMap = new HashMap<>();
    
    private ServiceFactory(){
    }
    
    public synchronized static ServiceFactory getInstance(){
        if(serviceFactory==null) {
        	serviceFactory=new ServiceFactory();
        }
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
        			serviceMap.put(serviceName,(Remote)service);
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
    
    public void startServices() {
    	startServices(DEFAULT_FILE);
    }
    
    public void stopServices() {
		Registry registry=RmiConnection.getInstance().getRegistry();
    	for(String serviceName:serviceMap.keySet()) {
    		try {
				registry.unbind(serviceName);
			}
    		catch (AccessException ex) {
				ex.printStackTrace();
			}
    		catch (RemoteException ex) {
				ex.printStackTrace();
			}
    		catch (NotBoundException ex) {
				ex.printStackTrace();
			}
    		serviceMap.remove(serviceName);
    	}
    }
    
    public Map<String,Remote> getServices() {
    	return serviceMap;
    }
}

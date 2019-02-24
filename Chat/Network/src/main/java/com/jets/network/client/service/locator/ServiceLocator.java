package com.jets.network.client.service.locator;

import java.rmi.Remote;

import com.jets.network.exception.ConnectionException;

/**
 * service locator the client uses
 * @author Mohamed Ali
 */
public class ServiceLocator {
	
	private ServiceCache serviceCache;
	private InitialContext locatorContext;
	private static ServiceLocator serviceLocator;
    
    private ServiceLocator(){
    	serviceCache=new ServiceCache();
    	locatorContext=new InitialContext();
    }
    
    public synchronized static ServiceLocator getInstance(){
        if(serviceLocator==null) {
        	serviceLocator=new ServiceLocator();
        }
        return serviceLocator;
    }
    
    public Remote getService(String serviceName) throws ConnectionException {
    	Remote service=serviceCache.getService(serviceName);
    	if(service==null) {
    		service=locatorContext.lookup(serviceName);
    		if(service!=null) {
    			serviceCache.putService(serviceName, service);
    		}
    	}
    	return service;
    }
}

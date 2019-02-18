package com.jets.network.client.service.locator;

import java.rmi.Remote;
import java.util.HashMap;
import java.util.Map;

/**
 * cache of the service locator
 * @author Mohamed Ali
 */
class ServiceCache {
	
	private Map<String,Remote> services;
	
	public ServiceCache() {
		services = new HashMap<>();
	}
	
	public void putService(String serviceName, Remote service) {
		services.put(serviceName, service);
	}
	
	public Remote getService(String serviceName) {
		return services.get(serviceName);
	}

}

package com.jets.network.client.service.locator;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import com.jets.network.common.RmiConnection;
import com.jets.network.exception.NoSuchServiceException;
import com.jets.network.exception.ConnectionException;

/**
 * initial context of the service locator
 * @author Mohamed Ali
 */
class InitialContext {
	public Remote lookup(String serviceName) throws ConnectionException {
		Remote service=null;
		try {
			Registry registry=RmiConnection.getInstance().getRegistry();
			if(registry!=null) {
				service=registry.lookup(serviceName);
			}
		}
		catch (NotBoundException ex) {
			throw new NoSuchServiceException(ex.getMessage());
		}
		catch (RemoteException ex) {
			throw new ConnectionException("Access denied");
		}
		return service;
	}
}

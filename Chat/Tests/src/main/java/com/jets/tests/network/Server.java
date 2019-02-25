package com.jets.tests.network;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.jets.network.server.service.ServiceFactory;
import com.jets.network.server.service.impl.Announcement;

public class Server {
	
	 public static void main(String[] args) {
		 /*
	        try {
	            Announcement announcement=new Announcement();
	            Registry registry = LocateRegistry.createRegistry(6601);
	            registry.rebind("chat", announcement);
	            System.out.println("Server  Started");
	        } catch (RemoteException ex) {
	            ex.printStackTrace();
	        }
	        */
		 ServiceFactory.getInstance().startServices();
	       
	    }

}

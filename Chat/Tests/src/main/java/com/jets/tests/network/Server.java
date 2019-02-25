package com.jets.tests.network;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.jets.network.server.service.ServiceFactory;
import com.jets.network.server.service.impl.Announcement;
import com.jets.network.server.service.impl.IntroduceUser;

public class Server {
	
	 public static void main(String[] args) {
		 
	        try {
	        	
	            Announcement announcement=new Announcement();
	            IntroduceUser introduceUser = new IntroduceUser();
	            Registry registry = LocateRegistry.createRegistry(5000);
	            registry.rebind("chat", announcement);
	            registry.rebind("introduce", introduceUser);
	            registry.rebind("login", introduceUser);
	            System.out.println("Server  Started");
	        } catch (RemoteException ex) {
	            ex.printStackTrace();
	        }
	        
		// ServiceFactory.getInstance().startServices();
	       
	    }

}

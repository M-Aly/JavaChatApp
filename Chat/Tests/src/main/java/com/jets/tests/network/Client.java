package com.jets.tests.network;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.jets.network.client.impl.AnnouncementReceiver;
import com.jets.network.common.callback.AnnouncementReceiverInt;
import com.jets.network.common.serverservice.AnnouncementsInt;
import com.jets.network.exception.NoSuchUserException;
import com.jets.network.server.service.impl.Announcement;

public class Client {
	
	
	
	public static void main(String argc[])
	{
		AnnouncementsInt announcementsInt;
		
		try {
			//Announcement receiver = new Announcement();
			 
			
			Registry registry = LocateRegistry.getRegistry(6601);
			AnnouncementsInt receiver = (AnnouncementsInt) registry.lookup("chat");
			// Announcement announcement= new Announcement();
			//announcement.observe(receiver);
			receiver.broadcastAnnouncement("Hello");		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
	}
	

	}}

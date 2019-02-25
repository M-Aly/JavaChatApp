package com.jets.network.common.callback.impl;

/**
 * @author Amer Salah
 */



import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import com.jets.network.common.callback.AnnouncementReceiverInt;
import com.jets.network.common.serverservice.AnnouncementsInt;
import com.jets.network.exception.NoSuchUserException;

/**
 * @author Amer Salah
 */

public class Announcement  extends UnicastRemoteObject implements AnnouncementsInt {
	
	
	public Announcement() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	ArrayList<AnnouncementReceiverInt> users = new ArrayList<>();

	@Override
	public void observe(AnnouncementReceiverInt announcementReceiver) throws RemoteException, NoSuchUserException {
		
		users.add(announcementReceiver);
		
	}

	@Override
	public void unObserve(AnnouncementReceiverInt announcementReceiver) throws RemoteException, NoSuchUserException {
		
		users.remove(announcementReceiver);
		
	}

	@Override
	public void broadcastAnnouncement(String announcement) throws RemoteException {
		
		if(!users.isEmpty()) {
			
			for(AnnouncementReceiverInt  receiver : users)
			{
				
				receiver.receiveAnnouncement(announcement);
			}
			
		}
		
	}

	

	
}

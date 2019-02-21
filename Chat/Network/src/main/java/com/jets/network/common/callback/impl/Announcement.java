package com.jets.network.common.callback.impl;

/**
 * @author Amer Salah
 */



import java.rmi.RemoteException;
import java.util.ArrayList;

import com.jets.network.common.callback.AnnouncementReceiverInt;
import com.jets.network.common.serverservice.AnnouncementsInt;
import com.jets.network.exception.NoSuchUserException;

public class Announcement implements AnnouncementsInt {
	
	
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

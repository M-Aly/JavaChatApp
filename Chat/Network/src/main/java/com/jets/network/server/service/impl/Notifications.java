package com.jets.network.server.service.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.jets.database.dal.dto.Friend;
import com.jets.network.common.callback.NotifcationReceiverInt;
import com.jets.network.common.serverservice.NotificationsInt;
import com.jets.network.exception.NoSuchUserException;

public class Notifications implements NotificationsInt{
	
	ArrayList<NotifcationReceiverInt> friends = new ArrayList<>();

	@Override
	public void observe(NotifcationReceiverInt notifcationReceiver) throws RemoteException, NoSuchUserException {
		
		friends.add(notifcationReceiver);
		
	}

	@Override
	public void unObserve(NotifcationReceiverInt notifcationReceiver) throws RemoteException, NoSuchUserException {
		
		friends.remove(notifcationReceiver);
		
	}

	@Override
	public void sendFriendRequestNotifications(List<Friend> friendsRequests) throws RemoteException {
		
		for(int i=0 ;i<friendsRequests.size() ; i++)
		{
			this.friends.get(i).receiveFriendRequestNotifications(friendsRequests);
		}
		
		
	}

	@Override
	public void sendFriendStatusChangeNotifications(List<Friend> friendsNotification) throws RemoteException {
		
		
		for(int i=0 ;i<friendsNotification.size() ; i++)
		{
			this.friends.get(i).receiveFriendStatusChangeNotifications(friendsNotification);	
		}
		
	}

}

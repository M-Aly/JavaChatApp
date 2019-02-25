package com.jets.network.server.impl;

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
	public void sendFriendRequestNotifications(List<Friend> friends) throws RemoteException {
		
		
	}

	@Override
	public void sendFriendStatusChangeNotifications(List<Friend> friends) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}

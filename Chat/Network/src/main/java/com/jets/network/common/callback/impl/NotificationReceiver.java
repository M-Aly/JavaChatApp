package com.jets.network.common.callback.impl;

import java.rmi.RemoteException;
import java.util.List;

import com.jets.database.dal.dto.Friend;
import com.jets.network.common.callback.NotifcationReceiverInt;

/**
 * @author Amer Salah
 */

public class NotificationReceiver implements NotifcationReceiverInt {
	

	@Override
	public void receiveFriendRequestNotifications(List<Friend> friends) throws RemoteException {
		
		
	}

	@Override
	public void receiveFriendStatusChangeNotifications(List<Friend> friends) throws RemoteException {
		
		
	}

}

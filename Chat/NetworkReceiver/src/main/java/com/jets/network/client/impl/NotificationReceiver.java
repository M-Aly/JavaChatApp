package com.jets.network.client.impl;

import java.rmi.RemoteException;
import java.util.List;

import com.jets.database.dal.dto.Friend;
import com.jets.gui.controller.client.DashboardController;
import com.jets.network.common.callback.NotifcationReceiverInt;

/**
 * @author Mohamed Ali
 */

public class NotificationReceiver implements NotifcationReceiverInt {
	
	private DashboardController dashboardController;
	
	public NotificationReceiver(DashboardController dashboardController) {
		this.dashboardController=dashboardController;
	}

	@Override
	public void receiveFriendRequestNotifications(List<Friend> friends) throws RemoteException {
		dashboardController.receiveFriendRequestNotifications(friends);
		
	}

	@Override
	public void receiveFriendStatusChangeNotifications(List<Friend> friends) throws RemoteException {
		dashboardController.receiveFriendStatusChangeNotifications(friends);
		
	}

}

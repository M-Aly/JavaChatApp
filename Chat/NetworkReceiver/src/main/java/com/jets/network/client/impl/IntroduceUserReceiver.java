package com.jets.network.client.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import com.jets.database.dal.dto.Friend;
import com.jets.database.dal.dto.Group;
import com.jets.gui.controller.client.DashboardController;
import com.jets.gui.controller.client.notifications.ItemListStatusController;
import com.jets.network.common.callback.AnnouncementReceiverInt;
import com.jets.network.common.callback.ChatReceiverInt;
import com.jets.network.common.callback.IntroduceUserReceiverInt;

/**
 * @author Mohamed Ali
 */

public class IntroduceUserReceiver  extends UnicastRemoteObject implements IntroduceUserReceiverInt {

	private DashboardController dashboardController;
	
	public IntroduceUserReceiver(DashboardController dashboardController) throws RemoteException {
		this.dashboardController=dashboardController;
	}

	@Override
	public void receiveFriends(List<Friend> friends) throws RemoteException {
		dashboardController.receiveFriends(friends);
		
	}

	@Override
	public void receiveGroups(List<Group> groups) throws RemoteException {
		dashboardController.receiveGroups(groups);
		
	}

	

}

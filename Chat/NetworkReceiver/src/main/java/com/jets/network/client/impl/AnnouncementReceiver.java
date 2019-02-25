package com.jets.network.client.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.jets.gui.controller.client.DashboardController;
import com.jets.gui.controller.client.notifications.ItemListStatusController;
import com.jets.network.common.callback.AnnouncementReceiverInt;

/**
 * @author Mohamed Ali
 */

public class AnnouncementReceiver  extends UnicastRemoteObject implements AnnouncementReceiverInt {

	private DashboardController dashboardController;
	
	public AnnouncementReceiver(DashboardController dashboardController) throws RemoteException {
		this.dashboardController=dashboardController;
	}

	@Override
	public void receiveAnnouncement(String announcement) throws RemoteException {
		dashboardController.receiveAnnouncement(announcement);
	}

}

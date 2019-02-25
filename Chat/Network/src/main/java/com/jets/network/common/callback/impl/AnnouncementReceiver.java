package com.jets.network.common.callback.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.jets.network.common.callback.AnnouncementReceiverInt;

/**
 * @author Amer Salah
 */

public class AnnouncementReceiver  extends UnicastRemoteObject implements AnnouncementReceiverInt {

	public AnnouncementReceiver() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void receiveAnnouncement(String announcement) throws RemoteException {
		System.out.println(announcement);
		
	}

}

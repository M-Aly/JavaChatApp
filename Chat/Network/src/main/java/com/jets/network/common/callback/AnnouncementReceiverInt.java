package com.jets.network.common.callback;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AnnouncementReceiverInt extends Remote{
	
	public void receiveAnnouncement(String announcement) throws RemoteException;

}

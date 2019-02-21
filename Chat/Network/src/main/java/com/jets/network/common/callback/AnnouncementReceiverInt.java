package com.jets.network.common.callback;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Amer Salah
 */

public interface AnnouncementReceiverInt extends Remote{
	
	void receiveAnnouncement(String receivedAnnouncement) throws RemoteException;

}

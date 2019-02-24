package com.jets.network.common.callback;

import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

import com.healthmarketscience.rmiio.RemoteInputStream;
import com.jets.database.dal.dto.User;

/**
receive chat messages and files
@author Mohamed Ali
*/
public interface ChatReceiverInt extends Remote {
	/**
	accept or reject
	*/
	boolean acceptFile(User senderUser,UUID uuid,File file) throws RemoteException;
	
	/**
	receive file if it is accepted
	*/
	void receiveFile(User senderUser,UUID uuid,File file,RemoteInputStream fileStream) throws RemoteException;
	
	/**
	receive message
	*/
	void receiveMessage(User senderUser,UUID uuid,String message) throws RemoteException;
}

package com.jets.network.common.callback;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.jets.database.dal.dto.Friend;
import com.jets.database.dal.dto.Group;

/**
receive friends and groups
@author Mohamed Ali
*/
public interface IntroduceUserReceiverInt extends Remote {
	/**
	receive friends
	*/
	void receiveFriends(List<Friend> friends) throws RemoteException;
	
	/**
	receive groups
	*/
	void receiveGroups(List<Group> groups) throws RemoteException;
}

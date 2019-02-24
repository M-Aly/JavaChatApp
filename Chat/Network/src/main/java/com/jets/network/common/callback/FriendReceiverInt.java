package com.jets.network.common.callback;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.jets.database.dal.dto.Friend;

public interface FriendReceiverInt extends Remote {
	void receiveFriends(List<Friend> friends) throws RemoteException;
}

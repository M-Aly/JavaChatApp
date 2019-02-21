package com.jets.network.common.callback;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.jets.database.dal.dto.Friend;

public interface NotifcationReceiverInt extends Remote {
	/**
    receive friend requests
    */
    void receiveFriendRequestNotifications(List<Friend> friends) throws RemoteException;
    
    /**
    receive friend status changes
    */
    void receiveFriendStatusChangeNotifications(List<Friend> friends) throws RemoteException;
    
    
    
}

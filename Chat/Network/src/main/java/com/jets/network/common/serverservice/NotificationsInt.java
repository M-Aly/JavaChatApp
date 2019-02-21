package com.jets.network.common.serverservice;

import com.jets.database.dal.dto.Friend;
import com.jets.network.common.callback.NotifcationReceiverInt;
import com.jets.network.exception.NoSuchUserException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Mohamed Ali
 */
public interface NotificationsInt extends Remote {
    /**
    observe server notifications
    */
    void observe(NotifcationReceiverInt notifcationReceiver) throws RemoteException,NoSuchUserException;
    
    /**
    un observe server notifications
    */
    void unObserve(NotifcationReceiverInt notifcationReceiver) throws RemoteException,NoSuchUserException;
    
    /**
    send friend requests
    */
    void sendFriendRequestNotifications(List<Friend> friends) throws RemoteException;
    
    /**
    send friend status changes
    */
    void sendFriendStatusChangeNotifications(List<Friend> friends) throws RemoteException;
}

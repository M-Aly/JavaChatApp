package com.jets.network.common.callback;

import com.jets.database.dal.dto.Friend;
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
    void observe(String phoneNumber) throws RemoteException,NoSuchUserException;
    
    /**
    un observe server notifications
    */
    void unObserve(String phoneNumber) throws RemoteException,NoSuchUserException;
    
    /**
    friend requests
    */
    void friendRequestNotifications(List<Friend> friends) throws RemoteException;
    
    /**
    friend status changes
    */
    void friendStatusChangeNotifications(List<Friend> friends) throws RemoteException;
}

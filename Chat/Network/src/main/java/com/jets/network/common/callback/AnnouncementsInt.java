package com.jets.network.common.callback;

import com.jets.network.exception.NoSuchUserException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Mohamed Ali
 */
public interface AnnouncementsInt extends Remote {
    /**
    observe server announcements
    */
    void observe(String phoneNumber) throws RemoteException,NoSuchUserException;
    
    /**
    un observe server announcements
    */
    void unObserve(String phoneNumber) throws RemoteException,NoSuchUserException;
    
    /**
    when the server admin sends an announcement
    */
    void getAnnouncement(String announcement) throws RemoteException;
}

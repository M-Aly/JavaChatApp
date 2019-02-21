package com.jets.network.common.serverservice;

import com.jets.network.common.callback.AnnouncementReceiverInt;
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
    void observe(AnnouncementReceiverInt announcementReceiver) throws RemoteException,NoSuchUserException;
    
    /**
    un observe server announcements
    */
    void unObserve(AnnouncementReceiverInt announcementReceiver) throws RemoteException,NoSuchUserException;
    
    /**
     * broadCast Announcement
     */
    void broadcastAnnouncement(String announcement) throws RemoteException;

}

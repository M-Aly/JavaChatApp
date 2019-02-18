package com.jets.network.common.serverservice;

import com.jets.database.dal.dto.Friend;
import com.jets.database.dal.dto.Group;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * search
 * @author Mohamed Ali
 */
public interface SearchInt extends Remote {
    /**
    search friend by phone number or name
    */
    List<Friend> searchFriend(String search) throws RemoteException;
    
    /**
    search group by name
    */
    List<Group> searchGroup(String search) throws RemoteException;
}

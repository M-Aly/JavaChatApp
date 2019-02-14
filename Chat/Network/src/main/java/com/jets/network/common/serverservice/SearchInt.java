package com.jets.network.common.serverservice;

import com.jets.database.dal.dto.Group;
import com.jets.database.dal.dto.User;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Mohamed Ali
 */
public interface SearchInt extends Remote {
    /**
    search user by phone number or name
    */
    User searchUser(String search) throws RemoteException;
    
    /**
    search group by name
    */
    Group searchGroup(String search) throws RemoteException;
}

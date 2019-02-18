package com.jets.network.common.serverservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import com.jets.network.exception.NoSuchUserException;
import com.jets.network.exception.StatusChangeFailedException;
import com.jets.network.exception.UpdateUserFailedException;
import com.jets.database.dal.dto.User;

/**
 * log in
 * register
 * sign out
 * @author Mohamed Ali
 */
public interface IntroduceUserInt extends Remote {
    /**
    persist user
    */
    void register(User user) throws RemoteException,NoSuchUserException;
    
    /**
    retrieve user from database and callback if it is the first time to log in
    */
    User logIn(String phoneNumber,String password) throws RemoteException;
    
    /**
    update status to offline and callback online friends
    */
    void signOut(User user) throws RemoteException,StatusChangeFailedException;
}

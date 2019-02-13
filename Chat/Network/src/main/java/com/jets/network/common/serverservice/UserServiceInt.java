package com.jets.network.common.serverservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import com.jets.network.exception.NoSuchUserException;
import com.jets.network.exception.StatusChangeFailedException;
import com.jets.network.exception.UpdateUserFailedException;
import com.jets.dal.dto.User;

/**
 *
 * @author Mohamed Ali
 */
public interface UserServiceInt extends Remote {
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
    
    /**
    update profile in database
    */
    void updateProfile(User user) throws RemoteException,UpdateUserFailedException;
    
    /**
    search user by phone number or name
    */
    User searchUser(String search) throws RemoteException;
}

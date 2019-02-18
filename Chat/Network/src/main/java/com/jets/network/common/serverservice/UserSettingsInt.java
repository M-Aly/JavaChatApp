package com.jets.network.common.serverservice;

import com.jets.database.dal.dto.Group;
import com.jets.database.dal.dto.User;
import com.jets.database.dal.dto.enums.UserStatus;
import com.jets.network.exception.UpdateUserFailedException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Mohamed Ali
 */
public interface UserSettingsInt extends Remote {
    /**
    update profile in database
    */
    void updateProfile(User user) throws RemoteException,UpdateUserFailedException;
    
    /**
    update status
    */
    void updateStatus(UserStatus userStatus) throws RemoteException,UpdateUserFailedException;
    
    /**
    add friend
    */
    void addFriend(User user) throws RemoteException,UpdateUserFailedException;
    
    /**
    remove friend
    */
    void removeFriend(User user) throws RemoteException,UpdateUserFailedException;
    
    /**
    add friends
    */
    void addFriends(List<User> friends) throws RemoteException,UpdateUserFailedException;
    
    /**
    add group
    */
    void addGroup(Group group) throws RemoteException,UpdateUserFailedException;
    
    /**
    remove group
    */
    void removeGroup(Group group) throws RemoteException,UpdateUserFailedException;
}
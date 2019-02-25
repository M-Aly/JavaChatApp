package com.jets.network.common.serverservice;

import com.jets.database.dal.dto.Friend;
import com.jets.database.dal.dto.Group;
import com.jets.database.dal.dto.User;
import com.jets.database.dal.dto.enums.UserStatus;
import com.jets.network.exception.UpdateUserFailedException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * profile and status
 * @author Mohamed Ali
 */
public interface UserSettingsInt extends Remote {
    /**
    update profile in database
    */
    void updateProfile(User user) throws RemoteException,UpdateUserFailedException;
    
    /**
    add friend
    */
    void addFriend(User user,Friend friend) throws RemoteException,UpdateUserFailedException;
    
    /**
    remove friend
    */
    void removeFriend(User user,Friend friend) throws RemoteException,UpdateUserFailedException;
    
    /**
    add friends
    */
    void addFriends(User user,List<Friend> friends) throws RemoteException,UpdateUserFailedException;
    
    /**
    add group
    */
    void addGroup(User user,Group group) throws RemoteException,UpdateUserFailedException;
    
    /**
    remove group
    */
    void removeGroup(User user,Group group) throws RemoteException,UpdateUserFailedException;
}

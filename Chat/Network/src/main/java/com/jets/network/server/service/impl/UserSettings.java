package com.jets.network.server.service.impl;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import com.jets.database.dal.dao.impl.FriendsDao;
import com.jets.database.dal.dao.impl.GroupDao;
import com.jets.database.dal.dao.impl.UserDao;
import com.jets.database.dal.dto.Friend;
import com.jets.database.dal.dto.Group;
import com.jets.database.dal.dto.User;
import com.jets.database.dal.dto.enums.UserStatus;
import com.jets.network.common.serverservice.UserSettingsInt;
import com.jets.network.exception.UpdateUserFailedException;

/**
 * 
 * @author Nouran Amr
 *
 */
public class UserSettings implements UserSettingsInt {

	UserDao currentUser;
	FriendsDao currentFriend;
	GroupDao groupobj;
	
	
	public UserSettings() {
		
		currentUser = new UserDao();
		
	}
	
	
	
	
	@Override
	public void updateProfile(User user) throws RemoteException, UpdateUserFailedException {
		
		
	
		try {
			currentUser.update(user);
		} catch (SQLException e) {
			
			throw new UpdateUserFailedException();
		}
		
	}

	@Override
	public void addFriend(User user,Friend friend) throws RemoteException, UpdateUserFailedException {
		
		try {
			currentFriend = new FriendsDao(user);
			currentFriend.persist(friend);
		} catch (SQLException e) {
			
			throw new UpdateUserFailedException();
		}
		
	}

	@Override
	public void removeFriend(User user,Friend friend) throws RemoteException,UpdateUserFailedException {
		try {
			currentFriend = new FriendsDao(user);
			currentFriend.delete(friend.getPhoneNumber());
		} catch (SQLException e) {
			
			throw new UpdateUserFailedException();
		}
		
		
	}

	@Override
	public void addFriends(User user,List<Friend> friends) throws RemoteException, UpdateUserFailedException {
		currentFriend = new FriendsDao(user);
		for(int i=0 ;i<friends.size();i++) {
		try {
			
			currentFriend.persist(friends.get(i));
		} catch (SQLException e) {
			throw new  UpdateUserFailedException();
		}
		}
		
	}

	@Override
	public void addGroup(User user,Group group) throws RemoteException, UpdateUserFailedException {
		
		try {
			groupobj = new GroupDao(user.getPhoneNumber());
			groupobj.persist(group);
		} catch (SQLException e) {
			throw new  UpdateUserFailedException();
		}
	}

	@Override
	public void removeGroup(User user,Group group) throws RemoteException, UpdateUserFailedException {
		try {
			groupobj = new GroupDao(user.getPhoneNumber());
			groupobj.delete(group.getGroupId());
		} catch (SQLException e) {
			throw new  UpdateUserFailedException();
		}
		
	}

}

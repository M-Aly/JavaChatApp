package com.jets.network.server.impl;

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
	@Override
	public void updateProfile(User user) throws RemoteException, UpdateUserFailedException {
		
		
	
		try {
			currentUser.update(user);
		} catch (SQLException e) {
			
			throw new UpdateUserFailedException();
		}
		
	}

	@Override
	public void updateStatus(UserStatus userStatus) throws RemoteException, UpdateUserFailedException {
	
		//not implement yet
		
	}

	@Override
	public void addFriend(Friend friend) throws RemoteException, UpdateUserFailedException {
		try {
			currentFriend.persist(friend);
		} catch (SQLException e) {
			
			throw new UpdateUserFailedException();
		}
		
	}

	@Override
	public void removeFriend(Friend friend) throws RemoteException,UpdateUserFailedException {
		try {
			currentFriend.delete(friend.getPhoneNumber());
		} catch (SQLException e) {
			
			throw new UpdateUserFailedException();
		}
		
		
	}

	@Override
	public void addFriends(List<Friend> friends) throws RemoteException, UpdateUserFailedException {
		for(int i=0 ;i<friends.size();i++) {
		try {
			currentFriend.persist(friends.get(i));
		} catch (SQLException e) {
			throw new  UpdateUserFailedException();
		}
		}
		
	}

	@Override
	public void addGroup(Group group) throws RemoteException, UpdateUserFailedException {
		
		try {
			groupobj.persist(group);
		} catch (SQLException e) {
			throw new  UpdateUserFailedException();
		}
	}

	@Override
	public void removeGroup(Group group) throws RemoteException, UpdateUserFailedException {
		try {
			groupobj.delete(group.getGroupId());
		} catch (SQLException e) {
			throw new  UpdateUserFailedException();
		}
		
	}

}

package com.jets.network.server.impl;

import java.rmi.RemoteException;
import java.util.List;

import com.jets.database.dal.dao.impl.FriendsDao;
import com.jets.database.dal.dao.impl.GroupDao;
import com.jets.database.dal.dto.Friend;
import com.jets.database.dal.dto.Group;
import com.jets.network.common.serverservice.SearchInt;

/**
  *
 * @author Nouran Amr
 *
 */
public class Search implements SearchInt {

	FriendsDao friend;
	GroupDao group;
	@Override
	public List<Friend>  searchFriend(String search) throws RemoteException {
		
		
		List<Friend> friendList=friend.retrieveByName(search);
		
		return friendList;
	}

	@Override
	public List<Group> searchGroup(String search) throws RemoteException {
		
		List<Group>groupList=group.retrieveByName(search);
		
		return groupList;
	}

}

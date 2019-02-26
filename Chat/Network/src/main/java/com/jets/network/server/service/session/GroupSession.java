package com.jets.network.server.service.session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.jets.database.dal.dto.Group;

/**
@author Mohamed Ali
*/
public class GroupSession implements Session {
	
	private UUID uuid;
	
	private List<String> friendList;
	
	private String userPhoneNumber;
	
	private int groupId;
	
	public GroupSession(String userPhoneNumber, Group group) {
		this.userPhoneNumber = userPhoneNumber;
		this.groupId = group.getGroupId();
		this.friendList = new ArrayList<String>();
		this.uuid = UUID.randomUUID();
	}
	
	public final UUID getUuid() {
		return uuid;
	}
	
	public final String getUserPhoneNumber() {
		return userPhoneNumber;
	}
	
	public int getGroupId() {
		return groupId;
	}

	public final List<String> getFriendList() {
		List<String> friendList = new ArrayList<String>(this.friendList.size());
		Collections.copy(friendList, this.friendList);
		return friendList;
	}
	
	public final int getNumberOfFriends() {
		return friendList.size();
	}
	
	public final void addUser(String userPhoneNumber) {
		this.friendList.add(userPhoneNumber);
	}
	
	public final void removeUser(String userPhoneNumber) {
		this.friendList.remove(userPhoneNumber);
	}
}

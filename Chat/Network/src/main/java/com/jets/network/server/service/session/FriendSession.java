package com.jets.network.server.service.session;

import java.util.UUID;

/**
@author Mohamed Ali
*/
public class FriendSession implements Session {
	
	private UUID uuid;
	
	private String friendPhoneNumber;
	
	private String userPhoneNumber;
	
	public FriendSession(String userPhoneNumber, String friendPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
		setFriendPhoneNumber(friendPhoneNumber);
		this.uuid = UUID.randomUUID();
	}
	
	public final UUID getUuid() {
		return uuid;
	}
	
	public final String getUserPhoneNumber() {
		return userPhoneNumber;
	}
	
	public final String getFriendPhoneNumber() {
		return friendPhoneNumber;
	}
	
	private final void setFriendPhoneNumber(String friendPhoneNumber) {
		this.friendPhoneNumber = friendPhoneNumber;
	}
}

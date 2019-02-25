package com.jets.database;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.jets.database.dal.dao.impl.FriendsDao;
import com.jets.database.dal.dao.impl.UserDao;
import com.jets.database.dal.dto.Friend;
import com.jets.database.dal.dto.User;
import com.jets.database.dal.dto.enums.InvitationStatus;
import com.jets.database.dal.dto.enums.UserStatus;

/**
@author Mohamed Ali
*/
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DatabaseFriendsTableTest {

	private static UserDao userDao;
	private static User testUser;
	private static FriendsDao friendsDao;
	private static User friendUser;
	private static Friend friend;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		userDao = new UserDao();
		testUser = userDao.retrieveByPhoneNumber("+0101530354");
		friendsDao = new FriendsDao(testUser);
		friendUser = userDao.retrieveByPhoneNumber("+2760006621");
		friend = new Friend(friendUser,InvitationStatus.PENDING);
	}
	
	@Test
	public void testApersistFriendTest() {
		try {
			friendsDao.persist(friend);
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		List<Friend> retrievedFriends = friendsDao.retrieveAllFriends();
		Friend retrievedFriend=retrievedFriends.get(0);
		assertEquals(null, "+2760006621", retrievedFriend.getPhoneNumber());
		assertEquals(null, "mayada", retrievedFriend.getName());
		assertEquals(null, UserStatus.OFFLINE.toString(), retrievedFriend.getStatus().toString());
		assertEquals(null, "2010-11-15", retrievedFriend.getDateOfBirth().toString());
		assertEquals(null, InvitationStatus.PENDING.toString(), retrievedFriend.getInvitationStatus().toString());
	}
	
	@Test
	public void testBupdateFriendTest() {
		Friend updateFriend = null;
		try {
			updateFriend = new Friend(friendUser,InvitationStatus.ACCEPTED);
			friendsDao.update(updateFriend);

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		List<Friend> retrievedFriends = friendsDao.retrieveAllFriends();
		Friend retrievedFriend=retrievedFriends.get(0);
		assertEquals(null, "+2760006621", retrievedFriend.getPhoneNumber());
		assertEquals(null, "mayada", retrievedFriend.getName());
		assertEquals(null, UserStatus.OFFLINE.toString(), retrievedFriend.getStatus().toString());
		assertEquals(null, "2010-11-15", retrievedFriend.getDateOfBirth().toString());
		assertEquals(null, InvitationStatus.ACCEPTED.toString(), retrievedFriend.getInvitationStatus().toString());
	}
	
	@Test
	public void testCdeleteFriendTest() {
		Friend deleteFriend = null;
		try {
			deleteFriend = new Friend(friendUser,InvitationStatus.ACCEPTED);
			friendsDao.delete(deleteFriend.getPhoneNumber());

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		List<Friend> retrievedFriends = friendsDao.retrieveAllFriends();
		assertEquals(null, null, retrievedFriends);
	}

}

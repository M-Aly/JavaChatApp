package com.jets.tests.database;

import com.jets.database.dal.dao.impl.FriendsDao;
import com.jets.database.dal.dao.impl.UserDao;
import com.jets.database.dal.dto.Friend;
import com.jets.database.dal.dto.User;
import com.jets.database.dal.dto.enums.InvitationStatus;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Mohamed Ali
 */
public class DatabaseFriendsTableTest {

	public static void main(String[] args) {

		UserDao userDao = new UserDao();
		User testUser = userDao.retrieveByPhoneNumber("+0101530354");
		FriendsDao friendsDao = new FriendsDao(testUser);
		User friendUser = userDao.retrieveByPhoneNumber("+2760006621");
		Friend friend = new Friend(friendUser,InvitationStatus.PENDING);
		
		System.out.println("insert new friend ....");
		try {
			friendsDao.persist(friend);
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		System.out.println("new friend is inserted ....\n");

		System.out.println("retrive friends by name...");
		List<Friend> returnedFriendsByName = friendsDao.retrieveByName("mayada");
		for (Friend returnedFriendByName : returnedFriendsByName) {
			System.out.println("phone number :" + returnedFriendByName.getPhoneNumber());
			System.out.println("name :" + returnedFriendByName.getName());
			System.out.println("statues :" + returnedFriendByName.getStatus());
			System.out.println("invitation status :" + returnedFriendByName.getInvitationStatus());
		}
		System.out.println("Done\n");

		System.out.println("retrive all....");
		List<Friend> returnedFriends = friendsDao.retrieveAllFriends();
		for (Friend returnedFriend : returnedFriends) {
			System.out.println("phone number :" + returnedFriend.getPhoneNumber());
			System.out.println("name :" + returnedFriend.getName());
			System.out.println("statues :" + returnedFriend.getStatus());
			System.out.println("invitation status :" + returnedFriend.getInvitationStatus());
		}
		System.out.println("Done\n");
		
		System.out.println("updating...");
		Friend updateFriend = null;
		try {
			updateFriend = new Friend(friendUser,InvitationStatus.ACCEPTED);
			friendsDao.update(updateFriend);

		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Done\n");
		
		System.out.println("deleting...");
		Friend deleteFriend = null;
		try {
			deleteFriend = new Friend(friendUser,InvitationStatus.ACCEPTED);
			friendsDao.delete(deleteFriend.getPhoneNumber());

		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Done\n");
		
	}
}

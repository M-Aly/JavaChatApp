package com.jets.database;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.jets.database.dal.dao.impl.UserDao;
import com.jets.database.dal.dto.User;
import com.jets.database.dal.dto.enums.Country;
import com.jets.database.dal.dto.enums.UserStatus;
import com.jets.database.exception.InvalidInputException;

/**
@author Mohamed Ali
*/
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DatabaseUserTableTest {

	private static User testUser = null;
	private static User testUser2 = null;
	private static UserDao userDao = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {

			testUser = new User("+0101530354", "yahiaamr", Country.Albania, "1003777776", User.CREATE_FROM_CLIENT,
					UserStatus.OFFLINE, null, "hello!", 'M', Date.valueOf("2010-11-15"), "yahiaamr@gmail.com");
			testUser2 = new User("+2760006621", "mayada", Country.Australia, "1003777776", User.CREATE_FROM_SERVER,
					UserStatus.OFFLINE, null, "hello!", 'F', Date.valueOf("2010-11-15"), "mayadakhaled@gmail.com");
		}
		catch (InvalidInputException ex) {
			ex.printStackTrace();
		}

		userDao = new UserDao();
	}

	@Test
	public void testApersistUserTest() {
		try {
			userDao.persist(testUser);
			userDao.persist(testUser2);
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		List<User> retrievedUsers = userDao.retrieveAllUsers();
		User user1=retrievedUsers.get(0);
		assertEquals(null, "+0101530354", user1.getPhoneNumber());
		assertEquals(null, "yahiaamr", user1.getName());
		assertEquals(null, UserStatus.OFFLINE.toString(), user1.getStatus().toString());
		assertEquals(null, "2010-11-15", user1.getDateOfBirth().toString());
		User user2=retrievedUsers.get(1);
		assertEquals(null, "+2760006621", user2.getPhoneNumber());
		assertEquals(null, "mayada", user2.getName());
		assertEquals(null, UserStatus.OFFLINE.toString(), user2.getStatus().toString());
		assertEquals(null, "2010-11-15", user2.getDateOfBirth().toString());
	}
	
	@Test
	public void testBretrieveByNameTest() {
		List<User> retrievedUsers = userDao.retrieveByName("yahiaamr");
		User user1=retrievedUsers.get(0);
		assertEquals(null, "yahiaamr", user1.getName());
	}
	
	@Test
	public void testCretrieveByPhoneNumberTest() {
		User retrievedUser = userDao.retrieveByPhoneNumber("+0101530354");
		assertEquals(null, "+0101530354", retrievedUser.getPhoneNumber());
	}
	
	@Test
	public void testDupdateUserTest() {
		User updateUser = null;
		try {
			updateUser = new User("+0101530354", "yahiaamr", Country.Albania, "1113777776", User.CREATE_FROM_SERVER, UserStatus.BUSY, null,
					"hello!", 'M', Date.valueOf("2010-11-15"), "yahiaamr@gmail.com");
			userDao.update(updateUser);
		}
		catch (InvalidInputException ex) {
			ex.printStackTrace();
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		List<User> retrievedUsers = userDao.retrieveAllUsers();
		User user1=retrievedUsers.get(0);
		assertEquals(null, "+0101530354", user1.getPhoneNumber());
		assertEquals(null, "yahiaamr", user1.getName());
		assertEquals(null, UserStatus.BUSY.toString(), user1.getStatus().toString());
		assertEquals(null, "2010-11-15", user1.getDateOfBirth().toString());
	}

}

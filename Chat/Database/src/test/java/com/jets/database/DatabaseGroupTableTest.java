package com.jets.database;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.jets.database.dal.dao.impl.GroupDao;
import com.jets.database.dal.dao.impl.UserDao;
import com.jets.database.dal.dto.Group;
import com.jets.database.dal.dto.User;
import com.jets.database.exception.InvalidInputException;

/**
@author Mohamed Ali
*/
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DatabaseGroupTableTest {
	
	private static UserDao userDao;
	private static User groupAdmin;
	private static User user;
	private static GroupDao groupDao;
	private static List<Group> groupList;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		userDao=new UserDao();
    	groupAdmin=userDao.retrieveByName("yahiaamr").get(0);
    	user=userDao.retrieveByName("mayada").get(0);
    	groupDao=new GroupDao(groupAdmin.getPhoneNumber());
	}

	@Test
	public void testApersistGroupTest() {
		Group persistGroup;
		try {
			persistGroup = new Group("group",groupAdmin.getPhoneNumber());
	    	groupDao.persist(persistGroup);
	    	groupList=groupDao.retrieveAllGroups();
	    	Group group=groupList.get(0);
	    	assertEquals(null, "group", group.getName());
			assertEquals(null, groupAdmin.getPhoneNumber(), group.getUserPhoneNumber());
		}
		catch (InvalidInputException ex) {
			ex.printStackTrace();
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	@Test
	public void testBupdateGroupTest() {
		try {
			Group updateGroup=groupList.get(0);
	    	updateGroup.setName("hello");
	    	updateGroup.addFriend(user.getPhoneNumber());
	    	groupDao.update(updateGroup);
	    	groupList=groupDao.retrieveAllGroups();
	    	Group group=groupList.get(0);
	    	Object[] friendPhoneNumbers = group.getFriends().toArray();
	    	assertEquals(null, "hello", group.getName());
			assertEquals(null, groupAdmin.getPhoneNumber(), group.getUserPhoneNumber());
			assertEquals(null, user.getPhoneNumber(), (String)friendPhoneNumbers[1]);
		}
		catch (InvalidInputException ex) {
			ex.printStackTrace();
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	@Test
	public void testCupdateGroupTest() {
		try {
			Group updateGroup=groupList.get(0);
	    	updateGroup.removeFriend(user.getPhoneNumber());
	    	groupDao.update(updateGroup);
	    	groupList=groupDao.retrieveAllGroups();
	    	Group group=groupList.get(0);
	    	Object[] friendPhoneNumbers = group.getFriends().toArray();
	    	assertEquals(null, "hello", group.getName());
			assertEquals(null, groupAdmin.getPhoneNumber(), group.getUserPhoneNumber());
			assertEquals(null, 1, friendPhoneNumbers.length);
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	@Test
	public void testDdeleteGroupTest() {
		try {
			Group updateGroup=groupList.get(0);
			groupDao.delete(updateGroup.getGroupId());
	    	groupList=groupDao.retrieveAllGroups();
	    	assertEquals(null, null, groupList);
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

}

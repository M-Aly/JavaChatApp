package com.jets.tests.database;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import com.jets.database.dal.dao.impl.GroupDao;
import com.jets.database.dal.dao.impl.UserDao;
import com.jets.database.dal.dto.Friend;
import com.jets.database.dal.dto.Group;
import com.jets.database.dal.dto.User;
import com.jets.database.exception.InvalidInputException;

/**
 * test of GroupDao
 * @author Mohamed Ali
 */
public class DatabaseGroupTableTest {

    public static void main(String[] args) throws SQLException,InvalidInputException {
    	UserDao userDao=new UserDao();
    	User user1=userDao.retrieveByName("yahiaamr").get(0);
    	User user2=userDao.retrieveByName("mayada khaled").get(1);
    	GroupDao groupDao=new GroupDao(user1.getPhoneNumber());
    	
    	System.out.println("persist group");
    	Group group=new Group("group",user2.getPhoneNumber());
    	groupDao.persist(group);
    	System.out.println("done\n");
    	
    	System.out.println("retrieve groups");
    	List<Group> groupList=groupDao.retrieveAllGroups();
    	for(Group group2:groupList) {
    		System.out.println("name:"+group2.getName());
    		System.out.println("id:"+group2.getGroupId());
    		System.out.println("admin:"+group2.getUserPhoneNumber());
    		Set<String> friendList=group2.getFriends();
    		for(String friend:friendList) {
    			System.out.println("friend:"+friend);
    		}
    	}
    	System.out.println();
    	
    	Group group3=groupList.get(0);
    	group3.setName("hello");
    	group3.addFriend("+12345678956");
    	groupDao.update(group3);
    	groupList=groupDao.retrieveAllGroups();
    	System.out.println("name:"+group3.getName());
		Set<String> friendList=group3.getFriends();
		for(String friend:friendList) {
			System.out.println("friend:"+friend);
		}
		System.out.println();
    	
    	System.out.println("delete group");
    	groupDao.delete(group3.getGroupId());
    }
}

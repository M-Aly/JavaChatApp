package com.jets.tests.database;

import java.sql.SQLException;
import java.util.List;

import com.jets.database.dal.dao.impl.GroupDao;
import com.jets.database.dal.dao.impl.UserDao;
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
    	User groupAdmin=userDao.retrieveByName("yahiaamr").get(0);
    	User user=userDao.retrieveByName("mayada khaled").get(1);
    	GroupDao groupDao=new GroupDao(groupAdmin.getPhoneNumber());
    	
    	System.out.println("persist group");
    	Group persistGroup=new Group("group",groupAdmin.getPhoneNumber());
    	groupDao.persist(persistGroup);
    	System.out.println("done\n");
    	List<Group> groupList=retrieveGroups(groupDao);
    	
    	System.out.println("update group");
    	Group updateGroup=groupList.get(0);
    	updateGroup.setName("hello");
    	updateGroup.addFriend(user.getPhoneNumber());
    	groupDao.update(updateGroup);
    	System.out.println("done\n");
    	groupList=retrieveGroups(groupDao);
    	
    	System.out.println("update group");
    	updateGroup=groupList.get(0);
    	updateGroup.removeFriend(groupAdmin.getPhoneNumber());
    	groupDao.update(updateGroup);
    	System.out.println("done\n");
    	groupList=retrieveGroups(groupDao);
    	
    	System.out.println("update group");
    	updateGroup=groupList.get(0);
    	groupDao.update(updateGroup);
    	System.out.println("done\n");
    	groupList=retrieveGroups(groupDao);
    	
    	System.out.println("delete group");
    	groupDao.delete(updateGroup.getGroupId());
    	System.out.println("done\n");
    	groupList=retrieveGroups(groupDao);
    }
    
    private static List<Group> retrieveGroups(GroupDao groupDao) {
    	System.out.println("retrieve groups");
    	List<Group> groupList=groupDao.retrieveAllGroups();
    	for(Group retrieveGroup:groupList) {
    		System.out.println(retrieveGroup.toString());
    	}
    	return groupList;
    }
}

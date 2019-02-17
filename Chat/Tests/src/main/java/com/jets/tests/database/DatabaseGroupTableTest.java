package com.jets.tests.database;

import com.jets.database.dal.dao.IGroupDao;
import com.jets.database.dal.dao.impl.GroupDao;
import com.jets.database.dal.dto.Group;
import com.jets.database.dal.dto.User;
import com.jets.database.dal.dto.enums.Country;
import com.jets.database.dal.dto.enums.UserStatus;
import com.jets.database.exception.InvalidInputException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zainab
 */
public class DatabaseGroupTableTest {

    public static void main(String[] args) throws SQLException {
        System.out.println("Not yet done");
        User testUser = null;
//        try {
//            testUser = new User(
//                    "1234567800",
//                    "Zainab Ashour",
//                    Country.Albania,
//                    "+20000000000",
//                    "false",
//                    UserStatus.OFFLINE,
//                    null,
//                    "Hello",
//                    'F',            
//                    new Date(2010, 11, 1),
//                    "ZaianbAshour33@gmail.com"
//                    );
//        } catch (InvalidInputException ex) {
//            ex.printStackTrace();
//        }
        GroupDao groupDao = new GroupDao(testUser);
        
        Group group = new Group(1,"friendGroup");
                  
        groupDao.persist(group);
        
       System.out.println("insert new Group...."); 

        List<Group> returnedUserGroups = groupDao.retrieveAllGroups();
        for (Group item : returnedUserGroups) {
            System.out.println("Group Id :" + item.getGroupId());
            System.out.println("Group Id :" + item.getName());
        }
        
        List<Group> returnedUserGroup = groupDao.retrieveByName("fr");
        for (Group item : returnedUserGroup) {
            System.out.println("Group Id :" + item.getGroupId());
            System.out.println("Group Id :" + item.getName());

        }
        
        group.setName("friendChatting");
        groupDao. update(group) ;
      //  groupDao. delete(group.getGroupId());
    }
}

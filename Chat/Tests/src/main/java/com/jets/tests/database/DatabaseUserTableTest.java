package com.jets.tests.database;

import com.jets.database.dal.dao.impl.FriendsDao;
import com.jets.database.dal.dao.impl.UserDao;
import com.jets.database.dal.dto.Friend;
import com.jets.database.dal.dto.User;
import com.jets.database.dal.dto.enums.Country;
import com.jets.database.dal.dto.enums.InvitationStatus;
import com.jets.database.dal.dto.enums.UserStatus;
import com.jets.database.exception.InvalidInputException;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mayada khaled
 */
public class DatabaseUserTableTest {
    public static void main(String[] args){
        System.out.println("Not yet done");
        User testUser=null;
        try {
            testUser = new User (
                    "mayada",
                    "1003",
                    "eee@gmail.com",
                    Country.Albania,
                    new Date(2010,11,1),
                    'F',
                    UserStatus.OFFLINE,
                    null,
                    "hello !",
                    "+20000000000");
        } catch (InvalidInputException ex) {
            Logger.getLogger(DatabaseUserTableTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        UserDao userDao =new UserDao((testUser));
        System.out.println("insert new user ....");
        userDao.persist(testUser);
        System.out.println("new user is inserted ....");
        System.out.println("retrive user by name...");
        List<User> returnedUserByName =userDao.retrieveByName("mayada");
        for(User item : returnedUserByName)
        {
            System.out.println("phone number :"+item.getPhoneNumber());
            System.out.println("name :"+item.getName());
            System.out.println("statues :"+item.getStatus());
        }
        System.out.println("Done");
        System.out.println("retrive by phone....");
        User returnedUserByphone =userDao.retrieveByPhoneNumber("mayada");
        
            System.out.println("phone number :"+returnedUserByphone.getPhoneNumber());
            System.out.println("name :"+returnedUserByphone.getName());
            System.out.println("statues :"+returnedUserByphone.getStatus());
        System.out.println("Done");
        System.out.println("updating...");       
        User updateUser=null;
        try {
            updateUser = new User ("zainab",
                    "0005",
                    "eee@gmail.com",
                    Country.Antigua_and_Barbuda,
                    new Date(2010,11,1),
                    'F',
                    UserStatus.AVAILABLE,
                    null,
                    "hello00000000 !",
                    "+20000000000");
        } catch (InvalidInputException ex) {
            Logger.getLogger(DatabaseUserTableTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        User newUser=null;
        try {
            newUser = new User ("khaled",
                    "1092",
                    "eee@gmail.com",
                    Country.Central_African_Republic,
                    new Date(2010,11,1),
                    'F',
                    UserStatus.AWAY,
                    null,
                    "hello00000000 !",
                    "+20000000000");
        } catch (InvalidInputException ex) {
            Logger.getLogger(DatabaseUserTableTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        userDao.persist(newUser);
        userDao.update(updateUser);
        System.out.println("Done");
        //User friend =new Friend("1002", "444", InvitationStatus.PENDING);
        FriendsDao friendDao = new FriendsDao(newUser);
        
        friendDao.persist(testUser);
        System.out.println("Done");
        friendDao.retrieveAllFriends();
        System.out.println("Done");
        friendDao.retrieveByName("mayada");
        System.out.println("Done");
    }
}

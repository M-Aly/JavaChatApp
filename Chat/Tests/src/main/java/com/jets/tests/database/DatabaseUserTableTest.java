package com.jets.tests.database;

import com.google.protobuf.Empty;
import com.jets.database.dal.dao.impl.FriendsDao;
import com.jets.database.dal.dao.impl.UserDao;
import com.jets.database.dal.dto.Friend;
import com.jets.database.dal.dto.User;
import com.jets.database.dal.dto.enums.Country;
import com.jets.database.dal.dto.enums.InvitationStatus;
import com.jets.database.dal.dto.enums.UserStatus;
import com.jets.database.exception.InvalidInputException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author Mayada khaled
 */
//String name, String password,String email, Country country, Date dateOfBirth, char gender, UserStatus status, byte[] picture, 
//String bio, String phoneNumber
public class DatabaseUserTableTest{

    public static void main(String[] args) {

        User testUser = null;
        try {

            testUser = new User("+0101530354",
                    "yahiaamr",
                    Country.Albania,
                    "1003777776",
                    true,
                    UserStatus.OFFLINE,
                    null,
                    "hello!",
                    'F',
                    new Date(2010, 11, 1),
                    "yahiaamr@gmail.com"
            );
        } catch (InvalidInputException ex) {
            ex.printStackTrace();
        }

        UserDao userDao = new UserDao();
/*
            System.out.println("insert new user ....");
        try {
            userDao.persist(testUser);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
  System.out.println("new user is inserted ....");
  */
        System.out.println("retrive user by name...");
        List<User> returnedUserByName = userDao.retrieveByName("mayada khaled");
        for (User item : returnedUserByName) {
            System.out.println("phone number :" + item.getPhoneNumber());
            System.out.println("name :" + item.getName());
            System.out.println("statues :" + item.getStatus());
        }
        System.out.println("Done");

      System.out.println("retrive by phone....");
        User returnedUserByphone = userDao.retrieveByPhoneNumber("+2314567896");
        if(returnedUserByphone!=null) {
	        System.out.println("phone number :" + returnedUserByphone.getPhoneNumber());
	        System.out.println("name :" + returnedUserByphone.getName());
	        System.out.println("statues :" + returnedUserByphone.getStatus());
	        System.out.println("Done");
        }
        else {
        	System.out.println("no user retrieved");
        }
        System.out.println("updating...");
        User updateUser = null;
        try {
            updateUser = new User("+0101530354", "yahiaamr", Country.Albania, "1113777776", true, UserStatus.BUSY, null, "hello!", 'M',
                    new Date(2010, 11, 1), "yahiaamr@gmail.com");

        } catch (InvalidInputException ex) {
            ex.printStackTrace();
        }

          User newUser = null;
        try {
            newUser = new User("+2760006621", "khaled taher", Country.Central_African_Republic, "10000000092", false, UserStatus.AWAY,
            		null, "hello00000000 !", 'M', new Date(2010, 11, 1), "eee@gmail.com");
        } catch (InvalidInputException ex) {
            ex.printStackTrace();
        }
        /*
        try {
            userDao.persist(newUser);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        */
        try {
            userDao.update(updateUser);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("Done");

        User testUser2 = null;
        boolean flag = false;
        try {
            testUser2 = new User("+0303530354",
                    "yahiaamr",
                    Country.Albania,
                    "1003777776",
                    true,
                    UserStatus.OFFLINE,
                    null,
                    "hello!",
                    'F',
                    new Date(2010, 11, 1),
                    "yahiaamr@gmail.com"
            );
            flag = userDao.validate(testUser2);
        } catch (InvalidInputException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (flag == true) {
            System.out.println("done");

        }

         Friend friend = new Friend(newUser, InvitationStatus.PENDING);
         /*   
         FriendsDao friendDao = new FriendsDao(newUser);
            
            try {
            friendDao.persist(friend);
            } catch (SQLException ex) {
            ex.printStackTrace();
            }
            System.out.println("Done");
            friendDao.retrieveAllFriends();
            System.out.println("Done");
            friendDao.retrieveByName("mayada khaled");
            System.out.println("Done");
            */
    }
}

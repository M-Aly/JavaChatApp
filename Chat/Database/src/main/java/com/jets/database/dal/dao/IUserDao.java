package com.jets.database.dal.dao;

import java.util.List;
import java.util.Map;

import com.jets.database.dal.dto.User;
import java.sql.SQLException;

/**
CRUD operations for User table
no passwords are retrieved
@author Mohamed Ali
*/
public interface IUserDao{
    /**
    INSERT new user
    */
    void persist(User newUser) throws SQLException;

    /**
    search by phone number
    */
    User retrieveByPhoneNumber(String phoneNumber);

    /**
    search by name
    it is not the primary key so it returns a list
    */
    List<User> retrieveByName(String name);
    
    /**
    retrieve all users
    */
    List<User> retrieveAllUsers();

    /**
    update the current user
    it is read if the id matches the id in a user retrieved
    */
    void update(User currentUser) throws SQLException;
    
    /**
    validate phone number and password
    */
    User retrieveByPassword(String phoneNumber,String password) throws SQLException;

    /**
     * count all Online Users for Statistics
     
    
    int countOnlineUsers()throws SQLException;
    
    
    /**
     * count all Offline Users for Statistics
     
    int countOfflineUsers()throws SQLException;
    
    
    /**
     * count all Male Users for Statistics
     
    int countMaleUsers()throws SQLException;
    
    
    /**
     * count all Female Users for Statistics
     
    int countFemaleUsers()throws SQLException;
    
    
    /**
     * get All Countries for all users for Statistics
     
    Map<String, Integer> getUserCountries()throws SQLException;
    
    */
}
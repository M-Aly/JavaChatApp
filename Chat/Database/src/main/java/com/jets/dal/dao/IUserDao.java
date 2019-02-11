package com.jets.dal.dao;

import java.util.List;
import com.jets.dal.dto.User;

/**
CRUD operations for User table
@author Mohamed Ali
*/
public interface IUserDao{
    /**
    INSERT new user
    */
    void persist(User newUser);

    /**
    search by phone number
    */
    User retrieveByPhoneNumber();

    /**
    search by name
    it is not the primary key so it returns a list
    */
    List<User> retrieveByName(String name);

    /**
    update the current user
    it is read if the id matches the id in a user retrieved
    */
    void update(User currentUser);
}
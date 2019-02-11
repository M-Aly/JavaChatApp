package com.jets.dal.dao.impl;

import com.jets.dal.dao.IUserDao;
import com.jets.dal.dto.User;
import java.util.List;

/**
CRUD operations for User table
*/
public class UserDao implements IUserDao{
    private User user;
    public UserDao(User user){
        this.user=user;
    }
    /**
    INSERT USER
    */
    public void persist(User newUser){
    }
}
package com.jets.dal.dao.impl;

import com.jets.dal.dao.IUserDao;
import com.jets.dal.dto.User;

/**
CRUD operations for User table
*/
public class UserDao {
    private User user;
    public UserDao(User user){
        this.user=user;
    }
}
package com.jets.dal.dao;

import java.util.List;
import com.jets.dal.dto.Friend;
import com.jets.dal.dto.User;
import java.sql.SQLException;

/**
CRUD operations for Friends table
@author Mohamed Ali
*/
public interface IFriendsDao{
    /**
    INSERT new friend
    */
    void persist(Friend friend) throws SQLException;

    /**
    SELECT all friends
    */
    List<User> retrieveAllFriends();

    /**
    search by friend name
    */
    List<User> retrieveByName(String name);

    /**
    delete a friend by his phone number
    */
    void delete(int friendPhoneNumber) throws SQLException;
    
    /**
    delete a friend by his phone number
    */
    void update(Friend friend) throws SQLException;
}
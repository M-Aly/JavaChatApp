package com.jets.database.dal.dao;

import java.util.List;
import com.jets.database.dal.dto.Friend;
import com.jets.database.dal.dto.User;
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
    List<Friend> retrieveAllFriends();

    /**
    search by friend name
    */
    List<Friend> retrieveByName(String name);

    /**
    delete a friend by his phone number
    */
    void delete(String friendPhoneNumber) throws SQLException;
    
    /**
    delete a friend by his phone number
    */
    void update(Friend friend) throws SQLException;
}
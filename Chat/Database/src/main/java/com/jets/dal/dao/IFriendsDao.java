package com.jets.dal.dao;

import java.util.List;
import com.jets.dal.dto.User;

/**
CRUD operations for Friends table
@author Mohamed Ali
*/
public interface IFriendsDao{
    /**
    INSERT new friend
    */
    void persist(User friend);

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
    void delete(int phoneNumber);
}
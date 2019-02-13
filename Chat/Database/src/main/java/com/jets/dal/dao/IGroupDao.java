package com.jets.dal.dao;

import java.util.List;
import com.jets.dal.dto.Group;
import java.sql.SQLException;

/**
CRUD operations for Message table
@author Mohamed Ali
*/
public interface IGroupDao{
    /**
    INSERT new group
    */
    void persist(Group group) throws SQLException;

    /**
    SELECT all groups the current user has
    */
    List<Group> retrieveAllGroups();

    /**
    search by name
    */
    List<Group> retrieveByName(String name);
    
    /**
    update group
    */
    void update(Group group) throws SQLException;

    /**
    delete a friend
    */
    void delete(int groupId) throws SQLException;
}
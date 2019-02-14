/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.database.dal.dao.impl;

import com.jets.database.controller.impl.ConnectionMySql;
import com.jets.database.dal.dao.IGroupDao;
import com.jets.database.dal.dto.Group;
import com.jets.database.dal.dto.User;
import com.jets.database.exception.InvalidInputException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zainab
 */
public class GroupDao implements IGroupDao {

    private Connection connection = ConnectionMySql.getInstance().getConnection();;
    private Statement statement;
    private User user;
    private List<Group> listGroup = new ArrayList<>();
    private ResultSet result;

    public GroupDao(User user) {
        this.user = user;
        try {
            connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(GroupDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void persist(Group group) throws SQLException {
        String insertNewGroup = "INSERT INTO group_user(groupId,phoneNumber)" + " VALUES(" + group.getGroupId() + "," + user.getPhoneNumber() + ")";
        if (statement != null) {
            result = statement.executeQuery(insertNewGroup);
        }

    }

    @Override
    public List<Group> retrieveAllGroups() {
        String retrieveGroups = "SELECT * FROM group_user where phoneNumber=" + user.getPhoneNumber();
        if (statement != null) {
            try {
                result = statement.executeQuery(retrieveGroups);
                while (result.next()) {
                    listGroup.add(new Group(result.getInt(1), result.getString(0)));
                }
            } catch (SQLException ex) {
                return null;
            }
        }
        return listGroup;
    }

    @Override
    public List<Group> retrieveByName(String name) {

        String retrieveByNameQuery = "SELECT *"
                + " FROM group_user u JOIN groub g ON g.groupId = u.groupId WHERE u.pno = " + user.getPhoneNumber()
                + " AND g.groupName LIKE " + name + "%";
        if (statement != null) {
            try {
                result = statement.executeQuery(retrieveByNameQuery);
                
                while (result.next()) {
                    listGroup.add(new Group(result.getInt(1), result.getString(2)));
                }
            } catch (SQLException ex) {
                return null;
            }
        }
        return listGroup;
    }

    @Override
    public void update(Group group) throws SQLException {
        String updateGroupQuery =
         "UPDATE groub SET groupName =" + group.getName()+ " WHERE groupName=[SELECT g.groupName "
                +"FROM group_user u JOIN groub g ON g.groupId = u.groupId WHERE u.pno = " + user.getPhoneNumber() +"]";
        
        if (statement != null) {
            result = statement.executeQuery(updateGroupQuery);
        }
    }

    @Override
    public void delete(int groupId) throws SQLException {
        String deleteGroup = "DELETE FROM groub where groubid=groupId";
        if (statement != null) {
            result = statement.executeQuery(deleteGroup);
        }
    }

    public void close() {
        if(statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(GroupDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

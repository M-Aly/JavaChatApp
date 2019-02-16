package com.jets.database.dal.dao.impl;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jets.database.controller.impl.ConnectionMySql;
import com.jets.database.dal.dao.IGroupDao;
import com.jets.database.dal.dto.Friend;
import com.jets.database.dal.dto.Group;
import com.jets.database.dal.dto.User;
import com.jets.database.exception.InvalidInputException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zainab
 */
public class GroupDao implements IGroupDao {

    private Connection connection;
    private Statement statement;
    private User user;
    private List<Group> listGroup;
    private ResultSet result;
    

    public GroupDao(User user) {
    	 connection = ConnectionMySql.getInstance().getConnection();
    	 
    	 List<Group> listGroup = new ArrayList<>();
        this.user = user;
        try {
        	connection.setAutoCommit(false);
        	statement=connection.createStatement();
        	
        } catch (SQLException ex) {
            Logger.getLogger(GroupDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void persist(Group group) {
    	
    	String insertNewGroup = "INSERT INTO groub(groupName)" + " VALUES(" + group.getName()+")";
    	
        if (statement != null) {
        	try {
                statement.executeUpdate(insertNewGroup);
                commit();
            } catch (SQLException ex) {
                Logger.getLogger(GroupDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } 
    }
    
    @Override
    public List<Group> retrieveAllGroups() {
        String retrieveGroups = "SELECT * FROM group_user where phoneNumber=" + user.getPhoneNumber();
        if (statement != null) {
            try {
                result = statement.executeQuery(retrieveGroups);
                commit();
                while (result.next()) {
                    listGroup.add(new Group(result.getInt(1), result.getString(0)));
                }
            } catch (SQLException ex) {
                return null;
            }
        }
        return listGroup;
    }
    
    // try using retriveFriend and complete logic of retrieveByName and retrieveAllGroups

    @Override
    public List<Group> retrieveByName(String name) {
    	Group group;
    	Friend friend;

        String retrieveByNameQuery = "SELECT u.phoneNumber"
                + " FROM group_user u JOIN groub g ON g.groupId = u.groupId WHERE"
                +" g.groupName LIKE " + name + "%";
        if (statement != null) {
            try {
                result = statement.executeQuery(retrieveByNameQuery);
                commit();
                while (result.next()) {
                	group=new Group(result.getInt(1), result.getString(2));
                //	friend= retriveFriend(Group group);
                //	group.addFriend(friend);
                    listGroup.add(group);
                    
                }
            } catch (SQLException ex) {
                return null;
            }
        }
        return listGroup;
    }

    @Override
    public void update(Group group) throws SQLException {
    	
        String updateGroupName =
        "UPDATE groub SET groupName =" + group.getName()+ ")";
        statement.addBatch(updateGroupName);
        
        String updateGroupPhone;       
        Set setFriend = group.getFriends();
        for(Friend  friends:group.getFriends() ) {
        	updateGroupPhone =
                    "UPDATE group_user SET phoneNumber =" + friends.getPhoneNumber()+")";
        
        	statement.addBatch(updateGroupPhone);
        }
        statement.executeBatch();
        commit();
   
    }  

    @Override
    public void delete(int groupId) throws SQLException {
        String deleteGroup = "DELETE FROM groub where groubid=groupId";
        if (statement != null) {
            result = statement.executeQuery(deleteGroup);
            commit();
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
    
//    public void retriveFriend(Group group){
//        String x="SELECT u.phoneNumber FROM group_user u JOIN groub g ON g.groupId = u.groupId WHERE groupName = "+group.getName()+")"; 
//        try {
//            result = statement.executeQuery(x);
//           // Friend f= new Friend(user, InvitationStatus.PENDING);
//           // f.setPhone(result.getString(1));    
//           // result.getString(1);            
//            while(result.next()){
//             // group.addFriend(new Friend());  
//            }
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(GroupDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//    }
    
    public void commit() {
    	try {
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

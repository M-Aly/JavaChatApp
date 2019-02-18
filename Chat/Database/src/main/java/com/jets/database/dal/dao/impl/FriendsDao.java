package com.jets.database.dal.dao.impl;

import com.jets.database.controller.impl.ConnectionMySql;
import com.jets.database.dal.dao.IFriendsDao;
import com.jets.database.dal.dto.Friend;
import com.jets.database.dal.dto.User;
import com.jets.database.dal.dto.enums.Country;
import com.jets.database.dal.dto.enums.InvitationStatus;
import com.jets.database.dal.dto.enums.UserStatus;
import com.jets.database.exception.InvalidInputException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mayada Khaled
 */
public class FriendsDao implements IFriendsDao {

    private User user;
    private Connection connection;

    public FriendsDao(User user) {
        this.user = user;
        connection = ConnectionMySql.getInstance().getConnection();
    }

    /**
     * INSERT new friend
     */
    @Override
    public void persist(Friend friend) throws SQLException {
    	Statement statement = null;
        try {
            String insertNewUserQuery =  "INSERT INTO user_friend (userphone,friendPhone,invitationState)"
                    + " VALUES('" + user.getPhoneNumber() + "','" + friend.getPhoneNumber() + "','" + InvitationStatus.PENDING + "')";
            statement = connection.createStatement();
            statement.executeUpdate(insertNewUserQuery);
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * SELECT all friends
     */
    @Override
    public List<Friend> retrieveAllFriends() {
        List<Friend> userList = new ArrayList<Friend>();
        ResultSet result;
        String retrieveByNameQuery = "SELECT u.phonenumber,u.name ,u.country, u.password ,u.changePassword ,u.statues,u.picture"
                + ",u.bio,u.gender,u.birthofdate,u.email"
                + ",f.invitationstate FROM user u JOIN user_friend f ON u.phonenumber = f.userphone where u.userphone='" + user.getPhoneNumber() +"'";
        
        Statement statement = null;
        try {
        	statement = connection.createStatement();
            result = statement.executeQuery(retrieveByNameQuery);

            while (result.next()) {
            	User user = new User(result.getString(1), result.getString(2), Country.valueOf(result.getString(3)), result.getString(4), 
            			result.getBoolean(5),  UserStatus.valueOf(result.getString(6).toUpperCase()), 
            			result.getBytes(7), result.getString(8)
            			, result.getString(9).charAt(0), result.getDate(10), result.getString(11));
            	
            	Friend friend = new Friend(user, InvitationStatus.valueOf(result.getString(12)));
            	
                userList.add(friend);
            }

        } catch (SQLException ex) {
            return null;
        } catch (InvalidInputException ex) {
            return null;
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return userList;
    }

    /**
     * search by friend name
     */
    @Override
    public List<Friend> retrieveByName(String name) {
        List<Friend> userList = new ArrayList<Friend>();
        ResultSet result;
        String retrieveByNameQuery = "SELECT u.phonenumber,u.name ,u.country, u.password ,u.changePassword ,u.statues,u.picture"
                + ",u.bio,u.gender,u.birthofdate,u.email"
                + ",f.invitationstate FROM user u JOIN user_friend f ON u.phonenumber = f.userphone where u.name='" + name +"'";
        
        Statement statement = null;
        try {
        	statement = connection.createStatement();
            result = statement.executeQuery(retrieveByNameQuery);

            while (result.next()) {
            	User user = new User(result.getString(1), result.getString(2), Country.valueOf(result.getString(3)), result.getString(4), 
            			result.getBoolean(5),  UserStatus.valueOf(result.getString(6).toUpperCase()), 
            			result.getBytes(7), result.getString(8)
            			, result.getString(9).charAt(0), result.getDate(10), result.getString(11));
            	
            	Friend friend = new Friend(user, InvitationStatus.valueOf(result.getString(12)));
            	
                userList.add(friend);
            }
        } catch (SQLException ex) {
            return null;
        } catch (InvalidInputException ex) {
            return null;
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return userList;

    }

    /**
     * delete a friend by his phone number
     */
    @Override
    public void delete(String friendphone) throws SQLException {
        Statement statement = null;
        try {
            String deleteUserQuery = "DELETE FROM user_friend WHERE userphone = '"+user.getPhoneNumber()+"' and friendphone ='"+friendphone+"'";
            statement = connection.createStatement();
            statement.execute(deleteUserQuery);
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void update(Friend friend) throws SQLException {
        Statement statement = null;
        try {
            String updateUserQuery = "UPDATE user_friend SET invitationstate='ACCEPTED' WHERE userphone = '"+user.getPhoneNumber()+"' and friendphone ='"+friend.getPhoneNumber()+"';";

            statement = connection.createStatement();
            statement.executeQuery(updateUserQuery);
            connection.commit();
            statement.close();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}

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
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Mayada Khaled
 */
close your statements and use finally
public class FriendsDao implements IFriendsDao{
    private User friends;
    private Connection connection;
    public FriendsDao(User friends){
        this.friends=friends;
        connection=ConnectionMySql.getInstance().getConnection();
    }
    /**
    INSERT new friend
    */
    @Override
    public void persist(Friend friend) throws SQLException
    {
        UserDao userDao = new UserDao(friend.getFriend());
        userDao.persist(friend.getFriend());
        String insertNewUserQuery = "INSERT INTO friends (userphone,friendPhone,invitationState)"
                + " VALUES(?,?,?)";
        
        PreparedStatement statement;
        statement = connection.prepareStatement(insertNewUserQuery);
        statement.setString(1, friend.getPhoneNumber());
        //statement.setString(2, friend.getName());
        statement.setString(3, InvitationStatus.PENDING.toString());

        statement.execute();
    }

    /**
    SELECT all friends
    */
    @Override
    public List<Friend> retrieveAllFriends()
    {
        List<User> userList = new ArrayList<User>();
        ResultSet result;
        String retrieveByNameQuery = "SELECT u.phonenumber,u.name,u.country,u.password,,u.statues,u.picture,u.bio"
                + ",u.gender,u.birthofdate,u.email FROM user u JOIN friends f ON u.phonenumber = f.userphone";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(retrieveByNameQuery);
            result = statement.executeQuery(retrieveByNameQuery);
            
            while (result.next())
            {
                userList.add(new User(result.getString(1),result.getString(2),
                        result.getString(3),
                        Country.valueOf(result.getString(4)),
                        result.getDate(5),
                result.getString(6).charAt(0),UserStatus.valueOf(result.getString(7)),result.getBytes(8),result.getString(9),result.getString(10)));
            }
            
            
        } catch (SQLException ex) {
            return null;
        } catch (InvalidInputException ex) {
            return null;
        }
        return userList;
    }

    /**
    search by friend name
    */
    @Override
    public List<Friend> retrieveByName(String name)
    {
        List<User> userList = new ArrayList<User>();
        ResultSet result;
        String retrieveByNameQuery = "SELECT u.phonenumber,u.name,u.country,u.password,,u.statues,u.picture,u.bio"
                + ",u.gender,u.birthofdate,u.email FROM user u JOIN friends f ON u.phonenumber = f.userphone WHERE u.name = ?";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(retrieveByNameQuery);
            connection.setAutoCommit(false);
            statement.setString(1, name);
            statement.addBatch();
            result = statement.executeQuery(retrieveByNameQuery);
            connection.commit();
            while (result.next())
            {
                userList.add(new User(result.getString(1),result.getString(2),
                        result.getString(3),
                        Country.valueOf(result.getString(4)),
                        result.getDate(5),
                result.getString(6).charAt(0),UserStatus.valueOf(result.getString(7).toUpperCase()),result.getBytes(8),result.getString(9),result.getString(10)));
            }
            
            
        } catch (SQLException ex) {
            return null;
        } catch (InvalidInputException ex) {
            return null;
        }
        return userList;
        
    }

    /**
    delete a friend by his phone number
    */
    @Override
    public void delete(String phoneNumber) throws SQLException
    {
        String deleteUserQuery = "DELETE FROM friends WHERE userphone = phoneNumber";
        PreparedStatement statement;
        statement = connection.prepareStatement(deleteUserQuery);
        statement.setString(1, phoneNumber);
        statement.execute();
    }

    @Override
    public void update(Friend friend) throws SQLException {
        empty
    }
    
    
}

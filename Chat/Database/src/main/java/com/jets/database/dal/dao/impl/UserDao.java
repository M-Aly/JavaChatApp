package com.jets.database.dal.dao.impl;

import com.jets.database.dal.dao.IUserDao;
import com.jets.database.dal.dto.User;
import com.jets.database.dal.dto.enums.Country;
import com.jets.database.dal.dto.enums.UserStatus;
import com.jets.database.exception.InvalidInputException;
import com.jets.database.controller.impl.ConnectionMySql;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
CRUD operations for User table
* @author Mayada Khaled
*/
close your statements and use finally
public class UserDao implements IUserDao{
    private User user;
    private Connection connection;
    public UserDao(User user){
        this.user=user;
        connection=ConnectionMySql.getInstance().getConnection();
        
    }
    /**
    INSERT USER
    */
    @Override
   public void persist(User newUser) throws SQLException
   {
        String insertNewUserQuery = "INSERT INTO user(phonenumber,name,country,password,statues,picture,bio,gender,birthofdate,email)"
                + " VALUES(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement statement;
        statement = connection.prepareStatement(insertNewUserQuery);
        connection.setAutoCommit(false);
        statement.setString(1, user.getPhoneNumber());
        statement.setString(2, user.getName());
        statement.setString(3, user.getCountry().toString());
        statement.setString(4, user.getPassword());
        statement.setString(5, user.getStatus().toString());
        statement.setBytes(6, user.getPicture());
        statement.setString(7, user.getBio()); 
        statement.setString(8,((Character)(user.getGender())).toString());
        statement.setDate(9, (Date) user.getDateOfBirth());
        statement.setString(10, user.getEmail());
        statement.addBatch();
        statement.executeQuery(insertNewUserQuery);
        connection.commit();
   }

    /**
    search by phone number
    */
   @Override
    public User retrieveByPhoneNumber(String phonenumber)
    {
        User resultUser = null;
        ResultSet result;
        String retrieveByPhoneNumberQuery = "SELECT * FROM user WHERE phonenumber = ?";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(retrieveByPhoneNumberQuery);
            connection.setAutoCommit(false);
            statement.setString(1, phonenumber);
            statement.addBatch();
            result = statement.executeQuery(retrieveByPhoneNumberQuery);
            connection.commit();
            resultUser=new User(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                   Country.valueOf(result.getString(4)),
                    result.getDate(5),
                    result.getString(6).charAt(0),
                    UserStatus.valueOf(result.getString(7)),
                    result.getBytes(8),
                    result.getString(9),
                    result.getString(10));
            
        } catch (SQLException ex) {
            return null;
        } catch (InvalidInputException ex) {
            return null;
        }
        return resultUser;
    }

    /**
    search by name
    it is not the primary key so it returns a list
    */
    @Override
    public List<User> retrieveByName(String name)
    {
        List<User> userList = new ArrayList<User>();
        ResultSet result;
        String retrieveByNameQuery = "SELECT name FROM user WHERE name = ?";
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
    update the current user
    it is read if the id matches the id in a user retrieved
    */
    @Override
    public void update(User currentUser) throws SQLException
    {
        String updateUserQuery = "UPDATE user SET name = ?, country = ?, password = ?,statues = ?,"
                + "picture = ?,bio = ?,gender = ?,birthofdate= ?,email = ? WHERE phonenumber = ?";
        PreparedStatement statement;
        statement = connection.prepareStatement(updateUserQuery);
        connection.setAutoCommit(false);
        statement.setString(1, currentUser.getName());
        statement.setString(2, currentUser.getCountry().toString());
        statement.setString(3, currentUser.getPassword());
        statement.setString(4, currentUser.getStatus().toString());
        statement.setBytes(5, currentUser.getPicture());
        statement.setString(6, currentUser.getBio()); 
        statement.setString(7, ((Character)(currentUser.getGender())).toString());
        statement.setDate(8, (Date) currentUser.getDateOfBirth());
        statement.setString(9, currentUser.getEmail());
        statement.setString(10, currentUser.getPhoneNumber());
        statement.addBatch();
        statement.executeQuery(updateUserQuery);
        connection.commit();
    }

    @Override
    public boolean validate(User user) throws SQLException {
    }
}
package com.jets.database.dal.dao.impl;

import com.jets.database.dal.dto.enums.Country;
import com.jets.database.dal.dto.enums.UserStatus;
import com.jets.database.exception.InvalidInputException;
import com.jets.database.controller.impl.ConnectionMySql;
import com.jets.database.dal.dao.IUserDao;
import com.jets.database.dal.dto.User;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;


/**
CRUD operations for User table
* @author Mayada Khaled
*/
public class UserDao implements IUserDao {

    private Connection connection;
    PreparedStatement statement = null;

    public UserDao() {
        connection = ConnectionMySql.getInstance().getConnection();

    }

    /**
     * INSERT USER
     */
    @Override
    public void persist(User newUser) throws SQLException {

        try {
            String insertNewUserQuery = "INSERT INTO user(phonenumber,name,country,password,changePassword,statues"
            		+ ",picture,bio,gender,birthofdate,email)VALUES('" + newUser.getPhoneNumber() + "','" 
            		+ newUser.getName() + "','" + newUser.getCountry() + "','" + newUser.getPassword() + "','" + newUser.getStatus() + "','" + newUser.getPicture() + "','" + newUser.getBio() + "','" + newUser.getGender() + "','" + newUser.getDateOfBirth() + "','" + newUser.getEmail() + "')";
            
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(insertNewUserQuery);

            statement.executeUpdate(insertNewUserQuery);
            connection.commit();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        /*finally
	{          
             statement.close();            
	}*/
    }

    /**
     * search by phone number
     */
    @Override
    public User retrieveByPhoneNumber(String phonenumber) {
        User resultUser = null;
        ResultSet result;
        String retrieveByPhoneNumberQuery = "select * from user where phonenumber = '" + phonenumber + "';";

        Statement statement = null;
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(retrieveByPhoneNumberQuery);

            while (result.next()) {
                // System.out.println(result.getString(2));
                resultUser = new User(result.getString(1), result.getString(2),
                        Country.valueOf(result.getString(3)), result.getString(4),
                        result.getBoolean(5), UserStatus.valueOf(result.getString(6)), result.getBytes(7), result.getString(8), result.getString(9).charAt(0), result.getDate(10), result.getString(11));

            }

        } catch (SQLException ex) {
            return null;
        } catch (InvalidInputException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return resultUser;
    }

    /**
     * search by name it is not the primary key so it returns a list
     */
    @Override
    public List<User> retrieveByName(String name) {
        List<User> userList = new ArrayList<>();
        ResultSet result;
        String retrieveByNameQuery = "SELECT * FROM user WHERE name like '%" + name + "%';";

        Statement statement = null;
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(retrieveByNameQuery);

            while (result.next()) {
                // System.out.println(result.getString(2));
                User user = new User(result.getString(1), result.getString(2),
                        Country.valueOf(result.getString(3)), result.getString(4),
                        result.getBoolean(5), UserStatus.valueOf(result.getString(6)), result.getBytes(7), result.getString(8), result.getString(9).charAt(0), result.getDate(10), result.getString(11));
                userList.add(user);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } catch (InvalidInputException ex) {
            return null;
        } finally {
            try {
                // connection.commit();
                statement.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return userList;
    }

    /**
     * update the current user it is read if the id matches the id in a user
     * retrieved
     */
    @Override
    public void update(User currentUser) throws SQLException {
        ResultSet result;
        String updateUserQuery = "UPDATE user SET name = '" + currentUser.getName() + "', country = '" + currentUser.getCountry() + "', password = '" + currentUser.getPassword() + "',statues = '" + currentUser.getStatus() + "',"
                + "bio = '" + currentUser.getBio() + "',gender = '" + currentUser.getGender() + "',birthofdate= '" + currentUser.getDateOfBirth() + "',email = '" + currentUser.getEmail() + "' WHERE phonenumber = '" + currentUser.getPhoneNumber() + "'";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(updateUserQuery);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            statement.close();
        }
    }

    /**
     * check if user exist in the user table
     */

    @Override
    public boolean validate(User user) throws SQLException {
       // User resultUser = null;
        ResultSet result;
        String validateByPhoneNumberQuery = "SELECT * FROM user WHERE phonenumber = '" + user.getPhoneNumber() + "'";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(validateByPhoneNumberQuery);
            
            if (result.next()) {
                return true;
            } else {
                return false;
            }

        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}

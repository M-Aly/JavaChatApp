package com.jets.database.dal.dao.impl;

import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jets.database.controller.impl.ConnectionMySql;
import com.jets.database.dal.dao.IUserDao;
import com.jets.database.dal.dto.User;
import com.jets.database.dal.dto.enums.Country;
import com.jets.database.dal.dto.enums.UserStatus;
import com.jets.database.exception.InvalidInputException;

/**
 * CRUD operations for User table
 *
 * @author Mayada Khaled
 * @author Mohamed Ali
 * @author Nouran Amr
 * @author Amer Salah
 */
public class UserDao implements IUserDao {

	private Connection connection;
	private PreparedStatement statement = null;
	private ResultSet result;
	private String sqlQuery;

	public UserDao() {

		connection = ConnectionMySql.getInstance().getConnection();

	}

	/**
	 * INSERT USER
	 */
	@Override
	public void persist(User newUser) throws SQLException {

		try {
			sqlQuery = "INSERT INTO user(phonenumber,name,country,password,changePassword,statues,picture,bio,gender,birthofdate,email)VALUES("
					+"?,?,?,?,?,?,?,?,?,?,?)";
			statement = connection.prepareStatement(sqlQuery);
			statement.setString(1,newUser.getPhoneNumber());
			statement.setString(2,newUser.getName());
			statement.setString(3,newUser.getCountry().toString());
			statement.setString(4,newUser.getPassword());
			statement.setBoolean(5,newUser.getChangePasswordFlag());
			statement.setString(6,newUser.getStatus().toString());
			if(newUser.getPicture() != null) {
				statement.setBlob(7,new ByteArrayInputStream(newUser.getPicture()));
			}
			else {
				Blob blob=null;
				statement.setBlob(7,blob);
			}
			statement.setString(8,newUser.getBio());
			statement.setString(9,""+newUser.getGender());
			statement.setDate(10,newUser.getDateOfBirth());
			statement.setString(11,newUser.getEmail());
			statement.executeUpdate();
		}
		finally {
			closeStatement();
		}
	}

	/**
	 * search by phone number
	 */
	@Override
	public User retrieveByPhoneNumber(String phoneNumber) {

		User resultUser = null;

		sqlQuery = "select * from user where phonenumber = '" + phoneNumber + "';";

		try {
			statement = connection.prepareStatement(sqlQuery);
			result = statement.executeQuery();

			while (result.next()) {
				Blob blob = result.getBlob(7);
				byte[] picture = null;
				if(blob != null) {
					picture=blob.getBytes(1,(int)blob.length());
				}
				resultUser = new User(result.getString(1), result.getString(2), Country.valueOf(result.getString(3)),
						"", result.getBoolean(5), UserStatus.valueOf(result.getString(6)),
						picture, result.getString(8), result.getString(9).charAt(0), result.getDate(10),
						result.getString(11));

			}

		} catch (SQLException ex) {
			resultUser = null;
		} catch (InvalidInputException ex) {
			ex.printStackTrace();
			resultUser = null;
		} finally {
			closeResult();
			closeStatement();
		}
		return resultUser;
	}

	/**
	 * search by name
	 * it is not the primary key so it returns a list
	 */
	@Override
	public List<User> retrieveByName(String name) {
		List<User> userList = new ArrayList<>();
		sqlQuery = "SELECT * FROM user WHERE name like '%" + name + "%';";

		try {
			statement = connection.prepareStatement(sqlQuery);
			result = statement.executeQuery();

			while (result.next()) {
				Blob blob = result.getBlob(7);
				byte[] picture = null;
				if(blob != null) {
					picture=blob.getBytes(1,(int)blob.length());
				}
				User user = new User(result.getString(1), result.getString(2), Country.valueOf(result.getString(3)),
						"", result.getBoolean(5), UserStatus.valueOf(result.getString(6)),
						picture, result.getString(8), result.getString(9).charAt(0), result.getDate(10),
						result.getString(11));
				userList.add(user);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			userList = null;
		} catch (InvalidInputException ex) {
			userList = null;
		} finally {
			closeResult();
			closeStatement();
		}
		return userList;
	}
	
	/**
    validate phone number and password
    */
    public User retrieveByPassword(String phoneNumber,String password) throws SQLException{
    	
		User resultUser = null;

		sqlQuery = "select * from user where phonenumber = '" + phoneNumber + "' AND password = '"+password+"';";

		try {
			statement = connection.prepareStatement(sqlQuery);
			result = statement.executeQuery();

			while (result.next()) {
				Blob blob = result.getBlob(7);
				byte[] picture = null;
				if(blob != null) {
					picture=blob.getBytes(1,(int)blob.length());
				}
				resultUser = new User(result.getString(1), result.getString(2), Country.valueOf(result.getString(3)),
						"", result.getBoolean(5), UserStatus.valueOf(result.getString(6)),
						picture, result.getString(8), result.getString(9).charAt(0), result.getDate(10),
						result.getString(11));

			}

		} catch (SQLException ex) {
			resultUser = null;
		} catch (InvalidInputException ex) {
			ex.printStackTrace();
			resultUser = null;
		} finally {
			closeResult();
			closeStatement();
		}
		return resultUser;
    }

	/**
	 * update the current user
	 * it is read if the id matches the id in a user retrieved
	 */
	@Override
	public void update(User currentUser) throws SQLException {
		sqlQuery = "UPDATE user SET name = ?, country = ?, password = ?,changePassword = ?,statues = ?,picture=?,bio = ?,gender = ?,birthofdate= ?,email = ? WHERE phonenumber = ?";
		
		try {
			statement = connection.prepareStatement(sqlQuery);
			statement.setString(1,currentUser.getName());
			statement.setString(2,currentUser.getCountry().toString());
			statement.setString(3,currentUser.getPassword());
			statement.setBoolean(4,currentUser.getChangePasswordFlag());
			statement.setString(5,currentUser.getStatus().toString());
			if(currentUser.getPicture() != null) {
				statement.setBlob(6,new ByteArrayInputStream(currentUser.getPicture()));
			}
			else {
				Blob blob=null;
				statement.setBlob(6,blob);
			}
			statement.setString(7,currentUser.getBio());
			statement.setString(8,""+currentUser.getGender());
			statement.setDate(9,currentUser.getDateOfBirth());
			statement.setString(10,currentUser.getEmail());
			statement.setString(11,currentUser.getPhoneNumber());
			statement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeStatement();
		}
	}
	
	private void closeStatement() {
		if(statement != null) {
			try {
				statement.close();
			}
			catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	private void closeResult() {
		if(result != null) {
			try {
				result.close();
			}
			catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	/*
	@Override
	public int countOnlineUsers() throws SQLException {
		
		int counter = 0;
		String sqlQuery = "select status from user";
		try {
			statement = connection.prepareStatement(sqlQuery);
			result = statement.executeQuery();
			while(result.next()) {
				
				if(!result.getString(0).equals(UserStatus.OFFLINE))
				{
					counter++;
				}
			}
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		
		return counter;
		
	}

	@Override
	public int countOfflineUsers() throws SQLException {
		int counter = 0;
		String sqlQuery = "select status from user";
		try {
			statement = connection.prepareStatement(sqlQuery);
			result = statement.executeQuery();
			while(result.next()) {
				
				if(result.getString(0).equals(UserStatus.OFFLINE))
				{
					counter++;
				}
			}
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		
		return counter;
	}

	@Override
	public int countMaleUsers() throws SQLException {
		
		int counter = 0;
		String sqlQuery = "select gender from user";
		try {
			statement = connection.prepareStatement(sqlQuery);
			result = statement.executeQuery();
			while(result.next()) {
				
				if(result.getString(0).toLowerCase().equals("m"))
				{
					counter++;
				}
			}
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		
		return counter;
		
	}

	@Override
	public int countFemaleUsers() throws SQLException {
		int counter = 0;
		String sqlQuery = "select gender from user";
		try {
			statement = connection.prepareStatement(sqlQuery);
			result = statement.executeQuery();
			while(result.next()) {
				
				if(result.getString(0).toLowerCase().equals("f"))
				{
					counter++;
				}
			}
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		
		return counter;
	}

	@Override
	public Map<String, Integer> getUserCountries() throws SQLException {
		
		Map<String , Integer> countries = new HashMap<>();
		String sqlQuery = "select country from user";
		
		try {
			statement = connection.prepareStatement(sqlQuery);
			result = statement.executeQuery();
			while(result.next()) {
				
				if(result.getString(0).toLowerCase().equals("f"))
				{
					
				}
			}
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		
		return countries;
	}
	*/
}

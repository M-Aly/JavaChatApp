package com.jets.database.dal.dao.impl;

import com.jets.database.controller.impl.ConnectionMySql;
import com.jets.database.dal.dao.IFriendsDao;
import com.jets.database.dal.dto.User;
import com.jets.database.dal.dto.enums.InvitationStatus;
import com.jets.database.exception.InvalidDTOException;
import com.jets.database.dal.dto.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mayada Khaled
 * @author Mohamed Ali
 */
public class FriendsDao implements IFriendsDao {
	private User user;
	private Connection connection;
	private Statement statement = null;
	private String sqlQuery=null;
	private ResultSet result;
	private UserDao userDao;
	public FriendsDao(User user) {
		this.user = user;
		connection = ConnectionMySql.getInstance().getConnection();
		userDao=new UserDao();
		if(user == null) {
			throw new InvalidDTOException("user can not be null");
		}
	}

	/**
	 * INSERT new friend
	 */
	@Override
	public void persist(Friend friend) throws SQLException {

		try {
			sqlQuery = "INSERT INTO user_friend (userphone,friendphone,invitationstatues)" + " VALUES('"
					+ user.getPhoneNumber() + "','" + friend.getPhoneNumber() + "','" + friend.getInvitationStatus() + "')";
			statement = connection.createStatement();
			statement.executeUpdate(sqlQuery);
		}
		finally {
			closeStatement();
		}
	}

	/**
	 * SELECT all friends
	 */
	@Override
	public List<Friend> retrieveAllFriends() {
		List<Friend> userList = new ArrayList<Friend>();
		sqlQuery = "SELECT friendphone,invitationstatues FROM user_friend where userphone='"
				+ user.getPhoneNumber() + "'";

		try {
			statement = connection.createStatement();
			result = statement.executeQuery(sqlQuery);

			while (result.next()) {
				String friendPhoneNumber=result.getString(1);
				User friendUser = userDao.retrieveByPhoneNumber(friendPhoneNumber);

				Friend friend = new Friend(friendUser, InvitationStatus.valueOf(result.getString(2)));

				userList.add(friend);
			}

		}
		catch (SQLException ex) {
			userList = null;
		}
		finally {
			closeResult();
			closeStatement();
		}
		return userList;
	}

	/**
	 * search by friend name
	 */
	@Override
	public List<Friend> retrieveByName(String name) {
		List<Friend> userList = new ArrayList<Friend>();
		sqlQuery = "SELECT f.friendphone,f.invitationstatues FROM user u JOIN user_friend f ON u.phonenumber = f.friendphone where u.name like '%"
				+ name + "%' and f.userphone='" + user.getPhoneNumber() + "'";

		Statement statement = null;
		try {
			statement = connection.createStatement();
			result = statement.executeQuery(sqlQuery);

			while (result.next()) {
				String friendPhoneNumber=result.getString(1);
				User friendUser = userDao.retrieveByPhoneNumber(friendPhoneNumber);

				Friend friend = new Friend(friendUser, InvitationStatus.valueOf(result.getString(2)));

				userList.add(friend);
			}
		}
		catch (SQLException ex) {
			userList = null;
		}
		finally {
			closeResult();
			closeStatement();
		}
		return userList;

	}

	/**
	 * delete a friend by his phone number
	 */
	@Override
	public void delete(String friendPhoneNumber) throws SQLException {
		
		try {
			sqlQuery= "DELETE FROM user_friend WHERE userphone = '" + user.getPhoneNumber()
					+ "' and friendphone ='" + friendPhoneNumber + "'";
			statement = connection.createStatement();
			statement.execute(sqlQuery);
		}
		finally {
			closeStatement();
		}
	}

	/**
    update a friend by his phone number
    */
	@Override
	public void update(Friend friend) throws SQLException {
		
		try {
			sqlQuery = "UPDATE user_friend SET invitationstatues='"+friend.getInvitationStatus()+"' WHERE userphone = '"
					+ user.getPhoneNumber() + "' and friendphone ='" + friend.getPhoneNumber() + "';";

			statement = connection.createStatement();
			statement.executeUpdate(sqlQuery);
		}
		finally {
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
}

package com.jets.tests.database;

import com.jets.database.dal.dao.impl.UserDao;
import com.jets.database.dal.dto.User;
import com.jets.database.dal.dto.enums.Country;
import com.jets.database.dal.dto.enums.UserStatus;
import com.jets.database.exception.InvalidInputException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Mayada khaled
 * @author Nouran Amr
 * @author Mohamed Ali
 */
//String name, String password,String email, Country country, Date dateOfBirth, char gender, UserStatus status, byte[] picture, 
//String bio, String phoneNumber
public class DatabaseUserTableTest {

	public static void main(String[] args) {

		User testUser = null;
		User testUser2 = null;
		try {

			testUser = new User("+0101530354", "yahiaamr", Country.Albania, "1003777776", User.CREATE_FROM_CLIENT,
					UserStatus.OFFLINE, null, "hello!", 'M', Date.valueOf("2010-11-1"), "yahiaamr@gmail.com");
			testUser2 = new User("+2760006621", "mayada", Country.Australia, "1003777776", User.CREATE_FROM_SERVER,
					UserStatus.OFFLINE, null, "hello!", 'F', Date.valueOf("2010-11-1"), "mayadakhaled@gmail.com");
		}
		catch (InvalidInputException ex) {
			ex.printStackTrace();
		}

		UserDao userDao = new UserDao();

		System.out.println("insert new user ....");
		try {
			userDao.persist(testUser);
			userDao.persist(testUser2);
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		System.out.println("new user is inserted ....\n");

		System.out.println("retrive user by name...");
		List<User> returnedUserByName = userDao.retrieveByName("yahiaamr");
		for (User user : returnedUserByName) {
			System.out.println("phone number :" + user.getPhoneNumber());
			System.out.println("name :" + user.getName());
			System.out.println("statues :" + user.getStatus());
			System.out.println("date of birth :" + user.getDateOfBirth());
		}
		System.out.println("Done\n");

		System.out.println("retrive by phone....");
		User returnedUserByphone = userDao.retrieveByPhoneNumber("+0101530354");
		if (returnedUserByphone != null) {
			System.out.println("phone number :" + returnedUserByphone.getPhoneNumber());
			System.out.println("name :" + returnedUserByphone.getName());
			System.out.println("statues :" + returnedUserByphone.getStatus());
			System.out.println("date of birth :" + returnedUserByphone.getDateOfBirth());
			System.out.println("Done\n");
		}
		else {
			System.out.println("no user retrieved\n");
		}
		
		System.out.println("updating...");
		User updateUser = null;
		FileInputStream fileInputStream=null;
		byte[] picture=null;
		try {
			fileInputStream=new FileInputStream("C:/picture.jpg");
			int fileSize=(int)new File("/picture.jpg").length();
			picture=new byte[fileSize];
			fileInputStream.read(picture);

		}
		catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		finally {
			if(fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		
		try {
			updateUser = new User("+0101530354", "yahiaamr", Country.Albania, "1113777776", User.CREATE_FROM_SERVER, UserStatus.BUSY, picture,
					"hello!", 'M', Date.valueOf("2010-11-1"), "yahiaamr@gmail.com");
			userDao.update(updateUser);
		}
		catch (InvalidInputException ex) {
			ex.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Done\n");
		
		System.out.println("retrive by phone....");
		returnedUserByphone = userDao.retrieveByPhoneNumber("+0101530354");
		FileOutputStream fileOutputStream=null;
		try {
			fileOutputStream = new FileOutputStream("D:/picture.jpg");
			fileOutputStream.write(returnedUserByphone.getPicture());
		}
		catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		finally {
			if(fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		
		System.out.println("Done\n");
	}
}

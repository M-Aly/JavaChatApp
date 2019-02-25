package com.jets.network.server.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import com.jets.database.dal.dao.impl.FriendsDao;
import com.jets.database.dal.dao.impl.UserDao;
import com.jets.database.dal.dto.User;
import com.jets.database.dal.dto.enums.UserStatus;
import com.jets.network.client.service.locator.ServiceLocator;
import com.jets.network.common.callback.NotifcationReceiverInt;
import com.jets.network.common.serverservice.IntroduceUserInt;
import com.jets.network.common.serverservice.NotificationsInt;
import com.jets.network.exception.NoSuchUserException;
import com.jets.network.exception.StatusChangeFailedException;

/**
 *
 * @author Amer Salah
 */
public class IntroduceUser implements IntroduceUserInt{

	UserDao userInfo;
	User user;
	
	public IntroduceUser(User user) {
		
		this.user=user;	
		userInfo = new UserDao();
	}
	
	
	@Override
	public void register(User user) throws RemoteException, NoSuchUserException {
		try {
			userInfo.persist(user);
		} catch (SQLException e) {
			throw new NoSuchUserException();
		}
		
	}

	@Override
	public User logIn(String phoneNumber, String password) throws RemoteException {
		
		UserDao userDao = new UserDao();
		User user= null;
		
		try {
			user = userDao.retrieveByPassword(phoneNumber, password);
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		
		return user;
		
	}

	@Override
	public void signOut(User user) throws RemoteException, StatusChangeFailedException {
		
		FriendsDao friendsDao = new FriendsDao(user);

		user.setStatus(UserStatus.OFFLINE);
		
		ServiceLocator serviceLocator = ServiceLocator.getInstance(); 
		NotificationsInt notifcationSender = (NotificationsInt)serviceLocator.getService("notification");
		notifcationSender.sendFriendStatusChangeNotifications(friendsDao.retrieveAllFriends());
		 BufferedWriter writer = null;
		 FileWriter fileWriter = null;
		
		try {

			File phoneNumberFile = new File("phoneNumberFile.txt");
			 if (!phoneNumberFile.exists()) {
				 phoneNumberFile.createNewFile();
	         }
			 
			  fileWriter = new FileWriter(phoneNumberFile.getAbsoluteFile());
	          writer = new BufferedWriter(fileWriter);
	         
	         writer.write(user.getPhoneNumber());

		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				writer.close();
				fileWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
	}
    
    
    
}
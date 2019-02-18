package com.jets.network.server.impl;

import java.rmi.RemoteException;
import java.sql.SQLException;

import com.jets.database.dal.dao.impl.UserDao;
import com.jets.database.dal.dto.User;
import com.jets.network.common.serverservice.IntroduceUserInt;
import com.jets.network.exception.NoSuchUserException;
import com.jets.network.exception.StatusChangeFailedException;

/**
 *
 * @author Amer
 */
public class IntroduceUser implements IntroduceUserInt{

	UserDao userInfo;
	
	
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
		return null;
	}

	@Override
	public void signOut(User user) throws RemoteException, StatusChangeFailedException {

		
		
	}
    
    
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.network.common.impl;

import java.rmi.RemoteException;
import java.sql.SQLException;

import com.jets.database.dal.dao.impl.UserDao;
import com.jets.database.dal.dto.User;
import com.jets.network.common.serverservice.IntroduceUserInt;
import com.jets.network.exception.NoSuchUserException;
import com.jets.network.exception.StatusChangeFailedException;

/**
 *
 * @author PC
 */
public class IntroduceUser implements IntroduceUserInt{

	UserDao userInfo;
	
	
	@Override
	public void register(User user) throws RemoteException, NoSuchUserException {
		// TODO Auto-generated method stub
		try {
			userInfo.persist(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new NoSuchUserException();
		}
		
	}

	@Override
	public User logIn(String phoneNumber, String password) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void signOut(User user) throws RemoteException, StatusChangeFailedException {
		// TODO Auto-generated method stub
		
		
	}
    
    
    
}
package com.jets.network.server;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jets.database.dal.dao.impl.UserDao;
import com.jets.database.dal.dto.User;
import com.jets.database.dal.dto.enums.Country;
import com.jets.database.dal.dto.enums.UserStatus;

/**
 * @author Amer Salah
 */

public class Statistics implements StatisticsInt{
	
	UserDao dao= new UserDao();

	@Override
	public int countOnlineUsers() {
		int numOfOnlineUsers=0;
		List<User> users;
		
			users = dao.retrieveAllUsers();
			for(int i=0 ;i<users.size() ; i++)
			{
				if(!users.get(i).getStatus().equals(UserStatus.OFFLINE))
				{
					numOfOnlineUsers++;
				}
			}
			
		
		return numOfOnlineUsers;
		
	}

	@Override
	public int countOfflineUsers() {
		int numOfOfflineUsers=0;
		List<User> users;
		
			users = dao.retrieveAllUsers();
			for(int i=0 ;i<users.size() ; i++)
			{
				if(users.get(i).getStatus().equals(UserStatus.OFFLINE))
				{
					numOfOfflineUsers++;
				}
			}
			
		
		return numOfOfflineUsers;
	}

	@Override
	public int countMaleUsers() {
		int numOfMaleUsers=0;
		List<User> users;
		
			users = dao.retrieveAllUsers();
			for(int i=0 ;i<users.size() ; i++)
			{
				if(users.get(i).getGender()=='m')
				{
					numOfMaleUsers++;
				}
			}
			
		
		return numOfMaleUsers;
	}

	@Override
	public int countFemaleUsers() {
		int numOfFemaleUsers=0;
		List<User> users;
		
			users = dao.retrieveAllUsers();
			for(int i=0 ;i<users.size() ; i++)
			{
				if(users.get(i).getGender()=='f')
				{
					numOfFemaleUsers++;
				}
			}
			
		
		return numOfFemaleUsers;
	}

	@Override
	public Map<Country, Integer> countUserCountries() {
		Map<Country , Integer> usersCountries = new HashMap<>();
		
		List<User> users = dao.retrieveAllUsers();
		//int counter= 0 ;
		
		
		for(int i=0 ;i< users.size() ; i++)
		{
			if(!usersCountries.containsKey(users.get(i).getCountry()))
			{
				usersCountries.put(users.get(i).getCountry(), 1);
				
			}else {
				
				
				usersCountries.replace(users.get(i).getCountry(), usersCountries.get(users.get(i).getCountry())+1);
			}
		}
		
		
		
		return usersCountries;
	}

}

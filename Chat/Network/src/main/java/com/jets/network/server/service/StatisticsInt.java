package com.jets.network.server.service;

import java.util.Map;

import com.jets.database.dal.dto.enums.Country;

/**
 * @author Amer Salah
 */

public interface StatisticsInt {
	
	public int countOnlineUsers();
	public int countOfflineUsers();
	public int countMaleUsers();
	public int countFemaleUsers();
	public Map<Country , Integer> countUserCountries();

}

package com.jets.tests.network;

import java.rmi.RemoteException;
import java.sql.Date;

import com.jets.database.dal.dto.User;
import com.jets.database.dal.dto.enums.Country;
import com.jets.database.dal.dto.enums.UserStatus;
import com.jets.database.exception.InvalidInputException;
import com.jets.network.client.service.locator.ServiceLocator;
import com.jets.network.common.serverservice.IntroduceUserInt;
import com.jets.network.exception.NoSuchUserException;

public class IntroduceUserNetworkTest {
	public static void main(String[] args) {
		IntroduceUserInt introduceUser = (IntroduceUserInt)ServiceLocator.getInstance().getService("introduce");
		User testUser;
		try {
			testUser = new User("+0101530354", "yahiaamr", Country.Albania, "1003777776", User.CREATE_FROM_CLIENT,
					UserStatus.OFFLINE, null, "hello!", 'M', Date.valueOf("2010-11-1"), "yahiaamr@gmail.com");
			introduceUser.register(testUser);
		}
		catch (InvalidInputException ex) {
			ex.printStackTrace();
		}
		catch (RemoteException ex) {
			ex.printStackTrace();
		}
		catch (NoSuchUserException ex) {
			ex.printStackTrace();
		}
	}
}

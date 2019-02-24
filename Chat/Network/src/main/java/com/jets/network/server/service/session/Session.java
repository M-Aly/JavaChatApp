package com.jets.network.server.service.session;

import java.util.UUID;

/**
@author Mohamed Ali
*/
public interface Session {
	UUID getUuid();
	String getUserPhoneNumber();
}

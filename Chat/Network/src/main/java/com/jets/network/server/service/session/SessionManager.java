package com.jets.network.server.service.session;

import com.jets.network.server.service.ServiceFactory;

/**
 * manage sessions between connected users or groups
 * @author Mohamed Ali
 */
public class SessionManager {
	private static SessionManager sessionManager;
	private List<> oneToOneUsers;
    
    private SessionManager(){
    }
    
    public synchronized static SessionManager getInstance(){
        if(sessionManager==null)
        	sessionManager=new SessionManager();
        return sessionManager;
    }
    
    public void createOneToOneSession() {
    }
}

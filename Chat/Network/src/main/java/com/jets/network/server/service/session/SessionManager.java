package com.jets.network.server.service.session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import com.jets.database.dal.dto.Group;


/**
 * manage sessions between connected users or groups
 * @author Mohamed Ali
 */
public class SessionManager {
	
	private static SessionManager sessionManager;
	
	private List<FriendSession> friendSessionList;
	private List<GroupSession> groupSessionList;
    
    private SessionManager(){
    	friendSessionList = new ArrayList<>();
    	groupSessionList = new ArrayList<>();
    }
    
    public synchronized static SessionManager getInstance(){
        if(sessionManager==null) {
        	sessionManager=new SessionManager();
        }
        return sessionManager;
    }
    
    /**
    start session if they are online
    */
    public FriendSession startFriendSession(String userPhoneNumber, String friendPhoneNumber) {
    	FriendSession session = null;
    	if(friendSessionList != null) {
	    	Iterator<FriendSession> friendSessionIterator = friendSessionList.iterator();
	    	while(friendSessionIterator.hasNext() && session == null) {
	    		FriendSession friendSession = friendSessionIterator.next();
	    		if(friendPhoneNumber.equals(friendSession.getUserPhoneNumber()) && userPhoneNumber.equals(friendSession.getFriendPhoneNumber())
	    				|| userPhoneNumber.equals(friendSession.getUserPhoneNumber()) && friendPhoneNumber.equals(friendSession.getFriendPhoneNumber())) {
	    			session = friendSession;
	    		}
	    	}
	    	if(session == null) {
	    		session = new FriendSession(userPhoneNumber, friendPhoneNumber);
	    		friendSessionList.add(session);
	    	}
    	}
    	return session;
    }
    
    /**
    start session if any is online
    */
    public GroupSession startGroupSession(String userPhoneNumber, Group group) {
    	GroupSession session = null;
    	if(groupSessionList != null) {
	    	Iterator<GroupSession> groupSessionIterator = groupSessionList.iterator();
	    	while(groupSessionIterator.hasNext() && session == null) {
	    		GroupSession groupSession = groupSessionIterator.next();
	    		if(groupSession.getGroupId() == group.getGroupId()) {
	    			session = groupSession;
	    			List<String> friendList = session.getFriendList();
	    			Iterator<String> friendListIterator = friendList.iterator();
	    			boolean exists=false;
	    			while(friendListIterator.hasNext() && !exists) {
	    				String friendPhoneNumber=friendListIterator.next();
	    				if(friendPhoneNumber.equals(userPhoneNumber)) {
	    					exists=true;
	    				}
	    			}
	    			if(!exists) {
	    				session.addUser(userPhoneNumber);
	    			}
	    		}
	    	}
	    	if(session == null) {
	    		session = new GroupSession(userPhoneNumber, group);
	    		session.addUser(userPhoneNumber);
	    		groupSessionList.add(session);
	    	}
    	}
    	return session;
    }
    
    /**
    stop session if they are offline
    */
    public void stopFriendSession(UUID uuid) {
    	FriendSession session = getFriendSession(uuid);
    	if(session != null) {
    		friendSessionList.remove(session);
    	}
    }
    
    /**
    the user stops the session if he will be offline
    */
    public boolean stopGroupSession(UUID uuid,String userPhoneNumber) {
    	GroupSession session = getGroupSession(uuid);
    	boolean removed=false;
    	if(session != null) {
	    	session.removeUser(userPhoneNumber);
	    	if(session.getNumberOfFriends() == 0) {
	    		groupSessionList.remove(session);
	    		removed=true;
	    	}
    	}
    	return removed;
    }
    
    private Session getSession(UUID uuid,List sessionList) {
    	Session session = null;
    	if(sessionList != null) {
	    	Iterator sessionIterator = sessionList.iterator();
	    	while(sessionIterator.hasNext() && session == null) {
	    		Session userSession = (Session)sessionIterator.next();
	    		if(uuid.equals(userSession.getUuid())) {
	    			session = userSession;
	    		}
	    	}
    	}
    	return session;
    }
    
    public FriendSession getFriendSession(UUID uuid) {
    	return (FriendSession)getSession(uuid,friendSessionList);
    }
    
    public GroupSession getGroupSession(UUID uuid) {
    	return (GroupSession)getSession(uuid,groupSessionList);
    }
}

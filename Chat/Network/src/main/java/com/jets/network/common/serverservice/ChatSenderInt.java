package com.jets.network.common.serverservice;

import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

import com.jets.database.dal.dto.Group;
import com.jets.database.dal.dto.User;
import com.jets.network.common.callback.ChatReceiverInt;

/**
send chat messages and files
@author Mohamed Ali
*/
public interface ChatSenderInt extends Remote {	
	/**
	join friend session
	@param chatReceiver joining user to observe
	@param userPhoneNumber joining user's phone
	@param friendPhoneNumber friend to join to
	@return uuid of the session
	*/
	UUID joinFriendSession(ChatReceiverInt chatReceiver,String userPhoneNumber,String friendPhoneNumber) throws RemoteException;
	
	/**
	join group session
	@param chatReceiver joining user to observe
	@param userPhoneNumber joining user's phone
	@param group group to join to
	@return uuid of the session
	*/
	UUID joinGroupSession(ChatReceiverInt chatReceiver,String userPhoneNumber,Group group) throws RemoteException;
	
	/**
	exit friend or group session
	@param userPhoneNumber exiting user's phone
	@param uuid uuid of the session
	*/
	void exitSession(String userPhoneNumber,UUID uuid) throws RemoteException;
	
	/**
	send message
	//@param userPhoneNumber sending user's phone
	@param user sending user
	@param uuid uuid of the session
	@param message message
	*/
	void sendMessage(User senderUser,UUID uuid,String message) throws RemoteException;
	
	/**
	send file
	@param user sending user
	@param uuid uuid of the session
	@param file file
	*/
	void sendFile(User senderUser,UUID uuid,File file) throws RemoteException;
}

package com.jets.network.server.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.healthmarketscience.rmiio.SimpleRemoteInputStream;
import com.jets.database.dal.dto.Group;
import com.jets.database.dal.dto.User;
import com.jets.network.common.callback.ChatReceiverInt;
import com.jets.network.common.serverservice.ChatSenderInt;
import com.jets.network.server.service.session.Session;
import com.jets.network.server.service.session.SessionManager;

/**
send chat messages and files
@author Mohamed Ali
*/
public class ChatSender extends UnicastRemoteObject implements ChatSenderInt {
	
	private Map<UUID,Set<String>> sessionMap;
	private Map<String,ChatReceiverInt> onlineUserMap;
	
	public ChatSender() throws RemoteException {
		sessionMap = new HashMap<>();
		onlineUserMap = new HashMap<>();
	}

	@Override
	public UUID joinFriendSession(ChatReceiverInt chatReceiver, String userPhoneNumber, String friendPhoneNumber)
			throws RemoteException {
		onlineUserMap.put(userPhoneNumber,chatReceiver);
		Session session = SessionManager.getInstance().startFriendSession(userPhoneNumber, friendPhoneNumber);
		Set<String> onlineUsers = sessionMap.get(session.getUuid());
		if(onlineUsers == null) {
			onlineUsers = new HashSet<>();
			sessionMap.put(session.getUuid(),onlineUsers);
		}
		onlineUsers.add(userPhoneNumber);
		onlineUsers.add(friendPhoneNumber);
		return session.getUuid();
	}

	@Override
	public UUID joinGroupSession(ChatReceiverInt chatReceiver, String userPhoneNumber, Group group)
			throws RemoteException {
		onlineUserMap.put(userPhoneNumber,chatReceiver);
		Session session = SessionManager.getInstance().startGroupSession(userPhoneNumber, group);
		Set<String> onlineUsers = sessionMap.get(session.getUuid());
		if(onlineUsers == null) {
			onlineUsers = new HashSet<>();
			sessionMap.put(session.getUuid(),onlineUsers);
		}
		onlineUsers.add(userPhoneNumber);
		return session.getUuid();
	}

	@Override
	public void exitSession(String userPhoneNumber, UUID uuid) throws RemoteException {
		onlineUserMap.remove(userPhoneNumber);
		Set<String> onlineUsers = sessionMap.get(uuid);
		onlineUsers.remove(userPhoneNumber);
		boolean removed = SessionManager.getInstance().stopGroupSession(uuid, userPhoneNumber);
		if(!removed && onlineUsers.isEmpty()) {
			SessionManager.getInstance().stopFriendSession(uuid);
		}
		if(onlineUsers.isEmpty()) {
			sessionMap.remove(uuid);
		}
	}
	
	@Override
	public void sendMessage(User senderUser, UUID uuid, String message) throws RemoteException {
		Set<String> onlineUsers = sessionMap.get(uuid);
		for(String userPhoneNumber:onlineUsers) {
			if(!userPhoneNumber.equals(senderUser.getPhoneNumber())) {
				ChatReceiverInt chatReceiver = onlineUserMap.get(userPhoneNumber);
				chatReceiver.receiveMessage(senderUser, uuid, message);
			}
		}
	}

	@Override
	public void sendFile(User senderUser, UUID uuid, File file) throws RemoteException {
		Set<String> onlineUsers = sessionMap.get(uuid);
		ExecutorService executorService = Executors.newFixedThreadPool(onlineUsers.size()-1);
		for(String userPhoneNumber:onlineUsers) {
			if(!userPhoneNumber.equals(senderUser.getPhoneNumber())) {
				ChatReceiverInt chatReceiver = onlineUserMap.get(userPhoneNumber);
				boolean accept = chatReceiver.acceptFile(senderUser, uuid, file);
				if(accept) {
					executorService.submit(()->{
						FileInputStream fileInputStream=null;
						SimpleRemoteInputStream remoteInputStream=null;
						try {
							fileInputStream=new FileInputStream(file);
							remoteInputStream=new SimpleRemoteInputStream(fileInputStream);
							chatReceiver.receiveFile(senderUser, uuid, file,remoteInputStream.export());
						}
						catch (FileNotFoundException ex) {
							ex.printStackTrace();
						}
						catch (RemoteException ex) {
							ex.printStackTrace();
						}
						finally {
							if(remoteInputStream!=null) {
								remoteInputStream.close();
							}
							if(fileInputStream!=null) {
								try {
									fileInputStream.close();
								}
								catch (IOException ex) {
									ex.printStackTrace();
								}
							}
						}
					});
				}
			}
		}
	}
	
}

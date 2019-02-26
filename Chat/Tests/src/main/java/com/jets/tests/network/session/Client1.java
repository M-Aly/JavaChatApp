package com.jets.tests.network.session;

import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.UUID;

import com.healthmarketscience.rmiio.RemoteInputStream;
import com.jets.database.dal.dao.impl.UserDao;
import com.jets.database.dal.dto.User;
import com.jets.network.client.service.locator.ServiceLocator;
import com.jets.network.common.callback.ChatReceiverInt;
import com.jets.network.common.serverservice.ChatSenderInt;

public class Client1 {
	public static void open(String user,String friend) {
		try {
			ChatSenderInt chatSender=(ChatSenderInt)ServiceLocator.getInstance().getService("chat");
			ChatReceiverInt chatReceiver = new ChatReceiver();
			UUID uuid=chatSender.joinFriendSession(chatReceiver, user, friend);
			Scanner scanner = new Scanner(System.in);
			UserDao userDao=new UserDao();
			User senderUser=userDao.retrieveByPhoneNumber(user);
			while(true) {
				String line=scanner.nextLine();
				chatSender.sendMessage(senderUser, uuid, line);
			}
		}
		catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		open("+0101530354", "+2760006621");
	}
}

class ChatReceiver extends UnicastRemoteObject implements ChatReceiverInt {

	protected ChatReceiver() throws RemoteException {
		super();
	}

	@Override
	public boolean acceptFile(User senderUser, UUID uuid, File file) throws RemoteException {
		System.out.println(senderUser.getName()+":"+file.getName());
		return true;
	}

	@Override
	public void receiveFile(User senderUser, UUID uuid, File file, RemoteInputStream fileStream)
			throws RemoteException {
		System.out.println(senderUser.getName()+":"+file.getName());
	}

	@Override
	public void receiveMessage(User senderUser, UUID uuid, String message) throws RemoteException {
		System.out.println(senderUser.getName()+":"+message);
	}
}
package com.jets.network.client.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

import com.healthmarketscience.rmiio.RemoteInputStream;
import com.healthmarketscience.rmiio.RemoteInputStreamClient;
import com.jets.database.dal.dto.User;
import com.jets.gui.controller.client.DashboardController;
import com.jets.network.client.service.locator.ServiceLocator;
import com.jets.network.common.callback.ChatReceiverInt;
import com.jets.network.common.serverservice.ChatSenderInt;

/**
receive chat messages and files
@author Mohamed Ali
@author Amer
*/
public class ChatReceiver extends UnicastRemoteObject implements ChatReceiverInt {

	private DashboardController dashboardController;
	//private ChatSenderInt chatSender;
	
	public ChatReceiver(DashboardController dashboardController) throws RemoteException {
		this.dashboardController=dashboardController;
		//chatSender = (ChatSenderInt)ServiceLocator.getInstance().getService("chat");
	}
	
	@Override
	public boolean acceptFile(User senderUser, UUID uuid, File file) throws RemoteException {
		return dashboardController.acceptFile(senderUser, uuid, file);
	}

	@Override
	public void receiveFile(User senderUser, UUID uuid, File file, RemoteInputStream fileStream)
			throws RemoteException {
		FileOutputStream ostream = null;
        InputStream istream = null;
        boolean readSuccess = false;
        try {
        	istream = RemoteInputStreamClient.wrap(fileStream);
            File saveFile = dashboardController.saveFile(file);
            if(saveFile != null) {
	            ostream = new FileOutputStream(saveFile);
	            int fileLength=(int)file.length();
	            byte[] buf = new byte[fileLength];
	            int bytesRead = 0;
	            while ((bytesRead = istream.read(buf)) >= 0) {
	                ostream.write(buf, 0, bytesRead);
	                dashboardController.updateProgress((double)bytesRead/(double)fileLength);
	            }
	            ostream.flush();
	            readSuccess = true;
            }
        }
        catch (IOException ex) {
        	ex.printStackTrace();
        }
        finally{
            try {
                if(istream!=null){
                    istream.close();
                }
                if(istream!=null){
                    ostream.close();
                }
                if(fileStream!=null) {
                	fileStream.close(readSuccess);
                }
            }
            catch(IOException ex) {
            	ex.printStackTrace();
            }
        }
	}

	@Override
	public void receiveMessage(User senderUser, UUID uuid, String message) throws RemoteException {
		dashboardController.receiveMessage(senderUser, uuid, message);
	}
	
}

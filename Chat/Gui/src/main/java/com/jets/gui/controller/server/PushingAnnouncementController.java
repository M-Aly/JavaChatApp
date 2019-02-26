package com.jets.gui.controller.server;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import com.jets.network.server.service.ServiceFactory;
import com.jets.network.server.service.impl.Announcement;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 *@author Amer Salah 
 */

public class PushingAnnouncementController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		pushAnnouncement();
		
	}
	
	@FXML
	TextArea AnnounceTextArea;
	
	@FXML
	Button makeAnnounce;
	
	ServiceFactory factory = ServiceFactory.getInstance();
	
	
	
	public void pushAnnouncement() {
		
		Announcement announcement = (Announcement) factory.getServices().get("notification");
		if(announcement!=null) {
			String subject = AnnounceTextArea.getText().toString();
			
			try {
				announcement.broadcastAnnouncement(subject);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	

}

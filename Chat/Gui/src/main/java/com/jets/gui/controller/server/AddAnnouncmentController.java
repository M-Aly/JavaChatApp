package com.jets.gui.controller.server;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import com.jets.network.server.service.impl.Announcement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

public class AddAnnouncmentController implements Initializable {

	@FXML
	private Button announcement;
	@FXML
	private TextArea  writtingArea;
	
	Announcement newAnnouncement= null;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	
	@FXML
	public void makingAnnounce()
	{
		try {
			newAnnouncement = new Announcement();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		announcement.setOnMouseClicked((event) -> {
			
			
			if (writtingArea.getText()!=null) 
			{
				try {
					newAnnouncement.broadcastAnnouncement("Hello EveryOne");
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
	}
}

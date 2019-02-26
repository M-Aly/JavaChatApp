package com.jets.gui.controller.server;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;

import com.jets.network.server.service.ServiceFactory;

import javafx.fxml.Initializable;

public class ServerManagementController implements Initializable {

	@FXML
	private Button startButton;
	@FXML
	private Button stopButton;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		startButton.setDisable(false);
		stopButton.setDisable(true);
		
	}
	
	boolean startFlag=  true;
	boolean stopFlag =  true;
	
	
	@FXML
	public void startServer()
	{
		
			
			
				stopButton.setDisable(false);
				startButton.setDisable(true);
					ServiceFactory.getInstance().startServices();
				
			
		
	}
	@FXML
	public void stopServer()
	{
		
		startButton.setDisable(false);
		stopButton.setDisable(true);
			ServiceFactory.getInstance().stopServices();
		
	}

}

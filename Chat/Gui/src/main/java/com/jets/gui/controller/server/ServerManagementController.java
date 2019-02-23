package com.jets.gui.controller.server;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class ServerManagementController implements Initializable {

	@FXML
	private Button startButton;
	@FXML
	private Button stopButton;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	@FXML
	public void startServer()
	{
		startButton.setOnMouseClicked((event) -> {
			
			
		});
	}
	@FXML
	public void stopServer()
	{
		stopButton.setOnMouseClicked((event) -> {
			
			
		});
	}

}

package com.jets.gui.controller.server;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.fxml.Initializable;

public class AddUserController implements Initializable  {

	@FXML
	private TextField phoneTextArea;
	@FXML
	private Button addPhone;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	@FXML
	public void addUserPhone()
	{
		addPhone.setOnMouseClicked((event) -> {
			
			
		});
	}
}

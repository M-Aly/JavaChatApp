package com.jets.gui.controller.server;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	@FXML
	public void makingAnnounce()
	{
		announcement.setOnMouseClicked((event) -> {
			
			
		});
	}
}

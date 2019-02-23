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

public class ServerController  implements Initializable{


	@FXML
	private Button serverManagment;
	@FXML
	private Button serverStatistics;
	@FXML
	private Button serverAnnouncment;
	@FXML
	private Button serverAddUser;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}


public void manageServer()
{
	serverManagment.setOnMouseClicked((event) -> {
		
		FXMLLoader serverLoader = new FXMLLoader();
		Parent loader;
		try {
			ServerManagementController controller = new ServerManagementController();
			serverLoader.setController(controller);
			loader = serverLoader.load(getClass().getResource("/server/serverSwitchOn_OFF.fxml").openStream());

			Stage newStage = new Stage();
			newStage.setScene(new Scene(loader));
			newStage.setMinWidth(700);
			newStage.setMinHeight(700);
			newStage.show();
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		
		}
	);
}
public void announcmentServer()
{
	serverAnnouncment.setOnMouseClicked((event) -> {
		
		FXMLLoader serverLoader = new FXMLLoader();
		Parent loader;
		try {
			
			AddAnnouncmentController controller = new AddAnnouncmentController();
			serverLoader.setController(controller);
			loader = serverLoader.load(getClass().getResource("/server/addAnnoncment.fxml").openStream());
			Stage newStage = new Stage();
			newStage.setScene(new Scene(loader));
			newStage.setMinWidth(700);
			newStage.setMinHeight(700);
			newStage.show();
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		
		}
	);
}
public void AddUser()
{
	serverAddUser.setOnMouseClicked((event) -> {
		
		FXMLLoader serverLoader = new FXMLLoader();
		Parent loader;
		try {
			
			AddUserController controller = new AddUserController();
			serverLoader.setController(controller);
			loader = serverLoader.load(getClass().getResource("/server/AddUser.fxml").openStream());
			Stage newStage = new Stage();
			newStage.setScene(new Scene(loader));
			newStage.setMinWidth(700);
			newStage.setMinHeight(700);
			newStage.show();
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		
		}
	);
}
public void makeStatistics()
{
	serverStatistics.setOnMouseClicked((event) -> {
		
		FXMLLoader serverLoader = new FXMLLoader();
		Parent loader;
		try {
			
			StatisticsController controller = new StatisticsController();
			serverLoader.setController(controller);
			loader = serverLoader.load(getClass().getResource("/server/Statisticsform.fxml").openStream());
			Stage newStage = new Stage();
			newStage.setScene(new Scene(loader));
			newStage.setMinWidth(700);
			newStage.setMinHeight(700);
			newStage.show();
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		
		}
	);
}
}


















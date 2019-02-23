package com.jets.gui.controller.client;

import javafx.application.Platform;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jets.database.dal.dao.impl.UserDao;
import com.jets.database.dal.dto.User;
import com.jets.network.exception.UpdateUserFailedException;
import com.jets.network.server.impl.UserSettings;

import animatefx.animation.Pulse;
import animatefx.animation.Swing;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

public class UpdateProfileController implements Initializable {

	@FXML
	private Button updateButton;
	@FXML
	private TextField name;
	@FXML
	private TextField email;
	@FXML
	private TextField password;
	UserDao currentUser =null;
	User retreivedUser = null;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		//////////////////
		currentUser = new UserDao();
		retreivedUser = currentUser.retrieveByPhoneNumber("9380283098");//session managmenttttttttttttttttt
		name.setText(retreivedUser.getName());
		email.setText(retreivedUser.getEmail());
		password.setText(retreivedUser.getPassword());
		//////////////////

	}

	/*
	 * public void updateProfile() { // Create a Runnable Runnable task = new
	 * Runnable() { public void run() { startRun(); } };
	 * 
	 * // Run the task in a background thread Thread backgroundThread = new
	 * Thread(task); // Terminate the running thread if the application exits
	 * backgroundThread.setDaemon(true); // Start the thread
	 * backgroundThread.start(); } public void updateProfile() {
	 * 
	 * try {
	 * 
	 * Platform.runLater(new Runnable() {
	 * 
	 * @Override public void run() { updateButton.setOnMouseClicked((event) -> {
	 * updateButton.toFront(); new Pulse(updateButton).play();}); } });
	 * 
	 * Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
	 * 
	 * 
	 * }
	 */

	
	
	public void updateProfile() //session managment
	{
		UserSettings userSettings = new UserSettings();
		updateButton.toFront();
		Swing p = new Swing(updateButton);
		
		updateButton.setOnMouseClicked((event) -> {
			p.play();
			
			try {
				userSettings.updateProfile(retreivedUser);
			}
			 catch (RemoteException e) {
				
				e.printStackTrace();
				
			} catch (UpdateUserFailedException e) {
				
				e.printStackTrace();
			}
		
		});

	}

}

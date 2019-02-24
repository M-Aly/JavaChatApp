package com.jets.gui.controller.server;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jets.database.dal.dao.impl.UserDao;
import com.jets.database.dal.dto.User;
import com.jets.network.server.impl.IntroduceUser;

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
		UserDao userDao = new UserDao(); 
		
		addPhone.setOnMouseClicked((event) -> {
			//User newUser = new User(phoneNumber, name, country, password, changePasswordFlag, status, picture, bio, gender, dateOfBirth, email))
			if (phoneTextArea.getText()!=null)
			{
				if (userDao.retrieveByPhoneNumber(phoneTextArea.getText())!=null)
				{
					userDao.persist(newUser);
				}
				else
				{
					System.out.print("this user is already exist");
				}
			}
			else
			{
				System.out.print("no phone number exist");
			}
		});
	}
}

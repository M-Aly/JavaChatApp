package com.jets.gui.controller.client;

import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.*;

import com.jets.database.dal.dao.impl.FriendsDao;
import com.jets.database.dal.dao.impl.UserDao;
import com.jets.database.dal.dto.Friend;
import com.jets.database.dal.dto.User;
import com.jets.database.dal.dto.enums.InvitationStatus;
import com.jets.network.exception.UpdateUserFailedException;
import com.jets.network.server.impl.UserSettings;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class AddNewFriendController implements Initializable {

	@FXML
	private ListView<TextField> PhoneList;
	@FXML
	private Button addAnotherPhoneButton;
	@FXML
	private Button addButton;
	@FXML
	private TextField textArea;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		textArea = new TextField();
		PhoneList.getItems().add(textArea);
		addAnotherPhoneButton.setVisible(false);
		
	}
	@FXML
	public void addNewPhone()
	{
		
		addButton.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				addAnotherPhoneButton.setVisible(true);
				UserSettings userSettings = new UserSettings();
				
				////////
				UserDao userdao= new UserDao();	
				FriendsDao friendDao = new FriendsDao(userdao.retrieveByPhoneNumber("01235623666")); // // seesion must be here
				/////////
				
				
				ObservableList<TextField> friendPhoneList;			   
				friendPhoneList = PhoneList.getSelectionModel().getSelectedItems();
				
			    for (TextField textField : friendPhoneList)
			    {

			    	 	User retreivedUser = userdao.retrieveByPhoneNumber(textField.getText());
			    	 
					   if (retreivedUser!=null)
					   {
						   Friend newFriend = new Friend(retreivedUser, InvitationStatus.PENDING);
						  
							   
							   try {
								userSettings.addFriend(newFriend);
								
							} catch (RemoteException e) {
								
								e.printStackTrace();
								
							} catch (UpdateUserFailedException e) {
								
								e.printStackTrace();
							}
						   
						   
					   }
					   else 
					   {
						   System.out.println("this user dosen't support this application");
					   }
				  			  
				}
			}
		});
	}
	@FXML
	public void addAnotherPhone() {
		addAnotherPhoneButton.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				
				
				PhoneList.getItems().add(new TextField());

			}
		});
	}
}

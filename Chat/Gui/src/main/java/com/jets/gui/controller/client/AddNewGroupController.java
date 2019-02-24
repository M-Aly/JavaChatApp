/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.gui.controller.client;

import java.net.URL;
import java.util.ResourceBundle;

import com.jets.database.dal.dto.Group;
import com.jets.network.server.impl.UserSettings;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 *
 * @author Mayada Khaled
 */
public class AddNewGroupController implements Initializable{

    @FXML
	private VBox PhoneList;
	@FXML
	private Button addAnotherPhoneButton;
	@FXML
	private Button addButton;
	@FXML
	private TextField textArea; 
	@FXML
	private TextField groupName;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    @FXML
	public void addNewPhone()
	{
		addButton.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(groupName.getText()!=null)
				{		UserSettings userSettings = new UserSettings();
				
                                for (int counter =0; counter<PhoneList.getChildren().size();counter++)
                                {
                                	if (textArea.getText()!=null)
                                	{ 
                                		Group newGroup = new Group(groupName.getText(), userPhoneNumber); // which phonenumber ?
                                		newGroup.addFriend(PhoneList.getChildren().get(counter).toString());
                                        userSettings.addGroup(newGroup);                               
                                	}
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
				
				PhoneList.getChildren().add(new TextField());

			}
		});
	}
}

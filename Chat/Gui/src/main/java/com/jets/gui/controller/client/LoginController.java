/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.gui.controller.client;


import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jets.database.dal.dto.User;
import com.jets.network.client.service.locator.ServiceLocator;
import com.jets.network.common.serverservice.IntroduceUserInt;
import com.jets.network.server.service.impl.IntroduceUser;

import animatefx.animation.FadeIn;
import animatefx.animation.Swing;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;


/**
 *
 * @author PC
 */
public class LoginController implements Initializable {
    
    @FXML
    private  Pane PaneID;
    
    @FXML
    private  TextField PhoneTxt;
    
    @FXML
    private  PasswordField PasswordTxt;
    
    @FXML
    private  Button LoginBtn;
    
    @FXML
    private  Hyperlink CreateAccountLink;
    
   
    @FXML
    private  CheckBox RemembermeCheckbox;
    
    @FXML
    private  Label Error_lbl;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	PasswordTxt.setDisable(true);
    	IntroduceUserInt introduceUser = (IntroduceUserInt)ServiceLocator.getInstance().getService("login");
    	CreateAccountLink.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Swing swing = new Swing(CreateAccountLink);
				swing.play();
				FXMLLoader chatloader = new FXMLLoader();
				Parent loader;
				try {
					RegisterController controller = new RegisterController();
					chatloader.setController(controller);
					loader = chatloader
							.load(getClass().getResource("/client/login/Register.fxml").openStream());

					Stage newStage = new Stage();
					newStage.setScene(new Scene(loader));
					newStage.show();
					new FadeIn(loader).play();
				} catch (IOException ex) {
					ex.printStackTrace();
				}

			}
		});
    
       if(RemembermeCheckbox.isSelected())
       {
           //do smothing
       }
   	PhoneTxt.setOnKeyPressed((e)->{
   		if (e.getCode() == KeyCode.ENTER) {
   	       // System.out.println("A key was pressed");
   	    
   			
	     	
	     	try {
	     		User user= introduceUser.validate(PhoneTxt.getText());
	     		if(user!=null) {
	     			PasswordTxt.setDisable(false);
				
	     		}
			} catch (RemoteException e1) {
				
				e1.printStackTrace();
			}
   	
   		}
   	});
   		LoginBtn.setOnAction((e)->{
   			
   			User user1;
   			if(!PasswordTxt.getText().isEmpty())
			try {
				user1 = introduceUser.logIn(PhoneTxt.getText(), PasswordTxt.getText());
				if(user1==null)
				{
					Error_lbl.setText("Invalid Password");
				}
				else
					Error_lbl.setText("doneeeeeeeeeeeeeeeeeeeeeeeeeee");
			} catch (RemoteException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
   		});
   	
   	}
            
     

    
   
    
   
    
  
    
    
}

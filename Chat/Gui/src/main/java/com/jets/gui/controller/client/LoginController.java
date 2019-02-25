/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.gui.controller.client;


import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import com.jets.network.server.service.impl.IntroduceUser;

import animatefx.animation.FadeIn;
import animatefx.animation.Swing;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;


/**
 *
 * @author PC
 */
public class LoginController implements Initializable {
    
    @FXML
    private  AnchorPane AnchorPaneID;
    
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
       if(RemembermeCheckbox.isSelected())
       {
           //do smothing
       }
            
    } 
    @FXML
    public void newAccount()
    {
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
    }
    
    @FXML
    public void handleLoginButtonAction()
    {
    	LoginBtn.setOnMousePressed(new EventHandler<MouseEvent>() {
    		@Override
			public void handle(MouseEvent event) {
    			IntroduceUser loginUser = new IntroduceUser();
    			
    			try {
					loginUser.logIn(PhoneTxt.getText(), PasswordTxt.getText());
				} catch (RemoteException e) {
					
					e.printStackTrace();
				}
    		
    	}});
   
    }
  
    
    
}

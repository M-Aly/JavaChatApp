/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.gui.controller;

import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

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
    private  TextField PasswordTxt;
    
    @FXML
    private  Button LoginBtn;
    
    @FXML
    private  Hyperlink CreateAccountLink;
    
   
    @FXML
    private  CheckBox RemembermeCheckbox;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       //PasswordTxt.setVisible(false);
       
       PhoneTxt.setOnKeyPressed((event) -> {
           if(event.getCode().equals(KeyCode.ENTER)){
               if(PhoneTxt.equals("Nouran"))
               {
                   PasswordTxt.setVisible(true);
               }
           }
       });
       if(RemembermeCheckbox.isSelected())
       {
           //do smothing
       }
       CreateAccountLink.onMouseClickedProperty();
               {
                   //do something
               }
    } 
    
    @FXML
    public void handleLoginButtonAction(ActionEvent event)
    {
        if(PasswordTxt.equals("nouran") && PhoneTxt.equals("1233"))
        {
            System.out.println("Successfuly opened");
        }
    }
  
    
    
}

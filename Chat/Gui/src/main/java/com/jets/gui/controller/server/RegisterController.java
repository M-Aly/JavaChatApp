/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.gui.controller.server;

import java.awt.Checkbox;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Observable;
import java.util.ResourceBundle;

import com.jets.database.dal.dao.impl.UserDao;
import com.jets.database.dal.dto.User;
import com.jets.database.dal.dto.enums.Country;
import com.jets.database.dal.dto.enums.UserStatus;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author PC
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField PhoneNumTxt;

    @FXML
    private TextField NameTxt;

    @FXML
    private TextField EmailTxt;

    @FXML
    private ImageView imageView;

    @FXML
    private Button UploadPhotobTN;

    @FXML
    private Checkbox MaleCheckBox;

    @FXML
    private Checkbox FemaleCheckBox;

    @FXML
    private ComboBox CountryComboBox;

    @FXML
    private DatePicker BirthdateCalender;

    @FXML
    private TextArea BioTxtArea;

    @FXML
    private Button RegisterBtn;

    @FXML
    private PasswordField PassField;

    @FXML
    private PasswordField ConfrimPassField;

    @FXML
    private AnchorPane AnchorPaneID;

    @FXML
    private void uploadPhotoButtonAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
        File selectedImage = fileChooser.showOpenDialog(null);
    }

    @FXML
    private void registerButtonAction() {
    	
    	UserDao userDao = new UserDao();
    	
    	RegisterBtn.setOnMouseClicked((event) -> {
    		
    		if (PassField.getText()==ConfrimPassField.getText())
        	{
        		if (MaleCheckBox.is)
        		{
        			User newUser = new User(PhoneNumTxt.getText(),
    						NameTxt.getText(),
    						CountryComboBox.getValue(),
    						PassField.getText(), 
    						ConfrimPassField.getText(),
    						UserStatus.AVAILABLE ,
    						imageView.getb,
    						BioTxtArea.getText(),
    						'M',
    						BirthdateCalender,
    						);
        		}
        		else if(FemaleCheckBox)
        		{
        			
        		}
        		
        	}
    		
    		
    		
    		
    		}
    	);
    	
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    }

}

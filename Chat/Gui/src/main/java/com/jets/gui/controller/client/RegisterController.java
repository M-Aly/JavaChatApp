/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.gui.controller.client;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;
import java.util.ResourceBundle;

import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

import com.jets.database.dal.dto.User;
import com.jets.database.dal.dto.enums.Country;
import com.jets.database.dal.dto.enums.UserStatus;
import com.jets.network.common.serverservice.IntroduceUserInt;
import com.jets.network.exception.NoSuchUserException;
import com.jets.network.exception.StatusChangeFailedException;
import com.jets.network.server.service.impl.IntroduceUser;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.scene.control.RadioButton;
import javafx.scene.image.*;

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
	    private ImageView ProfileImageView;

	    @FXML
	    private Button UploadPhotobTN;

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
	    private Label Phone_lbl;

	    @FXML
	    private Label Name_lbl;

	    @FXML
	    private Label Password_lbl;

	    @FXML
	    private Label Email_lbl;

	    @FXML
	    private Label Confirm_lbl;

	    @FXML
	    private Label Birth_lbl;
	    
	    @FXML
	    private ToggleGroup Gender;
/*
	    @FXML
	    private Label gender_lbl;
	    
	     @FXML
	    private Label country_lbl;
	     
	      @FXML
	    private Label bio_lbl;
	      */
	       @FXML
	    private RadioButton maleRadiobtn;

	       @FXML
	    private RadioButton femaleRadiobtn;
	  
	   
	    

	   

	    @Override
	    public void initialize(URL url, ResourceBundle rb) {
	    	//IntroduceUser introduceUser=new IntroduceUser() ;
	    	for(Country country : Country.values()){
	           CountryComboBox.getItems().add(country.toString());
	     }
	    	UploadPhotobTN.setOnAction((e)-> {
	    		
	    		   FileChooser fileChooser = new FileChooser();
	   	        fileChooser.setTitle("Open Image");
	   	        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
	   	        File selectedImage = fileChooser.showOpenDialog(null);
	   	  
	   	        Image image = new Image(selectedImage.toURI().toString());
	    	     ProfileImageView.setImage(image);
	    		
	    		
	    	});
	     
	   	RegisterBtn.setOnAction((e)->{
	   		/*String phoneNumber,
            String name, 
            Country country,
            String password, 
            boolean changePasswordFlag,
            UserStatus status,
            byte[] picture,
            String bio ,
            char gender,
            Date dateOfBirth,
            String email 
            */
	    		
	    	/*	try {
	    				User user= new User(PhoneNumTxt.getText(), NameTxt.getText(),Country. , null, false, null, null, null, 0, null, null);
	    		}*/
	    		
	    		    
	    		
	    	});

	    }
}

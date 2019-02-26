/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.gui.controller.client;



import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

import com.jets.database.dal.dto.User;
import com.jets.database.dal.dto.enums.Country;
import com.jets.database.dal.dto.enums.UserStatus;
import com.jets.database.exception.InvalidInputException;
import com.jets.network.client.service.locator.ServiceLocator;
import com.jets.network.common.serverservice.IntroduceUserInt;
import com.jets.network.exception.NoSuchUserException;
import com.jets.network.exception.StatusChangeFailedException;
import com.jets.network.server.service.impl.IntroduceUser;

import animatefx.animation.FadeIn;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ToggleGroup;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.RadioButton;
import javafx.scene.image.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author PC
 */
public class RegisterController implements Initializable {
	 @FXML
	    private TextField PhoneNumTxt;

	    @FXML
	    private TextField NameTxt=null;

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

	    @FXML
	    private Label Gender_lbl;
	    
	     @FXML
	    private Label Country_lbl;
	     
	      @FXML
	    private Label Bio_lbl;
	      
	       @FXML
	    private RadioButton MaleRadioBtn;

	       @FXML
	    private RadioButton FemaleRadioBtn;
	     
	     Image profileimage;
	     byte [] imageByte;
	     boolean photoflag=false;
	    
	    @Override
	    public void initialize(URL url, ResourceBundle rb) {
	    	//IntroduceUser introduceUser=new IntroduceUser() ;
	    	//MaleRadioBtn.setSelected(false);
	    	
	    	
	    	for(Country country : Country.values()){
	           CountryComboBox.getItems().add(country.toString());
	     }
	    	UploadPhotobTN.setOnAction((e)-> {
	    		
	    	   FileChooser fileChooser = new FileChooser();
	   	        fileChooser.setTitle("Open Image");
	   	        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
	   	        File selectedImage = fileChooser.showOpenDialog(null);
	   	  
	   	        if(selectedImage != null) {
	   	        	try {
						FileInputStream fileInputStream = new FileInputStream(selectedImage);
						imageByte = new byte [(int) selectedImage.length()];
						fileInputStream.read(imageByte);
		   	        	profileimage = new Image(new ByteArrayInputStream(imageByte));
		   	        	ProfileImageView.setImage(profileimage);
		   	        	photoflag=true;
					} catch (FileNotFoundException e1) {
						
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	   	        	
	   	        }
	    		
	    		
	    	});
	     
	   	RegisterBtn.setOnAction((e)->{
	   		Name_lbl.setText("*");
	   		Password_lbl.setText("*");
	   		Country_lbl.setText("*");
	   		Confirm_lbl.setText("*");
	   		Phone_lbl.setText("*");
	   		Email_lbl.setText("*");
	   		Bio_lbl.setText("*");
	   	/*	System.out.println("Name "+NameTxt.getText().isEmpty());  
	   		System.out.println("password "+PassField.getText().isEmpty());
	   		System.out.println("Phone "+PhoneNumTxt.getText().isEmpty());
	   		System.out.println("confirm "+ConfrimPassField.getText().isEmpty());
	   		System.out.println("country "+CountryComboBox.getSelectionModel().isEmpty());
	   		System.out.println("bio "+BioTxtArea.getText().isEmpty());
	   		System.out.println("birth "+(BirthdateCalender.getValue()==null));
	   		//System.out.println("gender "+(Gender.getSelectedToggle()==null));
	   		System.out.println("enmail "+EmailTxt.getText().isEmpty());*/
	   		if(!(ConfrimPassField.getText().isEmpty()) &&!(NameTxt.getText().isEmpty()) && photoflag==true  && !(PassField.getText().isEmpty()) && !(PhoneNumTxt.getText().isEmpty()) && !(CountryComboBox.getSelectionModel().isEmpty()) && !(BioTxtArea.getText().isEmpty()) && !(BirthdateCalender.getValue()==null) && !(Gender.getSelectedToggle()==null) && !(EmailTxt.getText().isEmpty()))
	   		{
	   		
	   	
	   		LocalDate localDate = BirthdateCalender.getValue();
	   		Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
	   		java.sql.Date sqlDate = new java.sql.Date(Date.from(instant).getTime());
	      
	      try {

	 	    	/*ByteArrayOutputStream bos = new ByteArrayOutputStream();
	 	    	BufferedImage bImage= ImageIO.read(profileimage);
	 	        ImageIO.write(profileimage, "jpg", bos );
	 	        byte [] data = bos.toByteArray();*/
	    	    char gender;
	    	  	if(Gender.getSelectedToggle().toString().equals("Male"))
	    	  	{
	    	  		gender='M';
	    	  	}
	    	  	else
	    	  	{
	    	  		gender='F';
	    	  	}
	 	        
		     	User user= new User(PhoneNumTxt.getText(),
				NameTxt.getText(),
				Country.valueOf(CountryComboBox.getValue().toString()),
				PassField.getText(),
				true,
				UserStatus.AVAILABLE,
				imageByte,
				BioTxtArea.getText(),
				gender,
				sqlDate,
				EmailTxt.getText());
		     	
		     	IntroduceUserInt introduceUser = (IntroduceUserInt)ServiceLocator.getInstance().getService("introduce");
		     	introduceUser.register(user);
		     	
		     	FXMLLoader loginloader = new FXMLLoader();
				Parent loader;
				try {
					LoginController controller = new LoginController();
					loginloader.setController(controller);
					loader = loginloader
							.load(getClass().getResource("/client/login/Login.fxml").openStream());

					Stage newStage = new Stage();
					newStage.setScene(new Scene(loader));
					newStage.show();
					//new FadeIn(loader).play();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
		     	
		     		
		     	
		} catch (InvalidInputException e1) {
			Alert alert = new Alert((javafx.scene.control.Alert.AlertType) AlertType.ERROR);
	        alert.setTitle("Register Error");
	        alert.setHeaderText("Results:");
	        alert.setContentText(e1.getMessage());
	        //System.out.println(e1.getMessage());
	        alert.showAndWait();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		} catch (NoSuchUserException e1) {
			    Alert alert = new Alert((javafx.scene.control.Alert.AlertType) AlertType.ERROR);
		        alert.setTitle("Register Error");
		        alert.setHeaderText("Results:");
		        alert.setContentText("This User is already defined");
		 
		        alert.showAndWait();
		}    
	   		}
	   		else {
	   		
	   			if(NameTxt.getText().isEmpty())
	   			{
	   				Name_lbl.setText("Name Must be Specifed");
	   				
	   			}
	   			if(PhoneNumTxt.getText().isEmpty())
	   			{
	   				Phone_lbl.setText("PhoneNumber Must be Specifed");
	   			}
	   			if(PassField.getText().isEmpty())
	   			{
	   				Password_lbl.setText("Password Must be Specifed");
	   			}
	   			if(ConfrimPassField.getText().isEmpty())
	   			{
	   				Confirm_lbl.setText("ConfirmPassword Must be Specifed");
	   			}
	   			if(EmailTxt.getText().isEmpty())
	   			{
	   				Email_lbl.setText("Email Must be Specifed");
	   			}
	   			if(BioTxtArea.getText().isEmpty())
	   			{
	   				Bio_lbl.setText("BioInformation Must be Specifed");
	   			}
	   	    	
	   			if(CountryComboBox.getSelectionModel().isEmpty())
	   			{
	   				Country_lbl.setText("Country Must be Specifed");
	   			}
	   		}
	    	});

	    }
		
}


package com.jets.database.dal.dto;
import java.io.Serializable;
import java.sql.Date;

import com.jets.database.dal.dto.enums.Country;
import com.jets.database.dal.dto.enums.UserStatus;
import com.jets.database.exception.InvalidDTOException;
import com.jets.database.exception.InvalidInputException;



/**
DTO for User table
@author Mohamed Ali
*/
public class User implements Serializable{

	private String name;
    private String password;
    private boolean changePasswordFlag;
    private String email;
    private Country country;
    private Date dateOfBirth;
    private char gender;
    private UserStatus status;
    private byte[] picture;
    private String bio;
    private String phoneNumber;
    
    private boolean passwordFlag=true;
    
    public static final boolean CREATE_FROM_SERVER=true;
    public static final boolean CREATE_FROM_CLIENT=false;

    public User(
            String phoneNumber,
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
            
    ) throws InvalidInputException {
        StringBuilder errors=new StringBuilder("");
        try{
            setName(name);
        }
        catch(InvalidInputException exception){
            errors.append(exception.getMessage());
            errors.append("\n");
        }
        try{
            setPassword(password);
        }
        catch(InvalidInputException exception){
            errors.append(exception.getMessage());
            errors.append("\n");
        }
        
        try{
            setEmail(email);
        }
        catch(InvalidInputException exception){
            errors.append(exception.getMessage());
            errors.append("\n");
        }
        setCountry(country);
        try{
            setDateOfBirth(dateOfBirth);
        }
        catch(InvalidInputException exception){
            errors.append(exception.getMessage());
            errors.append("\n");
        }
        try{
            setGender(gender);
        }
        catch(InvalidInputException exception){
            errors.append(exception.getMessage());
            errors.append("\n");
        }
        setStatus(status);
        setPicture(picture);
        setBio(bio);
        setChangePasswordFlag(passwordFlag);
        try{
            setPhoneNumber(phoneNumber);
        }
        catch(InvalidInputException exception){
            errors.append(exception.getMessage());
            errors.append("\n");
        }
        String appendedErrors=errors.toString();
        if(!appendedErrors.equals("")) {
            throw new InvalidInputException(appendedErrors);
        }
        
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidInputException {
    	  if(name.length()>=4 && name.length()<=30){
            this.name = name;
        }
        else{
            throw new InvalidInputException("name must be between 4 and 30 characters");
        }
    }
    
    public String getPassword(){
        if(passwordFlag){
            return password;
        }
        else{
            throw new InvalidDTOException("you can not get the password from the server");
        }
    }

    public void setPassword(String password) throws InvalidInputException {
        if(password==null || password.equals("")){
            passwordFlag=false;
            return;
        }
        if(password.length()>=8 && password.length()<=30){
        	passwordFlag=true;
            this.password = password;
        }
        else{
            throw new InvalidInputException("password must be between 8 and 30 characters");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws InvalidInputException {
        if(email.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")){
            this.email = email;
        }
        else{
            throw new InvalidInputException("invalid email");
        }
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) throws InvalidInputException {
        if(dateOfBirth.compareTo(Date.valueOf("1980-1-1"))>=0 && dateOfBirth.compareTo(Date.valueOf("2013-12-31"))<=0){ 
        	this.dateOfBirth = dateOfBirth;
        }
        else{
            throw new InvalidInputException("Date must be between 1980 and 2013");
        }
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) throws InvalidInputException {
        if(gender=='M'||gender=='m'||gender=='F'||gender=='f'){
            this.gender = gender;
        }
        else{
            throw new InvalidInputException("gender must be M or F");
        }
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }   
    
    private void setPhoneNumber(String phoneNumber) throws InvalidInputException {
        if(phoneNumber.length()>=10 && phoneNumber.length()<=15){
            this.phoneNumber=phoneNumber;
        }
        else{
            throw new InvalidInputException("phone number must be between 10 and 15 characters");
        }
    }

	public boolean getChangePasswordFlag() {
		return changePasswordFlag;
	}

	private void setChangePasswordFlag(boolean changePasswordFlag) {
		this.changePasswordFlag = changePasswordFlag;
	}

}

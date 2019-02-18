package com.jets.database.dal.dto;

import com.jets.database.exception.InvalidDTOException;
import com.jets.database.exception.InvalidInputException;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
DTO for Group table
@author Mohamed Ali
*/
public class Group {
    /**
    when chatting
    */
    private int groupId;
    
    private String name;
    private String userPhoneNumber;
    private Set<String> friendPhoneNumbers=new TreeSet<>();
    private boolean groupSelectedFromDatabase;

    /**
    select from database
    */
    public Group(int groupId, String name, String userPhoneNumber) throws InvalidInputException {
        this.groupId = groupId;
        setName(name);
        this.userPhoneNumber=userPhoneNumber;
        groupSelectedFromDatabase=true;
    }
    
    /**
    add group to database
    */
    public Group(String name, String userPhoneNumber) throws InvalidInputException {
        setName(name);
        this.userPhoneNumber=userPhoneNumber;
        groupSelectedFromDatabase=false;
    }

    public int getGroupId() {
        if(groupSelectedFromDatabase){
            return groupId;
        }
        else{
            throw new InvalidDTOException("you must retrieve the group from the database to access the group id");
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
    
    public String getUserPhoneNumber() {
		return userPhoneNumber;
	}

	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}
    
    public void addFriend(String friendPhoneNumber){
    	friendPhoneNumbers.add(friendPhoneNumber);
    }
    
    public void removeFriend(String friendPhoneNumber){
    	friendPhoneNumbers.remove(friendPhoneNumber);
    }
    
    public void addFriends(List<String> friendPhoneNumbers){
    	this.friendPhoneNumbers.addAll(friendPhoneNumbers);
    }
    
    public void removeFriends(List<String> friendPhoneNumbers){
    	this.friendPhoneNumbers.removeAll(friendPhoneNumbers);
    }
    
    public Set<String> getFriends(){
        return friendPhoneNumbers;
    }
    
    
}

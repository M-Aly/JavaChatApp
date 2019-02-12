package com.jets.dal.dto;

import com.jets.exception.InvalidInputException;

/**
DTO for Group table
@author Mohamed Ali
*/
public class Group {
    private int groupId;
    private String name;

    public Group(int groupId, String name) throws InvalidInputException {
        this.groupId = groupId;
        setName(name);
    }

    public int getGroupId() {
        return groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidInputException {
        if(name.length()>8 && name.length()<30){
            this.name = name;
        }
        else{
            throw new InvalidInputException("name must be between 8 and 30 characters");
        }
    }
    
    
}

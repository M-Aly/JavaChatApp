package com.jets.database.dal.dto;

import com.jets.database.exception.InvalidDTOException;
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
    private Set<Friend> friends=new TreeSet<>();
    private boolean groupSelectedFromDatabase;

    /**
    select from database
    */
    public Group(int groupId, String name){
        this.groupId = groupId;
        setName(name);
        groupSelectedFromDatabase=true;
    }
    
    /**
    add group to database
    */
    public Group(String name){
        setName(name);
        groupSelectedFromDatabase=false;
    }

    public int getGroupId(){
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

    public void setName(String name) {
        if(name.length()>8 && name.length()<30){
            this.name = name;
        }
        else{
            throw new InvalidDTOException("name must be between 8 and 30 characters");
        }
    }
    
    public void addFriend(Friend friend){
        friends.add(friend);
    }
    
    public void removeFriend(Friend friend){
        friends.remove(friends);
    }
    
    public Set<Friend> getFriends(){
        return friends;
    }
    
    
}

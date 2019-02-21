package com.jets.database.dal.dto;

import com.jets.database.dal.dto.enums.Country;
import com.jets.database.dal.dto.enums.InvitationStatus;
import com.jets.database.dal.dto.enums.UserStatus;
import com.jets.database.exception.InvalidDTOException;

import java.sql.Date;

/**
DTO for Friend table
@author Mohamed Ali
*/
public class Friend {
    private InvitationStatus invitationStatus;
    private User friend;

    public Friend(User friend, InvitationStatus invitationStatus) {
        this.friend = friend;
        this.invitationStatus = invitationStatus;
        if(friend == null) {
        	throw new InvalidDTOException("friend can not be null");
        }
    }

    public InvitationStatus getInvitationStatus() {
        return invitationStatus;
    }

    public void setInvitationStatus(InvitationStatus invitationStatus) {
        this.invitationStatus = invitationStatus;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    public String getName() {
        return friend.getName();
    }

    public String getEmail() {
        return friend.getEmail();
    }

    public Country getCountry() {
        return friend.getCountry();
    }

    public Date getDateOfBirth() {
        return friend.getDateOfBirth();
    }

    public char getGender() {
        return friend.getGender();
    }

    public UserStatus getStatus() {
        return friend.getStatus();
    }

    public byte[] getPicture() {
        return friend.getPicture();
    }

    public String getBio() {
        return friend.getBio();
    }

    public String getPhoneNumber() {
        return friend.getPhoneNumber();
    }
    
    
    
    
}

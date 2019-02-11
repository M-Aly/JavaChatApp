package com.jets.dal.dto;

/**
@author M. ALI
*/
public class Friend {
    private int userPhoneNumber;
    private int friendPhoneNumber;
    private int invitationStatus;

    public Friend(int userPhoneNumber, int friendPhoneNumber, int invitationStatus) {
        this.userPhoneNumber = userPhoneNumber;
        this.friendPhoneNumber = friendPhoneNumber;
        this.invitationStatus = invitationStatus;
    }

    public int getInvitationStatus() {
        return invitationStatus;
    }

    public void setInvitationStatus(int invitationStatus) {
        this.invitationStatus = invitationStatus;
    }
    
    
}

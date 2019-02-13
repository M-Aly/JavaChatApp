package com.jets.dal.dto;

import com.jets.dal.dto.enums.InvitationStatus;

/**
DTO for Friend table
@author M. ALI
*/
public class Friend {
    private int userPhoneNumber;
    private int friendPhoneNumber;
    private InvitationStatus invitationStatus;
    private User friend;

    public Friend(User friend, int userPhoneNumber, int friendPhoneNumber, InvitationStatus invitationStatus) {
        this.userPhoneNumber = userPhoneNumber;
        this.friendPhoneNumber = friendPhoneNumber;
        this.invitationStatus = invitationStatus;
        this.invitationStatus = invitationStatus;
    }

    public InvitationStatus getInvitationStatus() {
        return invitationStatus;
    }

    public void setInvitationStatus(InvitationStatus invitationStatus) {
        this.invitationStatus = invitationStatus;
    }
    
    
}

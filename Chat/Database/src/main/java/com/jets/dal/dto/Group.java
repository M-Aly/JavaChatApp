package com.jets.dal.dto;

/**
@author Mohamed Ali
*/
public class Group {
    private int groupId;
    private String name;

    public Group(int groupId, String name) {
        this.groupId = groupId;
        this.name = name;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}

package com.jets.dal.dto;

import java.sql.Time;

/**
@author Mohamed Ali
*/
public class Message {
    private int id;
    private String text;
    private Time time;

    public Message(int id, String text, Time time) {
        this.id = id;
        this.text = text;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}

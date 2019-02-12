package com.jets.dal.dto;

import com.jets.exception.InvalidInputException;
import java.sql.Time;

/**
DTO for Message table
@author Mohamed Ali
*/
public class Message {
    private int id;
    private String text;
    private Time time;

    public Message(int id, String text, Time time) throws InvalidInputException {
        this.id = id;
        setText(text);
        setTime(time);
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) throws InvalidInputException {
        if(!text.equals("")){
            this.text = text;
        }
        else{
            throw new InvalidInputException("text can not be empty");
        }
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}

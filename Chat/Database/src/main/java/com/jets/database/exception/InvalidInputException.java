package com.jets.database.exception;

/**
thrown when validation of input is invalid
@author Mohamed Ali
*/
public class InvalidInputException extends Exception {
    public InvalidInputException(){
        super();
    }
    public InvalidInputException(String message){
        super(message);
    }
}

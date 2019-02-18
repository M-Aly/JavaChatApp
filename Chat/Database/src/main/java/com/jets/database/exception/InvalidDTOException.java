package com.jets.database.exception;

/**
 * when the data in the DTO is invalid to get
 * @author Mohamed Ali
 */
public class InvalidDTOException extends RuntimeException {
    public InvalidDTOException(){
        super();
    }
    public InvalidDTOException(String message){
        super(message);
    }
}

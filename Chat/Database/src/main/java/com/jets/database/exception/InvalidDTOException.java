package com.jets.database.exception;

/**
 *
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

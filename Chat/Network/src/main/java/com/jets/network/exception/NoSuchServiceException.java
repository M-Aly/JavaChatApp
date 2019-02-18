package com.jets.network.exception;

/**
 * the service does not exist
 * @author Mohamed Ali
 */
public class NoSuchServiceException extends RuntimeException {
	public NoSuchServiceException(){
        super();
    }
    public NoSuchServiceException(String message){
        super(message);
    }
}

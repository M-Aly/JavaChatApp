package com.jets.network.exception;

/**
 *
 * @author Mohamed Ali
 */
public class NoSuchUserException extends Exception {
    public NoSuchUserException(){
        super();
    }
    public NoSuchUserException(String message){
        super(message);
    }
}

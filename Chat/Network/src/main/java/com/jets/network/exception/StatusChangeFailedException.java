package com.jets.network.exception;

/**
 * status change failure
 * @author Mohamed Ali
 */
public class StatusChangeFailedException extends Exception {
    public StatusChangeFailedException(){
        super();
    }
    public StatusChangeFailedException(String message){
        super(message);
    }
}

package com.jets.network.exception;

/**
 * update user failure
 * @author Mohamed Ali
 */
public class UpdateUserFailedException extends Exception {
    public UpdateUserFailedException(){
        super();
    }
    public UpdateUserFailedException(String message){
        super(message);
    }
}

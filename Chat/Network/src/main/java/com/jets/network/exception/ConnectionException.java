package com.jets.network.exception;

/**
 * connection problem
 * @author Mohamed Ali
 */
public class ConnectionException extends Exception {
	public ConnectionException(){
        super();
    }
    public ConnectionException(String message){
        super(message);
    }
}

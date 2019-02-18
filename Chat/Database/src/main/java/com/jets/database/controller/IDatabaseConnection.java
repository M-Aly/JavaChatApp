package com.jets.database.controller;

import java.sql.Connection;

/**
 *
 * @author Zainab
 * @author Mohamed Ali
 */
public interface IDatabaseConnection {
    /**
    get a connection from the database
    */
    public Connection getConnection();
    
    /**
    set automatic commit
    */
    public void setAutoCommit(boolean autoCommit);
}

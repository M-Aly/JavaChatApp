package com.jets.database.controller;

import java.sql.Connection;

/**
 *
 * @author Zainab
 */
public interface IDatabaseConnection {
    /**
    get a connection from the database
    */
    public Connection getConnection();
}

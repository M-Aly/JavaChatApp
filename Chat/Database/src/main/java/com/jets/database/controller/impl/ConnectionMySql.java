package com.jets.database.controller.impl;

import com.jets.database.controller.IDatabaseConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mohamed Ali
 */
public class ConnectionMySql implements IDatabaseConnection {
    private static ConnectionMySql databaseConnection;
    private Connection connection;
    private static final String MYSQL_URL="jdbc:mysql://localhost:3333/chatdatabase";
    //private static final String DATABASE_NAME="chatdatabase";
    private static final String USER_NAME="root";
    private static final String PASSWORD="mysql";
    
    private ConnectionMySql(){
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            connection=DriverManager.getConnection(MYSQL_URL, USER_NAME, PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public synchronized static ConnectionMySql getInstance(){
        if(databaseConnection==null)
            databaseConnection=new ConnectionMySql();
        return databaseConnection;
    }
    
    @Override
    public Connection getConnection(){
        return connection;
    }
}

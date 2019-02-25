package com.jets.database.controller.impl;

import com.jets.database.controller.IDatabaseConnection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * get a connection to mysql database
 * @author Mohamed Ali
 */
public class ConnectionMySql implements IDatabaseConnection {
    private static ConnectionMySql databaseConnection;
    private Connection connection;
    private static final String URL="MYSQL_URL";
    private static final String DATABASE="DATABASE_NAME";
    private static final String USER="USER_NAME";
    private static final String PASSWORD="PASSWORD";
    private static final String FILE="/mysql.properties";
    
    private ConnectionMySql(){
    	try {
    		Properties properties=new Properties();
        	properties.load(new FileReader(FILE));
        	String mysqlUrl=properties.getProperty(URL)+properties.getProperty(DATABASE);
        	String user=properties.getProperty(USER);
        	String password=properties.getProperty(PASSWORD);
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            connection=DriverManager.getConnection(mysqlUrl,user,password);
        }
    	catch (IOException ex) {
    		ex.printStackTrace();
    	}
    	catch (SQLException ex) {
    		ex.printStackTrace();
    	}
    }
    
    public synchronized static ConnectionMySql getInstance(){
        if(databaseConnection==null)
            databaseConnection=new ConnectionMySql();
        return databaseConnection;
    }
    
    @Override
    public Connection getConnection(){
    	setAutoCommit(true);
        return connection;
    }

	@Override
	public void setAutoCommit(boolean autoCommit) {
		try {
			connection.setAutoCommit(autoCommit);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
    
}

package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	/*
	 * A factory is an object that builds other objects.
	 * Normally based on a configuration we've provided.
	 */
	
	//private static reference to ourself - one and only copy
	//this is the singleton pattern
	private static ConnectionFactory cf = new ConnectionFactory();
	
	private Connection[] all_connections;
	
	private ConnectionFactory(int num_of_connections) {
		super();
		
		String url = System.getenv("DB_URL");
		String username = System.getenv("DB_USERNAME");
		String password = System.getenv("DB_PASSWORD");
		
		try {
			all_connections = new Connection[num_of_connections];
			for(int i=0;i<num_of_connections;i++) {
				Connection conn = DriverManager.getConnection(url,username,password);
				all_connections[i] = conn;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private ConnectionFactory() {
		this(1);
	}
	
	public static ConnectionFactory getConnectionFactory() {
		return cf;
	}
	
	//If we were going to implement this for real, we would synchronize for multiple threads and lock for safety
	//We're not going to do that now
	//Only one thread exists, and we will only ever need a single connection
	
	public Connection getConnection() {
		return this.all_connections[0];
	}
	
	public void releaseConnection() {
		//do nothing
	}

}

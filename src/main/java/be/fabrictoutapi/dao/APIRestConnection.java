package be.fabrictoutapi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class APIRestConnection {

	//Attributes/Variables
	private static Connection conn = null;
	private static String connectionChain = null;
	private static String userNameDB = null;
	private static String passwordDB = null;

	//Methode
	public static void setConnection(Connection conn) {
		APIRestConnection.conn = conn;
	}

	//Constructor
	private APIRestConnection() {
	        try {
	        	Context context = (Context) new InitialContext().lookup("java:comp/env");
				connectionChain = (String) context.lookup("connectionChain");
				userNameDB = (String) context.lookup("userNameDB");
				passwordDB = (String) context.lookup("passwordDB");
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            conn = DriverManager.getConnection(connectionChain, userNameDB, passwordDB);
	        } 
	        catch (SQLException e) {
	        	e.printStackTrace();
	        } 
	        catch (ClassNotFoundException e) {
	        	e.printStackTrace();
	        } 
	        catch (NamingException e) {
				e.printStackTrace();
			}
	}

	//Methode
	public static Connection getInstance() {
		if (conn == null)
			new APIRestConnection();
		return conn;
	}
}
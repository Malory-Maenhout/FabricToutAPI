package be.fabrictoutapi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class APIRestConnection {

	private static Connection conn = null;

	public static void setConnection(Connection conn) {
		APIRestConnection.conn = conn;
	}

	private String userName = "STUDENT03_21";
	private String password = "condorcet2021";
	private String ip = "193.190.64.10";
	private String port = "1522";
	private String serviceName = "XEPDB1";
	private String chaineConnexion = "jdbc:oracle:thin:@//" + ip + ":" + port + "/" + serviceName;

	private APIRestConnection() {
	        try {
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            conn = DriverManager.getConnection(chaineConnexion, userName, password);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	    }

	public static Connection getInstance() {
		if (conn == null)
			new APIRestConnection();
		return conn;
	}
}
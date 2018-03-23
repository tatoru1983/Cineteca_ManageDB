package utility;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtility {
	
	private static String PUBLIC_DNS = "";
	private static String PORT = "";
	private static String DATABASE = "";
	private static String REMOTE_DATABASE_USERNAME = "";
	private static String DATABASE_USER_PASSWORD = "";
	private static Properties props;
	
	static {
		try{
			props = PropertiesUtility.getPropValues();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		PUBLIC_DNS = props.getProperty("PUBLIC_DNS");
		PORT = props.getProperty("PORT");
		DATABASE = props.getProperty("DATABASE");
		REMOTE_DATABASE_USERNAME = props.getProperty("REMOTE_DATABASE_USERNAME");
		DATABASE_USER_PASSWORD = props.getProperty("DATABASE_USER_PASSWORD");
	}
	
	public static void connectJDBCToAWSEC2() {
		System.out.println("----MySQL JDBC Connection Testing -------");
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        System.out.println("Where is your MySQL JDBC Driver?");
	        e.printStackTrace();
	        return;
	    }

	    System.out.println("MySQL JDBC Driver Registered!");
	    Connection connection = null;
	    try {
	        connection = DriverManager.
	                getConnection("jdbc:mysql://" + PUBLIC_DNS + ":" + PORT + "/" + DATABASE, REMOTE_DATABASE_USERNAME, DATABASE_USER_PASSWORD);
	    } catch (SQLException e) {
	        System.out.println("Connection Failed!:\n" + e.getMessage());
	    }

	    if (connection != null) {
	        System.out.println("SUCCESS!!!! You made it, take control     your database now!");
	    } else {
	        System.out.println("FAILURE! Failed to make connection!");
	    }
	}

}

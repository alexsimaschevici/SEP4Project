package dbadaptor;


/*   Main class. Handles creation and disposal of a connection to Oracle */
/*   The individual case to be run is instantiated and passed the        */
/*   connection                                                          */

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.GregorianCalendar;

import config.DatabaseConnectionConfig;
import oracle.jdbc.driver.OracleDriver;


/**
 * Database connection logic
 * @author Alexandru
 *
 */
public class DatabaseConnection implements DatabaseConnectionConfig {

	private static Connection conn;


	/*
	 * BUILDS A DATABASE CONNECTION TO AN ORACLE DATABASE
	 */
	
	public static Connection requestConnection(){

		try {
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

			conn = DriverManager.getConnection(connectString, userName, password);
			conn.setAutoCommit(false);
			System.out.println("connection established, autocommit off");


		} catch (SQLException e) {
			System.out.println("error establishing connection");
			System.out.println("Connection string in use: "+connectString + "(user/pwd " + userName + "/" + password + ")" );
			System.out.println(e.getMessage());
			e.printStackTrace();

		}

		return conn;
		
	}
	
	
	public static void closeConnection(){
		try {
			conn.close();
			System.out.println("connection closed");
		

		} catch (SQLException e) {
			System.out.println("error closing connection");
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.exit(0);

		}
	}
	
	
}

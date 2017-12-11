package config;

public interface DatabaseConnectionConfig {
	
	/* connection details */
	/* connects via TNS */
	final static String connectString = "jdbc:oracle:thin:@localhost:1521:assignment";
	final static String userName = "sdj3";
	final static String password = "sdj3";
}

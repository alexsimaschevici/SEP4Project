package config;

/**
 * Database connection details
 * @author Alexandru
 *
 */
public interface DatabaseConnectionConfig {
	
	/* connection details */
	/* connects via TNS */
	final static String connectString = "jdbc:oracle:thin:@localhost:1521:assignment";
	final static String userName = "sep4de";
	final static String password = "sep4de";
}

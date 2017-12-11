import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseConnection {
	
	private String DBURL;
	private String user;
	private String password;
	private Connection conn;
	private static SQLTranslator translator;
	private static DatabaseConnection instance;
	
	private DatabaseConnection() throws ClassNotFoundException
	{
		// JDBC driver name and database URL
			DBURL = "jdbc:postgresql://localhost:5432/postgres";

			//  Database credentials
			user = "postgres";
			password = "pass";
			
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");	
			      
			conn = null;
			try{
				//STEP 3: Open a connection
				conn = DriverManager.getConnection(DBURL, user, password);
			}catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public static DatabaseConnection getInstance() throws ClassNotFoundException 
	{
		if(instance == null)
		{
			instance = new DatabaseConnection();
		}
		return instance;
	}
	
	public void performStatement(String statement)
	{
		 Statement stmt = null;
		   try{
		      stmt = conn.createStatement();
		      stmt.executeUpdate(statement);
		      } catch (SQLException e) {
				e.printStackTrace();
			}
		   finally{
			     //finally block used to close resources
			     try{
			         	if(stmt!=null)
			            stmt.close();
			        }catch(SQLException se2){}// nothing we can do
		   } //end finally 	
	}
	
	public String findID(String statement, String column_desired)
	{
		 Statement stmt = null;
		   try{
		      stmt = conn.createStatement();
		      ResultSet rs = stmt.executeQuery(statement);
		      //STEP 5: Extract data from result set
		      String output = "";

		      while(rs.next()){
		         //Retrieve by column name
							output += rs.getString(column_desired);
		     }	
		   		System.out.println("Resultant ID found: " + output);
		   		return output;
		      }
		      catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }
		finally{
			      //finally block used to close resources
			      try{
			    	  	if(stmt!=null)
			            stmt.close();
			      }catch(SQLException se2){}// nothing we can do
			   } //end finally 
			return "Error: ID Not Found";   	
	}
	
	public String[] getColumnList(String table)
	{
		ArrayList<String> columns = new ArrayList<String>();
		
		String sql = "SELECT column_name "
				+ "FROM user_tab_cols "
				+ "WHERE table_name = '" + table + "';";
		
		 Statement stmt = null;
		   try{
		      stmt = conn.createStatement();
		      ResultSet rs = stmt.executeQuery(sql);

		      		while(rs.next())
		      		{
		    	  		columns.add(rs.getString("column_name"));
		      		}	
		      }
		      catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }
		finally{
			      //finally block used to close resources
			      try{
			    	  	if(stmt!=null)
			            stmt.close();
			      }catch(SQLException se2){}// nothing we can do
			   } //end finally 
			 
		String[] col_arr = new String[columns.size()];
		
		for(int i=0;i<col_arr.length;i++)
		{
			col_arr[i] = columns.get(i);
		}
		
		return col_arr;
	}
}

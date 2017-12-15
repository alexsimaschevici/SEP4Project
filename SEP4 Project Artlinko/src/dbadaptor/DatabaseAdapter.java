package dbadaptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import services.IDGen;
import model.*;

/**
 * This is the adapter for storing persistent data
 * 
 * @author
 * 
 */

public class DatabaseAdapter implements IDatabaseAdapter {

	
	Connection conn;
	
	
	public DatabaseAdapter(Connection conn){
		this.conn = conn;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see dbadaptor.IDatabaseAdapter#newGeneralSurveyColumn(java.lang.String)
	 */
	public void newGeneralSurveyColumn(String columnname) throws SQLException {
		if (columnname != "SURVEYID") {
			// Check if the column exists
			String[] columns = getColumnList("SURVEY");
			boolean column_exists = false;
			for (String el : columns) {
				if (columnname.equals(el)) {
					column_exists = true;
				}
			}
			if (!column_exists) {
				columnname= clean(columnname);
				String prepStatement1 = "ALTER TABLE SURVEY ADD "+columnname+" VARCHAR2(200)";				
				
				PreparedStatement st = conn.prepareStatement(prepStatement1.toUpperCase());
				try {
					st.execute(prepStatement1);
				} catch (Exception e) {
					//System.out.println("Skipped: "+columnname +" error:"+ e.getMessage());
				}
				st.close();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbadaptor.IDatabaseAdapter#newSurveyInstanceColumn(java.lang.String)
	 */
	public void newSurveyInstanceColumn(String column_name) throws SQLException {
		if (column_name != "SurveyID" && column_name != "PersonID"
				&& column_name != "SurveyInstanceID") {
			// Check if the column exists
			String[] columns = getColumnList("SurveyInstance");
			boolean column_exists = false;
			for (int i = 0; i < columns.length; i++) {
				if (column_name == columns[i]) {
					column_exists = true;
				}
			}
			if (!column_exists) {
				column_name= clean(column_name);
				String prepStatement1 = "ALTER TABLE FACT_SurveyInstance ADD "+column_name+" VARCHAR2(200)";				
				
				PreparedStatement st = conn.prepareStatement(prepStatement1.toUpperCase());
				try {
					st.execute(prepStatement1);
				} catch (Exception e) {
					//System.out.println("Skipped: "+columnname +" error:"+ e.getMessage());
				}
				st.close();

			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbadaptor.IDatabaseAdapter#newPerson(java.lang.String)
	 */
	public void newPerson(String nextPersonID) throws SQLException {
		String prepStatement2 = "INSERT INTO Person (PersonID) VALUES(?)";

		PreparedStatement st = conn.prepareStatement(prepStatement2);
		st.setString(1, nextPersonID);

		// execute the preparedstatement
		try {
			st.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		st.close();


		// sql.performStatement("INSERT INTO Person (PersonID) VALUES ('" +
		// nextPersonID + "');");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbadaptor.IDatabaseAdapter#newGeneralSurvey(java.lang.String,
	 * java.lang.String[], java.lang.String[])
	 */
	public void newGeneralSurvey(String nextSurveyID, List<String> columns,
			List<ResponseQA> values) throws SQLException {
///* del */      System.out.println(columns.size()+" "+values.size());

		if (columns.size() == values.size()) {
			String column_names = "";
			String column_value = "";
			for (int i = 0; i < values.size(); i++) {
				String temp= clean(columns.get(i));
				column_names+= " "+temp+",";
				column_value+= " '"+values.get(i).getAnswers().get(0)+"',";
			}
			
			
			column_names= column_names.substring(0, column_names.length()-1);
			column_value= column_value.substring(0, column_value.length()-1);

			String qu= "INSERT INTO Survey (SurveyID, "+column_names+") VALUES('"+nextSurveyID+"', "+column_value+")";
			
			
//  		String prepStatement3 = "INSERT INTO Survey (SurveyID, "+column_names+") VALUES(?, ?)";

			PreparedStatement st = conn.prepareStatement(qu);
//			st.setString(1, "'"+nextSurveyID+"'");
//			st.setString(2, column_value);

			// execute the preparedstatement
			st.executeQuery();
			st.close();



		}
		else throw new SQLException("Size not matching");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbadaptor.IDatabaseAdapter#newSurveyInstance(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String[],
	 * java.lang.String[])
	 */
	public void newSurveyInstance(String nextSurveyID, String nextPersonID,
			String nextSurveyInstanceID, List<String> columns, List<ResponseQA> values)
			throws SQLException {

		if (columns.size() == values.size()) {
			String column_names = "";
			String column_value = "";
			for (int i = 0; i < values.size(); i++) {
				String temp= clean(columns.get(i));
				column_names+= " "+temp+",";
				column_value+= " '"+values.get(i).getAnswers().get(0)+"',";
			}		
			
			column_names= column_names.substring(0, column_names.length()-1);
			column_value= column_value.substring(0, column_value.length()-1);
			
			String prepStatement4 = "INSERT INTO Fact_SurveyInstance (SurveyID, PersonID, SurveyInstanceID, "+column_names+") "
					+ "VALUES ('"+nextSurveyID+"','"+nextPersonID+"','"+nextSurveyInstanceID+"',"+column_value+")";

			PreparedStatement st = conn.prepareStatement(prepStatement4);
			
			// execute the preparedstatement
			st.executeUpdate(prepStatement4);
			st.close();

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbadaptor.IDatabaseAdapter#newSGQ(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public void newSGQ(String dimension_name, String question_text,
			String surveyID) throws SQLException {

		
		/*String targetSGD_ID = sql.findID(""
				+ "SELECT SGD_ID FROM StandardGraphDimension"
				+ "WHERE SGDimension_Name = '" + dimension_name + "';", "SGD_ID");
		//If it doesn't exist, create a record for it.
		if(targetSGD_ID == null)
		{
			targetSGD_ID = "ALEX's Magic NEW ID GENERATOR gives us a new ID for SGD_ID here";
			sql.performStatement(""
					+ "INSERT INTO StandardGraphDimension"
					+ "(SGD_ID, DimensionName) VALUES ('" + 
					targetSGD_ID + "', '" + 
					dimension_name + "');");
		}
		
		//Check if the question already exists
		String targetSGQ_ID = sql.findID(""
				+ "SELECT SGQ_ID FROM StandardGraphQuestion"
				+ "WHERE QuestionText = '" + question_text + "';", "SGQ_ID");
		//If it doesn't exist, create a record for it.
				if(targetSGQ_ID == null)
				{
					targetSGQ_ID = "ALEX's Magic NEW ID GENERATOR gives us a new ID for SGQ_ID here";
					//SAVE THIS ID SOMEWHERE. You'll need it when adding an answer. 
					sql.performStatement(""
							+ "INSERT INTO StandardGraphQuestion"
							+ "(SGQ_ID, SGD_ID, QuestionText) VALUES ('" + 
							targetSGQ_ID + "', '" + 
							targetSGD_ID + "', '" + 
							question_text + "');");
				}
		
		//Check if the question already was connected to this survey
		String targetSGQ_ID_join = sql.findID(""
						+ "SELECT SGQ_ID FROM StandardGraphQuestionsInSurvey"
						+ "WHERE SGQ_ID = '" + targetSGQ_ID + "' "
								+ "AND SurveyID = '" + surveyID + "';", "SGQ_ID");
				//If it doesn't exist, create a record for it.
						if(targetSGQ_ID_join == null)
						{
							sql.performStatement(""
									+ "INSERT INTO StandardGraphQuestionsInSurvey"
									+ "(SurveyID, SGQ_ID) VALUES ('" + 
									surveyID + "', '" + 
									targetSGQ_ID + "');");
						}
						
		return targetSGQ_ID;
		 * */
		

		// To create a new SG question, first find out if the dimension it
		// references exists.
		String statement1 = "SELECT SGD_ID FROM SGDIMENSION "
				+ "WHERE DimensionName = ?";

		PreparedStatement st1 = conn.prepareStatement(statement1);
		st1.setString(1, dimension_name);
		String targetSGD_ID = findID(st1, "SGD_ID");

		st1.executeUpdate();
		st1.close();

		// If it doesn't exist, create a record for it.
		if (targetSGD_ID == null) {
			targetSGD_ID = IDGen.generateId();

			String prepStatement5 = "INSERT INTO SGDIMENSION (SGD_ID, DimensionName) VALUES (?, ?)";

			PreparedStatement st = conn.prepareStatement(prepStatement5);
			st.setString(1, targetSGD_ID);
			st.setString(2, dimension_name);

			// execute the preparedstatement
			st.executeUpdate();
			st.close();
		}

		// Check if the question already exists

		String statement2 = "SELECT SGQ_ID FROM SGQUESTION "
				+ "WHERE QuestionText = ?";
		PreparedStatement st2 = conn.prepareStatement(statement2);
		st2.setString(1, dimension_name);
		String targetSGQ_ID = findID(st2, "SGQ_ID");

		st2.executeUpdate();
		st2.close();

		// If it doesn't exist, create a record for it.
		if (targetSGQ_ID == null) {
			targetSGQ_ID = IDGen.generateId();

			String prepStatement6 = "INSERT INTO SGQUESTION (SGQ_ID, SGD_ID, QuestionText) VALUES(?, ?, ?)";

			PreparedStatement st11 = conn.prepareStatement(prepStatement6);
			st11.setString(1, targetSGQ_ID);
			st11.setString(2, targetSGD_ID);
			st11.setString(3, question_text);

			// execute the preparedstatement
			st11.executeUpdate();
			st11.close();
		}

		// Check if the question already was connected to this survey
		
		String statement3 = "SELECT SGQ_ID FROM SGQInSurvey"
				+ " WHERE SGQ_ID = ?"
				+ " AND SurveyID = ?";
		PreparedStatement st3 = conn.prepareStatement(statement3);
		st3.setString(1, targetSGQ_ID);
		st3.setString(2, surveyID);
		String targetSGQ_ID_join = findID(st3, "SGQ_ID");
		
		st3.executeUpdate();
		st3.close();

		
		// If it doesn't exist, create a record for it.
		if (targetSGQ_ID_join == null) {
			String prepStatement7 = "INSERT INTO SGQInSurvey (SurveyID, SGQ_ID) VALUES(?, ?)";

			PreparedStatement st31 = conn.prepareStatement(prepStatement7);
			st31.setString(1, surveyID);
			st31.setString(2, targetSGQ_ID);

			// execute the preparedstatement
			st31.executeUpdate();
			st31.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbadaptor.IDatabaseAdapter#newLQ(java.lang.String[],
	 * java.lang.String, java.lang.String)
	 */
	public void newLQ(String[] categories, String question_text, String surveyID, String qId)
			throws SQLException {
		// Check if the question already exists
		
//		String statement3 = "SELECT LQ_ID FROM LQUESTION"
//				+ " WHERE QuestionText = ?";
//		PreparedStatement st3 = conn.prepareStatement(statement3);
//		st3.setString(1, question_text);
//		
//		String targetLQ_ID = findID(st3, "LQ_ID");
//		st3.executeUpdate();
//		st3.close();
		
		// If it doesn't exist, create a record for it.
//		if (targetLQ_ID == null) {
//			targetLQ_ID = IDGen.generateId();

			// SAVE THIS ID SOMEWHERE. You'll need it when adding an answer.

			String prepStatement8 = "INSERT INTO LQUESTION (LQ_ID, QuestionText) VALUES(?, ?)";

			PreparedStatement st = conn.prepareStatement(prepStatement8);
			st.setString(1, qId);
			st.setString(2, question_text);

			// execute the preparedstatement
			st.executeUpdate();
			st.close();

//		}

		// Next, find out if each of the categories it references exists.
		for (int i = 0; i < categories.length; i++) {
			String statement4 = "SELECT CategoryID FROM LayerCategory"
					+ " WHERE CategoryName = ?";
			PreparedStatement st4 = conn.prepareStatement(statement4);
			st4.setString(1, categories[i]);
			
			String targetCategoryID = findID(st4, "CategoryID");
			
			st4.executeUpdate();
			st4.close();

			
			// If it doesn't exist, create a record for it.
			if (targetCategoryID == null) {
				targetCategoryID = IDGen.generateId();

				String prepStatement9 = "INSERT INTO LayerCategory (CategoryID, CategoryName) VALUES(?, ?)";

				PreparedStatement st12 = conn.prepareStatement(prepStatement9);
				st12.setString(1, targetCategoryID);
				st12.setString(2, categories[i]);

				// execute the preparedstatement
				st12.executeUpdate();
				st12.close();

			}
			// Create join record.
			String statement5 = "SELECT CategoryID FROM FACT_LQUESTIONINCAT"
					+ " WHERE CategoryID = ?"
					+ " AND LQ_ID= ?";
			PreparedStatement st5 = conn.prepareStatement(statement5);
			st5.setString(1, categories[i]);
			st5.setString(2, qId);
			
			String targetCategoryID_join = findID(st5, "CategoryID");
			st5.executeUpdate();
			st5.close();

			
			// If it doesn't exist, create a record for it.
			if (targetCategoryID_join == null) {
				String prepStatement10 = "INSERT INTO FACT_LQUESTIONINCAT (CategoryID, LQ_ID) VALUES(?, ?)";

				PreparedStatement st123 = conn.prepareStatement(prepStatement10);
				st123.setString(1, targetCategoryID);
				st123.setString(2, qId);

				// execute the preparedstatement
				st123.executeUpdate();
				st123.close();

			}
		}

		// Check if the question already was connected to this survey
		String statement6 = "SELECT LQ_ID FROM LQINSURVEY"
				+ " WHERE LQ_ID = ?"
				+ " AND SurveyID = ?";
		PreparedStatement st6 = conn.prepareStatement(statement6);
		st6.setString(1, qId);
		st6.setString(2, surveyID);
		
		String LQisInSurvey = findID(st6, "LQ_ID");
		st6.executeUpdate();
		st6.close();

		
		// If it doesn't exist, create a record for it.
		if (LQisInSurvey == null) {
			String prepStatement11 = "INSERT INTO LQINSURVEY (SurveyID, LQ_ID) VALUES(?, ?)";

			PreparedStatement st13 = conn.prepareStatement(prepStatement11);
			st13.setString(1, surveyID);
			st13.setString(2, qId);

			// execute the preparedstatement
			st13.executeUpdate();
			st13.close();

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbadaptor.IDatabaseAdapter#newLayerResponse(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	public void newLayerResponse(String LQ_ID,String answer,String surveyID,String surveyInstanceID,String personID,
			String newLR_ID, String newLA_ID)
			throws SQLException {

		

		// Check if the answer already exists
		
//		String statement6 = "SELECT LA_ID FROM LayerAnswer"
//				+ " WHERE Answer = ?";
//		PreparedStatement st6 = conn.prepareStatement(statement6);
//		st6.setString(1, answer);		
//		
//		String targetLA_ID = findID(st6, "LA_ID");
//		st6.executeUpdate();
//		st6.close();

		
		
//			targetLA_ID = newLA_ID;

			String prepStatement112 = "INSERT INTO LayerAnswer (LA_ID, Answer) VALUES(?, ?)";

			PreparedStatement st21 = conn.prepareStatement(prepStatement112);
			st21.setString(1, newLA_ID);
			st21.setString(2, answer);

			// execute the preparedstatement
			st21.executeUpdate();
			st21.close();

		
		// Add Answer to Question in General
		
//		String statement7 = "SELECT LQ_ID FROM FACT_L_ATOQ"
//				+ " WHERE LQ_ID = ?"
//				+ " AND LA_ID = ?";
//		PreparedStatement st7 = conn.prepareStatement(statement7);
//		st7.setString(1, answer);
//		st7.setString(2, targetLA_ID);		
//		
//		String a2q = findID(st7, "LQ_ID");
//		st7.executeUpdate();
//		st7.close();

		// If it doesn't exist, create a record for it.
//		if (a2q == null) {
			String prepStatement11 = "INSERT INTO FACT_L_ATOQ (LA_ID, LQ_ID) VALUES(?, ?)";

			PreparedStatement st = conn.prepareStatement(prepStatement11);
			st.setString(1, newLA_ID);
			st.setString(2, LQ_ID);

			// execute the preparedstatement
			st.executeUpdate();
			st.close();

//		}

		// CREATE RESPONSE

		String prepStatement111 = "INSERT INTO FACT_LRESPONSE (LR_ID, PersonID, SurveyID, SurveyInstanceID, LQ_ID, LA_ID) VALUES( ? , ? , ? , ? , ? , ? )";

		PreparedStatement st14 = conn.prepareStatement(prepStatement111);
		st14.setString(1, newLA_ID);
		st14.setString(2, personID);
		st14.setString(3, surveyID);
		st14.setString(4, surveyInstanceID);
		st14.setString(5, LQ_ID);
		st14.setString(6, newLA_ID);

		// execute the preparedstatement
		st14.executeUpdate();
		st14.close();



	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbadaptor.IDatabaseAdapter#newSGResponse(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	public void newSGResponse(String SGQ_ID, String answer, String surveyID,
			String surveyInstanceID, String personID, String newSGR_ID)
			throws SQLException {


		// Check if the answer already exists
		String statement7 = "SELECT SGA_ID FROM StandardGraphAnswer"
				+ "WHERE Answer = ? ";
		PreparedStatement st7 = conn.prepareStatement(statement7);
		st7.setString(1, answer);		
		String targetSGA_ID = findID(st7, "SGA_ID");
		
		st7.executeUpdate();
		st7.close();

		
		// If it doesn't exist, create a record for it.
		if (targetSGA_ID == null) {
			targetSGA_ID = IDGen.generateId();

			String prepStatement11 = "INSERT INTO StandardGraphAnswer (SGA_ID, Answer) VALUES( ? , ? )";

			PreparedStatement st = conn.prepareStatement(prepStatement11);
			st.setString(1, targetSGA_ID);
			st.setString(2, answer);

			// execute the preparedstatement
			st.executeUpdate();
			st.close();

		}
		// Add Answer to Question in General
		String statement8 = "SELECT SGQ_ID FROM fact_StandardGraphAnswerToQuestion"
				+ " WHERE SGQ_ID = ?"
				+ " AND SGA_ID = ?";
		PreparedStatement st8 = conn.prepareStatement(statement8);
		st8.setString(1, SGQ_ID);
		st8.setString(2, targetSGA_ID);
		
		String a2q = findID(st8, "SGQ_ID");
		st8.executeUpdate();
		st8.close();
		
		// If it doesn't exist, create a record for it.
		if (a2q == null) {
			String prepStatement11 = "INSERT INTO fact_StandardGraphAnswerToQuestion (SGA_ID, SGQ_ID) VALUES( ? , ? )";

			PreparedStatement st = conn.prepareStatement(prepStatement11);
			st.setString(1, targetSGA_ID);
			st.setString(2, SGQ_ID);

			// execute the preparedstatement
			st.executeUpdate();
			st.close();

		}

		// CREATE RESPONSE

		String prepStatement11 = "INSERT INTO fact_StandardGraphResponse (SGR_ID, PersonID, SurveyID, SurveyInstanceID, SGQ_ID, SGA_ID) VALUES( ? , ? , ? , ? , ? , ? )";

		PreparedStatement st = conn.prepareStatement(prepStatement11);
		st.setString(1, newSGR_ID);
		st.setString(2, personID);
		st.setString(3, surveyID);
		st.setString(4, surveyInstanceID);
		st.setString(5, SGQ_ID);
		st.setString(6, targetSGA_ID);

		// execute the preparedstatement
		st.executeUpdate();
		st.close();


	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbadaptor.IDatabaseAdapter#findID(java.lang.String,
	 * java.lang.String)
	 */
	public String findID(PreparedStatement st, String column_desired)
			throws SQLException {

		ResultSet rs = st.executeQuery();
		
		// STEP 5: Extract data from result set
		String output = "";
		while (rs.next()) {
			// Retrieve by column name
			output += rs.getString(column_desired);
		}

		if (output.equals(""))
			return "Error: ID Not Found";
		else
			return output;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbadaptor.IDatabaseAdapter#getColumnList(java.lang.String)
	 */
	public String[] getColumnList(String table) throws SQLException{
	//	System.out.println("getColumnList start");
		ArrayList<String> columns = new ArrayList<String>();

		String statement8 = "SELECT column_name FROM user_tab_cols WHERE table_name = ?";
		
		PreparedStatement st8 = conn.prepareStatement(statement8);
		st8.setString(1, "'"+table.toUpperCase()+"'");
		ResultSet rs= st8.executeQuery();
		//System.out.println("Checkpoint");
		while (rs.next()) {
			columns.add(rs.getString("column_name"));
		}
		st8.close();
		String[] col_arr = new String[columns.size()];
		
			for (int i = 0; i < col_arr.length; i++) {
				col_arr[i] = columns.get(i);
			}
			return col_arr;

	}

	
	public String clean(String str){
		str=str.replaceAll("[^a-zA-Z0-9\\s+]", "_");
		str=str.replaceAll(" ", "_");
		str=str.replaceAll("___", "_");
		str=str.replaceAll("__", "_");
		str=str.toUpperCase();
		if(str.length()>30){
		str= str.substring(0, 29);
		}
		return str;
	}
}

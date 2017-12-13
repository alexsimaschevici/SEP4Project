package dbadaptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * This is the adapter for storing persistent data
 * 
 * @author
 * 
 */

public class DatabaseAdapter implements IDatabaseAdapter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbadaptor.IDatabaseAdapter#newGeneralSurveyColumn(java.lang.String)
	 */
	public void newGeneralSurveyColumn(String column_name) throws SQLException {
		if (column_name != "SurveyID") {
			// Check if the column exists
			String[] columns = getColumnList("Survey");
			boolean column_exists = false;
			for (String el : columns) {
				if (column_name.equals(el)) {
					column_exists = true;
				}
			}
			if (!column_exists) {
				String prepStatement1 = "ALTER TABLE Survey ADD '?' VARCHAR2(200)";

				Connection conn = DatabaseConnection.requestConnection();
				PreparedStatement st = conn.prepareStatement(prepStatement1);
				st.setString(1, column_name);

				// execute the preparedstatement
				st.executeUpdate();
				st.close();

				DatabaseConnection.closeConnection();

				// sql.performStatement(
				// "ALTER TABLE Survey "
				// + "ADD " + column_name + " VARCHAR2(200);"
				// );
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
				String prepStatement2 = "ALTER TABLE SurveyInstance ADD '?' VARCHAR2(200)";

				Connection conn = DatabaseConnection.requestConnection();
				PreparedStatement st = conn.prepareStatement(prepStatement2);
				st.setString(1, column_name);

				// execute the preparedstatement
				st.executeUpdate();
				st.close();

				DatabaseConnection.closeConnection();

				// sql.performStatement(
				// "ALTER TABLE SurveyInstance "
				// + "ADD " + column_name + " VARCHAR2(200);"
				// );

			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbadaptor.IDatabaseAdapter#newPerson(java.lang.String)
	 */
	public void newPerson(String nextPersonID) throws SQLException {
		String prepStatement2 = "INSERT INTO Person (PersonID) VALUES('?')";

		Connection conn = DatabaseConnection.requestConnection();
		PreparedStatement st = conn.prepareStatement(prepStatement2);
		st.setString(1, nextPersonID);

		// execute the preparedstatement
		st.executeUpdate();
		st.close();

		DatabaseConnection.closeConnection();

		// sql.performStatement("INSERT INTO Person (PersonID) VALUES ('" +
		// nextPersonID + "');");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbadaptor.IDatabaseAdapter#newGeneralSurvey(java.lang.String,
	 * java.lang.String[], java.lang.String[])
	 */
	public void newGeneralSurvey(String nextSurveyID, String[] column_names,
			String[] values) throws SQLException {

		if (column_names.length == values.length) {
			String single_column_name = "";
			String single_value = "";
			for (int i = 0; i < values.length; i++) {
				column_names[i] = ", '" + column_names[i] + "'";
				values[i] = ", '" + values[i] + "'";

				single_column_name = single_column_name + column_names[i];
				single_value = single_value + values[i];
			}
			String prepStatement3 = "INSERT INTO Survey (SurveyID, '?') VALUES('?','?')";

			Connection conn = DatabaseConnection.requestConnection();
			PreparedStatement st = conn.prepareStatement(prepStatement3);
			st.setString(1, single_column_name);
			st.setString(2, nextSurveyID);
			st.setString(3, single_value);

			// execute the preparedstatement
			st.executeUpdate();
			st.close();

			DatabaseConnection.closeConnection();

			// sql.performStatement("INSERT INTO Survey (SurveyID, "
			// + single_column_name + ") VALUES ('" +
			// nextSurveyID + "'" + single_value + ")"
			// );
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbadaptor.IDatabaseAdapter#newSurveyInstance(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String[],
	 * java.lang.String[])
	 */
	public void newSurveyInstance(String nextSurveyID, String nextPersonID,
			String nextSurveyInstanceID, String[] column_names, String[] values)
			throws SQLException {

		if (column_names.length == values.length) {
			String single_column_name = "";
			String single_value = "";
			for (int i = 0; i < values.length; i++) {
				column_names[i] = ", '" + column_names[i] + "'";
				values[i] = ", '" + values[i] + "'";

				single_column_name = single_column_name + column_names[i];
				single_value = single_value + values[i];
			}

			String prepStatement4 = "INSERT INTO SurveyInstance (SurveyID,PersonID,SurveyInstanceID, '?') VALUES ('?','?','?','?')";

			Connection conn = DatabaseConnection.requestConnection();

			PreparedStatement st = conn.prepareStatement(prepStatement4);
			st.setString(1, single_column_name);
			st.setString(2, nextSurveyID);
			st.setString(3, nextPersonID);
			st.setString(4, single_value);

			// execute the preparedstatement
			st.executeUpdate();
			st.close();

			DatabaseConnection.closeConnection();

			// sql.performStatement("INSERT INTO SurveyInstance ("
			// + "SurveyID, "
			// + "PersonID, "
			// + "SurveyInstanceID"
			// + single_column_name + ") "
			// + "VALUES ('" +
			// nextSurveyID + "', '" +
			// nextPersonID + "', '" +
			// nextSurveyInstanceID + "'"
			// + single_value + ")"
			// );
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

		// CONNECTION
		Connection conn = DatabaseConnection.requestConnection();

		// To create a new SG question, first find out if the dimension it
		// references exists.
		String statement1 = "SELECT SGD_ID FROM StandardGraphDimension"
				+ "WHERE SGDimension_Name = '?'";

		PreparedStatement st1 = conn.prepareStatement(statement1);
		st1.setString(1, dimension_name);
		String targetSGD_ID = findID(st1, "SGD_ID");

		st1.executeUpdate();
		st1.close();
		// String targetSGD_ID = findID(""
		// + "SELECT SGD_ID FROM StandardGraphDimension"
		// + "WHERE SGDimension_Name = '" + dimension_name + "';", "SGD_ID");

		// If it doesn't exist, create a record for it.
		if (targetSGD_ID == null) {
			targetSGD_ID = "ALEX's Magic NEW ID GENERATOR gives us a new ID for SGD_ID here";

			String prepStatement5 = "INSERT INTO StandardGraphDimension (SGD_ID, DimensionName) VALUES ('?', '?')";

			PreparedStatement st = conn.prepareStatement(prepStatement5);
			st.setString(1, targetSGD_ID);
			st.setString(2, dimension_name);

			// execute the preparedstatement
			st.executeUpdate();
			st.close();

			// targetSGD_ID =
			// "ALEX's Magic NEW ID GENERATOR gives us a new ID for SGD_ID here";
			// sql.performStatement(""
			// + "INSERT INTO StandardGraphDimension"
			// + "(SGD_ID, DimensionName) VALUES ('" +
			// targetSGD_ID + "', '" +
			// dimension_name + "');");
		}

		// Check if the question already exists

		String statement2 = "SELECT SGQ_ID FROM StandardGraphQuestion"
				+ "WHERE QuestionText = '?'";
		PreparedStatement st2 = conn.prepareStatement(statement2);
		st2.setString(1, dimension_name);
		String targetSGQ_ID = findID(st2, "SGQ_ID");

		st2.executeUpdate();
		st2.close();
		// String targetSGQ_ID = findID(""
		// + "SELECT SGQ_ID FROM StandardGraphQuestion"
		// + "WHERE QuestionText = '" + question_text + "';", "SGQ_ID");
		//

		// If it doesn't exist, create a record for it.
		if (targetSGQ_ID == null) {
			targetSGQ_ID = "ALEX's Magic NEW ID GENERATOR gives us a new ID for SGQ_ID here";

			String prepStatement6 = "INSERT INTO StandardGraphQuestion (SGQ_ID, SGD_ID, QuestionText) VALUES('?','?','?')";

			PreparedStatement st = conn.prepareStatement(prepStatement6);
			st.setString(1, targetSGQ_ID);
			st.setString(2, targetSGD_ID);
			st.setString(3, question_text);

			// execute the preparedstatement
			st.executeUpdate();
			st.close();

			// SAVE THIS ID SOMEWHERE. You'll need it when adding an answer.
			// sql.performStatement(""
			// + "INSERT INTO StandardGraphQuestion"
			// + "(SGQ_ID, SGD_ID, QuestionText) VALUES ('" +
			// targetSGQ_ID + "', '" +
			// targetSGD_ID + "', '" +
			// question_text + "');");
		}

		// Check if the question already was connected to this survey
		
		String statement3 = "SELECT SGQ_ID FROM StandardGraphQuestionsInSurvey"
				+ "WHERE SGQ_ID = '?'"
				+ "AND SurveyID = '?'";
		PreparedStatement st3 = conn.prepareStatement(statement3);
		st3.setString(1, targetSGQ_ID);
		st3.setString(2, surveyID);
		String targetSGQ_ID_join = findID(st3, "SGQ_ID");
		
		st3.executeUpdate();
		st3.close();
//		String targetSGQ_ID_join = findID(""
//				+ "SELECT SGQ_ID FROM StandardGraphQuestionsInSurvey"
//				+ "WHERE SGQ_ID = '" + targetSGQ_ID + "' " + "AND SurveyID = '"
//				+ surveyID + "';", "SGQ_ID");
		
		// If it doesn't exist, create a record for it.
		if (targetSGQ_ID_join == null) {
			String prepStatement7 = "INSERT INTO StandardGraphQuestionsInSurvey (SurveyID, SGQ_ID) VALUES('?','?')";

			PreparedStatement st = conn.prepareStatement(prepStatement7);
			st.setString(1, surveyID);
			st.setString(2, targetSGQ_ID);

			// execute the preparedstatement
			st.executeUpdate();
			st.close();

			DatabaseConnection.closeConnection();
			// sql.performStatement(""
			// + "INSERT INTO StandardGraphQuestionsInSurvey"
			// + "(SurveyID, SGQ_ID) VALUES ('" +
			// surveyID + "', '" +
			// targetSGQ_ID + "');");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbadaptor.IDatabaseAdapter#newLQ(java.lang.String[],
	 * java.lang.String, java.lang.String)
	 */
	public void newLQ(String[] categories, String question_text, String surveyID)
			throws SQLException {
		// Check if the question already exists
		Connection conn = DatabaseConnection.requestConnection();
		
		String statement3 = "SELECT LQ_ID FROM LayerQuestion"
				+ "WHERE QuestionText = '?'";
		PreparedStatement st3 = conn.prepareStatement(statement3);
		st3.setString(1, question_text);
		
		String targetLQ_ID = findID(st3, "LQ_ID");
		st3.executeUpdate();
		st3.close();

//		String targetLQ_ID = findID("" + "SELECT LQ_ID FROM LayerQuestion"
//				+ "WHERE QuestionText = '" + question_text + "';", "LQ_ID");
		
		// If it doesn't exist, create a record for it.
		if (targetLQ_ID == null) {
			targetLQ_ID = "ALEX's Magic NEW ID GENERATOR gives us a new ID for LQ_ID here";

			// SAVE THIS ID SOMEWHERE. You'll need it when adding an answer.

			String prepStatement8 = "INSERT INTO LayerQuestion (LQ_ID, QuestionText) VALUES('?','?')";

			PreparedStatement st = conn.prepareStatement(prepStatement8);
			st.setString(1, targetLQ_ID);
			st.setString(2, question_text);

			// execute the preparedstatement
			st.executeUpdate();
			st.close();

			// sql.performStatement(""
			// + "INSERT INTO LayerQuestion"
			// + "(LQ_ID, QuestionText) VALUES ('" +
			// targetLQ_ID + "', '" +
			// question_text + "');");
		}

		// Next, find out if each of the categories it references exists.
		for (int i = 0; i < categories.length; i++) {
			String statement4 = "SELECT CategoryID FROM LayerCategory"
					+ "WHERE CategoryName = '?'";
			PreparedStatement st4 = conn.prepareStatement(statement4);
			st4.setString(1, categories[i]);
			
			String targetCategoryID = findID(st4, "CategoryID");
			
			st4.executeUpdate();
			st4.close();
//			String targetCategoryID = findID(""
//					+ "SELECT CategoryID FROM LayerCategory"
//					+ "WHERE CategoryName = '" + categories[i] + "';",
//					"CategoryID");
			
			// If it doesn't exist, create a record for it.
			if (targetCategoryID == null) {
				targetCategoryID = "ALEX's Magic NEW ID GENERATOR gives us a new ID for CategoryID here";

				String prepStatement9 = "INSERT INTO LayerCategory (CategoryID, CategoryName) VALUES('?','?')";

				PreparedStatement st = conn.prepareStatement(prepStatement9);
				st.setString(1, targetCategoryID);
				st.setString(2, categories[i]);

				// execute the preparedstatement
				st.executeUpdate();
				st.close();

				// sql.performStatement(""
				// + "INSERT INTO LayerCategory"
				// + "(CategoryID, CategoryName) VALUES ('" +
				// targetCategoryID + "', '" +
				// categories[i] + "');");
			}
			// Create join record.
			String statement5 = "SELECT CategoryID FROM fact_LayerQuestionIsInCategory"
					+ "WHERE WHERE CategoryID = '?'"
					+ " AND LQ_ID= '?'";
			PreparedStatement st5 = conn.prepareStatement(statement5);
			st5.setString(1, categories[i]);
			st5.setString(2, targetLQ_ID);
			
			String targetCategoryID_join = findID(st5, "CategoryID");
			st5.executeUpdate();
			st5.close();
//			String targetCategoryID_join = findID(""
//					+ "SELECT CategoryID FROM fact_LayerQuestionIsInCategory"
//					+ "WHERE CategoryID= '" + categories[i] + "' AND "
//					+ "LQ_ID= '" + targetLQ_ID + "';", "CategoryID");
			
			// If it doesn't exist, create a record for it.
			if (targetCategoryID_join == null) {
				String prepStatement10 = "INSERT INTO fact_LayerQuestionIsInCategory (CategoryID, LQ_ID) VALUES('?','?')";

				PreparedStatement st = conn.prepareStatement(prepStatement10);
				st.setString(1, targetCategoryID);
				st.setString(2, targetLQ_ID);

				// execute the preparedstatement
				st.executeUpdate();
				st.close();

				// sql.performStatement(""
				// + "INSERT INTO fact_LayerQuestionIsInCategory"
				// + "(CategoryID, LQ_ID) VALUES ('" +
				// targetCategoryID + "', '" +
				// targetLQ_ID + "');");
			}
		}

		// Check if the question already was connected to this survey
		String statement6 = "SELECT LQ_ID FROM LayerQuestionInSurvey"
				+ "WHERE LQ_ID = '?'"
				+ " AND SurveyID = '?'";
		PreparedStatement st6 = conn.prepareStatement(statement6);
		st6.setString(1, targetLQ_ID);
		st6.setString(2, surveyID);
		
		String LQisInSurvey = findID(st6, "LQ_ID");
		st6.executeUpdate();
		st6.close();
//		String LQisInSurvey = findID(""
//				+ "SELECT LQ_ID FROM LayerQuestionInSurvey" + "WHERE LQ_ID = '"
//				+ targetLQ_ID + "'" + "AND SurveyID = '" + surveyID + "';",
//				"LQ_ID");
		
		// If it doesn't exist, create a record for it.
		if (LQisInSurvey == null) {
			String prepStatement11 = "INSERT INTO LayerQuestionInSurvey (SurveyID, LQ_ID) VALUES('?','?')";

			PreparedStatement st = conn.prepareStatement(prepStatement11);
			st.setString(1, surveyID);
			st.setString(2, targetLQ_ID);

			// execute the preparedstatement
			st.executeUpdate();
			st.close();

			DatabaseConnection.closeConnection();
			// sql.performStatement(""
			// + "INSERT INTO LayerQuestionInSurvey"
			// + "(SurveyID, LQ_ID) VALUES ('" +
			// surveyID + "', '" +
			// targetLQ_ID + "');");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbadaptor.IDatabaseAdapter#newLayerResponse(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	public void newLayerResponse(String LQ_ID, String answer, String surveyID,
			String surveyInstanceID, String personID, String newLR_ID)
			throws SQLException {

		Connection conn = DatabaseConnection.requestConnection();

		// Check if the answer already exists
		
		String statement6 = "SELECT LA_ID FROM LayerAnswer"
				+ "WHERE Answer = '?'";
		PreparedStatement st6 = conn.prepareStatement(statement6);
		st6.setString(1, answer);		
		
		String targetLA_ID = findID(st6, "LA_ID");
		st6.executeUpdate();
		st6.close();
//		String targetLA_ID = findID("" + "SELECT LA_ID FROM LayerAnswer"
//				+ "WHERE Answer = '" + answer + "';", "LA_ID");
		// If it doesn't exist, create a record for it.
		
		if (targetLA_ID == null) {
			targetLA_ID = "ALEX's Magic NEW ID GENERATOR gives us a new ID for LQ_ID here";

			String prepStatement11 = "INSERT INTO LayerAnswer (LA_ID, Answer) VALUES('?','?')";

			PreparedStatement st = conn.prepareStatement(prepStatement11);
			st.setString(1, targetLA_ID);
			st.setString(2, answer);

			// execute the preparedstatement
			st.executeUpdate();
			st.close();

			// sql.performStatement(""
			// + "INSERT INTO LayerAnswer"
			// + "(LA_ID, Answer) VALUES ('" +
			// targetLA_ID + "', '" +
			// answer + "');");
		}
		// Add Answer to Question in General
		
		String statement7 = "SELECT LQ_ID FROM fact_LayerAnswerToQuestion"
				+ "WHERE LQ_ID = '?'"
				+ "AND LA_ID = '?'";
		PreparedStatement st7 = conn.prepareStatement(statement7);
		st7.setString(1, answer);
		st7.setString(2, targetLA_ID);		
		
		String a2q = findID(st7, "LQ_ID");
		st7.executeUpdate();
		st7.close();
//		String a2q = findID("" + "SELECT LQ_ID FROM fact_LayerAnswerToQuestion"
//				+ "WHERE LQ_ID = '" + LQ_ID + "'" + "AND LA_ID = '"
//				+ targetLA_ID + "';", "LQ_ID");
		// If it doesn't exist, create a record for it.
		if (a2q == null) {
			String prepStatement11 = "INSERT INTO fact_LayerAnswerToQuestion (LA_ID, LQ_ID) VALUES('?','?')";

			PreparedStatement st = conn.prepareStatement(prepStatement11);
			st.setString(1, targetLA_ID);
			st.setString(2, LQ_ID);

			// execute the preparedstatement
			st.executeUpdate();
			st.close();

			// sql.performStatement(""
			// + "INSERT INTO fact_LayerAnswerToQuestion"
			// + "(LA_ID, LQ_ID) VALUES ('" +
			// targetLA_ID + "', '" +
			// LQ_ID + "');");
		}

		// CREATE RESPONSE

		String prepStatement11 = "INSERT INTO fact_LayerResponse (LR_ID, PersonID, SurveyID, SurveyInstanceID, LQ_ID, LA_ID) VALUES('?','?','?','?','?','?')";

		PreparedStatement st = conn.prepareStatement(prepStatement11);
		st.setString(1, newLR_ID);
		st.setString(2, personID);
		st.setString(3, surveyID);
		st.setString(4, surveyInstanceID);
		st.setString(5, LQ_ID);
		st.setString(6, targetLA_ID);

		// execute the preparedstatement
		st.executeUpdate();
		st.close();

		DatabaseConnection.closeConnection();

		// sql.performStatement("INSERT INTO fact_LayerResponse"
		// + "(LR_ID, PersonID, SurveyID, SurveyInstanceID, LQ_ID, LA_ID)"
		// + "VALUES"
		// + "('"
		// + newLR_ID + "', '"
		// + personID +"', '"
		// + surveyID +"', '"
		// + surveyInstanceID +"', '"
		// + LQ_ID +"', '"
		// + targetLA_ID +"'"
		// + ");"
		// );
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

		Connection conn = DatabaseConnection.requestConnection();

		// Check if the answer already exists
		String statement7 = "SELECT SGA_ID FROM StandardGraphAnswer"
				+ "WHERE Answer = '?'";
		PreparedStatement st7 = conn.prepareStatement(statement7);
		st7.setString(1, answer);		
		String targetSGA_ID = findID(st7, "SGA_ID");
		
		st7.executeUpdate();
		st7.close();
//		String targetSGA_ID = findID(""
//				+ "SELECT SGA_ID FROM StandardGraphAnswer" + "WHERE Answer = '"
//				+ answer + "';", "SGA_ID");
		
		// If it doesn't exist, create a record for it.
		if (targetSGA_ID == null) {
			targetSGA_ID = "ALEX's Magic NEW ID GENERATOR gives us a new ID for SQA_ID here";

			String prepStatement11 = "INSERT INTO StandardGraphAnswer (SGA_ID, Answer) VALUES('?','?')";

			PreparedStatement st = conn.prepareStatement(prepStatement11);
			st.setString(1, targetSGA_ID);
			st.setString(2, answer);

			// execute the preparedstatement
			st.executeUpdate();
			st.close();

			// sql.performStatement(""
			// + "INSERT INTO StandardGraphAnswer"
			// + "(SGA_ID, Answer) VALUES ('" +
			// targetSGA_ID + "', '" +
			// answer + "');");
		}
		// Add Answer to Question in General
		String statement8 = "SELECT SGQ_ID FROM fact_StandardGraphAnswerToQuestion"
				+ "WHERE SGQ_ID = '?'"
				+ "AND SGA_ID = '?'";
		PreparedStatement st8 = conn.prepareStatement(statement8);
		st8.setString(1, SGQ_ID);
		st8.setString(2, targetSGA_ID);
		
		String a2q = findID(st8, "SGQ_ID");
		st8.executeUpdate();
		st8.close();
		
//		String a2q = findID(""
//				+ "SELECT SGQ_ID FROM fact_StandardGraphAnswerToQuestion"
//				+ "WHERE SGQ_ID = '" + SGQ_ID + "'" + "AND SGA_ID = '"
//				+ targetSGA_ID + "';", "SGQ_ID");
		
		// If it doesn't exist, create a record for it.
		if (a2q == null) {
			String prepStatement11 = "INSERT INTO fact_StandardGraphAnswerToQuestion (SGA_ID, SGQ_ID) VALUES('?','?')";

			PreparedStatement st = conn.prepareStatement(prepStatement11);
			st.setString(1, targetSGA_ID);
			st.setString(2, SGQ_ID);

			// execute the preparedstatement
			st.executeUpdate();
			st.close();

			// sql.performStatement(""
			// + "INSERT INTO fact_StandardGraphAnswerToQuestion"
			// + "(SGA_ID, SGQ_ID) VALUES ('" +
			// targetSGA_ID + "', '" +
			// SGQ_ID + "');");
		}

		// CREATE RESPONSE

		String prepStatement11 = "INSERT INTO fact_StandardGraphResponse (SGR_ID, PersonID, SurveyID, SurveyInstanceID, SGQ_ID, SGA_ID) VALUES('?','?','?','?','?','?')";

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

		DatabaseConnection.closeConnection();

		// sql.performStatement("INSERT INTO fact_StandardGraphResponse"
		// + "(SGR_ID, PersonID, SurveyID, SurveyInstanceID, SGQ_ID, SGA_ID)"
		// + "VALUES"
		// + "('"
		// + newSGR_ID + "', '"
		// + personID +"', '"
		// + surveyID +"', '"
		// + surveyInstanceID +"', '"
		// + SGQ_ID +"', '"
		// + targetSGA_ID +"'"
		// + ");"
		// );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbadaptor.IDatabaseAdapter#findID(java.lang.String,
	 * java.lang.String)
	 */
	public String findID(PreparedStatement st, String column_desired)
			throws SQLException {
		Connection conn = DatabaseConnection.requestConnection();
		ResultSet rs = st.executeQuery();
		st.close();

		// STEP 5: Extract data from result set
		String output = "";
		while (rs.next()) {
			// Retrieve by column name
			output += rs.getString(column_desired);
		}
		conn.close();
		if (output.equals(""))
			return "Error: ID Not Found";
		else
			return output;

		// Statement stmt = null;
		// try{
		// stmt = conn.createStatement();
		// ResultSet rs = stmt.executeQuery(statement);
		// //STEP 5: Extract data from result set
		// String output = "";
		//
		// while(rs.next()){
		// //Retrieve by column name
		// output += rs.getString(column_desired);
		// }
		// System.out.println("Resultant ID found: " + output);
		// return output;
		// }
		// catch(SQLException se){
		// //Handle errors for JDBC
		// se.printStackTrace();
		// }catch(Exception e){
		// //Handle errors for Class.forName
		// e.printStackTrace();
		// }
		// finally{
		// //finally block used to close resources
		// try{
		// if(stmt!=null)
		// stmt.close();
		// }catch(SQLException se2){}// nothing we can do
		// } //end finally
		// return "Error: ID Not Found";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dbadaptor.IDatabaseAdapter#getColumnList(java.lang.String)
	 */
	public String[] getColumnList(String table) throws SQLException{
		
		Connection conn = DatabaseConnection.requestConnection();
		ArrayList<String> columns = new ArrayList<String>();

		String statement8 = "SELECT column_name"
				+ "FROM user_tab_cols "
				+ "WHERE table_name = '?'";
		
		PreparedStatement st8 = conn.prepareStatement(statement8);
		st8.setString(1, table);
		ResultSet rs= st8.executeQuery(statement8);
		st8.executeUpdate();
		while (rs.next()) {
			columns.add(rs.getString("column_name"));
		}
		st8.close();
		String[] col_arr = new String[columns.size()];
		
			for (int i = 0; i < col_arr.length; i++) {
				col_arr[i] = columns.get(i);
			}
			conn.close();
			return col_arr;
		
//		String sql = "SELECT column_name " + "FROM user_tab_cols "
//				+ "WHERE table_name = '" + table + "';";
//
//		Statement stmt = null;
//		try {
//			stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery(sql);
//
//			while (rs.next()) {
//				columns.add(rs.getString("column_name"));
//			}
//		} catch (SQLException se) {
//			// Handle errors for JDBC
//			se.printStackTrace();
//		} catch (Exception e) {
//			// Handle errors for Class.forName
//			e.printStackTrace();
//		} finally {
//			// finally block used to close resources
//			try {
//				if (stmt != null)
//					stmt.close();
//			} catch (SQLException se2) {
//			}// nothing we can do
//		} // end finally
//
//		String[] col_arr = new String[columns.size()];
//
//		for (int i = 0; i < col_arr.length; i++) {
//			col_arr[i] = columns.get(i);
//		}
//
			

	}

}

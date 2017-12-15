package dbadaptor;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ResponseQA;


/**
 * This is the adapter interface for storing persistent data
 * @author 
 * 
 */
public interface IDatabaseAdapter
{
	/**
	 * Add a new column to the General Survey 
	 * @param column_name
	 * @throws SQLException
	 */
	public void newGeneralSurveyColumn(String column_name) throws SQLException;
	/**
	 * column_name: this is the string that is found at the top of a CSV column, the Q.
	 * Add a new column to the Survey Instance
	 * @param column_name
	 * @throws SQLException
	 */
	public void newSurveyInstanceColumn(String column_name) throws SQLException;
	/**
	 * column_name: this is the string that is found at the top of a CSV column, the Q.
	 * nextPersonID: auto-generated unique ID
	 * @param nextPersonID
	 * @throws SQLException
	 */
	public void newPerson(String nextPersonID) throws SQLException;
	/**
	 * Insert new General Survey
	 * column_names: this is the string array that is found at the top of a CSV column, the Q.
	 * nextSurveyId: auto-generated unique ID
	 * @param nextSurveyID
	 * @param column_names
	 * @param values
	 * @throws SQLException
	 */
	public void newGeneralSurvey(String nextSurveyID, List<String> column_names,
			List<ResponseQA> values) throws SQLException ;
	/**
	 * Insert new Survey Instance
	 * nextSurveyId: auto-generated unique ID
	 * nextPersonID: auto-generated unique ID
	 * @param nextSurveyID
	 * @param nextPersonID
	 * @param nextSurveyInstanceID
	 * @param column_names
	 * @param values
	 * @throws SQLException
	 */
	public void newSurveyInstance(String nextSurveyID,String nextPersonID,String nextSurveyInstanceID,List<String> column_names,
			List<ResponseQA> values) throws SQLException;
	/**
	 * Insert new Standard Graph Question
	 * dimension_name: 	Just a string-- for example "I/We", or whatever the user happened to type in as the GRAPH dimension to use
	 * question_text:  Literally just the text of the question, the header of the CSV column
	 * surveyID: The id of the GENERAL Survey (for the entire batch
	 * @param dimension_name
	 * @param question_text
	 * @param surveyID
	 * @throws SQLException
	 */
	public void newSGQ(String dimension_name, String question_text, String surveyID) throws SQLException;
	/**
	 * Insert new Layer Question
	 * categories[]: an array of all the categories a user might want to attach to the question. 
	 * This can just be empty if we didn't implement this in the GUI, or if the user doesn't choose any
	 * question_text: Literally just the text of the question, the header of the CSV column
	 * surveyID: The id of the GENERAL Survey (for the entire batch)
	 * @param categories
	 * @param question_text
	 * @param surveyID
	 * @throws SQLException
	 */
	public void newLQ(String[] categories, String question_text, String surveyID, String qID) throws SQLException;
	/**
	 * Insert new Layer Response
	 * LQ_ID:	 			RETREIVE this as a result of that time you called newLQ(). it is the question ID.
	 * SurveyID:			Batch survey ID
	 * SurveyInstanceID:	Should be saved from when you sent it as an argument when you called newSurveyInstance
	 * PersonID:			Should be saved from when you sent it as an argument when you called newPerson
	 * newLR_ID:			Just make a new random value and don't remember it.
	 * @param LQ_ID
	 * @param answer
	 * @param surveyID
	 * @param surveyInstanceID
	 * @param personID
	 * @param newLR_ID
	 * @throws SQLException
	 */
	public void newLayerResponse(String LQ_ID,String answer,String surveyID,String surveyInstanceID,String personID,
			String newLR_ID, String newLA_ID) throws SQLException;
	/**
	 * LQ_ID:	 			RETREIVE this as a result of that time you called newLQ(). it is the question ID.
	 * SurveyID:			Batch survey ID
	 * SurveyInstanceID:	Should be saved from when you sent it as an argument when you called newSurveyInstance
	 * PersonID:			Should be saved from when you sent it as an argument when you called newPerson
	 * newLR_ID:			Just make a new random value and don't remember it.
	 * Insert new Standard Graph Response
	 * @param SGQ_ID
	 * @param answer
	 * @param surveyID
	 * @param surveyInstanceID
	 * @param personID
	 * @param newSGR_ID
	 * @throws SQLException
	 */
	public void newSGResponse(String SGQ_ID,String answer,String surveyID,String surveyInstanceID,String personID,
			String newSGR_ID) throws SQLException;
	/**
	 * Find Id
	 * @param statement
	 * @param column_desired
	 * @return
	 */

	public String findID(PreparedStatement st, String column_desired) throws SQLException;
	/**
	 * Get column list
	 * @param table
	 * @return
	 */
	public String[] getColumnList(String table)throws SQLException;
}

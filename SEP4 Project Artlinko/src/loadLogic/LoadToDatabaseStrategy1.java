package loadLogic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import services.IDGen;
import config.StructDefinitionElements;
import dbadaptor.DatabaseAdapter;
import dbadaptor.IDatabaseAdapter;
import model.ResponseQA;
import model.SurveyResponsesCollection;

/**
 * Based on Oracle database created during our project
 * @author Alexandru
 *
 */
public class LoadToDatabaseStrategy1 implements ILoadToDatabase, StructDefinitionElements {
	
	
	static IDatabaseAdapter adapter;
	
	
	public LoadToDatabaseStrategy1(IDatabaseAdapter adapter) {
		// TODO Auto-generated constructor stub
		this.adapter=adapter;
	}
	

	@Override
	public void loadToDatabase(SurveyResponsesCollection collection) throws SQLException{
		// TODO Auto-generated method stub
		
		//case general survey
		

	}

	/*

//	public void newGeneralSurveyColumn(List<ResponseQA> collection) throws SQLException{
//		System.out.println("START newGeneralSurveyColumn");
//	for(ResponseQA item: collection){
//		if(item.getProperty(GENERAL)&& item.getProperty(SURVEY))
//		adapter.newGeneralSurveyColumn(item.getQuestion());
//	}
//	System.out.println("DONE newGeneralSurveyColumn");
//	}
//

//	public void newSurveyInstanceColumn(List<ResponseQA> collection) throws SQLException{
//		System.out.println("START newSurveyInstanceColumn");
//	for(ResponseQA item: collection)	{
//		if(item.getProperty(SURVEY)&& item.getProperty(INSTANCE))
//		adapter.newSurveyInstanceColumn(item.getQuestion());
//	}
//	System.out.println("DONE newSurveyInstanceColumn");
//	}*/

	
	/**
	 * Generates the strings to be used in the statements for registering General Survey data
	 * @param collection
	 * @param structure
	 * @throws SQLException
	 */
	public void newGeneralSurvey(SurveyResponsesCollection collection) throws SQLException {
		System.out.println("Start inserting the survey unique id");
		
		adapter.newGeneralSurvey(collection.getSurveys().get(0).getEntireSurveyID());
		System.out.println("DONE General Survey");
	}
	
	
	
	/**
	 * Inserts all survey instances ids (a.k.a. persons who responded) into database
	 * @param structureInstancesID
	 * @throws SQLException
	 */
	public void newPerson(List<ResponseQA> collection) throws SQLException{
		System.out.println("Start Person adding");
		List<String> temp= new ArrayList<String>();
		int count=0;
		
		for(ResponseQA item: collection)	{
			if(!temp.contains(item.getSurveyInstanceID())){
				count++;
			adapter.newPerson(item.getSurveyInstanceID());
			temp.add(item.getSurveyInstanceID());
			}
		}
		System.out.println("Done Person adding "+count);
	}
	
	
	

	

	public void newSurveyInstances(SurveyResponsesCollection collection) throws SQLException{
		System.out.println("Start Survey Instance");

		String entireSurveyID = collection.getSurveys().get(0).getEntireSurveyID();
		
		List<String> temp= new ArrayList<String>();
		int count=0;
		
		for(ResponseQA item: collection.getSurveys())	{
			if(!temp.contains(item.getSurveyInstanceID())){
			temp.add(item.getSurveyInstanceID());
			}
		}
		
		for(String el: temp){
			
		String id= IDGen.generateId();	
			
		adapter.newSurveyInstance(entireSurveyID, el, id);
		}
		
		System.out.println("DONE Survey Instance");
	}
	
	
	public HashMap<String, String> newSGDimension (List<String> types)throws SQLException{
		
		HashMap<String, String> rezultedID = new HashMap<String, String>();
		
		for(String el: types){
			String id= IDGen.generateId();
			rezultedID.put(el, id);
			adapter.newSGDimension(el, id);
		}
		return rezultedID;
	}
	
	
	
	public void newSGQ(SurveyResponsesCollection collection, String dimensionID) throws SQLException {
		
		System.out.println("START new SGQ");
		List<String> tmp= new ArrayList<String>();
		for(ResponseQA el: collection.getSurveys() ){
			if(el.getProperty(QUESTION)&&el.getProperty(SGD)){
				adapter.newSGQ(el.getQuestionID(), el.getQuestion(), dimensionID);
			}
		}
		System.out.println("END new SGQ");
		
	}
	
	
public void newLQ(SurveyResponsesCollection collection) throws SQLException {
		
		System.out.println("START new LQ");
		List<String> tmp= new ArrayList<String>();
		for(ResponseQA el: collection.getSurveys() ){
			if(el.getProperty(QUESTION)&&el.getProperty(OTHER)){
				adapter.newLQ(el.getQuestionID(), el.getQuestion());
			}
		}
		System.out.println("END new LQ");

	}
	
public void newLayerResponse(SurveyResponsesCollection collection) throws SQLException {
	
	System.out.println("START new LResp");
	List<String> tmp= new ArrayList<String>();
	for(ResponseQA el: collection.getSurveys() ){
		if(el.getProperty(QUESTION)&&el.getProperty(OTHER)){
			adapter.newLayerResponse(el.getAnswerID(), el.getAnswers().get(0));
		}
	}
	System.out.println("END new LResp");
	
}

public void newSGResponse(SurveyResponsesCollection collection) throws SQLException {
	
	System.out.println("START new SGR");
	List<String> tmp= new ArrayList<String>();
	for(ResponseQA el: collection.getSurveys() ){
		if(el.getProperty(QUESTION)&&el.getProperty(SGD)){
			adapter.newSGResponse(el.getAnswerID(),el.getAnswers().get(0));
		}
	}
	System.out.println("END new SGR");
	
}

/*String personID, String surveyID,
		String surveyInstanceId, String sGQ_ID, String sGA_ID*/
public void newfact_SGResponse (SurveyResponsesCollection collection) throws SQLException{
	System.out.println("START new SGResponse");
	List<String> tmp= new ArrayList<String>();
	for(ResponseQA el: collection.getSurveys() ){
		if(el.getProperty(QUESTION)&&el.getProperty(SGD)){
			adapter.newfact_SGResponse(el.getSurveyInstanceID(), el.getEntireSurveyID(), el.getQuestionID(), el.getAnswerID());
		}
	}
	System.out.println("END new SGResponse");
}

public void newfact_LResponse(SurveyResponsesCollection collection) throws SQLException {
	
	System.out.println("START new LResp");
	List<String> tmp= new ArrayList<String>();
	for(ResponseQA el: collection.getSurveys() ){
		if(el.getProperty(QUESTION)&&el.getProperty(OTHER)){
			adapter.newfact_LResponse(el.getSurveyInstanceID(), el.getEntireSurveyID(), el.getQuestionID(), el.getAnswerID());
		}
	}
	System.out.println("END new LResp");
	
}


}

package loadLogic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	/**
	 * build column for general survey 
	 * @param collection
	 * @throws SQLException
	 */
	public void newGeneralSurveyColumn(List<ResponseQA> collection) throws SQLException{
		System.out.println("START newGeneralSurveyColumn");
	for(ResponseQA item: collection){
		if(item.getProperty(GENERAL)&& item.getProperty(SURVEY))
		adapter.newGeneralSurveyColumn(item.getQuestion());
	}
	System.out.println("DONE newGeneralSurveyColumn");
	}

	/**
	 * build column for survey instance
	 * @param collection
	 * @throws SQLException
	 */
	public void newSurveyInstanceColumn(List<ResponseQA> collection) throws SQLException{
		System.out.println("START newSurveyInstanceColumn");
	for(ResponseQA item: collection)	{
		if(item.getProperty(SURVEY)&& item.getProperty(INSTANCE))
		adapter.newSurveyInstanceColumn(item.getQuestion());
	}
	System.out.println("DONE newSurveyInstanceColumn");
	}

	
	/**
	 * Inserts all survey instances ids (a.k.a. persons who responded) into database
	 * @param structureInstancesID
	 * @throws SQLException
	 */
	public void newPerson(List<ResponseQA> collection) throws SQLException{
		System.out.println("Start Person adding");
		List<String> temp= new ArrayList<String>();
		for(ResponseQA item: collection)	{
			//if(item.getProperty(PERSON))
			if(!temp.contains(item.getSurveyInstanceID())){
			adapter.newPerson(item.getSurveyInstanceID());
			temp.add(item.getSurveyInstanceID());
			}
		}
		System.out.println("Done Person adding");
	}
	
	
	/**
	 * Generates the strings to be used in the statements for registering General Survey data
	 * @param collection
	 * @param structure
	 * @throws SQLException
	 */
	public void newGeneralSurvey(SurveyResponsesCollection collection) throws SQLException {
		System.out.println("Start General Survey");

		List<String> struct = new ArrayList<String>();
		for (ResponseQA el : collection.getSurveys()) {
			if(el.getProperty(GENERAL)==true&&el.getProperty(SURVEY)==true&&!struct.contains(el.getQuestion())){
				struct.add(el.getQuestion());
			}
		}
		List<ResponseQA> itemList = new ArrayList<ResponseQA>();
		int count=0;
		for(ResponseQA el : collection.getSurveys()){
			if(el.getProperty(GENERAL)&&el.getProperty(SURVEY)){
				itemList.add(el);
				count++;
			}
			if(count==struct.size()){
				System.out.println("Run query "+struct.size()+" "+itemList.size());
			adapter.newGeneralSurvey(el.getSurveyInstanceID(), struct, itemList);
			itemList= new ArrayList<ResponseQA>();
			count=0;
			}
		}	
		System.out.println("DONE General Survey");
	}

	

	public void newSurveyInstances(SurveyResponsesCollection collection) throws SQLException{
		System.out.println("Start Survey Instance");

		List<String> struct = new ArrayList<String>();
		for (ResponseQA el : collection.getSurveys()) {
			if(el.getProperty(INSTANCE)&&el.getProperty(SURVEY)&&!struct.contains(el.getQuestion())){
				struct.add(el.getQuestion());
			}
		}
		List<ResponseQA> itemList = new ArrayList<ResponseQA>();
		int count=0;
		for(ResponseQA el : collection.getSurveys()){
			if(el.getProperty(GENERAL)==true&&el.getProperty(SURVEY)==true){
				itemList.add(el);
				count++;
			}
			if(count==struct.size()){
				System.out.println("Run query "+struct.size()+" "+itemList.size());
			adapter.newSurveyInstance(el.getSurveyInstanceID(), el.getSurveyInstanceID(),el.getSurveyInstanceID(), struct, itemList);
			itemList= new ArrayList<ResponseQA>();
			count=0;
			}
		}	
		System.out.println("DONE Survey Instance");
	}
	
	
	public void newSGQ(SurveyResponsesCollection collection) throws SQLException {
		
		System.out.println("START new SGQ");
		List<String> tmp= new ArrayList<String>();
		for(ResponseQA el: collection.getSurveys() ){
			if(el.getProperty(QUESTION)&&el.getProperty(SGD)/*&&!tmp.contains(el.getQuestion())*/){
				adapter.newSGQ(el.getOtherColumnName(), el.getQuestion(), el.getEntireSurveyID());
			}
		}
		System.out.println("END new SGQ");
		//public void newSGQ(String dimension_name, String question_text, String surveyID) throws SQLException
	}
	
	
public void newLQ(SurveyResponsesCollection collection) throws SQLException {
		
		System.out.println("START new LQ");
		List<String> tmp= new ArrayList<String>();
		String[] cat= {"general"};
		for(ResponseQA el: collection.getSurveys() ){
			if(el.getProperty(QUESTION)&&el.getProperty(OTHER)/*&&!tmp.contains(el.getQuestion())*/){
				adapter.newLQ(cat, el.getQuestion(), el.getEntireSurveyID(), el.getQuestionID());
			}
		}
		System.out.println("END new LQ");
		//public void newSGQ(String dimension_name, String question_text, String surveyID) throws SQLException
	}
	
public void newLayerResponse(SurveyResponsesCollection collection) throws SQLException {
	
	System.out.println("START new LResp");
	List<String> tmp= new ArrayList<String>();
	String[] cat= {"general"};
	for(ResponseQA el: collection.getSurveys() ){
		if(el.getProperty(QUESTION)&&el.getProperty(OTHER)/*&&!tmp.contains(el.getQuestion())*/){
			//adapter.newLayerResponse(LQ_ID, answer, surveyID, surveyInstanceID, personID, newLR_ID);
			adapter.newLayerResponse(el.getQuestionID(), el.getAnswers().get(0), el.getEntireSurveyID(), el.getSurveyInstanceID(), el.getSurveyInstanceID(), el.getAnswerID(), el.getAnswerID());//(cat, el.getQuestion(), el.getEntireSurveyID());
		}
	}
	System.out.println("END new LResp");
	//public void newSGQ(String dimension_name, String question_text, String surveyID) throws SQLException
}

public void newSGResponse(SurveyResponsesCollection collection) throws SQLException {
	
	System.out.println("START new SGQ");
	List<String> tmp= new ArrayList<String>();
	for(ResponseQA el: collection.getSurveys() ){
		if(el.getProperty(QUESTION)&&el.getProperty(SGD)/*&&!tmp.contains(el.getQuestion())*/){
			adapter.newSGResponse(el.getQuestionID(), el.getAnswers().get(0), el.getEntireSurveyID(), el.getSurveyInstanceID(), el.getSurveyInstanceID(), el.getAnswerID());
		}
	}
	System.out.println("END new SGQ");
	//public void newSGQ(String dimension_name, String question_text, String surveyID) throws SQLException
}


}

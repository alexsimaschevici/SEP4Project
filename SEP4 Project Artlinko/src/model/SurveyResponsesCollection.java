package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



/**
 * 
 * Holds all the questions and responses of a survey from a current responder
 * @author Alexandru
 *
 */
public class SurveyResponsesCollection {

	List<ResponseQA> surveyResp;

	public SurveyResponsesCollection() {
		super();
		this.surveyResp = new ArrayList<ResponseQA>();
	}

	public List<ResponseQA> getSurveys() {
		return surveyResp;
	}

	public void setSurveys(List<ResponseQA> surveys) {
		this.surveyResp = surveys;
	}
	
	public void addSurvey(ResponseQA oneResp, int row){
		surveyResp.add(oneResp);
	}
	
	
	public ResponseQA getSurvey(int pos){
		return surveyResp.get(pos);
	}

	
	public int size(){
		return surveyResp.size();
	}
	
	//TO BE IMPLEMENTED
	public List<ResponseQA> getColumn(String questionID){
		return null;
	}
	
	
	//TO BE IMPLEMENTED
	public List<ResponseQA> getRow(String surveyInstanceID){
		return null;
	}
	
	//TO BE IMPLEMENTED
	public List<ResponseQA> getByProperty(HashMap<String, Boolean> properties){
		return null;
	}
	
}

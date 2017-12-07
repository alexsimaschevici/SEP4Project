package model;

import java.util.ArrayList;
import java.util.HashMap;



/**
 * 
 * Holds all the questions and responses of a survey from a current responder
 * @author Alexandru
 *
 */
public class SurveyResponsesCollection {

	HashMap<String, ResponseQA> surveyResp;

	public SurveyResponsesCollection() {
		super();
		this.surveyResp = new HashMap<String,ResponseQA>();
	}

	public HashMap<String,ResponseQA> getSurveys() {
		return surveyResp;
	}

	public void setSurveys(HashMap<String,ResponseQA> surveys) {
		this.surveyResp = surveys;
	}
	
	public void addSurvey(ResponseQA oneResp){
		surveyResp.put(oneResp.getQuestion(), oneResp);
	}
	
	
	public ResponseQA getSurvey(String key){
		return surveyResp.get(key);
	}
	
	
	
	
	
}

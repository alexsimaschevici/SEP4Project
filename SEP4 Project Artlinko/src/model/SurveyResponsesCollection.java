package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Holds all the questions and responses of a survey from a current responder
 * 
 * @author Alexandru, Cristian
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
	
	public void addSurvey(ResponseQA oneResp){
		surveyResp.add(oneResp);
	}
	
	
	public ResponseQA getSurvey(int pos){
		return surveyResp.get(pos);
	}

	public int size(){
		return surveyResp.size();
	}
	
	  // TO BE IMPLEMENTED
	   public List<ResponseQA> getColumn(String questionID)
	   {
	      ArrayList<ResponseQA> list = new ArrayList<ResponseQA>();
	      for (ResponseQA elem : surveyResp)
	         if (elem.getQuestionID().equals(questionID))
	            list.add(elem);
	      return list;
	   }

	   // TO BE IMPLEMENTED
	   public List<ResponseQA> getRow(String surveyInstanceID)
	   {
	      ArrayList<ResponseQA> list = new ArrayList<ResponseQA>();
	      for (ResponseQA elem : surveyResp)
	         if (elem.getSurveyInstanceID().equals(surveyInstanceID))
	            list.add(elem);
	      return list;
	   }

	   // TO BE IMPLEMENTED
//	   public List<ResponseQA> getByProperty(HashMap<String, Boolean> properties)
//	   {
//	      ArrayList<ResponseQA> list = new ArrayList<ResponseQA>();
//	      for (ResponseQA elem : surveyResp)
//	         if (elem.getProperty(key) == properties)
//	            list.add(elem);
//	      return list;
//
//	   }
	

}

package model;

import globalvar.StructDefinitionElements;

import java.util.ArrayList;
import java.util.HashMap;
import services.*;

/**
 * Model for Response that holds answer or multiple answers to a specific question
 * 
 * @author Alexandru
 *
 */
public class ResponseQA implements StructDefinitionElements {

	private ArrayList<String> answers;
	private String question;
	public HashMap<String, Boolean> property;
	//NEEDS TO BE INTEGRATED
	public String dimension;
	//NEEDS TO BE INTEGRATED
	//this defines in which row is the q and a placed
	public String surveyInstanceID;
	
	//this defines the question id for the whole column
	public String questionID;
	
	//this defines the question answer combination
	public String answerID;
	
	//general id of the batch
	public String entireSurveyID;
	
	public ResponseQA(ArrayList<String> answers, String question, String questionID,
			String surveyInstanceID, String entireSurveyID) {
		super();
		this.answers = answers;
		this.question = question;
		this.questionID=questionID;
		this.surveyInstanceID= entireSurveyID;
		this.entireSurveyID= entireSurveyID;
		this.answerID= IDGen.generateId();
		
		property= new HashMap<String, Boolean>() ;
		this.property.put(PERSON, false);
		this.property.put(QUESTION, false);
		this.property.put(SURVEY, false);
		this.property.put(GENERAL, false);
		this.property.put(INSTANCE, false);
		this.property.put(SGD, false);
		this.property.put(OTHER, false);	
	}
	public ArrayList<String> getAnswers() {
		return answers;
	}
	public void setAnswers(ArrayList<String> answers) {
		this.answers = answers;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public boolean getProperty(String key) {
		return property.get(key);
	}
	public void setProperty(String key, boolean value) {
		this.property.put(key, value);
	}
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	public String getSurveyInstanceID() {
		return surveyInstanceID;
	}
	public void setSurveyInstanceID(String surveyInstanceID) {
		this.surveyInstanceID = surveyInstanceID;
	}
	public String getQuestionID() {
		return questionID;
	}
	public void setQuestionID(String questionID) {
		this.questionID = questionID;
	}
	public String getAnswerID() {
		return answerID;
	}
	
	
	
	@Override
	public String toString() {
		return "ResponseQA [answers=" + answers + ", question=" + question
				+ ", property=" + property + ", dimension=" + dimension
				+ ", surveyInstanceID=" + surveyInstanceID + ", questionID="
				+ questionID + ", answerID=" + answerID + ", entireSurveyID="
				+ entireSurveyID + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((answerID == null) ? 0 : answerID.hashCode());
		result = prime * result + ((answers == null) ? 0 : answers.hashCode());
		result = prime * result
				+ ((dimension == null) ? 0 : dimension.hashCode());
		result = prime * result
				+ ((entireSurveyID == null) ? 0 : entireSurveyID.hashCode());
		result = prime * result
				+ ((property == null) ? 0 : property.hashCode());
		result = prime * result
				+ ((question == null) ? 0 : question.hashCode());
		result = prime * result
				+ ((questionID == null) ? 0 : questionID.hashCode());
		result = prime
				* result
				+ ((surveyInstanceID == null) ? 0 : surveyInstanceID.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResponseQA other = (ResponseQA) obj;
		if (answerID == null) {
			if (other.answerID != null)
				return false;
		} else if (!answerID.equals(other.answerID))
			return false;
		if (answers == null) {
			if (other.answers != null)
				return false;
		} else if (!answers.equals(other.answers))
			return false;
		if (dimension == null) {
			if (other.dimension != null)
				return false;
		} else if (!dimension.equals(other.dimension))
			return false;
		if (entireSurveyID == null) {
			if (other.entireSurveyID != null)
				return false;
		} else if (!entireSurveyID.equals(other.entireSurveyID))
			return false;
		if (property == null) {
			if (other.property != null)
				return false;
		} else if (!property.equals(other.property))
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (questionID == null) {
			if (other.questionID != null)
				return false;
		} else if (!questionID.equals(other.questionID))
			return false;
		if (surveyInstanceID == null) {
			if (other.surveyInstanceID != null)
				return false;
		} else if (!surveyInstanceID.equals(other.surveyInstanceID))
			return false;
		return true;
	}
	
	
}

package model;

import globalvar.StructDefinitionElements;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Model for Response that holds answer or multiple answers to a specific question
 * 
 * @author Alexandru
 *
 */
public class ResponseQA implements StructDefinitionElements {

	private ArrayList<String> answers;
	private String question;
	private HashMap<String, Boolean> property;
	public ResponseQA(ArrayList<String> answers, String question) {
		super();
		this.answers = answers;
		this.question = question;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answers == null) ? 0 : answers.hashCode());
		result = prime * result
				+ ((question == null) ? 0 : question.hashCode());
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
		if (answers == null) {
			if (other.answers != null)
				return false;
		} else if (!answers.equals(other.answers))
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		return true;
	}
	
	
	
}

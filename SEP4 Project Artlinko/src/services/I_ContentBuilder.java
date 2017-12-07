package services;

import java.util.ArrayList;

import model.ResponseQA;


/**
 * 
 * Factory to any new object related to the process
 * @author Alexandru
 *
 */
public interface I_ContentBuilder {

	public ResponseQA buildResponseQA(ArrayList<String> answers, String question);
	
}

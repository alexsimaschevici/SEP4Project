package services;

import java.util.ArrayList;

import model.ResponseQA;


/**
 * Implementation for building all the elements fetched from surveys
 * @author Alexandru
 *
 */
public class ContentBuilderFactory implements I_ContentBuilder
{

	@Override
	public ResponseQA buildResponseQA(ArrayList<String> answers, String question) {
		
		return new ResponseQA(answers, question);
	}

}

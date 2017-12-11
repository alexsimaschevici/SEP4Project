package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import globalvar.GlobalVar;
import globalvar.StructDefinitionElements;
import services.DataValidator;
import services.IDGen;
import view.View;
import model.ResponseQA;
import model.SurveyResponsesCollection;

/**
 * Main controller of the system
 * 
 * @author Alexandru
 *
 */
public class SystemController implements GlobalVar, StructDefinitionElements {

	private View view;
	private List<String> structure = null;
	private SurveyResponsesCollection allResponses;
	private List<List<String>> allData = null;

	public SystemController() {
		this.view = new View();
		this.allResponses = new SurveyResponsesCollection();
	}

	public void readSurveys() {

		// read all data from file
		allData = CSVHELPER.readSurveys();

		// retrieve structure and remove from extract 
		this.structure = CSVHELPER.getSurveyStructure(allData);
		allData.remove(0);	
		
		//Assign extract all id
		String entireSurveyID = IDGen.generateId();
		
		//Assign ids to all question of the structure
		List<String> strucureIdCollection = IDGen.assignIdToStruct(structure);
		
		// Process data into ResponseQA objects
		int count = 0;
		for (List<String> subList : allData) {
			String surveyInstaceID = IDGen.generateId();	
 			for (int i = 0; i < subList.size(); i++) {
				ArrayList<String> answers = new ArrayList<String>();
				
				// Response validator
				DataValidator.fixEmptyResponses(answers, subList.get(i));				
				
				//Finalize and add to all responses
				allResponses.addSurvey(
						new ResponseQA(answers, structure.get(i),
								strucureIdCollection.get(i),
								surveyInstaceID, entireSurveyID), count);
			}
			count++;
		}

		// assign types to e
		view.getTypesAssigned(structure,allResponses);
	}
	
	///TEST OUTPUT SURVEYS
	public void displaySurveys() {
		int count=0;
		for (int i=0; i<allResponses.size(); i++) {
			if(count==0)
			System.out.println(allResponses.getSurvey(i).getQuestion());
			count++;
			if(count>=structure.size()){
			count=0;
			System.exit(0);
			}
//			if(count==0)
//				System.out.println(allResponses.getSurvey(i).toString());
//			count++;
//			if(count>=structure.size()){
//			count=0;
//			}
		}
	}
	

	//TEST MAIN
	public static void main(String[] args) {
		SystemController controller = new SystemController();

		controller.readSurveys();
		controller.displaySurveys();

	}

}

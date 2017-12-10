package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import globalvar.GlobalVar;
import globalvar.StructDefinitionElements;
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

		// build objects from of all data and place them in allResponses
		// collection

		this.structure = CSVHELPER.getSurveyStructure(allData);
		allData.remove(0);
		int count = 0;
		for (List<String> subList : allData) {
			for (int i = 0; i < subList.size(); i++) {
				ArrayList<String> answers = new ArrayList<String>();

				// /VALIDATION OF ASNSWERS SHOULD OCCURE HERE

				if (subList.get(i).equals("")) {
					answers.add("n/a");
					// System.out.println(structure.get(i)+" || n/a");
				} else {
					answers.add(subList.get(i));
					// System.out.println(structure.get(i)+" || "+subList.get(i)+"");
				}
				allResponses.addSurvey(
						new ResponseQA(answers, structure.get(i)), count);

			}
			// System.out.println("\n\n");
			count++;
		}

		// assign types to e

		 List<ArrayList<Boolean>>  typesAssigned =
		 view.getTypesAssigned(structure);
		 count=0;
		 for(int i=0; i<structure.size(); i++)
		 {
			  for(int j=0; j<7; j++){
			 	this.allResponses.getSurveys().get(i).setProperty(TYPES[j], typesAssigned.get(i).get(j));
				//System.out.println(TYPES[j] +" "+ typesAssigned.get(i).get(j));
		//	 	System.out.println(this.allResponses.getSurveys().get(i).getProperty(TYPES[j]));
//				System.out.println("count: "+i+"| j:"+j);
//				System.out.println("comp size: "+ typesAssigned.size());
			}		 
		 }
		//System.exit(0);
	
	}

	
	///TEST OUTPUT SURVEYS
	public void displaySurveys() {
		int count = 0;
		for (int i=0; i<allResponses.getSurveys().size(); i++) {
			ResponseQA item= allResponses.getSurveys().get(i);
			System.out.println(" "+
					//PERSON, QUESTION, SURVEY, GENERAL, INSTANCE, SGD, OTHER
					count+"  "+ 
					item.getProperty(PERSON)+" "+
					item.getProperty(QUESTION)+" "+
					item.getProperty(SURVEY)+" "+
					item.getProperty(GENERAL)+" "+
					item.getProperty(INSTANCE)+" "+
					item.getProperty(SGD)+" "+
					item.getProperty(OTHER)+" "+
					" || "+item.getQuestion() + " || "
					+ item.getAnswers().get(0));
			
			count++;
			if(count>=structure.size()){
			System.out.println("\n\n");
			count=0;
			}
		}

		System.out.println(allResponses.getSurveys().get(0).getProperty(PERSON));
	}

	//TEST MAIN
	public static void main(String[] args) {
		SystemController controller = new SystemController();

		controller.readSurveys();
		controller.displaySurveys();

	}

}

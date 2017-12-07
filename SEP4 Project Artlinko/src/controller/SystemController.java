package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import globalvar.GlobalVar;
import view.View;
import model.ResponseQA;
import model.SurveyResponsesCollection;

/**
 * Main controller of the system
 * 
 * @author Alexandru
 *
 */
public class SystemController implements GlobalVar {

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

		// HashMap<String, ArrayList<Boolean>> typesAssigned =
		// view.getTypesAssigned(structure);

	}

	public void displaySurveys() {
		int count = 0;
		List<ResponseQA> temp = allResponses.getSurveys();
		for (ResponseQA item : temp) {
			view.printLog(item.getQuestion() + " || "
					+ item.getAnswers().get(0));
			
			count++;
			if(count>=structure.size()){
			System.out.println("\n\n");
			count=0;
			}
		}

		System.out.println("\n\n");
	}

	public static void main(String[] args) {
		SystemController controller = new SystemController();

		controller.readSurveys();
		controller.displaySurveys();

	}

}

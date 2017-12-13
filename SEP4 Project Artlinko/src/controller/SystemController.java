package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import config.GlobalVar;
import config.StructDefinitionElements;
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
	private List<String> structureID;
	private SurveyResponsesCollection allResponses;
	private List<List<String>> allData = null;

	public SystemController() {
		this.view = new View();
		this.allResponses = new SurveyResponsesCollection();
		structureID= new ArrayList<String>();
	}

	
	/**
	 * Builds up the survey structure in local memory as objects of type ResponseQA 
	 */
	public void readSurveys() {

		// read all data from file
		try{
		allData = CSVHELPER.readSurveys();
		  
		}
		catch(Exception E){
			//HANDLE READING EXCEPTION FROM FILE AND DISPLAY IN GUI
		  	}
		
		// retrieve structure and remove from extract 
		this.structure = CSVHELPER.getSurveyStructure(allData);
		allData.remove(0);	
		
		//Assign extract all id
		String entireSurveyID = IDGen.generateId();
		
		//Assign ids to all question of the structure
		List<String> strucureIdCollection = IDGen.assignIdToStruct(structure);
		
		// Process data into ResponseQA objects
		boolean structComplete=false;
		for (List<String> subList : allData) {
			
			//Assign ID to each row
			String surveyInstaceID = IDGen.generateId();

 			for (int i = 0; i < subList.size(); i++) {
				ArrayList<String> answers = new ArrayList<String>();
				
				// Response validator adds n/a into blanc cells
				DataValidator.fixEmptyResponses(answers, subList.get(i));	
				
				//Question validator removes question refference before ":"
				String procQuestion= DataValidator.fixQuestionBody(structure.get(i));
				
				//Finalize and add to all responses
				ResponseQA resp= new ResponseQA(answers, procQuestion,
						strucureIdCollection.get(i),
						surveyInstaceID, entireSurveyID);
				allResponses.addSurvey(resp);
				
				if(!structComplete)
				structureID.add(i,strucureIdCollection.get(i));
			}
 			structComplete=true;
		}

		// assign types to e
		view.getTypesAssigned(structure,allResponses);
	}
	
	

	/**
	 * Builds the strings for displaying the structure (requested by the view to show in the list)
	 * @return
	 */
	public List<String> getStructForView(){
		List<String> ret= new ArrayList<String>();
		String temp ="";
		for(int i=0; i<structureID.size(); i++){
			//add question id
			temp= allResponses.getColumn(structureID.get(i)).get(0).getQuestionID()+":";
			//add question body
			temp+=allResponses.getColumn(structureID.get(i)).get(0).getQuestion()+":";
			//add answer body
			temp+=allResponses.getColumn(structureID.get(i)).get(0).getAnswers().get(0)+":";
			//add each property value
			temp+=allResponses.getColumn(structureID.get(i)).get(0).getProperty(TYPES[0])+":";
			temp+=allResponses.getColumn(structureID.get(i)).get(0).getProperty(TYPES[1])+":";
			temp+=allResponses.getColumn(structureID.get(i)).get(0).getProperty(TYPES[2])+":";
			temp+=allResponses.getColumn(structureID.get(i)).get(0).getProperty(TYPES[3])+":";
			temp+=allResponses.getColumn(structureID.get(i)).get(0).getProperty(TYPES[4])+":";
			temp+=allResponses.getColumn(structureID.get(i)).get(0).getProperty(TYPES[6])+":";
			temp+=allResponses.getColumn(structureID.get(i)).get(0).getProperty(TYPES[5]);
			ret.add(temp);
		}
		return ret;
	}
	
	
	/**
	 * Method assigns values to the properties in each response
	 * @param ans
	 */
	public void saveNewProperties(List<String> ans){
		for(int i=0; i<ans.size(); i++){
			for(ResponseQA item : allResponses.getColumn(ans.get(i).split(":")[0])){
				String [] temp=ans.get(i).split(":");
				item.setProperty(TYPES[0], Boolean.getBoolean(temp[3]));
				item.setProperty(TYPES[1], Boolean.getBoolean(temp[4]));
				item.setProperty(TYPES[2], Boolean.getBoolean(temp[5]));
				item.setProperty(TYPES[3], Boolean.getBoolean(temp[6]));
				item.setProperty(TYPES[4], Boolean.getBoolean(temp[7]));
				item.setProperty(TYPES[5], Boolean.getBoolean(temp[8]));
				item.setProperty(TYPES[6], Boolean.getBoolean(temp[9]));
				item.setOtherColumnName(temp[10]);
			}
		}
	}
	
	
	///TEST OUTPUT SURVEYS
	public void displaySurveys() {
		int count=0;
		for (int i=0; i<allResponses.size(); i++) {
			System.out.println(allResponses.getSurvey(i).toString());
		}
	}
	
	//TEST RETURN READING RESULTS TO BE CHECKED
	public SurveyResponsesCollection getAllResponsesCollection(){
	return allResponses;
	}
	

	//TEST MAIN
	public static void main(String[] args) {
		SystemController controller = new SystemController();

		controller.readSurveys();
		controller.displaySurveys();

	}

}

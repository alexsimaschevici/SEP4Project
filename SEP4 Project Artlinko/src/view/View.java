package view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import model.ResponseQA;
import model.SurveyResponsesCollection;
import config.GlobalVar;
import config.StructDefinitionElements;
import controller.SystemController;


public class View implements GlobalVar, StructDefinitionElements{

	
	Calendar cal;
  
	
	public View(){
		this.cal = Calendar.getInstance();
	}
	
    
	public void printLog(String msg){
		  // You cannot use Date class to extract individual Date fields
	    int year = cal.get(Calendar.YEAR);
	    int month = cal.get(Calendar.MONTH);      // 0 to 11
	    int day = cal.get(Calendar.DAY_OF_MONTH);
	    int hour = cal.get(Calendar.HOUR_OF_DAY);
	    int minute = cal.get(Calendar.MINUTE);
	    int second = cal.get(Calendar.SECOND);
		System.out.printf("Log [%4d/%02d/%02d %02d:%02d:%02d] %s \n" ,  // Pad with zero
          year, month+1, day, hour, minute, second, msg);
	}


	public void getTypesAssigned(List<String> structure,
			SurveyResponsesCollection allResponses) {
		// TODO Auto-generated method stub
		
		
		int count=0;
		for (int j=0; j<allResponses.size(); j++){
		   if (count <=1){
				allResponses.getSurvey(j).setProperty(SURVEY, true);
				allResponses.getSurvey(j).setProperty(GENERAL, true);
		   }
		   else if (count>1 && count<=9){
			   allResponses.getSurvey(j).setProperty(SURVEY, true);
				allResponses.getSurvey(j).setProperty(INSTANCE, true);
		   }
		   
		   else if(count>9 && count<=11){
			   allResponses.getSurvey(j).setProperty(QUESTION, true);
				allResponses.getSurvey(j).setProperty(OTHER, true);
		   }
		   else if(count>11 && count<=17){
			   allResponses.getSurvey(j).setProperty(QUESTION, true);
				allResponses.getSurvey(j).setProperty(SGD, true);
				allResponses.getSurvey(j).setOtherColumnName("TEST");
		   }
		   else if(count>25 && count<=100){
			   allResponses.getSurvey(j).setProperty(PERSON, true);
		   }
		   count++;
		   if(count>=structure.size())
			   count=0;
		   
	}
	}
	

	
}


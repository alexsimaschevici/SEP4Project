package view;

import globalvar.GlobalVar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import controller.SystemController;


public class View implements GlobalVar{

	
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


	public List<ArrayList<Boolean>> getTypesAssigned(List<String> structure) {
		// TODO Auto-generated method stub

		List<ArrayList<Boolean>> prepCollection = new ArrayList<ArrayList<Boolean>>();
		
		for (int j=0; j<structure.size(); j++){
			
			ArrayList<Boolean> tempBool = new ArrayList<Boolean>();
			if (j < 20)
				for (int i = 0; i < 7; i++) {
					if (i == 0)
						tempBool.add(true);
					else
						tempBool.add(false);
				}

			else
				for (int i = 0; i < 7; i++) {
					if (i == 1)
						tempBool.add(true);
					else if (i == 3)
						tempBool.add(true);

					else
						tempBool.add(false);
				}
			prepCollection.add(tempBool);
	}
		return prepCollection;
	}
	

	
}


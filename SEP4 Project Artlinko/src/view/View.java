package view;

import java.util.Calendar;


public class View{

	
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
	
}


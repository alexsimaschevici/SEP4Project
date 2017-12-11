package globalvar;

import java.util.ArrayList;
import java.util.Arrays;

import services.CSVHelper;

public interface GlobalVar {
	 public final String TESTPATH= "C:\\SCHOOL\\SEP4\\Resources\\Surveys\\Original_data.csv";
	 public CSVHelper CSVHELPER = new CSVHelper();
	 public ArrayList<String> CURRENCYLIST = new ArrayList<>(Arrays.asList("$", "kr"));
}

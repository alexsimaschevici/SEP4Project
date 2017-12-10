package globalvar;

import java.util.ArrayList;
import java.util.Arrays;

import services.CSVHelper;

public interface GlobalVar {
 public final String TESTPATH= "C:\\Users\\Cristi\\Documents\\Course Material\\SEM4\\SEP4\\SEP4D\\Original_data.csv";
 public CSVHelper CSVHELPER = new CSVHelper();
 public ArrayList<String> CURRENCYLIST = new ArrayList<>(Arrays.asList("$", "kr"));
}

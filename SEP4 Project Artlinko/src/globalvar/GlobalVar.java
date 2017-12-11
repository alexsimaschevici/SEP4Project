package globalvar;

import java.util.ArrayList;
import java.util.Arrays;

import model.ResponseQA;
import services.CSVHelper;

public interface GlobalVar {
 public final String TESTPATH= "C:\\Users\\Cristi\\Documents\\Course Material\\SEM4\\SEP4\\SEP4D\\Original_data.csv";
 public CSVHelper CSVHELPER = new CSVHelper();
 public ArrayList<String> CURRENCYLIST = new ArrayList<>(Arrays.asList("$", "kr"));
 public ArrayList<String> ANSWERS  = new ArrayList<>(Arrays.asList("$100,000-$150,000", "$75,000-$100,000", "under $50,000"));
 public ResponseQA RESPQA = new ResponseQA(ANSWERS, "What's your anual income?", "inc01", "siid2", "esid01");
}

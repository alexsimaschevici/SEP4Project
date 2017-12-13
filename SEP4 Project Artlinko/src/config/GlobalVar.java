package config;

import java.util.ArrayList;
import java.util.Arrays;

import model.ResponseQA;
import services.CSVHelper;

/**
 * Global variables
 * @author Alexandru
 *
 */
public interface GlobalVar {
// public final String TESTPATH= "C:\\SCHOOL\\SEP4\\Resources\\Surveys\\Original_data.csv";
 public final String TESTPATH= "C:\\Users\\Cristi\\Documents\\Course Material\\SEM4\\SEP4\\SEP4D\\Original_data.csv";
 public CSVHelper CSVHELPER = new CSVHelper();
 public ArrayList<String> CURRENCYLIST = new ArrayList<>(Arrays.asList("$", "kr"));
 
 ArrayList<String> ANSWERS = new ArrayList<String>(Arrays.asList("$100,000-$150,000", "under $75,000", "$75,000-$100,000")); 
 public ResponseQA RESPQA = new ResponseQA(ANSWERS, "Question text", "qid1", "sid01", "esid01"); 
 
}

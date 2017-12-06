package services;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.util.List;
import java.util.Vector;



public class CSVHelper implements TestingVariables
{
    public static void writeLine(Writer w, List<String> values) 
        throws Exception
    {
        boolean firstVal = true;
        for (String val : values)  {
            if (!firstVal) {
                w.write(",");
            }
            w.write("\"");
            for (int i=0; i<val.length(); i++) {
                char ch = val.charAt(i);
                if (ch=='\"') {
                    w.write("\"");  //extra quote
                }
                w.write(ch);
            }
            w.write("\"");
            firstVal = false;
        }
        w.write("\n");
    }
 
    /**
    * Returns a null when the input stream is empty
    */
    public static List<String> parseLine(Reader r) throws Exception {
        int ch = r.read();
        while (ch == '\r') {
            ch = r.read();
        }
        if (ch<0) {
            return null;
        }
        Vector<String> store = new Vector<String>();
        StringBuffer curVal = new StringBuffer();
        boolean inquotes = false;
        boolean started = false;
        while (ch>=0) {
            if (inquotes) {
                started=true;
                if (ch == '\"') {
                    inquotes = false;
                }
                else {
                    curVal.append((char)ch);
                }
            }
            else {
                if (ch == '\"') {
                    inquotes = true;
                    if (started) {
   // if this is the second quote in a value, add a quote
   // this is for the double quote in the middle of a value
                        curVal.append('\"');
                    }
                }
                else if (ch == ',') {
                    store.add(curVal.toString());
                    curVal = new StringBuffer();
                    started = false;
                }
                else if (ch == '\r') {
                    //ignore LF characters
                }
                else if (ch == '\n') {
                    //end of a line, break out
                    break;
                }
                else {
                    curVal.append((char)ch);
                }
            }
            ch = r.read();
        }
        store.add(curVal.toString());
        return store;
    }
    
    
    public static List<List<String>> readData(String filePath) throws Exception {
        List<List<String>> collection = new Vector<List<String>>();
        File fileTemplate = new File(TESTPATH);
        FileInputStream fis = new FileInputStream(fileTemplate);
        Reader fr = new InputStreamReader(fis, "UTF-8");
     
        List<String> values = CSVHelper.parseLine(fr);
        while (values!=null) {
            collection.add( values);
            values = CSVHelper.parseLine(fr);
        }
        fr.close();
        return collection;
    }
    
    
    public static List<String> getSurveyStructure(List<List<String>> list){
    	return list.get(0);
    }
 
    
   /// LIGHT TEST 
//    public static void main(String  [] args){
//    	CSVHelper testobj = new CSVHelper();
//    	List<List<String>> list= null;
//    	try {
//    		list = testobj.readData();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	System.out.println(list.get(0));
//    	
//  
////    	for(List<String> el1: list){
////    		System.out.println(el1);
////    	}
//    	
//    }
    
    
    
}

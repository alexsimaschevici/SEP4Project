package services;

import config.GlobalVar;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.annotation.PostConstruct;

/**
 * Service class that handles the parsing of the csv file
 * 
 * @author Alexandru
 */
public class CSVHelper implements GlobalVar
{

   private String filePath;

/**
    * Parses the lines from the files Returns a null when the input stream is
    * empty
    * 
    * @param r
    * @return
    * @throws Exception
    */
   public List<String> parseLine(Reader r) throws Exception
   {
      int ch = r.read();
      while (ch == '\r')
      {
         ch = r.read();
      }
      if (ch < 0)
      {
         return null;
      }
      Vector<String> store = new Vector<String>();
      StringBuffer curVal = new StringBuffer();
      boolean inquotes = false;
      boolean started = false;
      while (ch >= 0)
      {
         if (inquotes)
         {
            started = true;
            if (ch == '\"')
            {
               inquotes = false;
            }
            else
            {
               curVal.append((char) ch);
            }
         }
         else
         {
            if (ch == '\"')
            {
               inquotes = true;
               if (started)
               {
                  curVal.append('\"');
               }
            }
            else if (ch == ',')
            {
               store.add(curVal.toString());
               curVal = new StringBuffer();
               started = false;
            }
            else if (ch == '\r')
            {
               // ignore LF characters
            }
            else if (ch == '\n')
            {
               // end of a line, break out
               break;
            }
            else
            {
               curVal.append((char) ch);
            }
         }
         ch = r.read();
      }
      store.add(curVal.toString());
      return store;
   }

   /**
    * Reads all the data from the file using the parseLine method
    * 
    * @param filePath
    * @return
    * @throws Exception
    */
   public List<List<String>> readData(String filePath) throws Exception
   {
      List<List<String>> collection = new Vector<List<String>>();
      File fileTemplate = new File(filePath);
      FileInputStream fis = new FileInputStream(fileTemplate);
      Reader fr = new InputStreamReader(fis, "UTF-8");
      List<String> values = parseLine(fr);

      while (values != null)
      {

         collection.add(values);
         values = parseLine(fr);

      }
      fr.close();
      return collection;

   }

   // test stuff for the percentage for the GUI - please ignore it
   public ArrayList<String> getProgressStatus(String filePath) throws Exception
   {
      List<List<String>> collection = this.readData(filePath);
      ArrayList<String> progressData = new ArrayList<>();
      String str = "";
      double progressStatus = 0.00;
      int lineNum = 0;
      double totalLines = (double) collection.size() - 1;

      while (collection.get(lineNum) != null && lineNum < totalLines)
      {
         lineNum++;
         progressStatus = lineNum / totalLines * 100;
         progressData.add(progressStatus + "%");
      
      }

      return progressData;
   }

   /**
    * Returns the first line of the reading of the csv file which represents the
    * structure of the survey
    * 
    * @param list
    * @return
    */
   public List<String> getSurveyStructure(List<List<String>> list)
   {
      return list.get(0);
   }

   public List<List<String>> readSurveys(String path)
   {
	  this.filePath=path; 
      List<List<String>> list = null;
      try
      {
         list = readData(path);
      }
      catch (Exception e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return list;
   }

   // // LIGHT TEST
   // public static void main(String [] args){
   // CSVHelper testobj = new CSVHelper();
   // List<List<String>> list= null;
   // try {
   // list = testobj.readData(TESTPATH);
   // } catch (Exception e) {
   // // TODO Auto-generated catch block
   // e.printStackTrace();
   // }
   // // System.out.println(list.get(0));
   //
   //
   //
   //
   //
   // for(String el: list.get(0)){
   // System.out.println(el);
   // }
   //
   //
   // }
   //

}

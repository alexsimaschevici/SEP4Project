package tests;



import static org.junit.Assert.*;
import globalvar.GlobalVar;

import java.util.List;

import org.junit.Test;

import services.CSVHelper;

public class ParsingTest implements GlobalVar
{

   String elem1 = "Response ID";
   String elem2 = "Panel Company Redirect (Completed)";
   int numbOfRows = 501;
   CSVHelper testobj = new CSVHelper();
   List<List<String>> list = null;

   
   
   /**
    * We're using boundary testing, where we perform a horizontal check,
    * with the first and last name of the columns,
    * as well as the total number of rows - responses PLUS the column names  
    */
   @Test
   public void fetchFirstColNameTest()
   {
      try
      {
         list = testobj.readData(TESTPATH);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      String result = list.get(0).get(0);

      assertEquals(elem1, result);
   }
   
   @Test
   public void fetchLastColNameTest() {
      try
      {
         list = testobj.readData(TESTPATH);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      String result = list.get(0).get(list.get(0).size()-1);

      assertEquals(elem2, result);
   }
   
   @Test
   public void checkNumbOfRows(){
      try
      {
         list = testobj.readData(TESTPATH);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      int result = list.size();

      assertEquals(numbOfRows, result);
   }

}

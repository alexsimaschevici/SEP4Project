package tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import services.CSVHelper;
import services.TestingVariables;

public class ParsingTest implements TestingVariables
{

   String elem1 = "Response ID";
   String elem2 = "Panel Company Redirect (Completed)";
   int numbOfRows = 501;
   List<List<String>> list = null;

   /**
    * We're using boundary testing, where we perform a horizontal check, with
    * the first and last name of the columns, as well as the total number of
    * rows - responses plus the column names
    */
   @Test
   public void fetchFirstColNameTest()
   {
      try
      {
         list = CSVHelper.readData(TESTPATH);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      String result = list.get(0).get(0);

      assertEquals(elem1, result);
   }

   @Test
   public void fetchLastColNameTest()
   {
      try
      {
         list = CSVHELPER.readData(TESTPATH);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      String result = list.get(0).get(list.get(0).size() - 1);

      assertEquals(elem2, result);
   }

   @Test
   public void checkNumbOfRows()
   {
      try
      {
         list = CSVHELPER.readData(TESTPATH);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      int result = list.size();

      assertEquals(numbOfRows, result);
   }

}

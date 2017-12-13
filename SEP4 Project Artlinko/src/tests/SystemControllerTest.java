package tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;

import model.ResponseQA;
import model.SurveyResponsesCollection;

import org.junit.Before;
import org.junit.Test;

import config.GlobalVar;
import config.StructDefinitionElements;
import controller.SystemController;

public class SystemControllerTest implements StructDefinitionElements,
      GlobalVar
{

   SystemController controller = new SystemController();

   /**
    * Bonded testing of first and last element added into the collection as a
    * result of fetching data. Uses default survey source.
    */
   @Test
   public void testReadSurveys01()
   {
      controller.readSurveys();
      List<ResponseQA> temps = controller.getAllResponsesCollection()
            .getSurveys();
      assertEquals(temps.get(0).getAnswers().get(0), "33");
      assertEquals(temps.get(temps.size() - 1).getAnswers().get(0), "n/a");
   }

   /**
    * Tests the number of fetched elements from default source
    */
   @Test
   public void testReadSurveys02()
   {
      controller.readSurveys();
      int size = controller.getAllResponsesCollection().size();
      assertEquals(size, 50000);
   }
   
   
   /**
 * Tests the correct size of the list of columns with parameters
 */
@Test
   public void testGetStructForView()
   {
	  controller.readSurveys();
      List<String> struct= controller.getStructForView();
      int size = struct.size();
      assertEquals(size, 100);
   }
   
   /**
 * Tests the correct reading and assigning of properties to ResponseQA collection
 */
@Test
   public void testSaveNewProperties()
   {
	  controller.readSurveys();
      List<String> struct= controller.getStructForView();
      int size = struct.size();
      assertEquals(controller.saveNewProperties(struct),50000);
   }

}

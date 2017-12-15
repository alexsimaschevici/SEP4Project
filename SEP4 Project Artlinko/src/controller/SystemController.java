package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import config.GlobalVar;
import config.StructDefinitionElements;
import services.DataValidator;
import services.IDGen;
import view.View;
import view.WelcomeWindow;
import loadLogic.LoadToDatabaseStrategy1;
import model.ResponseQA;
import model.SurveyResponsesCollection;

/**
 * Main controller of the system
 * 
 * @author Alexandru
 */

/**
 * @author Cristi
 */
public class SystemController implements GlobalVar, StructDefinitionElements
{

   private View tView;
   private WelcomeWindow view;
   private static List<String> structure = null;
   private List<String> structureID;
   private SurveyResponsesCollection allResponses;
   private List<List<String>> allData = null;

   public SystemController()
   {
      this.tView = new View();
    //  this.view = new WelcomeWindow(this);
      this.allResponses = new SurveyResponsesCollection();
      structureID = new ArrayList<String>();
   }

   public SystemController(String no)
   {
      this.tView = new View();
      this.allResponses = new SurveyResponsesCollection();
      structureID = new ArrayList<String>();
   }

   /**
    * Builds up the survey structure in local memory as objects of type
    * ResponseQA
    */
   public void readSurveys(String path)
   {

      try
      {
         allData = CSVHELPER.readSurveys(path);
      }
      catch (Exception E)
      {
      }

      this.structure = CSVHELPER.getSurveyStructure(allData);
      allData.remove(0);

      String entireSurveyID = IDGen.generateId();

      List<String> strucureIdCollection = IDGen.assignIdToStruct(structure);

      boolean structComplete = false;
      for (List<String> subList : allData)
      {
         String surveyInstaceID = IDGen.generateId();

         for (int i = 0; i < subList.size(); i++)
         {
            ArrayList<String> answers = new ArrayList<String>();

            DataValidator.fixEmptyResponses(answers, subList.get(i));

            String procQuestion = DataValidator.fixQuestionBody(structure
                  .get(i));

            ResponseQA resp = new ResponseQA(answers, procQuestion,
                  strucureIdCollection.get(i), surveyInstaceID, entireSurveyID);
            allResponses.addSurvey(resp);

            if (!structComplete)
               structureID.add(i, strucureIdCollection.get(i));
         }
         structComplete = true;
      }
      tView.getTypesAssigned(structure, allResponses);
   }

   /**
    * Builds the strings for displaying the structure (requested by the view to
    * show in the list)
    * 
    * @return
    */
   public List<String> getStructForView()
   {
      List<String> ret = new ArrayList<String>();
      String temp = "";
      for (int i = 0; i < structureID.size(); i++)
      {
         // add question id
         temp = allResponses.getColumn(structureID.get(i)).get(0)
               .getQuestionID()
               + " : ";
         // add question body
         temp += allResponses.getColumn(structureID.get(i)).get(0)
               .getQuestion()
               + " : ";
         // add answer body
         temp += allResponses.getColumn(structureID.get(i)).get(0).getAnswers()
               .get(0)
               + " : ";
         // add each property value
         temp += allResponses.getColumn(structureID.get(i)).get(0)
               .getProperty(TYPES[0])
               + " : ";
         temp += allResponses.getColumn(structureID.get(i)).get(0)
               .getProperty(TYPES[1])
               + " : ";
         temp += allResponses.getColumn(structureID.get(i)).get(0)
               .getProperty(TYPES[2])
               + " : ";
         temp += allResponses.getColumn(structureID.get(i)).get(0)
               .getProperty(TYPES[3])
               + " : ";
         temp += allResponses.getColumn(structureID.get(i)).get(0)
               .getProperty(TYPES[4])
               + " : ";
         temp += allResponses.getColumn(structureID.get(i)).get(0)
               .getProperty(TYPES[6])
               + " : ";
         temp += allResponses.getColumn(structureID.get(i)).get(0)
               .getProperty(TYPES[5]);
         ret.add(temp);
      }
      return ret;
   }

   /**
    * Method assigns values to the properties in each response
    * 
    * @param ans
    */
   public long saveNewProperties(List<String> ans)
   {
      long count = 0;
      for (int i = 0; i < ans.size(); i++)
      {
         for (ResponseQA item : allResponses
               .getColumn(ans.get(i).split(":")[0]))
         {
            String[] temp = ans.get(i).split(":");
            item.setProperty(TYPES[0], Boolean.getBoolean(temp[2]));
            item.setProperty(TYPES[1], Boolean.getBoolean(temp[3]));
            item.setProperty(TYPES[2], Boolean.getBoolean(temp[4]));
            item.setProperty(TYPES[3], Boolean.getBoolean(temp[5]));
            item.setProperty(TYPES[4], Boolean.getBoolean(temp[6]));
            item.setProperty(TYPES[5], Boolean.getBoolean(temp[7]));
            item.setProperty(TYPES[6], Boolean.getBoolean(temp[8]));
            item.setOtherColumnName(temp[9]);
            count++;
         }
      }
      return count;
   }

   // /TEST OUTPUT SURVEYS
   public void displaySurveys()
   {
      for (int i = 0; i < allResponses.size(); i++)
      {
         System.out.println(allResponses.getSurvey(i).toString());
      }
   }

   // TEST RETURN READING RESULTS TO BE CHECKED
   public SurveyResponsesCollection getAllResponsesCollection()
   {
      return allResponses;
   }

   /**
    * Fetches the structure of the survey and passes it into a String array
    * 
    * @return
    */
   // ///REDUNDANT ???????????????????????????
   public String[] getColNames()
   {
      String[] colName = new String[structure.size()];
      List<String> columns = structure;
      for (int i = 0; i < columns.size(); i++)
      {
         colName[i] = columns.get(i);
      }
      return colName;
   }

   // /TEST AREA
   public static void main(String[] args)
   {
      SystemController controller = new SystemController("no");

      controller
            .readSurveys(TESTPATH);
      /* {PERSON, QUESTION, SURVEY, GENERAL, INSTANCE, SGD, OTHER}; */
      HashMap<String, Boolean> properties = new HashMap<String, Boolean>();
      properties.put(TYPES[0], false);
      properties.put(TYPES[1], false);
      properties.put(TYPES[2], true);
      properties.put(TYPES[3], true);
      properties.put(TYPES[4], false);
      properties.put(TYPES[5], false);
      properties.put(TYPES[6], false);

      List<ResponseQA> SGlist = controller.allResponses
            .getByPropertyHM(properties);

      try
      {
         LoadToDatabaseStrategy1.registerGeneralSurveyBatch(SGlist, structure);
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

   }
}

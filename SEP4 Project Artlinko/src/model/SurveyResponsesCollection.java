package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import config.GlobalVar;
import controller.SystemController;

/**
 * Holds all the questions and responses of a survey from a current responder
 * 
 * @author Alexandru, Cristian
 */


public class SurveyResponsesCollection implements GlobalVar
{

   List<ResponseQA> surveyResp;

   public SurveyResponsesCollection()
   {
      super();
      this.surveyResp = new ArrayList<ResponseQA>();
   }

   public List<ResponseQA> getSurveys()
   {
      return surveyResp;
   }

   public void setSurveys(List<ResponseQA> surveys)
   {
      this.surveyResp = surveys;
   }

   public void addSurvey(ResponseQA oneResp)
   {
      surveyResp.add(oneResp);
   }

   public ResponseQA getSurvey(int pos)
   {
      return surveyResp.get(pos);
   }

   public int size()
   {
      return surveyResp.size();
   }

   /**This method gets all the elements of a column with the corresponding questionID
    * @param questionID
    * @return List<ResponseQA>
    */
   public List<ResponseQA> getColumn(String questionID)
   {
      ArrayList<ResponseQA> list = new ArrayList<ResponseQA>();
      for (ResponseQA elem : surveyResp)
         if (elem.getQuestionID().equals(questionID))
            list.add(elem);
      return list;
   }

   /**This method returns a row from the table, with the corresponding surveyInstanceID
    * @param surveyInstanceID
    * @return List<ResponseQA> 
    */
   public List<ResponseQA> getRow(String surveyInstanceID)
   {
      ArrayList<ResponseQA> list = new ArrayList<ResponseQA>();
      for (ResponseQA elem : surveyResp)
         if (elem.getSurveyInstanceID().equals(surveyInstanceID))
            list.add(elem);
      return list;
   }

   
   /**The method returns all the answers of the same category 
    * @param properties
    * @return
    */
   public List<ResponseQA> getByPropertyHM(HashMap<String, Boolean> properties)
   {
      ArrayList<ResponseQA> list = new ArrayList<ResponseQA>();
      HashMap<String, Boolean> hMap = null;
      for (ResponseQA elem : surveyResp)
      {
         hMap = elem.getAllProperties();
         if (mapsAreEqual(hMap, properties))
            list.add(elem);
      }
      return list;

   }

   
   /**This method returns a list of all the answers that share one property
    * @param propKey
    * @return
    */
   public List<ResponseQA> getByPropertyKey(String propKey)
   {
      ArrayList<ResponseQA> list = new ArrayList<>();
      for (ResponseQA elem : surveyResp)
      {
         if (elem.getProperty(propKey) == true)
            list.add(elem);
      }
      return list;
   }

   /**Assist method to help compare 2 hashmaps
    * @param mapA
    * @param mapB
    * @return
    */
   public static boolean mapsAreEqual(HashMap<String, Boolean> mapA,
         HashMap<String, Boolean> mapB)
   {

      try
      {
         for (String k : mapB.keySet())
         {
            if (!(mapA.get(k) == mapB.get(k)))
            {
               return false;
            }
         }
         for (String y : mapA.keySet())
         {
            if (!mapB.containsKey(y))
            {
               return false;
            }
         }
      }
      catch (NullPointerException np)
      {
         return false;
      }
      return true;
   }


   //TEST 
   public static void main(String[] args)
   {
      
   
      SystemController contr = new SystemController(); 
      contr.readSurveys("");
      SurveyResponsesCollection coll = contr.getAllResponsesCollection(); 
      System.out.println(contr.getStructForView().size());
   
   }

}

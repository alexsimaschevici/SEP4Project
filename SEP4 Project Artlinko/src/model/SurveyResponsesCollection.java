package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import config.GlobalVar;
import config.StructDefinitionElements;
import controller.SystemController;

/**
 * Holds all the questions and responses of a survey from a current responder
 * 
 * @author Alexandru, Cristian
 */

public class SurveyResponsesCollection implements GlobalVar, StructDefinitionElements
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

   // TO BE IMPLEMENTED
   public List<ResponseQA> getColumn(String questionID)
   {
      ArrayList<ResponseQA> list = new ArrayList<ResponseQA>();
      for (ResponseQA elem : surveyResp)
         if (elem.getQuestionID().equals(questionID))
            list.add(elem);
      return list;
   }

   // TO BE IMPLEMENTED
   public List<ResponseQA> getRow(String surveyInstanceID)
   {
      ArrayList<ResponseQA> list = new ArrayList<ResponseQA>();
      for (ResponseQA elem : surveyResp)
         if (elem.getSurveyInstanceID().equals(surveyInstanceID))
            list.add(elem);
      return list;
   }

   // VERSION 1
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

   // VERSION 2
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

   
   /**
    * 
    * Helper method for row retrieval methods
 * @param collection
 * @return
 */
public List<List<ResponseQA>> helperFilter(List<ResponseQA> collection){
   
    List<List<ResponseQA>> resp= new ArrayList<List<ResponseQA>>();
	Set<String> idSet = new HashSet<String>();
	
	for (ResponseQA el : collection) {
		idSet.add(el.getSurveyInstanceID());
	}

	for (String str : idSet) {
		List<ResponseQA> information= new ArrayList<ResponseQA>();
		for (ResponseQA el : collection) {
			if (el.getSurveyInstanceID().equals(str)) {
				information.add(el);
			}
		}
		resp.add(information);
	}
	return resp;
   }
   
   
   /**
    * 
    * Get Person Row collection
 * @return
 */
public List<List<ResponseQA>> getPersonRowCollection(){
	   HashMap<String, Boolean> properties = propertiesDefaultHelp();
		properties.put(TYPES[0], true);
		List<ResponseQA> collection = getByPropertyHM(properties);
		
		return helperFilter(collection);
   }
   

   /**
    * Get survey general type row collection
 * @return
 */
public List<List<ResponseQA>> getSurveyGeneralRowCollection(){
	   HashMap<String, Boolean> properties = propertiesDefaultHelp();
		properties.put(SURVEY, true);
		properties.put(GENERAL, true);
		List<ResponseQA> collection = getByPropertyHM(properties);
	return helperFilter(collection);
   }
   
   

   /**
    *  Get surveys instance type row collection
 * @return
 */
public List<List<ResponseQA>> getSurveyInstanceRowCollection(){
	   HashMap<String, Boolean> properties = propertiesDefaultHelp();
		properties.put(TYPES[2], true);
		properties.put(TYPES[4], true);
		List<ResponseQA> collection = getByPropertyHM(properties);
		
	return helperFilter(collection);
   }
   
   

   /**
    *  Get questions sgd row collection
 * @return
 */
public List<List<ResponseQA>> getQuestionSGDRowCollection(){
	   HashMap<String, Boolean> properties = propertiesDefaultHelp();
		properties.put(TYPES[1], true);
		properties.put(TYPES[5], true);
		List<ResponseQA> collection = getByPropertyHM(properties);
		
	return helperFilter(collection);
   }
   
   
   
   /**
    * Get questions row collection
 * @return
 */
public List<List<ResponseQA>> getQuestionOtherRowCollection(){
	   HashMap<String, Boolean> properties = propertiesDefaultHelp();
		properties.put(TYPES[1], true);
		properties.put(TYPES[6], true);
		List<ResponseQA> collection = getByPropertyHM(properties);
		
	return helperFilter(collection);
   }
   

public HashMap<String, Boolean> propertiesDefaultHelp(){
	HashMap<String, Boolean> properties = new HashMap<String, Boolean>();
	properties.put(TYPES[0], false);
	properties.put(TYPES[1], false);
	properties.put(TYPES[2], false);
	properties.put(TYPES[3], false);
	properties.put(TYPES[4], false);
	properties.put(TYPES[5], false);
	properties.put(TYPES[6], false);
	return properties;
}
   
   
   /**
    * 
    * Extracts the questions structure form the collection
 * @param collection
 * @return
 */
public List<String> extractStructureFromCollection(List<ResponseQA> collection){
	   List<String> resp= new ArrayList<String>();
	   for(ResponseQA item: collection){
		   resp.add(item.getQuestion());
	   }
	   return resp;
   }
   
   
   /**
    * 
    * Returns all ids of all survey instances present in the survey collection
 * @return
 */
public List<String> getEachInstaceID(){
	   Set<String> tmp = new HashSet<String>();
	   List<String> res= new ArrayList<String>();
	   for(ResponseQA el:surveyResp){	 
			   tmp.add(el.getSurveyInstanceID());
	   }
	   for(String el: tmp)
		   res.add(el);
	   return res;
   }
   
   

   //TEST 
//   public static void main(String[] args)
//   {
//      SystemController contr = new SystemController(); 
//      contr.readSurveys("");
//      SurveyResponsesCollection coll = contr.getAllResponsesCollection(); 
//      System.out.println(contr.getStructForView().size());
//   
//   }

}

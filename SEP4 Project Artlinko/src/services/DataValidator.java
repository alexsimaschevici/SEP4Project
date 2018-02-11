package services;

import java.util.ArrayList;
import java.util.List;

import config.GlobalVar;
import model.ResponseQA;

/**
 * @author Alexandru, Cristian
 */
public class DataValidator implements GlobalVar
{

   /**checks if the first element of the answer from a given RsponseQA that's not empty is a double
    * @param response
    * @return
    */
   public boolean isDouble(ResponseQA response)
   {
      int i = 0;
      boolean flag = false;
      for (String elem : response.getAnswers())
      {
         if (elem.equalsIgnoreCase("n/a"))
            i++;
         else
         {
            try
            {
               double d = Double.parseDouble(response.getAnswers().get(i));
               flag = true;
            }
            catch (NumberFormatException nfe)
            {
               flag = false;
            }
         }
      }
      return flag;

   }

    
   /**checks if the ResponseQA is an SGD question-response
    * @param response
    * @return
    */
   public boolean isSGD(ResponseQA response)
   {

      String str = response.getAnswers().get(0);
      str = str.toLowerCase();
      return (str.contains("agree") || str.contains("neutral"));
   }

   /**checks if the answers to the ResponseQA are of format "Other - Write in (Required)"
    * @param response
    * @return
    */
   public boolean isOtherQA(ResponseQA response)
   {
      boolean valid = false;
      String str = "";
      for (int i = 0; i < response.getAnswers().size(); i++)
      {
         str = response.getAnswers().get(i);
         str = str.toLowerCase();
         if (str.contains("other - write in (required)"))

            valid = true;
         else
            valid = false;
      }
      return valid;
   }

   // a method for dealing with the "other" columns ????
   public void cleanOtherCol(ResponseQA response)
   {
      List<String> columnList = CSVHELPER.readSurveys("C:\\SCHOOL\\SEP4\\Resources\\Surveys\\Original_data.csv").get(0);

      if (isOtherQA(response))
      {
         for (String elem : columnList)
         {
            if (response.getQuestion().equals(elem))
            {
               // replace the answers or delete the first ResponseQA column?
            }
         }
      }

   }

   /**checks if the first element of the answer from a given RsponseQA object is a range
    * @param response
    * @return
    */
   public boolean isRange(ResponseQA response)
   {
      boolean valid = false;
      String str = response.getAnswers().get(0);

      if (str.contains("-"))
      {
         String[] arr = str.split("-");
         if (str.contains(" "))
            str = str.replace(" ", "");

         if (arr.length != 2 || arr[0].length() == 0 || arr[1].length() == 0)
         {
            valid = false;

         }
         else if (arr[0].length() == arr[1].length()
               || arr[0].length() + 1 == arr[1].length())
         {
            valid = true;
         }
         else
            valid = false;
      }
      else if (str.startsWith("over") || str.startsWith("less")
            || str.startsWith("lower") || str.startsWith("under"))
         valid = true;
      else
         valid = false;

      return valid;
   }

   
   /**checks if the first element of the answer has currency range values 
    * if that is the case, it returns the 2 values in a long format 
    * as elements of an arraylist
    * @param response
    * @return
    */
   public ArrayList<Long> returnCurrValues(ResponseQA response)
   {
      String str = response.getAnswers().get(0);
      ArrayList<Long> values = new ArrayList<>();

      for (String elem : CURRENCYLIST)
      {
         if (str.contains(elem))
            str = str.replace(elem, "");
      }

      if (str.contains(","))
         str = str.replace(",", "");

      if (str.contains("under"))
      {
         str = str.replace("under", "0");
         String[] arr = str.split(" ");
         values.add(Long.parseLong(arr[0], 10));
         values.add(Long.parseLong(arr[1], 10));
      }

      if (str.contains(" "))
         str = str.replace(" ", "");

      if (str.contains("-"))
      {
         String[] arr = str.split("-");

         if (arr.length != 2)
         {
            System.out.println("error ");

         }
         else if (arr[0].length() == arr[1].length()
               || arr[0].length() + 1 == arr[1].length())
         {
            values.add(Long.parseLong(arr[0], 10));
            values.add(Long.parseLong(arr[1], 10));
         }
      }

      return values;
   }

  
   /**checks if the first element of a non-empty answer from a RsponseQA is a Long
    * @param response
    * @return
    */
   public static boolean isLong(ResponseQA response)
   {
      int i = 0;
      boolean flag = false;
      for (String elem : response.getAnswers())
      {
         if (elem.equalsIgnoreCase("n/a"))
            i++;
         else
         {
            try
            {
               long l = Long.parseLong(response.getAnswers().get(i));
               flag = true;
            }
            catch (NumberFormatException nfe)
            {
               flag = false;
            }
         }
      }
      return flag;
   }

   /**checks if the first element of the answer from a given RsponseQA object is an age
    * @param response
    * @return
    */
   public boolean isAge(ResponseQA response)
   {
      String str = response.getAnswers().get(0);

      if (isRange(response))
      {
         if (str.matches("\\d{2}-\\d{2}"))
            return true;
         else if ((str.matches("under \\d{2}")) || str.matches("over \\d{2}")
               || str.contains("older") || str.contains("younger"))
            return true;
         else
            return false;
      }

      return false;
   }

   public String returnType(ResponseQA response)
   {
      String type = "unknown";

      
      if (this.isLong(response))
         type = "number.long";
      else if (this.isDouble(response))
         type = "number.double";
      else if (this.isAge(response))
         type = "age";
      else if (this.isRange(response))
         type = "range";
      else if (this.isSGD(response))
         type = "SGD value";
      else if (this.isOtherQA(response))
         type = "other write-in";
      else
         type = "text";

      return type;
   }

   // test
   public static void main(String[] args)
   {
      ArrayList<String> answers = new ArrayList<>();
      DataValidator dv = new DataValidator();
      answers.add("");
      answers.add("");
      answers.add("Other - Write in (Required)");
      // ResponseQA res = new ResponseQA(answers, "QUESTION");
      // System.out.println(dv.isOtherQA(RESPQA));

   }

   /**
    * This method fills in n/a where there is no answer present
    * 
    * @param answers
    * @param r
    */
   public static void fixEmptyResponses(ArrayList<String> answers, String r)
   {
      if (r.equals(""))
      {
         answers.add("n/a");
         // System.out.println(structure.get(i)+" || n/a");
      }
      else
      {
         answers.add(r);
         // System.out.println(structure.get(i)+" || "+subList.get(i)+"");
      }
   }

   /**
    * Cleans the question body when reference question body is included inside
    * the actual question and split by ":"
    * 
    * @param q
    */
   public static String fixQuestionBody(String q)
   {
      String ret = q;
      if (q.contains(":"))
      {
         String[] temp = q.split(":");
         ret = temp[1];
      }
      return ret;
   }
}

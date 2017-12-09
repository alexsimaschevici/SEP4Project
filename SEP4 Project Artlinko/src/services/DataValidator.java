package services;

import globalvar.GlobalVar;

import java.util.ArrayList;

import model.ResponseQA;

public class DataValidator implements GlobalVar
{

   // checks if the first element of the answer from a given RsponseQA object is
   // a double
   public boolean isDouble(ResponseQA response)
   {

      String str = response.getAnswers().get(0);

      try
      {
         double d = Double.parseDouble(str);
      }
      catch (NumberFormatException nfe)
      {
         return false;
      }
      return true;

   }

   // checks if the ResponseQA is an SGD question-response
   public boolean isSGD(ResponseQA response){
      
      String str = response.getAnswers().get(0); 
      str = str.toLowerCase(); 
      return (str.contains("agree") || str.contains("neutral"));
   }
   
   
   // checks if the first element of the answer from a given RsponseQA object is
   // a range
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

   // checks if the first element of the answer has currency range values
   // if that is the case, it returns the 2 values in a long format as elements
   // of an arraylist
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

   // checks if the first element of the answer from a given RsponseQA object is
   // a Long
   public boolean isLong(ResponseQA response)
   {

      String str = response.getAnswers().get(0);

      try
      {
         long l = Long.parseLong(str);
      }
      catch (NumberFormatException nfe)
      {
         return false;
      }
      return true;

   }

   // checks if the first element of the answer from a given RsponseQA object is
   // an age
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

   // test
   public static void main(String[] args)
   {
      ArrayList<String> answers = new ArrayList<>();
      DataValidator dv = new DataValidator();
      answers.add("Strongly Agree");
      ResponseQA res = new ResponseQA(answers, "What's the temperature?");
      System.out.println(dv.isSGD(res));
      
   }
  
}

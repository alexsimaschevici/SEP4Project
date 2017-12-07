package services;

import java.util.ArrayList;

import model.ResponseQA;

public class DataValidator
{

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

   public boolean isRange(ResponseQA response)
   {

      String str = response.getAnswers().get(0);
      String[] arr = str.split("-");
      int length1 = arr[0].length();
      int length2 = arr[1].length();

      if ((arr.length != 2) || (length1 == 0) || (length2 == 0))
         return false;
      else if ((length1 == length2) || (length1 + 1 == length2))
         return true;
      else
         return true;
   }
   
   public boolean isLong(ResponseQA response){
      
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

}

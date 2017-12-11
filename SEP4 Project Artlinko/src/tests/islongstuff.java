package tests;

import java.util.ArrayList;
import java.util.Arrays;

import model.ResponseQA;

public class islongstuff
{

   public static void main(String[] args)
   {
      ArrayList<String> ANSWERSLONG = new ArrayList<>(Arrays.asList(" ", "", " ", "10000"));
      ResponseQA RESPQA1 = new ResponseQA(ANSWERSLONG, "Long numb quesiton",
            "que02", "siid3", "esid02");

      System.out.println(isLong(RESPQA1));
   }

   public static boolean isLong(ResponseQA response)
   {
      int i = 0;
      boolean flag = false;
      for (String elem : response.getAnswers())
      {
         if (elem.isEmpty() || elem.equalsIgnoreCase(" "))
            i++;
         else
         {
            try
            {
               long l = Long.parseLong(response.getAnswers().get(i));
               System.out.println(i);
               flag = true;
            }
            catch (NumberFormatException nfe)
            {
               System.out.println(i);
               flag = false;
            }
           
         }
      }
      return flag;

   }
}

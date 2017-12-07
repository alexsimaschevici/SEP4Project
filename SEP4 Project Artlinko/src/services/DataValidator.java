package services;

import model.ResponseQA;

public class DataValidator
{

   public boolean isDouble(ResponseQA response){
      
      String str = response.getAnswers().get(0);
      
        try  
        {  
          double d = Double.parseDouble(str);  
        }  
        catch(NumberFormatException nfe)  
        {  
          return false;  
        }  
        return true;  
      
   }
   
 /*  public static void main(String[] args)
   {
      
      
   //   ResponseQA res = new ResponseQA(answers, question); 
   }
   */
   
}

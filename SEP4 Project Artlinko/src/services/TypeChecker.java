package services;

import model.ResponseQA;

public class TypeChecker
{

  ResponseQA response = new ResponseQA();
    
  public static boolean isNumeric(String str)  
  {  
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
   
}

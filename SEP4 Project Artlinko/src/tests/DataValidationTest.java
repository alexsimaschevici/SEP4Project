package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.ResponseQA;

import org.junit.Test;

import services.DataValidator;

public class DataValidationTest
{
   ArrayList<String> answers = new ArrayList<>();
   boolean validated1 = true;
   boolean validated2 = true; 
   DataValidator dv = new DataValidator();

   @Test
   public void doubleValidation()
   {
      answers.add("-12.5");
      answers.add("-12");
      ResponseQA res = new ResponseQA(answers, "What's the temperature?"); 
      boolean result = dv.isDouble(res);
      assertEquals(validated1, result);
   }
   
   @Test
   public void rangeValidation()
   {
      answers.add("11.2-12.5");
      answers.add("-12");
      ResponseQA res = new ResponseQA(answers, "What's the temperature?"); 
      boolean result = dv.isRange(res);
      assertEquals(validated2, result);
   }

}

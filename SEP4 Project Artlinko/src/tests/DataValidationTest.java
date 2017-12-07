package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.ResponseQA;

import org.junit.Test;

import services.DataValidator;

public class DataValidationTest
{
   ArrayList<String> answers = new ArrayList<>();
   boolean valid = true;
   boolean invalid = true; 
   DataValidator dv = new DataValidator();

   @Test
   public void doubleValidation()
   {
      answers.add("-12.5");
      answers.add("-12");
      ResponseQA res = new ResponseQA(answers, "What's the temperature?"); 
      boolean result = dv.isDouble(res);
      assertEquals(valid, result);
   }
   
   @Test
   public void rangeValidation()
   {
      answers.add("11.2-12.5");
      answers.add("-12");
      ResponseQA res = new ResponseQA(answers, "What's the temperature?"); 
      boolean result = dv.isRange(res);
      assertEquals(valid, result);
   }

   @Test
   public void longValidation()
   {
      answers.add("13225");
      answers.add("-12.3");
      ResponseQA res = new ResponseQA(answers, "What's the temperature?"); 
      boolean result = dv.isLong(res);
      assertEquals(valid, result);
   }
   
}

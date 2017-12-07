package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.ResponseQA;

import org.junit.Test;

import services.DataValidator;

public class DataValidationTest
{
   ArrayList<String> answers = new ArrayList<>();
   boolean validated = false;
   DataValidator dv = new DataValidator();

   @Test
   public void doubleValidation()
   {
      answers.add("-12.5.12");
      answers.add("-12");
      ResponseQA res = new ResponseQA(answers, "What's the temperature?"); 
      boolean result = dv.isDouble(res);
      assertEquals(validated, result);
   }

}

package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import model.ResponseQA;

import org.junit.Test;

import services.DataValidator;

public class DataValidationTest
{
   ArrayList<String> answers = new ArrayList<>();
   boolean valid = true;
   boolean invalid = false;
   ArrayList<Long> valueList = new ArrayList<Long>()
   {
      {
         add((long) 70000);
         add((long) 100000);

      }
   };;

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
      answers.add("over 18");
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

   @Test
   public void currValReturnValidation()
   {
      answers.add("$ 70,000-$ 100,000");
      ResponseQA res = new ResponseQA(answers, "What's your salary?");
      ArrayList<Long> result = dv.returnCurrValues(res);
      assertEquals(valueList, result);
   }

}

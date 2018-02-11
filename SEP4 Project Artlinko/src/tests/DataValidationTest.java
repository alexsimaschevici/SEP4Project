package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import model.ResponseQA;

import org.junit.Test;

import config.GlobalVar;
import services.DataValidator;

public class DataValidationTest implements GlobalVar
{
   ArrayList<String> answers = new ArrayList<>();
   boolean valid = true;
   boolean invalid = false;
   ArrayList<Long> valueList = new ArrayList<Long>()
   {
      {
         add((long) 100000);
         add((long) 150000);

      }
   };

   DataValidator dv = new DataValidator();

   /**We're performing different tests, to check data-types 
    * and/or formats
    */
   @Test
   public void doubleValidation()
   {
      answers.add("-12.5");
      answers.add("-12");
      boolean result = dv.isDouble(RESPQA);
      assertEquals(invalid, result);
   }

   @Test
   public void rangeValidation()
   {
      answers.add("18-24");
      answers.add("-12");
      boolean result = dv.isRange(RESPQA);
      assertEquals(valid, result);
   }

   @Test
   public void longValidation()
   {
      answers.add("-12.3");
      boolean result = dv.isLong(RESPQA);
      assertEquals(invalid, result);
   }

   @Test
   public void currValReturnValidation()
   {
      answers.add("under $70,000");
      ArrayList<Long> result = dv.returnCurrValues(RESPQA);
      assertEquals(valueList, result);
   }
   
   @Test
   public void isSGDValidation(){
      answers.add("Strongly Agree");
      boolean result = dv.isSGD(RESPQA);
      assertEquals(invalid, result);
   }

   
   @Test
   public void isOtherQAValidation(){
      answers.add("");
      answers.add("");
      answers.add("Other - Write in (Required)");
      boolean result = dv.isOtherQA(RESPQA);
      assertEquals(invalid, result);
   }
}

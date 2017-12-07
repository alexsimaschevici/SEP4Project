package tests;

import java.util.ArrayList;
import java.util.List;

import services.TestingVariables;
import model.ResponseQA;

public class ResponseQATest implements TestingVariables
{
   public static void main(String[] args)
   {
      ResponseQA res = new ResponseQA();

      res.attachAnswers(2);

      System.out.println(res.getQuestion());
      System.out.println(res.getAnswers());
      System.out.println(res.valueAt(3));
      System.out.println(res.getQuestions());

      res.addAnswer("21321");
      System.out.println(res.getAnswers());
   }
}

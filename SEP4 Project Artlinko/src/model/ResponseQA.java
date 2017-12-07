package model;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
import services.CSVHelper;
import services.TestingVariables;

public class ResponseQA implements TestingVariables
{

   private ArrayList<String> answers; // extrinsic attribute
   private String question; // intrinsic attribute -> shared
   List<List<String>> list = null;

   public List<List<String>> getList()
   {
      try
      {
         this.list = CSVHELPER.readData(TESTPATH);

      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return list;
   }

   // fetches the questions and places them in a list

   public ArrayList<String> getQuestions()
   {
      this.getList();
      ArrayList<String> columnList = new ArrayList<String>();
      for (String element : list.get(0))
      {
         columnList.add(element);
      }
      return columnList;
   }

   // attaches the String values of all the responses to one question
   // it is the index-th question from the column list
   public void attachAnswers(int index)
   {
      // this.getList();
      ArrayList<String> answerList = new ArrayList<>();

      setQuestion(this.getQuestions().get(index));

      for (int j = 1; j < list.size(); j++)
      {
         answerList.add(list.get(j).get(index));
      }
      setAnswers(answerList);

   }

   // returns the value stored at the index-th element of the answers arraylist
   public String valueAt(int index)
   {
      return getAnswers().get(index);
   }

   // adds new answer to the ResponseQA answer list
   public void addAnswer(String value)
   {
      answers.add(value);
   }

   public ArrayList<String> getAnswers()
   {
      return answers;
   }

   public void setAnswers(ArrayList<String> answers)
   {
      this.answers = answers;
   }

   public String getQuestion()
   {
      return question;
   }

   public void setQuestion(String question)
   {
      this.question = question;
   }
}
=======

/**
 * Model for Response that holds answer or multiple answers to a specific question
 * 
 * @author Alexandru
 *
 */
public class ResponseQA {

	private ArrayList<String> answers;
	private String question;
	public ResponseQA(ArrayList<String> answers, String question) {
		super();
		this.answers = answers;
		this.question = question;
	}
	public ArrayList<String> getAnswers() {
		return answers;
	}
	public void setAnswers(ArrayList<String> answers) {
		this.answers = answers;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answers == null) ? 0 : answers.hashCode());
		result = prime * result
				+ ((question == null) ? 0 : question.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResponseQA other = (ResponseQA) obj;
		if (answers == null) {
			if (other.answers != null)
				return false;
		} else if (!answers.equals(other.answers))
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		return true;
	}
	
	
	
}
>>>>>>> origin/master

package tests;

import static org.junit.Assert.*;

import java.util.List;

import globalvar.StructDefinitionElements;
import model.ResponseQA;
import model.SurveyResponsesCollection;

import org.junit.Before;
import org.junit.Test;

import controller.SystemController;

public class SystemControllerTest implements StructDefinitionElements{


	SystemController controller= new SystemController();
	
	/**
	 * Bonded testing of first and last element added into the collection
	 * as a result of fetching data. Uses default survey source.
	 */
	@Test
	public void testReadSurveys01() {
		controller.readSurveys();
		List<ResponseQA> temps =controller.getAllResponsesCollection().getSurveys();
		assertEquals(temps.get(0).getAnswers().get(0), "33");
		assertEquals(temps.get(temps.size()-1).getAnswers().get(0), "n/a");
	}

	
	/**
	 * Tests the number of fetched elements from default source
	 */
	@Test
	public void testReadSurveys02() {
		controller.readSurveys();
		int size =controller.getAllResponsesCollection().size();
		assertEquals(size, 50000);
	}
}

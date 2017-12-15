package loadLogic;

import java.sql.SQLException;
import java.util.List;

import model.ResponseQA;
import model.SurveyResponsesCollection;

/**
 * Strategy pattern interface that allows easy switch to other storing environment
 * @author Alexandru
 *
 */
public interface ILoadToDatabase {

	public void loadToDatabase(SurveyResponsesCollection collection) throws SQLException;
	public void newGeneralSurveyColumn(List<ResponseQA> collection) throws SQLException;
	public void newSurveyInstanceColumn(List<ResponseQA> collection) throws SQLException;
	public void newPerson(List<ResponseQA> structureInstancesID) throws SQLException;
	public void newGeneralSurvey(SurveyResponsesCollection allResponses) throws SQLException ;
	public void newSurveyInstances(SurveyResponsesCollection collection) throws SQLException;
	public void newSGQ(SurveyResponsesCollection collection) throws SQLException;
	public void newLQ(SurveyResponsesCollection collection) throws SQLException;
	public void newLayerResponse(SurveyResponsesCollection collection) throws SQLException ;
	public void newSGResponse(SurveyResponsesCollection collection) throws SQLException ;
}

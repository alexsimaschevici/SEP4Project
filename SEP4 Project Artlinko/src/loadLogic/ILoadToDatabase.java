package loadLogic;

import java.sql.SQLException;

import model.SurveyResponsesCollection;

/**
 * Strategy pattern interface that allows easy switch to other storing environment
 * @author Alexandru
 *
 */
public interface ILoadToDatabase {

	public void loadToDatabase(SurveyResponsesCollection collection) throws SQLException;
}

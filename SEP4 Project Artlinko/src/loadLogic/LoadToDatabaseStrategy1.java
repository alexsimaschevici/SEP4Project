package loadLogic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dbadaptor.DatabaseAdapter;
import dbadaptor.IDatabaseAdapter;
import model.ResponseQA;
import model.SurveyResponsesCollection;

/**
 * Based on Oracle database created during our project
 * @author Alexandru
 *
 */
public class LoadToDatabaseStrategy1 implements ILoadToDatabase {
	
	
	static IDatabaseAdapter adapter = new DatabaseAdapter();

	@Override
	public void loadToDatabase(SurveyResponsesCollection collection) throws SQLException{
		// TODO Auto-generated method stub
		
		//case general survey
		
		
		
		
		
	}
	
	//build column for general survey 
	public void buildStructColGeneralSurvey(List<ResponseQA> collection) throws SQLException{
		
	for(ResponseQA item: collection)	
		adapter.newGeneralSurveyColumn(item.getQuestion());
	
	}
	
	//build column for survey instance
	public void buildStructColSurveyInstance(List<ResponseQA> collection) throws SQLException{
		
	for(ResponseQA item: collection)	
		adapter.newSurveyInstanceColumn(item.getQuestion());
	
	}

	public static void registerGeneralSurveyBatch(List<ResponseQA> collection,
			List<String> structure) throws SQLException {

		String[] struct = structure.toArray(new String[structure.size()]);

		Set<String> idSet = new HashSet<String>();

		int count = 0;
		for (ResponseQA el : collection) {
			idSet.add(el.getSurveyInstanceID());
		}

		for (String str : idSet) {
			String[] information = new String[structure.size()];
			for (ResponseQA el : collection) {
				if (el.getSurveyInstanceID().equals(str)) {
					information[count] = el.getAnswers().get(0);
					count++;
				}
			}
			count = 0;
//			for (int i = 0; i < information.length; i++) {
//				if (information[i].toString().length() > 15)
//					System.out.print(information[i].toString().substring(0, 15)
//							+ " | ");
//				else
//					System.out.print(information[i].toString() + " | ");
//			}
//			System.out.print("\n");
			adapter.newGeneralSurvey(str, information, struct);
		}

//		for (int i = 0; i < struct.length; i++) {
//			if (struct[i].toString().length() > 15)
//				System.out.print(struct[i].toString().substring(0, 15) + " | ");
//			else
//				System.out.print(struct[i].toString() + " | ");
//		}
//		System.out.print("\n");
	}

}

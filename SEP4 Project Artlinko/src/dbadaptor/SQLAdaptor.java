
public class SQLAdaptor {

	private DatabaseConnection sql;
	
	public SQLAdaptor() throws ClassNotFoundException{
		sql = DatabaseConnection.getInstance();
	}
	
	public void newGeneralSurveyColumn(String column_name){
		if(column_name != "SurveyID")
		{
			//Check if the column exists
			String[] columns = sql.getColumnList("Survey");
			boolean column_exists = false;
			for(int i=0;i<columns.length;i++)
			{
				if(column_name == columns[i])
				{
					column_exists = true;
				}
			}
			if(!column_exists)
			{
				sql.performStatement(
						"ALTER TABLE Survey "
						+ "ADD " + column_name + " VARCHAR2(200);"
						);
			}
		}
	}
	
	public void newSurveyInstanceColumn(String column_name){
		if(column_name != "SurveyID" &&
		   column_name != "PersonID" &&
		   column_name != "SurveyInstanceID")
		{
			//Check if the column exists
			String[] columns = sql.getColumnList("SurveyInstance");
			boolean column_exists = false;
			for(int i=0;i<columns.length;i++)
			{
				if(column_name == columns[i])
				{
					column_exists = true;
				}
			}
			if(!column_exists)
			{
				sql.performStatement(
						"ALTER TABLE SurveyInstance "
						+ "ADD " + column_name + " VARCHAR2(200);"
						);
			}
		}
	}
	
	public void newPerson(String nextPersonID){
		sql.performStatement("INSERT INTO Person (PersonID) VALUES ('" + nextPersonID + "');");
	}
	

	public void newGeneralSurvey(String nextSurveyID, String[] column_names, String[] values){
		
		if(column_names.length == values.length)
		{
			String single_column_name = "";
			String single_value = "";
			for(int i=0;i<values.length;i++)
			{
				column_names[i] = ", '" + column_names[i] + "'";
				values[i] = ", '" + values[i] + "'";
				
				single_column_name = single_column_name + column_names[i];
				single_value = single_value + values[i];
			}
			sql.performStatement("INSERT INTO Survey (SurveyID, " 
								+ single_column_name + ") VALUES ('" +
								nextSurveyID + "'" + single_value + ")"
								);
		}
	}
	
	public void newSurveyInstance(String nextSurveyID, 
								  String nextPersonID,
								  String nextSurveyInstanceID,
								  String[] column_names, 
								  String[] values){
		
		
		if(column_names.length == values.length)
		{
			String single_column_name = "";
			String single_value = "";
			for(int i=0;i<values.length;i++)
			{
				column_names[i] = ", '" + column_names[i] + "'";
				values[i] = ", '" + values[i] + "'";
				
				single_column_name = single_column_name + column_names[i];
				single_value = single_value + values[i];
			}
			sql.performStatement("INSERT INTO SurveyInstance ("
					+ "SurveyID, "
					+ "PersonID, "
					+ "SurveyInstanceID" 
					+ single_column_name + ") "
					+ "VALUES ('" +
					nextSurveyID + "', '" +
					nextPersonID + "', '" +
					nextSurveyInstanceID + "'" 
					+ single_value + ")"
								);
		}
	}
	
	public void newSGQ(String dimension_name, String question_text, String surveyID){
		//To create a new SG question, first find out if the dimension it references exists.
		String targetSGD_ID = sql.findID(""
				+ "SELECT SGD_ID FROM StandardGraphDimension"
				+ "WHERE SGDimension_Name = '" + dimension_name + "';", "SGD_ID");
		//If it doesn't exist, create a record for it.
		if(targetSGD_ID == null)
		{
			targetSGD_ID = "ALEX's Magic NEW ID GENERATOR gives us a new ID for SGD_ID here";
			sql.performStatement(""
					+ "INSERT INTO StandardGraphDimension"
					+ "(SGD_ID, DimensionName) VALUES ('" + 
					targetSGD_ID + "', '" + 
					dimension_name + "');");
		}
		
		//Check if the question already exists
		String targetSGQ_ID = sql.findID(""
				+ "SELECT SGQ_ID FROM StandardGraphQuestion"
				+ "WHERE QuestionText = '" + question_text + "';", "SGQ_ID");
		//If it doesn't exist, create a record for it.
				if(targetSGQ_ID == null)
				{
					targetSGQ_ID = "ALEX's Magic NEW ID GENERATOR gives us a new ID for SGQ_ID here";
					//SAVE THIS ID SOMEWHERE. You'll need it when adding an answer. 
					sql.performStatement(""
							+ "INSERT INTO StandardGraphQuestion"
							+ "(SGQ_ID, SGD_ID, QuestionText) VALUES ('" + 
							targetSGQ_ID + "', '" + 
							targetSGD_ID + "', '" + 
							question_text + "');");
				}
		
		//Check if the question already was connected to this survey
		String targetSGQ_ID_join = sql.findID(""
						+ "SELECT SGQ_ID FROM StandardGraphQuestionsInSurvey"
						+ "WHERE SGQ_ID = '" + targetSGQ_ID + "' "
								+ "AND SurveyID = '" + surveyID + "';", "SGQ_ID");
				//If it doesn't exist, create a record for it.
						if(targetSGQ_ID_join == null)
						{
							sql.performStatement(""
									+ "INSERT INTO StandardGraphQuestionsInSurvey"
									+ "(SurveyID, SGQ_ID) VALUES ('" + 
									surveyID + "', '" + 
									targetSGQ_ID + "');");
						}
	}
	
	
	public void newLQ(String[] categories, String question_text, String surveyID){
		//Check if the question already exists
		String targetLQ_ID = sql.findID(""
				+ "SELECT LQ_ID FROM LayerQuestion"
				+ "WHERE QuestionText = '" + question_text + "';", "LQ_ID");
		//If it doesn't exist, create a record for it.
				if(targetLQ_ID == null)
				{
					targetLQ_ID = "ALEX's Magic NEW ID GENERATOR gives us a new ID for LQ_ID here";
					//SAVE THIS ID SOMEWHERE. You'll need it when adding an answer. 
					sql.performStatement(""
							+ "INSERT INTO LayerQuestion"
							+ "(LQ_ID, QuestionText) VALUES ('" + 
							targetLQ_ID + "', '" + 
							question_text + "');");
				}
		
		//Next, find out if each of the categories it references exists.
		for(int i=0; i<categories.length;i++)
		{
			String targetCategoryID = sql.findID(""
						+ "SELECT CategoryID FROM LayerCategory"
						+ "WHERE CategoryName = '" + categories[i] + "';", "CategoryID");
				//If it doesn't exist, create a record for it.
				if(targetCategoryID == null)
				{
					targetCategoryID = "ALEX's Magic NEW ID GENERATOR gives us a new ID for CategoryID here";
					sql.performStatement(""
							+ "INSERT INTO LayerCategory"
							+ "(CategoryID, CategoryName) VALUES ('" + 
							targetCategoryID + "', '" + 
							categories[i] + "');");
				}
			//Create join record.
				String targetCategoryID_join = sql.findID(""
						+ "SELECT CategoryID FROM fact_LayerQuestionIsInCategory"
						+ "WHERE CategoryID= '" + categories[i] + "' AND "
								+ "LQ_ID= '" + targetLQ_ID + "';", "CategoryID");
				//If it doesn't exist, create a record for it.
				if(targetCategoryID_join == null)
				{
					sql.performStatement(""
							+ "INSERT INTO fact_LayerQuestionIsInCategory"
							+ "(CategoryID, LQ_ID) VALUES ('" + 
							targetCategoryID + "', '" + 
							targetLQ_ID + "');");
				}
		}
				
		//Check if the question already was connected to this survey
		String LQisInSurvey = sql.findID(""
								+ "SELECT LQ_ID FROM LayerQuestionInSurvey"
								+ "WHERE LQ_ID = '" + targetLQ_ID + "'"
										+ "AND SurveyID = '" + surveyID + "';", "LQ_ID");
						//If it doesn't exist, create a record for it.
						if(LQisInSurvey == null)
						{
									sql.performStatement(""
											+ "INSERT INTO LayerQuestionInSurvey"
											+ "(SurveyID, LQ_ID) VALUES ('" + 
											surveyID + "', '" + 
											targetLQ_ID + "');");
						}
	}
	
	public void newLayerResponse(
			String LQ_ID, 
			String answer, 
			String surveyID, 
			String surveyInstanceID,
			String personID,
			String newLR_ID){
		
		//Check if the answer already exists
				String targetLA_ID = sql.findID(""
						+ "SELECT LA_ID FROM LayerAnswer"
						+ "WHERE Answer = '" + answer + "';", "LA_ID");
				//If it doesn't exist, create a record for it.
						if(targetLA_ID == null)
						{
							targetLA_ID = "ALEX's Magic NEW ID GENERATOR gives us a new ID for LQ_ID here";
							sql.performStatement(""
									+ "INSERT INTO LayerAnswer"
									+ "(LA_ID, Answer) VALUES ('" + 
									targetLA_ID + "', '" + 
									answer + "');");
						}
		//Add Answer to Question in General
		String a2q = sql.findID(""
					+ "SELECT LQ_ID FROM fact_LayerAnswerToQuestion"
					+ "WHERE LQ_ID = '" + LQ_ID + "'"
					+ "AND LA_ID = '" + targetLA_ID + "';", "LQ_ID");
		//If it doesn't exist, create a record for it.
		if(a2q == null)
		{
			sql.performStatement(""
					+ "INSERT INTO fact_LayerAnswerToQuestion"
					+ "(LA_ID, LQ_ID) VALUES ('" + 
					targetLA_ID + "', '" + 
					LQ_ID + "');");
		}
				
		//CREATE RESPONSE
		sql.performStatement("INSERT INTO fact_LayerResponse"
				+ "(LR_ID, PersonID, SurveyID, SurveyInstanceID, LQ_ID, LA_ID)"
				+ "VALUES"
				+ "('"
				+ newLR_ID + "', '"
				+ personID +"', '"
				+ surveyID +"', '"
				+ surveyInstanceID +"', '"
				+ LQ_ID +"', '"
				+ targetLA_ID +"'"
				+ ");"
				);
	}
	
	public void newSGResponse(
			String SGQ_ID, 
			String answer, 
			String surveyID, 
			String surveyInstanceID,
			String personID,
			String newSGR_ID){
		
		//Check if the answer already exists
				String targetSGA_ID = sql.findID(""
						+ "SELECT SGA_ID FROM StandardGraphAnswer"
						+ "WHERE Answer = '" + answer + "';", "SGA_ID");
				//If it doesn't exist, create a record for it.
						if(targetSGA_ID == null)
						{
							targetSGA_ID = "ALEX's Magic NEW ID GENERATOR gives us a new ID for SQA_ID here";
							sql.performStatement(""
									+ "INSERT INTO StandardGraphAnswer"
									+ "(SGA_ID, Answer) VALUES ('" + 
									targetSGA_ID + "', '" + 
									answer + "');");
						}
		//Add Answer to Question in General
		String a2q = sql.findID(""
					+ "SELECT SGQ_ID FROM fact_StandardGraphAnswerToQuestion"
					+ "WHERE SGQ_ID = '" + SGQ_ID + "'"
					+ "AND SGA_ID = '" + targetSGA_ID + "';", "SGQ_ID");
		//If it doesn't exist, create a record for it.
		if(a2q == null)
		{
			sql.performStatement(""
					+ "INSERT INTO fact_StandardGraphAnswerToQuestion"
					+ "(SGA_ID, SGQ_ID) VALUES ('" + 
					targetSGA_ID + "', '" + 
					SGQ_ID + "');");
		}
				
		//CREATE RESPONSE
		sql.performStatement("INSERT INTO fact_StandardGraphResponse"
				+ "(SGR_ID, PersonID, SurveyID, SurveyInstanceID, SGQ_ID, SGA_ID)"
				+ "VALUES"
				+ "('"
				+ newSGR_ID + "', '"
				+ personID +"', '"
				+ surveyID +"', '"
				+ surveyInstanceID +"', '"
				+ SGQ_ID +"', '"
				+ targetSGA_ID +"'"
				+ ");"
				);
	}
}

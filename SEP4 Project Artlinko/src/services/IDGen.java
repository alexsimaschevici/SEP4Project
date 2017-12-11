package services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IDGen {

	public static String generateId()
	{
		return UUID.randomUUID().toString();
	}
	
	public static List<String> assignIdToStruct(List<String> structure){
		List<String> ret= new ArrayList<String>();
		for(int i=0; i<structure.size(); i++){
			ret.add(generateId());
		}
		return ret;
	}
}

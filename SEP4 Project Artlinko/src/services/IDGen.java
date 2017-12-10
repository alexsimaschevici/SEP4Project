package services;

import java.util.UUID;

public class IDGen {

	public static String generateId()
	{
		return UUID.randomUUID().toString();
	}
}

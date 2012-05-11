package test;

import logger.RMILogger;

public class ExampleUsage {
	
	public static void main(String[] args) {

		RMILogger log = new RMILogger();
		
		log.setError(0, "DEF Message", true, true);
		
	}
}

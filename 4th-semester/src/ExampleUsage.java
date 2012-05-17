import logger.RMILogger;

public class ExampleUsage {
	
	public static void main(String[] args) {

		// create an object of the log
		RMILogger log = new RMILogger();
		
		/*** OPTIONAL SETTINGS ***/
		/**
		// set a different path for the log file ( default is the location of the program which invokes the log )
		log.setFilePath("c:\\LogFolder\\"); // make sure that the folder already exists
		
		// set a different file name for the log file ( default RMILogger.txt )
		log.setFileName("LogFile.txt");
		
		// set a different path for the configuration file ( default is the location of the program which invokes the log )
		log.setConfPath("c:\\ConfFolder\\"); // make sure that the folder already exists
		
		// set a different file name for the configuration file ( default RMILogger.conf )
		log.setConfFile("ConfFile.conf"); // changing the configuration file may result in loss of the log IDs !!!
		***/
		/*** EXAMPLE HOW TO LOG AN ERROR ***/
		
		try{
			
			Integer.parseInt("ABC");
			
		} catch ( Exception e ) { 
		
			/*** Parameters
			 * 
			 * 1st: warning type ( 0 - Warning Message, 1 - Error Message, 2 - Severe Error Message )
			 * 2nd: Message to be displayed / printed in the log
			 * 3rd: Message on Screen ( true - Display message on screen, false - Do NOT display message on screen )
			 * 4th: Level of details ( true - display / print a full description of the error, false - display / print a summary of the error )
			 * 
			 ***/
			
			log.setError(1, "Error converting to Integer (" + e + ")", true, true);
		
		}
	}
}

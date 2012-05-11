/******************************************************************************************************************************************
 * 
 * author.: Ricardo Masumi Iwashima ( RMI )
 * version: 1.00
 * Date...: 21/04/2012
 * 
 * 	- Type 
 * 		0 - Warning Message 
 * 		1 - Error Message 
 * 		3 - Severe Error Message )
 * 	
 * 	- ID 
 * 		Automatic, last ID stored into RMILogger.conf file
 * 
 * 	- Message
 * 		Message passed when calling the logger
 *  
 *  - Time
 * 		Time in Epoch
 * 
 * 	- Class
 * 		Name of the class that called the logger, it is automatically assigned
 * 
 * - Package
 * 		Name of the package in which the program that called the logger is in
 *  
 *  Simple error logger which:
 *  
 * 	-> Display error ( simple or detailed )
 * 		- Simple ( type of log, ID and Message )
 * 		- Detailed ( type of log, ID, Message, Time, Class and Package )
 * 
 * 	-> Path of the log file and the configuration file can be changed through:
 * 
 *  	- setting up a new path of the log file
 *  		
 *  		setFilePath( path ); <- Where path is the path where the log file should be stored
 *  
 *  	- setting up a new path of the configuration file configuration file
 *  
 *  		setConfFile( path ); <- Where path is the path where the configuration file should be stored
 *  
 *  -> Name of the log file and the configuration file can be changed through:
 *  
 *  	- setting up a new file name of the log file
 *  		
 *  		setFileName( name ); Where name is the name of the file that should be used to record the log ( default RMILogger.txt )
 *  
 * 		- setting up a new file name of the configuration file
 * 
 * 			* * * changing the configuration file may result in loss of the log IDs * * *
 * 
 * 			setConfFile( name ); Where name is the name of the file that should be used to store configuration ( default RMILogger.conf )
 * 			
 * 
 * @param 1st: warning type ( 0 - Warning Message, 1 - Error Message, 2 - Severe Error Message )
 * @param 2nd: Message to be displayed / printed in the log
 * @param 3rd: Message on Screen ( true - Display message on screen, false - Do NOT display message on screen )
 * @param 4th: Level of details ( true - display / print a full description of the error, false - display / print a summary of the error )
 * 
 **********************************************************************************************************************************/


package logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*** RMILogger - display and print log messages ***/
public class RMILogger {
	
	/*** INFORMATION STORED INTO THE CONFFIGURATION FILE ***/
	private String logConfFile = "RMILogger.conf";
	private String logConfPath = ".\\";
	private FileReader logFR; 
	private BufferedReader BFLogger;
	private BufferedWriter BRLogger;
	private BufferedWriter BRConf;
	
	private Throwable t = new Throwable(); 
	private StackTraceElement[] trace = t.getStackTrace(); 

	/*** LOGGER VARIABLES ***/
	private int logID = 0;
	
	private String logFilePath = ".\\";
	private String logFileName = "RMILogger.txt";
	private String[] logType = {"Warning Message", "Error Message", "Severe Error Message"};
	
	/*** START UP - IMPORT CONFIGURATION FILE TO GET LAST ERROR ID ***/
	private void startUp(){
		
		try {
		
			logFR = new FileReader(new File(logConfPath + logConfFile));

			BFLogger = new BufferedReader(logFR);
			
			logID = Integer.parseInt(BFLogger.readLine());
			
			logID++;
			
		} catch (Exception e) {
			
			// IF FILE DOES NOT EXIST ID = 1
			logID = 1;

		}
		
	}
	
	/*** Set up a new path for the log file
	 * 
	 * @param path new path for the log file ( make sure the folder already exists !!! )
	 * 
	 * ***/
	public void setFilePath(String path) { 
		
		this.logFilePath = path;
		
	}
	
	/*** Set up a new name for the log file 
	 * 
	 * @param file new name for the log file ( DEFAULT: RMILogger.txt )
	 * 
	 ***/
	public void setFileName(String file) {
		
		this.logFileName = file;
		
	}

	/*** Set up new path for the configuration file 
	 * 
	 * @param path new path of the file ( make sure the folder already exists  !!! )
	 * 
	 * @see warning *** WARNING *** Changing the path of the configuration file name may result in loss of log IDs
	 * 
	 ***/
	public void setConfPath(String path) {
		
		this.logConfPath = path;
		
	}
	
	/*** Set up a new name for the configuration file
	 * 
	 * @param file new name of the configuration file ( DEFAULT: RMILogger.conf )
	 * 
	 * @see warning *** WARNING *** Changing the name of the configuration file name may result in loss of log IDs 
	 * 
	 ***/
	public void setConfFile(String file) { 
		
		this.logConfFile = file;
		
	}
	
	/*** Method that displays / prints the log messages 
	 * 
	 * @param 1st: warning type ( 0 - Warning Message, 1 - Error Message, 2 - Severe Error Message )
	 * @param 2nd: Message to be displayed / printed in the log
	 * @param 3rd: Message on Screen ( true - Display message on screen, false - Do NOT display message on screen )
	 * @param 4th: Level of details ( true - display / print a full description of the error, false - display / print a summary of the error )
	 *
	 ***/
	public void setError(int type, String msg, boolean scr, boolean det) {
		
		startUp(); // GET A NEW ID
		
		/*** DISPLAY LOG ON SCREEN ***/
		if ( scr ) {
			
			displayError(type, msg, det);
			
		}
		
		/*** PRINT LOG ON FILE ***/
		logError(type, msg, det);
	}
	
	/*** LOG ERRO TO FILE ***/
	private void logError(int type, String message, boolean det) {
		
		startUp();
		
		// GET THE NAME OF THE CLASS WHICH INVOKED THE LOGGER
		String className   = trace[1].getFileName();
		
		// GET THE NAME OF THE PACKAGE WHICH THE CLASS THAT INVOKED THE LOGGER BELONG TO 
		String packageName = trace[1].getClass().getPackage().getPackages()[2].getName();
		
		long epoch = System.currentTimeMillis()/1000;
		
		try{ 

			BRLogger = new BufferedWriter(new FileWriter(logFilePath + logFileName, true ));
			
			/*** WRITE INFORMATION INTO THE FILE ***/
			BRLogger.write(" * * * * " + logType[type] + " * * * * \r\n" );
			BRLogger.write("BugID..: " + logID         + "\r\n");
			BRLogger.write("Message: " + message       + "\r\n");
			
			/*** DETAILED LOG ***/
			if ( det ) {
				
				BRLogger.write("Time...: " + epoch         + "\r\n");
				BRLogger.write("Class..: " + className     + "\r\n");
				BRLogger.write("Package: " + packageName   + "\r\n");
			
			}
			
			BRLogger.write("\r\n");
			
			BRLogger.close();
			
			confFile();
			
		} catch (Exception e) { 
			
			System.out.println(e);
			
		}
	}
	
	/*** LOG ERRO TO SCREEN ***/
	private void displayError(int type, String message, boolean det ) {
		
		// GET THE NAME OF THE CLASS WHICH INVOKED THE LOGGER
		String className   = trace[1].getFileName();
		
		// GET THE NAME OF THE PACKAGE WHICH THE CLASS THAT INVOKED THE LOGGER BELONG TO 
		String packageName = trace[1].getClass().getPackage().getPackages()[2].getName();
		
		long epoch = System.currentTimeMillis()/1000;

		System.out.print(" * * * * " + logType[type] + " * * * * \n" );
		System.out.print("BugID..: " + logID         + "\n");
		System.out.print("Message: " + message       + "\n");
		
		/*** DETAILED LOG ***/
		if ( det ) {
			
			System.out.print("Time...: " + epoch         + "\n");
			System.out.print("Class..: " + className     + "\n");
			System.out.print("Package: " + packageName   + "\n\n");
			
		}
	}
	
	/*** WRITE INFORMATION INTO THE CONF FILE ***/
	private void confFile() {
		
		try {
			
			BRConf = new BufferedWriter(new FileWriter(logConfPath + logConfFile));
			
			BRConf.write(String.valueOf(logID));
			
			BRConf.close();
		
		} catch (IOException e) {

			System.out.println(e);
			
		}
	}
}

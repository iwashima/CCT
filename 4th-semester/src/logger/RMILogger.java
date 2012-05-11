/******************************************************************************************************************************************
 * 
 * Author: Ricardo Masumi Iwashima ( RMI )
 * Date..: 21/04/2012
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
 *  	- setting a new path of the log file
 *  		
 *  		setFilePath( path ); <- Where path is the path where the log file should be stored
 *  
 *  	- setting a new path of the configuration file ( RMILogger.conf )
 *  
 *  		setConfFile( path ); <- Where path is the path where the configuration file should be stored
 *  
 *  -> Name of the log file can be changed through:
 *  
 *  	setFileName( name ); Where name is the name of the file that should be used to record the log ( default RMILogger.txt )
 *  
 **********************************************************************************************************************************/


package logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class RMILogger {
	
	/*** INFORMATION STORED INTO THE CONFFIGURATION FILE ***/
	private File logConfFile = new File("RMILogger.conf");
	private String logConfPath = ".";
	private FileReader logFR; 
	private BufferedReader BFLogger;
	private BufferedWriter BRLogger;
	private BufferedWriter BRConf;
	
	private Throwable t = new Throwable(); 
	private StackTraceElement[] trace = t.getStackTrace(); 

	
	/*** LOGGER VARIABLES ***/
	private int logID = 0;
	
	private String logFilePath = ".";
	private String logFileName = "RMILogger.txt";
	private String logMessage;
	private String[] logType = {"Warning Message", "Error Message", "Severe Error Message"};
	
	/*** START UP - IMPORT CONFIGURATION FILE TO GET LAST ERROR ID ***/
	private void startUp(){
		
		try {
		
			logFR = new FileReader(logConfFile);

			BFLogger = new BufferedReader(logFR);
			
			logID = Integer.parseInt(BFLogger.readLine());
			
			logID++;
			
		} catch (Exception e) {
			
			// IF FILE DOES NOT EXIST ID = 1
			logID = 1;

		}
		
	}
	
	/*** SET UP THE PATH OF THE LOG FILE ***/
	public void setFilePath(String path) { 
		
		this.logFilePath = path;
		
	}
	
	/*** SET UP A NEW NAME FOR THE LOG FILE - DEFAULT IS RMILogger.txt ***/
	public void setFileName(String file) {
		
		this.logFileName = file;
		
	}
	
	public void setConfPath(String path) {
		
		this.logConfPath = path;
		
	}
	
	/*** MAIN METHOD FOR DISPLAYING OR PRINTING MESSAGES ***/
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

			BRLogger = new BufferedWriter(new FileWriter(logFilePath + "\\" + logFileName, true ));
			
			/*** WRITE INFORMATION INTO THE FILE ***/
			BRLogger.write(" * * * * " + logType[type] + " * * * * \n" );
			BRLogger.write("BugID..: " + logID         + "\n");
			BRLogger.write("Message: " + message       + "\n");
			
			/*** DETAILED LOG ***/
			if ( det ) {
				
				BRLogger.write("Time...: " + epoch         + "\n");
				BRLogger.write("Class..: " + className     + "\n");
				BRLogger.write("Package: " + packageName   + "\n");
			
			}
			
			BRLogger.write("\n");
			
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
			
			BRConf = new BufferedWriter(new FileWriter(logConfPath + "//" + logConfFile));
			
			BRConf.write(String.valueOf(logID));
			
			BRConf.close();
		
		} catch (IOException e) {

			System.out.println(e);
			
		}
	}
}

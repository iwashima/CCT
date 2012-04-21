package logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class RMILogger {
	
	/*** INFORMATION STORED INTO A CONFFIGURATION FILE ***/
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
			BRLogger.write("Time...: " + epoch         + "\n");
			BRLogger.write("Class..: " + className     + "\n");
			BRLogger.write("Package: " + packageName   + "\n\n");
			
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
		System.out.print("Time...: " + epoch         + "\n");
		System.out.print("Class..: " + className     + "\n");
		System.out.print("Package: " + packageName   + "\n\n");

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

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
	private FileReader logFR; 
	private BufferedReader BFLogger;
	private BufferedWriter BRLogger;
	
	private Throwable t = new Throwable(); 
	private StackTraceElement[] trace = t.getStackTrace(); 

	
	/*** LOGGER VARIABLES ***/
	private int logID = 0;
	
	private String logFilePath;
	private String logFileName = "RMILogger.txt";
	private String logMessage;
	private String[] logType = {"Warning Message", "Error Message", "Severe Error Message"};
	
	/*** START UP - IMPORT CONFIGURATION FILE TO GET LAST ERROR ID ***/
	public RMILogger(){
		
		try {
		
			logFR = new FileReader(logConfFile);

			BFLogger = new BufferedReader(logFR);
			
			logID = Integer.parseInt(BFLogger.readLine());
	
		} catch (Exception e) {
			
			// THERE IS NO NEED TO INFORM ANY ERROR !!!
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
	
	/*** LOG ERRO TO THE FILE ***/
	public void logError(String Message, int ID, int type) {
		
		// GET THE NAME OF THE CLASS WHICH INVOKED THE LOGGER
		String className   = trace[1].getFileName();
		
		// GET THE NAME OF THE PACKAGE WHICH THE CLASS THAT INVOKED THE LOGGER BELONG TO 
		String packageName = trace[1].getClass().getPackage().getPackages()[2].getName();
		
		long epoch = System.currentTimeMillis()/1000;
		
		System.getSecurityManager().getClass().getName();
		
		try{ 
			
			BRLogger = new BufferedWriter(new FileWriter(logFilePath + "\\" + logFileName, true ));
			
			/*** WRITE INFORMATION INTO THE FILE ***/
			BRLogger.write(" * * * " + logType[logType] + )
			BRLogger.write("BugID..:" + logID       + "\n");
			BRLogger.write("Message:" + Message     + "\n");
			BRLogger.write("Time...:" + epoch       + "\n");
			BRLogger.write("Class..:" + className   + "\n");
			BRLogger.write("Package:" + packageName + "\n");
			
		} catch (Exception e) { 
			
			System.out.println(e);
			
		}
	}
}

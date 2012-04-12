import java.io.IOException;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class lab3 {
	
	private String path = "f://error.log";
	
	private Random random = new Random();
	
	private static Logger logger = Logger.getLogger("lab3");
	
	private static FileHandler fh;

	public static void main ( String[] args ) {
		
		new lab3();
		
	}
	
	public lab3() { 
		
		int number_1 = 0;
		int number_2 = 0;
		
		try {
			
			fh = new FileHandler(path);
		
		} catch (IOException e) {

			e.printStackTrace();
		
		}
		
		logger.addHandler(fh);
		
		logger.setLevel(Level.ALL);
		
		logger.info("doing stuff");
		
		for(int x = 0 ; x < 20 ; x++ ) {
		
			number_1 = random.nextInt(100);
			
			try {
				
				number_2 = Integer.parseInt(Integer.toHexString(number_1));
				
			} catch(Exception ex) {
				logger.log(Level.WARNING,"Hexadecimal has a letter !!!",ex);
			}
		
		}
		
		logger.fine("Done !");
		
	}
}
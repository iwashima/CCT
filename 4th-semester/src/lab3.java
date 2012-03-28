import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class lab3 {

	private String path = "g:\\log.txt";
	private File file = new File(path);
	private FileReader FR;
	private BufferedReader BR;
	
	public static void main(String[] args) {
		
		new lab3();
		
	}
	
	public lab3() {
		
		try {
			
			FR = new FileReader(file);
		
		} catch ( Exception e ) { 
			
		}
		
		try { 
			
			BR = new BufferedReader(FR);
			
		} catch ( Exception e ) {
			
		}
	}
}

import static org.junit.Assert.*;
import org.junit.Test;

public class lab5 {

	private int intVar    = 0;
	private String strVar = "";
	
	public static void main(String[] args) {
		
		new lab5();
		
	}
	
	public lab5() {
		
		testInt();
		
		testStr();
		
	}
	
	@Test
	public void testInt() {
		
		// set the integer variable value as the parameter value
		
		intVar = 100;
		
		assertEquals(intVar, methodInt(intVar));

	}
	
	// Receives an integer as parameter and return it
	public int methodInt(int var){
		
		return var;
		
	}

	@Test
	public void testStr() {
		
		// set the string variable as the same as the parameter value
		
		strVar = "ABC";
		
		assertEquals("ABC", methodStr(strVar));

	}
	
	// Receives a string as parameter and return it
	public String methodStr(String var){
		
		return var;
		
	}

}

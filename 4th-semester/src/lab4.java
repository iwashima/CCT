import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class lab4 {

	public static void main(String[] args) {
		
		// listing fields and methods
		try{ 
			
			Class c = Class.forName("simpleProgram");
			
			Method m[] = c.getDeclaredMethods();
			
			Field f[] = c.getDeclaredFields();
			
			// fields
			
			System.out.println("Fields ... ");
			
			for ( int x = 0 ; x < f.length ; x++ ){
				
				System.out.println("  " + ( x + 1 ) + " - " + f[x].toString());
				
			}
			
			System.out.println(" ");
			
			// Methods
			
			System.out.println("Methods ...");
			
			for ( int x = 0 ; x < m.length ; x++ ){
				
				System.out.println("  " + ( x + 1 ) + " - " + m[x].toString());
				
			}
			
		} catch( Throwable e) {
			
			System.err.println(e);
			
		}
		
		// calling a method to sum up 3 values
		try { 
			
			Class c = Class.forName("simpleProgram");
			
			Class types[] =  new Class[3];
			
			types[0] = Integer.TYPE;
			types[1] = Integer.TYPE;
			types[2] = Integer.TYPE;
			
			Method m = c.getMethod("addTotal", types);
			
			// calling a method, passing 3 number as parameter
			simpleProgram simple = new simpleProgram();
			
			Object numbers[] = new Object[3];
			
			numbers[0] = new Integer(9);
			numbers[1] = new Integer(27);
			numbers[2] = new Integer(79);
			
			Object total = m.invoke(simple, numbers);
			
			Integer sum = (Integer)total;
			
			System.out.println(" ");
			
			System.out.println("Summing up ... ");
			
			System.out.println(numbers[0] + " + " + numbers[1] + " + " + numbers[2] + " = " + sum.intValue());
			
		} catch(Throwable e) {
			
			System.err.println(e);
			
		}
	}
}

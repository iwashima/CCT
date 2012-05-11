import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class lab6 {
	
	Connection conn = null;
	ResultSet  res  = null;
	Statement  stmt = null;
	String user     = "root";
	String password = "";
	
	public static void main(String[] args) {

		new lab6();
		
	}
	
	public lab6(){
		
		try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();

		} catch (Exception ex) {

		}

		
		try{ 
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost/2010619?" + 
					"user=" + user + "&password=" + password);
			
			stmt = conn.createStatement();

			res  = stmt.executeQuery("SELECT name FROM clubs");

		} catch( SQLException e ) {
			
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState....: " + e.getSQLState());
			System.out.println("VendorError.: " + e.getErrorCode());
			
		}

		finally {
		    // it is a good idea to release
		    // resources in a finally{} block
		    // in reverse-order of their creation
		    // if they are no-longer needed

		    if (res != null) {
		        try {
		            res.close();
		        } catch (SQLException sqlEx) { } // ignore

		        res = null;
		    }

		    if (stmt != null) {
		        try {
		            stmt.close();
		        } catch (SQLException sqlEx) { } // ignore

		        stmt = null;
		    }
		}
	}

}

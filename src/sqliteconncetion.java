
import java.sql.*;
import javax.swing.JOptionPane;


public class sqliteconncetion {

Connection connection = null;

	
	public static Connection dbConnector(){
		
		try
	      {
	  // create a database connection
			
	  Class.forName("org.sqlite.JDBC");
	  Connection connection = DriverManager.getConnection("jdbc:sqlite:/Users/asheshbyanju/Downloads/checkpoint/src/login.sqlite");
	  JOptionPane.showMessageDialog(null, "connection successful"); 
	  return connection;
	      }catch(Exception e){
	    	  
	    	  JOptionPane.showMessageDialog(null, e); 
	    	  return null;
	      }
		
	}

}

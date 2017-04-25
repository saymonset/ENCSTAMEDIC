package ve.org.bcv.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@Named(value="manejadorDB")
public class ManejadorDB {
	
	public Connection coneccionPool() throws  SQLException {
 
	    String DATASOURCE_CONTEXT = "java:/jdbc/encuestaDSPool";
	    
	    Connection result = null;
	    try {
	      Context initialContext = new InitialContext();
	      if ( initialContext == null){
	    	  System.out.println("JNDI problem. Cannot get InitialContext.");
	      }
	      DataSource datasource = (DataSource)initialContext.lookup(DATASOURCE_CONTEXT);
	      if (datasource != null) {
	        result = datasource.getConnection();
	      }
	      else {
	    	  System.out.println("Failed to lookup datasource.");
	    	 // result= coneccionPoolToTest() ;
	      }
	    }
	    catch ( NamingException ex ) {
	    	System.out.println("Cannot get connection: " + ex);
	    	//  result= coneccionPoolToTest() ;
	    }
	    catch(SQLException ex){
	    	System.out.println("Cannot get connection: " + ex);
	    }
	    return result;
		
	}
}

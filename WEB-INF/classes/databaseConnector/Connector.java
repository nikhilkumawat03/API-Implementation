/**
 * 
 */
package databaseConnector;

import java.sql.*;

public class Connector {
	public static Connection getConnectionURL() {
		Connection con = null;
		System.out.println("Connector accessed");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/MovieDatabase","root","rj45mcq!@N#");
			System.out.println(con.isClosed());
			
		}
		catch(Exception e){
			System.out.println(e);
		}
		return con;  
		
	}

}

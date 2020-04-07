package api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import databaseConnector.Connector;

@Path("/user")
public class LoginAPI {

	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("signin/{userpass}")
	public String userLogin(@PathParam("userpass") String userpass) throws IOException, SQLException{
		
		Connection conn = Connector.getConnectionURL();
		String username = userpass.split(",")[0];	//Getting username from string userpass	
		String password = userpass.split(",")[1];	//Getting password from string userpass
		
		System.out.println("Username is(API): " + username);
		System.out.println("Password is(API): " + password);
		
		//String getUserPass = "SELECT username, password FROM UserData WHERE username LIKE '?'";
		//System.out.println(getUserPass);
		PreparedStatement getInfo = conn.prepareStatement("SELECT username, password FROM UserData WHERE username LIKE ?");
		getInfo.setString(1, username);
		System.out.println(conn.isClosed());
		
		ResultSet userData = getInfo.executeQuery();
		
		String database_username  = null;
		String database_password = null;
		while(userData.next()) {
			
			database_username = userData.getString("username");
			database_password = userData.getString("password");
			
			System.out.println("Database user name:" + database_username);
			System.out.println("Database password: " + database_password);
			
		}
		
		System.out.println(database_username + "\n" + database_password);
		
		if(database_username.equals(username) && database_password.equals(password)) {
			if(database_username.equals("admin@gmail.com"))
				return "admin";
			else
				return "user";
		}
		else
			return "notUser";
	}
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("signup")
	public String userSignup(JSONObject inputJsonObject) throws IOException, SQLException{
		Connection conn = Connector.getConnectionURL();
		
	    String username = (String) inputJsonObject.get("username");
	    String password = (String) inputJsonObject.get("password");
		
		System.out.println("SignUp(API) is called");
		
		System.out.println("Username is: " + username);
		System.out.println("Password is: " + password);
		
		PreparedStatement enterUser = conn.prepareStatement("INSERT INTO UserData Values(?,?)");
		enterUser.setString(1, username);
		enterUser.setString(2, password);
		System.out.println(conn.isClosed());
		
		int recordsUpdates = enterUser.executeUpdate();  
		System.out.println("Records Updates: "+recordsUpdates);

		return username;
	}
}

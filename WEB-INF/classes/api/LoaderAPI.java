package api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import databaseConnector.Connector;

@Path("/load")
public class LoaderAPI {

	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("movie/{movieinfo}")
	public String loadMovieData(@PathParam("movieinfo") String movieinfo) throws IOException, SQLException{
		
		Connection conn = Connector.getConnectionURL();
		
		System.out.println("Loader API Accessed");
		System.out.println("Movie Details: " + movieinfo);
		
		String moviesData[] = movieinfo.split(",");
		
		String insertData = "INSERT INTO MovieData VALUES (?,?,?,?,?,?,?)";
		PreparedStatement getInfo = conn.prepareStatement(insertData);
		getInfo.setString(1, moviesData[0]);
		getInfo.setString(2, moviesData[1]);
		getInfo.setString(3, moviesData[2]);
		getInfo.setString(4, moviesData[3]);
		getInfo.setString(5, moviesData[4]);
		getInfo.setString(6, moviesData[5]);
		getInfo.setString(7, moviesData[6]);
		
		/*******TO GET UPDATED COLUMN********/
		int recordsUpdates = getInfo.executeUpdate();  
		System.out.println("Records Updates: "+recordsUpdates);
		
		return "Data Updates Successfully";
	}
	
	@DELETE
	@Path("remove/{id}")
	public void deleteById(@PathParam("id") String movieName) throws SQLException{
		Connection conn = Connector.getConnectionURL();
		
		System.out.println("Loader API Accessed");
		System.out.println(movieName);
		
		PreparedStatement removeRow = conn.prepareStatement("DELETE FROM MovieData WHERE movie_name like ?");
		removeRow.setString(1, movieName);
		
		int rs = removeRow.executeUpdate();
		System.out.println("Rows affected:" + rs);
	}
	
	@PUT
	@Path("update/{updateMovie}")
	public void updateMovie(@PathParam("updateMovie") String updateMovie) throws SQLException{
		Connection conn = Connector.getConnectionURL();
		
		System.out.println("Update API accessed");
		System.out.println(updateMovie);
		
		String movieName = updateMovie.split(",")[0];
		String description = updateMovie.split(",")[1];
		
		System.out.println("Movie name in API:" + movieName);
		System.out.println("Movie Description:" + description);
		
		PreparedStatement updateRow = conn.prepareStatement("UPDATE MovieData Set movie_title=? WHERE movie_name=?");
		updateRow.setString(1, description);
		updateRow.setString(2, movieName);
		
		int recordsUpdated = updateRow.executeUpdate();
		
		System.out.println("Rows affected: " + recordsUpdated);
	}
	
}
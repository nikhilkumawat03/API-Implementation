<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.Connection" %>
    <%@ page import="java.sql.PreparedStatement" %>
    <%@ page import="databaseConnector.Connector" %>
    <%@ page import="java.sql.ResultSet" %>
    <%
    			Connection conn = Connector.getConnectionURL();
    			PreparedStatement getInfo = conn.prepareStatement("SELECT * FROM MovieData order by viewer_rating DESC");
    			ResultSet userData = getInfo.executeQuery();
    %>
<!DOCTYPE html>
<html>
<head>
<title>Movie Detail</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
  <h2>Movie Detail</h2>
 
  <table class="table">
    <thead class="thead-dark">
      <tr>
        <th>#</th>
        <th>Movie name</th>
        <th>Movie title</th>
        <th>Theater Name</th>
        <th>Theater Address</th>
        <th>Show time</th>
        <th>Movie Genre</th>
        <th>Viewer rating</th>
      </tr>
    </thead>
    <%
    			int i=0;
    			while(userData.next()) {
    				++i;
    				String movie_name = userData.getString("movie_name");
    				String movie_title = userData.getString("movie_title");
    				String theater_name = userData.getString("theater_name");
    				String theater_address = userData.getString("theater_address");
    				String show_time = userData.getString("show_time");
    				String movie_genre = userData.getString("movie_genre");
    				String viewer_rating = userData.getString("viewer_rating");
				%>	
				    <tbody>
				      <tr>
				        <td><%= i %></td>
				        <td><%= movie_name %></td>
				        <td><%= movie_title %></td>
				        <td><%= theater_name %></td>
				        <td><%= theater_address %></td>
				        <td><%= show_time %></td>
				        <td><%= movie_genre %></td>
				        <td><%= viewer_rating %></td>
				      </tr>
				    </tbody>
				<%	
			}
%>
  </table>
</div>
</body>
</html>
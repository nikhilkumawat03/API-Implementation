package dataloader;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * Servlet implementation class Loader
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/loader" })
public class Loader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Loader() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("doPost method called");
		doGet(request, response);
		

		System.out.println("Loader(Servlet) accessed");
		
		String movie_name = request.getParameter("movie_name");
		String movie_title = request.getParameter("movie_title");
		String theater_name = request.getParameter("theater_name");
		String theater_address = request.getParameter("theater_address");
		String show_time = request.getParameter("show_time");
		String movie_genre = request.getParameter("movie_genre");
		String viewer_rating = request.getParameter("viewer_rating");
		
		String info = movie_name+","+movie_title+","+theater_name+","+theater_address+","+show_time+","+movie_genre+","+viewer_rating;
		
		Client client = ClientBuilder.newClient();
		
		WebTarget target = client.target("http://localhost:8080/Movies/rest/load/movie/"+info);
		
		String status = target.request(MediaType.TEXT_HTML).get(String.class);
		
		
		
		System.out.println("Status:" + status);
		
		System.out.println("API Request send");
		
	}

}

package dataloader;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String movieName = request.getParameter("movieName");
		Client client = ClientBuilder.newClient();
		
		WebTarget target = client.target("http://localhost:8080/Movies/rest/load/remove/"+movieName);
		target.request(MediaType.TEXT_HTML).delete();
		
		response.setContentType("text/html");
		String site = new String("http://localhost:8080/Movies/dataLoad.html");
	    response.setStatus(response.SC_MOVED_TEMPORARILY);
	    response.setHeader("Location", site);
		
	}

}

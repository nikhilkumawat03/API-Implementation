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
 * Servlet implementation class Update
 */
@WebServlet("/update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Client client = ClientBuilder.newClient();
		
		String moviename = request.getParameter("moviename");
		String description = request.getParameter("description");
		
		System.out.println(moviename+"\n"+description);
		
		String movieDesc = moviename+","+description;
		
		WebTarget target = client.target("http://localhost:8080/Movies/rest/load/update/"+movieDesc);
		target.request(MediaType.TEXT_HTML).put(Entity.entity(movieDesc, "application/json"));
		
		response.setContentType("text/html");
		String site = new String("http://localhost:8080/Movies/dataLoad.html");
	    response.setStatus(response.SC_MOVED_TEMPORARILY);
	    response.setHeader("Location", site);
	}

}

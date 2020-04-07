package signup;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class Login
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/login" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		
		System.out.println("Username is:(Servlet) " + email);
		System.out.println("Password is:(Servlet) " + pass);
		
		String userpass = email+","+pass;
		
		Client client = ClientBuilder.newClient();
		
		WebTarget target = client.target("http://localhost:8080/Movies/rest/user/signin/"+userpass);
		
		String validation = target.request(MediaType.TEXT_HTML).get(String.class);
		
		System.out.println(validation+"(Servlet)");
		RequestDispatcher rd = null;
		if(validation.equals("admin")) {
			//for admin
			System.out.println("Admin page accessed");
			response.setContentType("text/html");
			String site = new String("http://localhost:8080/Movies/dataLoad.html");
		    response.setStatus(response.SC_MOVED_TEMPORARILY);
		    response.setHeader("Location", site);
		}
		else if(validation.equals("user")) {
			System.out.println("User page accessed");
			response.setContentType("text/html");
			String site = new String("http://localhost:8080/Movies/moviedata.jsp");
		    response.setStatus(response.SC_MOVED_TEMPORARILY);
		    response.setHeader("Location", site);
		}
		else
			System.out.println("User Doesn't Exists");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

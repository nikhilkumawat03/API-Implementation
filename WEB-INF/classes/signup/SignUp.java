package signup;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		System.out.println("doPost(Servlet) is called for signup");
		String email = request.getParameter("username");
		String pass = request.getParameter("pass");
	    String query_url = "http://localhost:8080/Movies/rest/user/signup";
	    JSONObject obj = new JSONObject();
	    
	    obj.put("username", email);
	    obj.put("password", pass);
	    
	    System.out.println(obj.toJSONString());
	    
	    URL url = new URL(query_url);
	    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	    connection.setConnectTimeout(500);
	    connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	    connection.setDoOutput(true);
	    connection.setDoInput(true);
	    connection.setRequestMethod("POST");
	    
	    OutputStream os = connection.getOutputStream();
	    os.write(obj.toJSONString().getBytes("UTF-8"));
	    
	    os.close();
	    
	    //Response from the API
	    Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
	    
	    
		response.setContentType("text/html");
		String site = new String("http://localhost:8080/Movies/index.html");
	    response.setStatus(response.SC_MOVED_TEMPORARILY);
	    response.setHeader("Location", site);
		
	}

}

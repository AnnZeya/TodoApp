package app;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public RegServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void service(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
	// TODO Auto-generated method stub
	
    super.service(request, response);}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		///doPost(request,response);
	}
		
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		String url = "jdbc:mysql://localhost:3306/todo";
	    String username = "root";
	    String password = "root";
	    String sql = "INSERT INTO users (name, email, password) VALUES (?,?,?)";
	    
	    PrintWriter out = response.getWriter();
	    response.setContentType("text/html");
	    
	    try {
          		
            Class.forName("com.mysql.cj.jdbc.Driver");//new
            Connection connection = DriverManager.getConnection(url, username, password);
            
             String n = request.getParameter("name"); 
             String e = request.getParameter("email"); 
             String p = request.getParameter("password");
             
             System.out.println("name:"+n);  //checking
             System.out.println("email:"+e);  //checking
             System.out.println("pwd:"+p);     //checking
             
            // Creating prepared statement
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, n);
            pstmt.setString(2, e);
            pstmt.setString(3, p);
            
           int rs =pstmt.executeUpdate();
           
           
            //here change redirect page
            if(rs> 0) {
            	
            	RequestDispatcher rd = request.getRequestDispatcher("reg.jsp");
            	rd.forward(request, response);
            }
            else {
            	out.println("<font color=red size=18>reg failed!!!<br>");
            	out.println("<a href=login.jsp>Try again!!!</a>");
            }
            // Closing resources
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
        }
	

	}
		
		
		
    }


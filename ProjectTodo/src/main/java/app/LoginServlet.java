package app;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "jdbc:mysql://localhost:3306/todo";
	    String username = "root";
	    String password = "root";
	    String sql = "SELECT id FROM users WHERE email = ? AND password = ?";
	    
	    try {
	    	PrintWriter out = response.getWriter();
	    	response.setContentType("text/html");		
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            
            String e = request.getParameter("email"); 
            String p = request.getParameter("password");
      
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setString(1,e);
            preparedStatement.setString(2,p);
            ResultSet rs = preparedStatement.executeQuery();
            
            if(rs.next()) {
            	
            	int userId = rs.getInt("id");
            	System.out.println("userid retrievved:");  //checking
            	System.out.println(userId);                 //checking
            	// Create session
                HttpSession session = request.getSession();
        // Store userId in session
                session.setAttribute("userId", userId);
                
            	RequestDispatcher rd = request.getRequestDispatcher("projects.jsp");
            	rd.forward(request, response);
            }
            else {
            	out.println("<font color=red size=18>Login failed!!!<br>");
            	out.println("<a href=login.jsp>Try again!!!</a>");
            }
            
	    } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    
	    
	}

}

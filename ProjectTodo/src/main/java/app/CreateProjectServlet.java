package app;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import app.Project;


public class CreateProjectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve project details from the form,Save the project to the database
        try {
            String url = "jdbc:mysql://localhost:3306/todo";
            String username = "root";
            String password = "root";

            PrintWriter out = response.getWriter();
    	    response.setContentType("text/html");
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            
            String title = request.getParameter("title");
            String createdDate = request.getParameter("createdDate"); 
            System.out.println("title:"+title);
            
         // Retrieve userId from session
            HttpSession session = request.getSession();
            int userId = (int) session.getAttribute("userId");
            
            String sql = "INSERT INTO projects (title, created_date, userId) VALUES (?, ?, ?)";
            
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, title);
            statement.setString(2, createdDate);
            statement.setInt(3, userId); 
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new project was inserted successfully!");
                RequestDispatcher rd = request.getRequestDispatcher("projects.jsp");
            	rd.forward(request, response);
            } else {
                System.out.println("Failed to insert new project!");
            }
            
            statement.close();
            connection.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
      
    }
}

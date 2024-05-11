package app;
/*
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import app.Project;

//@WebServlet("/ProjectsServlet")
public class ProjectsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //project list name- projects
    	List<Project> projects = new ArrayList<>();

        // Database connection details
        String url = "jdbc:mysql://localhost:3306/todo";
        String username = "root";
        String password = "root";

        // SQL query to fetch projects
        String sql = "SELECT id, title, created_date FROM projects";
        
       
	    
	    try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
        try {
        		
        	 
        	 Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery();

             System.out.println("hi  db connection ok");
             
             
             
            // Iterate over the result set and create Project objects
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String createdDate = resultSet.getString("created_date");
                 
                System.out.println("title5:"+title);
                
                
                Project proj = new Project(id, title, createdDate);
                projects.add(proj);
            }

            // Pass the list of projects to the JSP page
            request.setAttribute("projects", projects);

            // Forward the request to projects.jsp for display
            RequestDispatcher dispatcher = request.getRequestDispatcher("projects.jsp");
            dispatcher.forward(request, response);
            
            
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors
        }
    }
}
*/


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class ProjectsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/todo";
        String username = "root";
        String password = "root";

     // SQL query to fetch projects per user
        String sql = "SELECT id, title, created_date FROM projects WHERE userId = ?";


        try {
            // Establish database connection
            Connection connection = DriverManager.getConnection(url, username, password);
            
            // Retrieve userId from session newly added
            HttpSession session = request.getSession();
            int userId = (int) session.getAttribute("userId");
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("hi  db connection ok");
            // Forward the result set to the JSP page for display
            request.setAttribute("resultSet", resultSet);

            // Forward the request to projects.jsp for display
            RequestDispatcher dispatcher = request.getRequestDispatcher("projects.jsp");
            dispatcher.forward(request, response);

            // Close resources
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors
        }
    }
}

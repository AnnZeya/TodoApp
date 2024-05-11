package app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class CreateTodoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve parameters from the request
        String description = request.getParameter("description");
        String status = request.getParameter("status");
        String dueDateStr = request.getParameter("dueDate");
        String createdDateStr = request.getParameter("createdDate");
       String  projectTitle ="";
        //fetch the projectid 
        int projectId = Integer.parseInt(request.getParameter("projectId"));
        
        System.out.println("description is :"); //checking
        System.out.println(description);         //checking
        System.out.println("projectid is :");     //checking
        System.out.println(projectId);             //checking
        
        // Parse dates
        Date dueDate = null;
        Date createdDate = new Date();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            dueDate = formatter.parse(dueDateStr);
            if (createdDateStr != null && !createdDateStr.isEmpty()) {
                createdDate = formatter.parse(createdDateStr);
            } else {
                // If createdDateStr is null or empty, use current date
                createdDate = new Date();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        // Insert the new todo into the database
        try {
            String url = "jdbc:mysql://localhost:3306/todo";
            String username = "root";
            String password = "root";
            
            Connection connection = DriverManager.getConnection(url, username, password);
            //inserting data to database
            PreparedStatement statement = connection.prepareStatement("INSERT INTO todos (project_id, description, status, due_date, created_date) VALUES (?, ?, ?, ?, ?)");
            statement.setInt(1, projectId);
            statement.setString(2, description);
            statement.setString(3, status);
            statement.setDate(4, new java.sql.Date(dueDate.getTime()));
            statement.setDate(5, new java.sql.Date(createdDate.getTime()));
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new todo was inserted successfully!");
            }
            
            //fetching title using id
            PreparedStatement sstatement = connection.prepareStatement("SELECT title FROM projects WHERE id = ?");
            sstatement.setInt(1, projectId);
            ResultSet rresultSet = sstatement.executeQuery();
            if (rresultSet.next()) {
                 projectTitle = rresultSet.getString("title");
            }
         
            
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        // Redirect to the todos page for the project
      
       response.sendRedirect("todos.jsp?projectTitle=" + projectTitle);
       
    }
}

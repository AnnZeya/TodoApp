package app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class UpdateProjectTitleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve parameters from the request
        String newProjectTitle = request.getParameter("projectTitle");
        int projectId = Integer.parseInt(request.getParameter("projectId"));
        System.out.println("update project title page");  
        System.out.println("projectid:::::::");
        System.out.println(projectId);
        System.out.println("projecttitle:::::::"+newProjectTitle);
        
        
        // Update the project title in the database
        if (projectId != -1) { // Check if projectId was found
            try {
                String url = "jdbc:mysql://localhost:3306/todo";
                String username = "root";
                String password = "root";
                
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement updateStatement = connection.prepareStatement("UPDATE projects SET title = ? WHERE id = ?");
                updateStatement.setString(1, newProjectTitle);
                updateStatement.setInt(2, projectId);
                
                int rowsUpdated = updateStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Project title updated successfully!");
                }
                
                updateStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        // Redirect back to the todos page
        response.sendRedirect("todos.jsp?projectTitle=" + newProjectTitle);
    }
}

package app;

import java.io.IOException;
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


public class TodoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve project ID from the request parameters
        int projectId = Integer.parseInt(request.getParameter("projectId"));
        
        // Retrieve project title from the database based on project ID
        String projectTitle = "";
        try {
            String url = "jdbc:mysql://localhost:3306/todo";
            String username = "root";
            String password = "root";
            
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement projectStatement = connection.prepareStatement("SELECT title FROM projects WHERE id = ?");
            projectStatement.setInt(1, projectId);
            ResultSet projectResultSet = projectStatement.executeQuery();
            if (projectResultSet.next()) {
                projectTitle = projectResultSet.getString("title");
            }
            projectResultSet.close();
            projectStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        // Set project title in request attribute
        request.setAttribute("projectTitle", projectTitle);
        request.setAttribute("projectId", projectId);
        
        // Forward the request to todos.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("todos.jsp");
        dispatcher.forward(request, response);
    }
}

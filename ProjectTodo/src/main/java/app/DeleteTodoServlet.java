package app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class DeleteTodoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve todo ID from the request
        int todoId = Integer.parseInt(request.getParameter("todoId"));
        String projectTitle = request.getParameter("projectTitle"); // Retrieve projectTitle
        
        System.out.println("todoid is ::");
        System.out.println(todoId);
        
        try {
            String url = "jdbc:mysql://localhost:3306/todo";
            String username = "root";
            String password = "root";
            
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement("DELETE FROM todos WHERE todo_id = ?");
            statement.setInt(1, todoId);
            
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Todo deleted successfully!");
            }
            
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        // Redirect to the todos page
        response.sendRedirect("todos.jsp?projectTitle=" + projectTitle);
    }
}

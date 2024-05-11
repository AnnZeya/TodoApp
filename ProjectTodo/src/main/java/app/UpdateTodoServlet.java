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


public class UpdateTodoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve parameters from the request
        int todoId = Integer.parseInt(request.getParameter("todoId"));
        String projectTitle = request.getParameter("projectTitle");
        String description = request.getParameter("description");
        String dueDate = request.getParameter("dueDate");
        String status = request.getParameter("status");

        try {
            // Establish database connection
            String url = "jdbc:mysql://localhost:3306/todo";
            String username = "root";
            String password = "root";
            Connection connection = DriverManager.getConnection(url, username, password);

            // Prepare SQL statement for updating todo
            String sql = "UPDATE todos SET description = ?, due_date = ?, status = ? WHERE todo_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, description);
            statement.setString(2, dueDate);
            statement.setString(3, status);
            statement.setInt(4, todoId);

            // Execute update operation
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Todo updated successfully!");
            }

            // Close resources
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Redirect back to todos.jsp with updated projectTitle
        response.sendRedirect("todos.jsp?projectTitle=" + projectTitle);
    }
}

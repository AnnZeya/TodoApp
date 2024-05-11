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


public class UpdateTodoStatusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve parameters from the request
        int todoId = Integer.parseInt(request.getParameter("todoId"));
        String newStatus = request.getParameter("status");

        // Update the todo status in the database
        try {
            String url = "jdbc:mysql://localhost:3306/todo";
            String username = "root";
            String password = "root";

            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement("UPDATE todos SET status = ? WHERE todo_id = ?");
            statement.setString(1, newStatus);
            statement.setInt(2, todoId);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Todo status updated successfully!");
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database connection or query errors
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error updating todo status.");
        }
    }
}

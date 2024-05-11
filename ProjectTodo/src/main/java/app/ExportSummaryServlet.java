package app;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;
import java.sql.*;

//@WebServlet("/export-summary")
public class ExportSummaryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get projectId from request parameter
        int projectId = Integer.parseInt(request.getParameter("projectId"));

        // Initialize variables to store project details
        String projectTitle = "";
        int totalTodos = 0;
        int completedTodos = 0;
        StringBuilder pendingTodos = new StringBuilder();
        StringBuilder completedTodo = new StringBuilder();

        // Establish database connection
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo", "root", "root")) {
            // Fetch project details
            String projectQuery = "SELECT title FROM projects WHERE id = ?";
            try (PreparedStatement projectStatement = connection.prepareStatement(projectQuery)) {
                projectStatement.setInt(1, projectId);
                try (ResultSet projectResultSet = projectStatement.executeQuery()) {
                    if (projectResultSet.next()) {
                        projectTitle = projectResultSet.getString("title");
                    }
                }
            }

            // Fetch todos for the project
            String todoQuery = "SELECT description, status FROM todos WHERE project_id = ?";
            try (PreparedStatement todoStatement = connection.prepareStatement(todoQuery)) {
                todoStatement.setInt(1, projectId);
                try (ResultSet todoResultSet = todoStatement.executeQuery()) {
                    while (todoResultSet.next()) {
                        totalTodos++;
                        String description = todoResultSet.getString("description");
                        String status = todoResultSet.getString("status");
                        if (status.equals("completed")) {
                            completedTodos++;
                            completedTodo.append("- [x] ").append(description).append("\n");
                        } else {
                            pendingTodos.append("- [ ] ").append(description).append("\n");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Generate Markdown summary
        String markdownContent = "# " + projectTitle + "\n\n"
                + "## Summary:\n"
                + "- " + completedTodos + " / " + totalTodos + " completed.\n\n"
                + "## Pending Todos\n" + pendingTodos.toString() + "\n"
                + "## Completed Todos\n" + completedTodo.toString() + "\n";

        /*
        // Generate filename for the gist
        String fileName = projectTitle + ".md";

        // Create secret gist
        String gistUrl = GistExporter.createPublicGist(markdownContent, fileName);

        // Redirect to the gist URL
        response.sendRedirect(gistUrl);*/
        
        
        // Generate filename for the markdown file
        String fileName = projectTitle + ".md";
        String directoryPath = System.getProperty("user.home") + "/Desktop/";
        // Write markdown content to a local file
        //String filePath = "/path/to/your/directory/" + fileName;
        String filePath = directoryPath + fileName;
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.println(markdownContent);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            response.getWriter().println("Error: Failed to write summary to file.");
            return;
        }

        // Send response with the path to the local file
        response.getWriter().println("Summary exported successfully. File path: " + filePath);
        //response.sendRedirect("todos.jsp");
    }
        
        
    }


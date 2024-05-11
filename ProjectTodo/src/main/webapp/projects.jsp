<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.*, app.Project" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Todo App</title>
    <style>
        /* Header styling */
        header {
            background-color: #000;
            color: #fff;
            padding: 10px 20px;
            display: flex;
         
            justify-content: space-between;
            align-items: center;
            width: 100%;
        }

        /* Footer styling */
        footer {
            background-color: #000;
            color: #fff;
            padding: 10px 20px;
            text-align: center;
            position: fixed;
            bottom: 0;
            width: 100%;
        }

        /* Center align home */
        .centered {
            text-align: center;
            flex-grow: 1; /* Takes remaining space */
        }

        /* Center align project titles */
        .project-title {
            text-align: center;
        }

        /* Add surrounding space around table columns */
        table {
            margin: 0 auto; /* Center table */
            border-collapse: collapse;
            width: 80%; /* Adjust width as needed */
        }

        th, td {
            padding: 10px;
            text-align: center;
            border: 1px solid #ddd; /* Add border */
            width: 50%;
        }
        
        /* Style for New Project button */
        .new-project-button {
            width: 80%; /* Same width as table */
            margin: 10px auto; /* Center button */
            display: block;
            padding: 10px;
           background-color: #007bff;
          
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
           
         
        
            
    </style>
</head>
<body>
    <!-- Header -->
    <header>
        <div>
            <h1>TodoApp</h1>
        </div>
        <div class="centered">
            <span><h2>Home</h2></span>
        </div>
        <div>
            <a href="reg.jsp" style="color: white; text-decoration: none;"><h2>Logout</h2></a>
        </div>
    </header>

    <h1 class="project-title">Projects</h1>   <!-- projects list -->
    
    <table>
        <thead>
            <tr>
                <th>Title</th>
                <th>Created Date</th>
            </tr>
        </thead>
       <tbody>
            <% 
                try {
                	
                	// Retrieve userId from session
                    HttpSession userSession = request.getSession();
                    int userId = (int) userSession.getAttribute("userId");
                	
                    // Database connection details
                    String url = "jdbc:mysql://localhost:3306/todo";
                    String username = "root";
                    String password = "root";

                    // Connect to the database
                    Connection connection = DriverManager.getConnection(url, username, password);
                  
                 // SQL query to fetch projects per userid
                    String sql = "SELECT id, title, DATE(created_date) AS created_date FROM projects WHERE userId = ?";
                    
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setInt(1, userId); //newly
                    ResultSet resultSet = statement.executeQuery();

                    // Iterate over the result set and display project details
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String title = resultSet.getString("title");
                        String createdDate = resultSet.getString("created_date");
            %>
                        <tr onclick="location.href='todos.jsp?projectTitle=<%= title %>'" style="cursor: pointer;">
                            <td><%= title %></td>
                            <td><%= createdDate %></td>
                        </tr>
            <% 
                    }
                    // Close resources
                    resultSet.close();
                    statement.close();
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            %>
        </tbody>
        
    </table>
     <br>
    
    
    <br> <br> 
   <div style="text-align: center;">
        <form action="CreateProject.jsp" method="get">
            <button type="submit" class="new-project-button">New Project</button>
        </form>
    </div>

    <!-- Footer -->
    <footer>
        &copy; 2024 TodoApp | Ann
    </footer>
    
</body>
</html>

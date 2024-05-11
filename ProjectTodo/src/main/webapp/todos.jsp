<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, app.Project" %>
<%@ page import="app.TodoServlet" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Todo List</title>
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
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
            padding: 5px;
           text-align: center;
            border: 1px solid #ddd; /* Add border */
            width: 10%;
        }
        
        /* Style for New Project button */
        .new-todo-button {
            width: 80%; /* Same width as table */
            margin: 10px auto; /* Center button */
            display: block;
            padding: 10px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
       
       
      /* Style for action buttons */
    .action-buttons form {
        display: inline-block;
        margin-right: 5px;
    }

    .action-buttons .icon-button {
        background: none;
        border: none;
        cursor: pointer;
        padding: 5px;
    }

    .action-buttons .icon-button.update {
        color: #007bff; /* Update button color */
    }

    .action-buttons .icon-button.delete {
        color: #dc3545; /* Delete button color */
    }
   
    </style>
    
    
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- Add any necessary CSS or JavaScript files here -->
</head>
<body>

    <!-- Header -->
    <header>
        
        <div class="left">
            <a href="projects.jsp" style="color: white; text-decoration: none;"><h2>Projects</h2></a>
        </div>
        
    </header>





    <!--  <h1> <a href="projects.jsp">Home</a></h1> --> <!-- to go to projects page -->
    <h1 class="project-title"><%= request.getParameter("projectTitle") %></h1> <!-- todo list for projecttitle -->
    
    
    
    
    <table border="1">
        <thead>
            <tr>
                <th>Todo</th>
                <th class="due-date">Due Date</th>
                <th class="status">Status</th>
                <th class="action">Action</th>
            </tr>
        </thead>
        <tbody>
            <% 
            int projectId = -1;
            String projectTitle = request.getParameter("projectTitle");
                try {
                    // Database connection details
                    String url = "jdbc:mysql://localhost:3306/todo";
                    String username = "root";
                    String password = "root";

                    // Connect to the database
                    Connection connection = DriverManager.getConnection(url, username, password);
                    
                    // Retrieve projectId based on projectTitle
                    String projectIdQuery = "SELECT id FROM projects WHERE title = ?";
                    PreparedStatement projectIdStatement = connection.prepareStatement(projectIdQuery);
                    projectIdStatement.setString(1, projectTitle);
                    ResultSet projectIdResultSet = projectIdStatement.executeQuery();
                    if (projectIdResultSet.next()) {
                        projectId = projectIdResultSet.getInt("id");
                    }
                    projectIdResultSet.close();
                    projectIdStatement.close();

                    // SQL query to fetch todos for the project
                    String sql = "SELECT * FROM todos WHERE project_id = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setInt(1, projectId);
                    ResultSet resultSet = statement.executeQuery();

                    // Iterate over the result set and display todo details
                    while (resultSet.next()) {
                        String description = resultSet.getString("description");
                        String dueDate = resultSet.getString("due_date");
                        String status = resultSet.getString("status");
                        int todoId = resultSet.getInt("todo_id");
            %>
                        <tr>
                           <td >
                                <form>
                                    <input type="checkbox" id="todoCheckbox<%= todoId %>" onchange="toggleStatus(<%= todoId %>)" <% if(status.equals("completed")) { %> checked <% } %>>
                                 <%= description %>
                                </form>
                           </td>
                            <td><%= dueDate %></td>
                            <td id="status<%= todoId %>">
                                <%-- Display the status --%>
                                <%= status %>
                            </td>
                          
                            <td class="action-buttons" align="center"> 
                            <div class="action-buttons">
                                 <form action="UpdateTodo.jsp" method="get">
                                    <input type="hidden" name="todoId" value="<%= resultSet.getInt("todo_id") %>">
                                    <input type="hidden" name="projectTitle" value="<%= projectTitle %>">
                                     <input type="hidden" name="description" value="<%= description %>">
                                    <input type="hidden" name="dueDate" value="<%= dueDate %>">
                                   <input type="hidden" name="status" value="<%= status %>">
                                    <button type="submit" class="icon-button update">
                                    <i class="fas fa-pencil-alt"></i>Update <!-- --></button>
                                </form>
                                
                                
                                <form action="DeleteTodoServlet" method="post">
                                    <input type="hidden" name="todoId" value="<%= resultSet.getInt("todo_id") %>">
                                    <input type="hidden" name="projectTitle" value="<%= projectTitle %>">
                                    <button type="submit" class="icon-button delete"> <i class="fas fa-trash-alt"></i>
                                       Delete </button>
                                </form>   
                                  </div>
                            </td>
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
    
       <div style="text-align: center;">
      <form action="UpdateProjectTitleServlet" method="post">
                           <input type="hidden" name="projectId" value="<%= projectId %>"> <!-- Assuming projectId is available -->
                          
                          <!-- <label for="projectTitle">New Title:</label> -->
                          <input type="text" id="projectTitle" name="projectTitle" placeholder="New Project Title"> <!-- Input field for the new project title -->
                          <button type="submit">Update</button>
      </form>
       </div> <br>
      
      
   <div style="text-align: center;">
    <form action="CreateTodo.jsp" method="get">
       <input type="hidden" name="projectId" value="<%= projectId %>">
        <button type="submit" class="new-todo-button">New Todo</button>
    </form>
 </div>
 
 <!--  secretgist -->
 <form action="ExportSummaryServlet" method="post">
    <input type="hidden" name="projectId" value="<%= projectId %>">
    <button type="submit">Export Summary</button>
</form>
 
      <footer>
        &copy; 2024 TodoApp | Ann
    </footer>
  
  <script>
        function toggleStatus(todoId) {
            var checkbox = document.getElementById('todoCheckbox' + todoId);
            var statusElement = document.getElementById('status' + todoId);
            var newStatus = checkbox.checked ? 'completed' : 'pending';
            statusElement.innerText = newStatus;
            $.ajax({
                url: 'UpdateTodoStatusServlet',
                type: 'POST',
                data: {
                    todoId: todoId,
                    status: newStatus
                },
                success: function(response) {
                    console.log('Status updated successfully.');
                },
                error: function(xhr, status, error) {
                    console.error('Error updating status:', error);
                }
            });
        }
    </script>  

 
</body>
</html>

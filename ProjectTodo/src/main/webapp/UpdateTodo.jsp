<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Todo</title>
    <!-- Add any necessary CSS or JavaScript files here -->
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        
        .container {
            width: 50%;
        }
        
        h1 {
            text-align: center;
        }
        
        form {
            text-align: left;
        }
        
        label {
            display: block;
            margin-bottom: 10px;
        }
        
        input[type="text"],
        input[type="date"],
        button[type="submit"],/* {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            box-sizing: border-box;
        }*/
        select {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            box-sizing: border-box;
            border: 1px solid #ced4da; /* Add border to select */
            border-radius: 5px;
            background-color: #fff; /* Add background color */
            font-size: 16px;
            appearance: none; /* Remove default select arrow */
            cursor: pointer;
        }
        
        /* Style select arrow */
        select::-ms-expand {
            display: none;
        }
        
        select option {
            padding: 10px;
        }
        
        button[type="submit"] {
            background-color: #28a745; /* Green color */
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
    </style>
    
</head>
<body>
<div class="container">
    <h1>Update Todo</h1>
    
    <form action="UpdateTodoServlet" method="post">
        <input type="hidden" name="todoId" value="<%= request.getParameter("todoId") %>">
        <input type="hidden" name="projectTitle" value="<%= request.getParameter("projectTitle") %>">
        <label for="description">Title:</label>
        <input type="text" id="description" name="description" value="<%= request.getParameter("description") %>"><br>
        <label for="dueDate">Due Date:</label>
        <input type="date" id="dueDate" name="dueDate" value="<%= request.getParameter("dueDate") %>"><br>
        <label for="status">Status:</label>
        <select id="status" name="status">
            <option value="pending" <% if ("pending".equals(request.getParameter("status"))) { %> selected <% } %>>Pending</option>
            <option value="completed" <% if ("completed".equals(request.getParameter("status"))) { %> selected <% } %>>Completed</option>
        </select><br>
        <button type="submit">Update</button>
    </form>
     </div>
</body>
</html>

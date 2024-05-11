<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.Date" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create New Todo</title>
    <!-- Add any necessary CSS or JavaScript files here --><style>
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
    <h1>Create Todo</h1>
    
    <form action="CreateTodoServlet" method="post">
        <label for="description">Title:</label>
        <input type="text" id="description" name="description" required><br><br>
        
        <label for="status">Status:</label>
        <select id="status" name="status" required>
            <option value="pending">Pending</option>
            <option value="completed">Completed</option>
        </select><br><br>
        
        <label for="dueDate">Due Date:</label>
        <input type="date" id="dueDate" name="dueDate" required><br><br>
        
       
        <input type="hidden" name="projectId" value="<%= request.getParameter("projectId") %>">
        <button type="submit">Create</button>
    </form>
    
    </div>
</body>
</html>

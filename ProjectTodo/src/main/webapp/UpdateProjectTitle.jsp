<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Project Title</title>
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
            text-align: center;
        }
        
        form {
            width: 100%;
            text-align: center;
        }
        
        h1 {
            text-align: center;
            margin-top: 20px; /* Adjust the margin-top as needed */
        }
        
        label {
            display: block;
            margin-bottom: 10px;
            text-align: left;
        }
        
        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            box-sizing: border-box;
            border: 1px solid #ced4da;
            border-radius: 5px;
            font-size: 16px;
        }
        
        button[type="submit"] {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 5px;
            background-color: #28a745;
            color: white;
            font-size: 16px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Update Project Title</h1>
        
        <form action="UpdateProjectTitleServlet" method="post">
            <input type="hidden" name="projectId" value="<%= request.getParameter("projectId") %>">
            <input type="hidden" name="projectTitle" value="<%= request.getParameter("projectTitle") %>">
            <label for="projectTitle">New Title:</label>
            <input type="text" id="projectTitle" name="projectTitle"><br> <!-- Input field for the new project title -->
            <button type="submit">Update</button>
        </form>
    </div>
</body>
</html>

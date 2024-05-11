<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create New Project</title>
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
        button[type="submit"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            box-sizing: border-box;
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
        <h1>Create Project</h1>
        
        <form action="CreateProjectServlet" method="post">
            <label for="title">Title:</label>
            <input type="text" id="title" name="title" placeholder="Enter project title" required><br><br>
            <label for="createdDate">Created Date:</label>
            <input type="date" id="createdDate" name="createdDate" required><br><br>
            <!-- Add other input fields for project details -->
            <button type="submit">Create</button>
        </form>
    </div>
    
    <script>
        // Get today's date in YYYY-MM-DD format
        const today = new Date().toISOString().split('T')[0];
        // Set the value of the createdDate input field to today's date
        document.getElementById('createdDate').value = today;
    </script>
</body>
</html>

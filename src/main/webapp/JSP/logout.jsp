<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Logged Out - Petroleum Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/JSP/CSS/style.css">
    <style>
        .logout-container {
            background-color: #ffffff;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
            width: 100%;
            max-width: 400px;
            text-align: center;
            margin: 100px auto;
        }

        .logout-container h2 {
            color: #333;
            margin-bottom: 20px;
        }

        .logout-container p {
            color: #666;
            margin-bottom: 30px;
        }

        .logout-container a button {
            padding: 12px 24px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .logout-container a button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<div class="logout-container">
    <h2>You've been logged out.</h2>
    <p>Thank you for using Petroleum Management System.</p>
    <a href="${pageContext.request.contextPath}/JSP/login.jsp">
        <button>Back to Login</button>
    </a>
</div>
</body>
</html>

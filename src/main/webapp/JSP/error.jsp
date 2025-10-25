<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f8d7da; }
        .container { max-width: 500px; margin: 100px auto; padding: 30px; background: #fff; border-radius: 10px; text-align: center; }
        h2 { color: #721c24; }
        p { color: #721c24; }
    </style>
</head>
<body>
    <div class="container">
        <h2>⚠️ An Error Occurred</h2>
        <p><%= request.getAttribute("errorMsg") != null ? request.getAttribute("errorMsg") : "Unknown error" %></p>
        <a href="<%= request.getContextPath() %>/DashboardServlet">Back to Dashboard</a>
    </div>
</body>
</html>

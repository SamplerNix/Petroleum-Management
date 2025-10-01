<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login - Petroleum Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/JSP/CSS/style.css">
</head>
<body>
    <div class="login-container">
        <h2>Petroleum Management Login</h2>
        
        <form method="post" action="${pageContext.request.contextPath}/LoginServlet">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

 <label for="Role">Role</label>
            <input type="text" id="Role" name="Role" required>

            <button type="submit">Login</button>
            <br>
            <br>
            <a href="${pageContext.request.contextPath}/JSP/Signup.jsp">
    <button type="button">Sign Up</button>
</a>
        </form>

        <p>
            ${requestScope.errorMsg != null ? requestScope.errorMsg : ""}
        </p>
    </div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sign Up - Petroleum Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/JSP/CSS/style.css">
</head>
<body>
    <div class="login-container">
        <h2>Create an Account</h2>

        <form method="post" action="${pageContext.request.contextPath}/SignupServlet">
            <label for="full_name">Full Name:</label>
            <input type="text" id="full_name" name="full_name" required>

            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <label for="role">Role:</label>
            <input type="text" id="role" name="role" placeholder="Admin, Manager, Staff" required>

            <button type="submit">Sign Up</button>
        </form>

        <p>
            ${requestScope.errorMsg != null ? requestScope.errorMsg : ""}
        </p>
    </div>
</body>
</html>

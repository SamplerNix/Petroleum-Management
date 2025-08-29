<%@ page language="java" import="java.util.*, model.*" %>
<%@ page session="true" %>
<html>
<head>
    <title>Users</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<script src="../JavaScript/main.js"></script>
<body>
<jsp:include page="navbar.jsp" />

<h2>User Management</h2>

<h3>Add New User</h3>
<form method="post" action="../users">
    <label>Full Name:</label>
    <input type="text" name="full_name" required /><br/><br/>

    <label>Username:</label>
    <input type="text" name="username" required /><br/><br/>

    <label>Password:</label>
    <input type="password" name="password" required /><br/><br/>

    <label>Role:</label>
    <select name="role" required>
        <option value="">-- Select Role --</option>
        <option value="Admin">Admin</option>
        <option value="Manager">Manager</option>
        <option value="Staff">Staff</option>
    </select><br/><br/>

    <input type="submit" value="Add User" />
</form>

<% if (request.getAttribute("message") != null) { %>
    <p style="color:green;"><%= request.getAttribute("message") %></p>
<% } else if (request.getAttribute("error") != null) { %>
    <p style="color:red;"><%= request.getAttribute("error") %></p>
<% } %>

<hr/>

<h3>User List</h3>
<table border="1">
    <tr>
        <th>User ID</th>
        <th>Full Name</th>
        <th>Username</th>
        <th>Role</th>
        <th>Created At</th>
    </tr>
    <%
        List<User> users = (List<User>) request.getAttribute("users");
        if (users != null) {
            for (User u : users) {
    %>
    <tr>
        <td><%= u.getUserId() %></td>
        <td><%= u.getFullName() %></td>
        <td><%= u.getUsername() %></td>
        <td><%= u.getRole() %></td>
        <td><%= u.getCreatedAt() %></td>
    </tr>
    <%   }
        } else { %>
    <tr><td colspan="5">No users found.</td></tr>
    <% } %>
</table>
</body>
</html>

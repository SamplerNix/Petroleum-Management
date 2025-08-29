<%@ page language="java" import="model.User" %>
<%@ page session="true" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || !"Admin".equals(user.getRole())) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<jsp:include page="navbar.jsp" />

<div class="container">
    <h2>Welcome Admin, <%= user.getFullName() %></h2>
    <p>This is your admin dashboard.</p>

    <ul>
        <li><a href="users.jsp">Manage Users</a></li>
        <li><a href="stations.jsp">Manage Stations</a></li>
        <li><a href="reports.jsp">Generate Reports</a></li>
    </ul>
</div>

<script src="../JavaScript/main.js"></script>
</body>
</html>

<%@ page language="java" import="model.User" %>
<%@ page session="true" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || !"Staff".equals(user.getRole())) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<html>
<head>
    <title>Staff Dashboard</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<jsp:include page="navbar.jsp" />

<div class="container">
    <h2>Welcome Staff, <%= user.getFullName() %></h2>
    <p>This is your staff dashboard.</p>

    <ul>
        <li><a href="sales.jsp">Record Sale</a></li>
        <li><a href="inventory.jsp">View Inventory</a></li>
    </ul>
</div>

<script src="../JavaScript/main.js"></script>
</body>
</html>

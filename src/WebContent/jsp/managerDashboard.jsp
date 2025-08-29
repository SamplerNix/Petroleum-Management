<%@ page language="java" import="model.User" %>
<%@ page session="true" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || !"Manager".equals(user.getRole())) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<html>
<head>
    <title>Manager Dashboard</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<jsp:include page="navbar.jsp" />

<div class="container">
    <h2>Welcome Manager, <%= user.getFullName() %></h2>
    <p>This is your manager dashboard.</p>

    <ul>
        <li><a href="inventory.jsp">View Inventory</a></li>
        <li><a href="supply.jsp">Log Fuel Supply</a></li>
        <li><a href="reports.jsp">Generate Reports</a></li>
    </ul>
</div>

<script src="../JavaScript/main.js"></script>
</body>
</html>

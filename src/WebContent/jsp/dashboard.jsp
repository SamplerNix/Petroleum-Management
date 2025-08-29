<%@ page language="java" %>
<%@ page session="true" %>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<script src="../JavaScript/main.js"></script>
<body>
    <jsp:include page="navbar.jsp" />
    <h2>Dashboard</h2>
    <p>Welcome, <%= session.getAttribute("username") %>!</p>
    <!-- You can pull in stats via request attributes -->
</body>
</html>

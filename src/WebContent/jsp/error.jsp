<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<script src="../JavaScript/main.js"></script>
<body>
    <h2 style="color:red;">An error has occurred</h2>
    <p>Sorry! Something went wrong while processing your request.</p>

    <% if (exception != null) { %>
        <h4>Error Details:</h4>
        <pre><%= exception.getMessage() %></pre>
    <% } %>

    <p><a href="dashboard.jsp">Back to Dashboard</a></p>
</body>
</html>

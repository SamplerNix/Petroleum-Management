<%@ page language="java" import="java.util.*, model.*" %>
<%@ page session="true" %>
<html>
<head>
    <title>Stations</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<script src="../JavaScript/main.js"></script>
<body>
<jsp:include page="navbar.jsp" />

<h2>Manage Fuel Stations</h2>

<!-- New Station Form -->
<h3>Add New Station</h3>
<form method="post" action="../stations">
    <label>Station Name:</label>
    <input type="text" name="station_name" required /><br/><br/>

    <label>Location:</label>
    <input type="text" name="location" required /><br/><br/>

    <label>Manager (User ID):</label>
    <input type="number" name="manager_id" required /><br/><br/>

    <label>Contact Info:</label>
    <input type="text" name="contact_info" required /><br/><br/>

    <input type="submit" value="Add Station" />
</form>

<% if (request.getAttribute("message") != null) { %>
    <p style="color:green;"><%= request.getAttribute("message") %></p>
<% } else if (request.getAttribute("error") != null) { %>
    <p style="color:red;"><%= request.getAttribute("error") %></p>
<% } %>

<hr/>

<h3>Station List</h3>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Location</th>
        <th>Manager ID</th>
        <th>Contact</th>
    </tr>
    <%
        List<Station> stations = (List<Station>) request.getAttribute("stations");
        if (stations != null) {
            for (Station s : stations) {
    %>
    <tr>
        <td><%= s.getStationId() %></td>
        <td><%= s.getStationName() %></td>
        <td><%= s.getLocation() %></td>
        <td><%= s.getManagerId() %></td>
        <td><%= s.getContactInfo() %></td>
    </tr>
    <%   }
        } else { %>
    <tr><td colspan="5">No stations found.</td></tr>
    <% } %>
</table>
</body>
</html>

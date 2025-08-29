<%@ page language="java" import="java.util.*, model.*" %>
<%@ page session="true" %>
<html>
<head>
    <title>Fuel Types</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>

<jsp:include page="navbar.jsp" />

<div class="container">

    <h2>Manage Fuel Types</h2>

    <!-- Form to Add New Fuel Type -->
    <h3>Add New Fuel Type</h3>
    <form method="post" action="../fueltypes">
        <label>Fuel Name:</label>
        <input type="text" name="fuel_name" required /><br/>

        <label>Price per Liter:</label>
        <input type="number" step="0.01" name="price_per_liter" required /><br/>

        <input type="submit" value="Add Fuel Type" />
    </form>

    <!-- Flash messages -->
    <% if (request.getAttribute("message") != null) { %>
        <p style="color:green;"><%= request.getAttribute("message") %></p>
    <% } else if (request.getAttribute("error") != null) { %>
        <p style="color:red;"><%= request.getAttribute("error") %></p>
    <% } %>

    <hr/>

    <!-- Fuel Type List -->
    <h3>Fuel Types List</h3>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Fuel Name</th>
                <th>Price per Liter (₹)</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<FuelType> fuelTypes = (List<FuelType>) request.getAttribute("fuelTypes");
                if (fuelTypes != null && !fuelTypes.isEmpty()) {
                    for (FuelType fuel : fuelTypes) {
            %>
            <tr>
                <td><%= fuel.getFuelTypeId() %></td>
                <td><%= fuel.getFuelName() %></td>
                <td><%= fuel.getPricePerLiter() %></td>
            </tr>
            <%   }
                } else {
            %>
            <tr><td colspan="3">No fuel types found.</td></tr>
            <% } %>
        </tbody>
    </table>

</div>

<script src="../JavaScript/main.js"></script>
</body>
</html>

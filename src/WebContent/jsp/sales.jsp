<%@ page language="java" import="java.util.*, model.*" %>
<%@ page session="true" %>
<html>
<head>
    <title>Fuel Sales</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<script src="../JavaScript/main.js"></script>
<body>

<jsp:include page="navbar.jsp" />

<h2>Fuel Sales</h2>

<!-- 💼 Add New Sale Form -->
<h3>Add New Sale</h3>
<form method="post" action="../sales">
    <label for="station">Station:</label>
    <select name="station_id" required>
        <option value="">-- Select Station --</option>
        <%
            List<Station> stations = (List<Station>) request.getAttribute("stations");
            for (Station s : stations) {
        %>
            <option value="<%= s.getStationId() %>"><%= s.getStationName() %></option>
        <% } %>
    </select><br/><br/>

    <label for="fuelType">Fuel Type:</label>
    <select name="fuel_type_id" required>
        <option value="">-- Select Fuel --</option>
        <%
            List<FuelType> fuelTypes = (List<FuelType>) request.getAttribute("fuelTypes");
            for (FuelType f : fuelTypes) {
        %>
            <option value="<%= f.getFuelTypeId() %>"><%= f.getFuelName() %> (₹<%= f.getPricePerLiter() %>/L)</option>
        <% } %>
    </select><br/><br/>

    <label for="quantity">Quantity (Liters):</label>
    <input type="number" step="0.01" name="quantity_liters" required /><br/><br/>

    <input type="hidden" name="sold_by" value="<%= session.getAttribute("user_id") %>" />
    <input type="submit" value="Record Sale" />
</form>

<%-- Success/Error messages --%>
<% if (request.getAttribute("message") != null) { %>
    <p style="color:green;"><%= request.getAttribute("message") %></p>
<% } else if (request.getAttribute("error") != null) { %>
    <p style="color:red;"><%= request.getAttribute("error") %></p>
<% } %>

<hr/>

<!-- 📊 Sales History Table -->
<h3>Sales History</h3>
<table border="1">
    <thead>
        <tr>
            <th>Sale ID</th>
            <th>Station</th>
            <th>Fuel Type</th>
            <th>Quantity (L)</th>
            <th>Total Price</th>
            <th>Sold By</th>
            <th>Timestamp</th>
        </tr>
    </thead>
    <tbody>
        <%
            List<Sale> sales = (List<Sale>) request.getAttribute("salesList");
            if (sales != null && !sales.isEmpty()) {
                for (Sale sale : sales) {
        %>
        <tr>
            <td><%= sale.getSaleId() %></td>
            <td><%= sale.getStationName() %></td>
            <td><%= sale.getFuelTypeName() %></td>
            <td><%= sale.getQuantityLiters() %></td>
            <td>₹<%= sale.getTotalPrice() %></td>
            <td><%= sale.getSoldByName() %></td>
            <td><%= sale.getTimestamp() %></td>
        </tr>
        <%   }
            } else { %>
        <tr><td colspan="7">No sales found.</td></tr>
        <% } %>
    </tbody>
</table>

</body>
</html>

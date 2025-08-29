<%@ page language="java" import="java.util.*, model.*" %>
<%@ page session="true" %>
<html>
<head>
    <title>Fuel Supply</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<script src="../JavaScript/main.js"></script>
<body>

<jsp:include page="navbar.jsp" />

<h2>Log New Fuel Supply</h2>

<form method="post" action="../supply">
    <label>Station:</label>
    <select name="station_id" required>
        <option value="">-- Select Station --</option>
        <%
            List<Station> stations = (List<Station>) request.getAttribute("stations");
            for (Station s : stations) {
        %>
            <option value="<%= s.getStationId() %>"><%= s.getStationName() %></option>
        <% } %>
    </select><br/><br/>

    <label>Fuel Type:</label>
    <select name="fuel_type_id" required>
        <option value="">-- Select Fuel --</option>
        <%
            List<FuelType> fuelTypes = (List<FuelType>) request.getAttribute("fuelTypes");
            for (FuelType f : fuelTypes) {
        %>
            <option value="<%= f.getFuelTypeId() %>"><%= f.getFuelName() %></option>
        <% } %>
    </select><br/><br/>

    <label>Quantity (Liters):</label>
    <input type="number" name="quantity_liters" step="0.01" required /><br/><br/>

    <label>Supplier Name:</label>
    <input type="text" name="supplier_name" required /><br/><br/>

    <label>Delivery Date:</label>
    <input type="date" name="delivery_date" required /><br/><br/>

    <input type="submit" value="Log Supply" />
</form>

<%-- Show feedback message --%>
<% if (request.getAttribute("message") != null) { %>
    <p style="color:green;"><%= request.getAttribute("message") %></p>
<% } else if (request.getAttribute("error") != null) { %>
    <p style="color:red;"><%= request.getAttribute("error") %></p>
<% } %>

<hr/>

<h3>Supply History</h3>

<table border="1">
    <thead>
        <tr>
            <th>Supply ID</th>
            <th>Station</th>
            <th>Fuel Type</th>
            <th>Quantity (L)</th>
            <th>Supplier</th>
            <th>Delivery Date</th>
        </tr>
    </thead>
    <tbody>
        <%
            List<Supply> supplyList = (List<Supply>) request.getAttribute("supplyList");
            if (supplyList != null && !supplyList.isEmpty()) {
                for (Supply s : supplyList) {
        %>
        <tr>
            <td><%= s.getSupplyId() %></td>
            <td><%= s.getStationName() %></td>
            <td><%= s.getFuelTypeName() %></td>
            <td><%= s.getQuantityLiters() %></td>
            <td><%= s.getSupplierName() %></td>
            <td><%= s.getDeliveryDate() %></td>
        </tr>
        <%   }
            } else { %>
        <tr><td colspan="6">No supply records found.</td></tr>
        <% } %>
    </tbody>
</table>

</body>
</html>

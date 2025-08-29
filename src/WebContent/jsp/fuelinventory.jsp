<%@ page language="java" %>
<html>
<head>
    <title>Fuel Inventory</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<script src="../JavaScript/main.js"></script>
<body>
    <jsp:include page="navbar.jsp" />
    <h2>Fuel Inventory</h2>
    <table border="1">
        <tr>
            <th>Station</th>
            <th>Fuel Type</th>
            <th>Quantity (L)</th>
            <th>Last Updated</th>
        </tr>
        <%
           List<FuelInventory> inventoryList = (List<FuelInventory>) request.getAttribute("inventory");
           for(FuelInventory inv : inventoryList) {
        %>
        <tr>
            <td><%= inv.getStationName() %></td>
            <td><%= inv.getFuelTypeName() %></td>
            <td><%= inv.getQuantityLiters() %></td>
            <td><%= inv.getLastUpdated() %></td>
        </tr>
        <% } %>
    </table>
</body>
</html>

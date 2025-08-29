<%@ page language="java" import="java.util.*, model.*" %>
<%@ page session="true" %>
<html>
<head>
    <title>Reports</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<script src="../JavaScript/main.js"></script>
<body>

<jsp:include page="navbar.jsp" />

<h2>Generate Reports</h2>

<!-- 🧾 Report Selection Form -->
<form method="get" action="../reports">
    <label>Report Type:</label>
    <select name="type" required onchange="toggleFilters(this.value)">
        <option value="">-- Select --</option>
        <option value="sales" <%= "sales".equals(request.getAttribute("reportType")) ? "selected" : "" %>>Sales Report</option>
        <option value="inventory" <%= "inventory".equals(request.getAttribute("reportType")) ? "selected" : "" %>>Inventory Report</option>
    </select><br/><br/>

    <div id="salesFilters" style="display:none;">
        <label>From:</label>
        <input type="date" name="from_date" /><br/>
        <label>To:</label>
        <input type="date" name="to_date" /><br/>
    </div>

    <div id="inventoryFilters" style="display:none;">
        <label>Station:</label>
        <select name="station_id">
            <option value="">-- All Stations --</option>
            <%
                List<Station> stations = (List<Station>) request.getAttribute("stations");
                if (stations != null) {
                    for (Station s : stations) {
            %>
            <option value="<%= s.getStationId() %>"><%= s.getStationName() %></option>
            <%   }
                }
            %>
        </select><br/>
    </div>

    <br/>
    <input type="submit" value="Generate Report" />
</form>

<hr/>

<!-- 📊 Display Sales Report -->
<%
    String reportType = (String) request.getAttribute("reportType");

    if ("sales".equals(reportType)) {
        List<Sale> sales = (List<Sale>) request.getAttribute("salesReport");
%>
    <h3>Sales Report</h3>
    <table border="1">
        <tr>
            <th>Sale ID</th>
            <th>Station</th>
            <th>Fuel Type</th>
            <th>Quantity (L)</th>
            <th>Total Price</th>
            <th>Sold By</th>
            <th>Date</th>
        </tr>
        <%
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
        <%      }
            } else { %>
        <tr><td colspan="7">No sales found for selected range.</td></tr>
        <% } %>
    </table>
<%
    } else if ("inventory".equals(reportType)) {
        List<FuelInventory> inventory = (List<FuelInventory>) request.getAttribute("inventoryReport");
%>
    <h3>Inventory Report</h3>
    <table border="1">
        <tr>
            <th>Station</th>
            <th>Fuel Type</th>
            <th>Quantity (L)</th>
            <th>Last Updated</th>
        </tr>
        <%
            if (inventory != null && !inventory.isEmpty()) {
                for (FuelInventory inv : inventory) {
        %>
        <tr>
            <td><%= inv.getStationName() %></td>
            <td><%= inv.getFuelTypeName() %></td>
            <td><%= inv.getQuantityLiters() %></td>
            <td><%= inv.getLastUpdated() %></td>
        </tr>
        <%      }
            } else { %>
        <tr><td colspan="4">No inventory data available.</td></tr>
        <% } %>
    </table>
<% } %>

<script>
    function toggleFilters(type) {
        document.getElementById("salesFilters").style.display = (type === "sales") ? "block" : "none";
        document.getElementById("inventoryFilters").style.display = (type === "inventory") ? "block" : "none";
    }

    // Ensure correct filter is shown on page load
    window.onload = function() {
        const selectedType = "<%= reportType != null ? reportType : "" %>";
        toggleFilters(selectedType);
    }
</script>

</body>
</html>

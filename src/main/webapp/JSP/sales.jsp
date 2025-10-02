<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Record Sale</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/JSP/CSS/style.css" />
</head>

<body>
    <div class="login-container">
        <h2>Record a Fuel Sale</h2>

        <%-- Show error if exists --%>
        <% if (request.getAttribute("error") != null) { %>
            <p style="color:red;"><%= request.getAttribute("error") %></p>
        <% } %>

        <form action="SalesServlet" method="post">
            <label for="fuelTypeId">Fuel Type:</label>
            <select name="fuelTypeId" required>
                <option value="">-- Select Fuel Type --</option>
                <%
                    java.util.List<componet.model.FuelType> fuelTypes =
                        (java.util.List<componet.model.FuelType>) request.getAttribute("fuelTypes");
                    if (fuelTypes != null) {
                        for (componet.model.FuelType fuel : fuelTypes) {
                %>
                            <option value="<%= fuel.getFuelTypeId() %>">
                                <%= fuel.getFuelName() %> (<%= fuel.getPricePerLiter() %>)
                            </option>
                <%
                        }
                    } else {
                        out.println("<option disabled>No fuel types available</option>");
                    }
                %>
            </select>

            <label for="stationId">Station:</label>
            <select name="stationId" required>
                <option value="">-- Select Station --</option>
                <%
                    java.util.List<componet.model.Station> stations =
                        (java.util.List<componet.model.Station>) request.getAttribute("stations");
                    if (stations != null) {
                        for (componet.model.Station station : stations) {
                %>
                            <option value="<%= station.getId() %>">
                                <%= station.getName() %> (<%= station.getLocation() %>)
                            </option>
                <%
                        }
                    } else {
                        out.println("<option disabled>No stations available</option>");
                    }
                %>
            </select>

            <label for="quantity">Quantity (liters):</label>
            <input type="number" name="quantity" step="0.01" min="0.01" required>

            <button type="submit">Record Sale</button>
        </form>
    </div>
</body>
</html>

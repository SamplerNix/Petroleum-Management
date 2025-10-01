<%@ page import="java.util.*, componet.model.FuelType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Fuel Types</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}//style.css"> <!-- ✅ Dynamic path -->
    <style>
        .fuel-container {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
            width: 100%;
            max-width: 800px;
            text-align: center;
            margin: auto;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 30px;
        }

        table th, table td {
            padding: 12px;
            border: 1px solid #ccc;
            text-align: center;
        }

        table th {
            background-color: #4CAF50;
            color: white;
        }

        input[type="text"], input[type="number"] {
            width: 90%;
            padding: 8px;
            border-radius: 4px;
            border: 1px solid #ccc;
        }

        .action-buttons {
            display: flex;
            justify-content: center;
            gap: 5px;
        }

        button {
            padding: 6px 12px;
            font-size: 14px;
        }

        button.delete {
            background-color: crimson;
            color: white;
        }
    </style>
</head>
<body>

<div class="fuel-container">
    <h2>Manage Fuel Types</h2>

    <!-- Add New Fuel Type -->
    <form action="${pageContext.request.contextPath}/fueltype" method="post" style="margin-bottom: 20px;"> <!-- ✅ Correct path -->
        <input type="hidden" name="action" value="add" />
        <label for="fuelName">Fuel Name</label>
        <input type="text" name="fuelName" required />
        <br>

        <label for="pricePerLiter">Price Per Liter</label>
        <input type="number" name="pricePerLiter" step="0.01" required />

        <button type="submit">Add Fuel Type</button>
    </form>

    <!-- Display Fuel Types -->
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Fuel Name</th>
                <th>Price (per Liter)</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
        <%
            List<FuelType> fuelTypes = (List<FuelType>) request.getAttribute("fuelTypes"); // ✅ match attribute name
            if (fuelTypes != null) {
                for (FuelType ft : fuelTypes) {
        %>
            <tr>
                <td><%= ft.getFuelTypeId() %></td>

                <!-- Update Form -->
                <form action="${pageContext.request.contextPath}/fueltype" method="post"> <!-- ✅ Correct servlet path -->
                    <td>
                        <input type="text" name="fuelName" value="<%= ft.getFuelName() %>" required />
                    </td>
                    <td>
                        <input type="number" name="pricePerLiter" step="0.01" value="<%= ft.getPricePerLiter() %>" required />
                        <input type="hidden" name="id" value="<%= ft.getFuelTypeId() %>" />
                        <input type="hidden" name="action" value="update" />
                    </td>
                    <td class="action-buttons">
                        <button type="submit">Update</button>
                </form>

                <!-- Delete Form -->
                <form action="${pageContext.request.contextPath}/fueltype" method="post" onsubmit="return confirm('Are you sure you want to delete this fuel type?');">
                    <input type="hidden" name="id" value="<%= ft.getFuelTypeId() %>" />
                    <input type="hidden" name="action" value="delete" />
                    <button type="submit" class="delete">Delete</button>
                </form>
                    </td>
            </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>
    <!-- Show Fuel Types Button -->
<form action="${pageContext.request.contextPath}/fueltype" method="get" style="margin-bottom: 20px;">
    <input type="hidden" name="action" value="show" />
    <button type="submit">Show Fuel Types</button>
</form>

    
</div>

</body>
</html>

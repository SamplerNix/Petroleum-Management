<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="componet.model.User" %>
<%
HttpSession sessio = request.getSession(false);
User user = (session != null) ? (User) session.getAttribute("user") : null;
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Dashboard - Petroleum Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/JSP/CSS/style.css">
    <style>
        .dashboard-container {
            max-width: 900px;
            margin: auto;
            padding: 30px;
            background: #fff;
            border-radius: 10px;
        }
        .welcome {
            text-align: center;
            margin-bottom: 30px;
        }
        .cards {
            display: flex;
            justify-content: space-between;
            flex-wrap: wrap;
        }
        .card {
            background: #4CAF50;
            color: white;
            padding: 20px;
            flex: 1 1 200px;
            margin: 10px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.2);
            text-align: center;
        }
        .card h3 {
            margin: 10px 0 5px;
        }
        .logout-btn button {
    background-color: #f44336; /* Red */
    color: #fff;
    padding: 10px 20px;
    font-size: 15px;
    border: none;
    border-radius: 6px;
    cursor: pointer;
    transition: background-color 0.3s ease, transform 0.2s ease;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.logout-btn button:hover {
    background-color: #d32f2f;
    transform: scale(1.05);
}
        
    </style>
</head>
<body>
    <div class="dashboard-container">
        <div class="welcome">
            <h2>Welcome, <%= user.getFullName() %>!</h2>
            <p>Role: <%= user.getRole() %></p>
            <div class="logout-btn" style="text-align: right; margin-bottom: 10px;">
            <form action="${pageContext.request.contextPath}/LogoutServlet" method="post">
    <button  type="submit">Logout</button>
</form>
</div>
<%@ page import="java.time.LocalDateTime, java.time.format.DateTimeFormatter" %>
<%
    LocalDateTime now = LocalDateTime.now();
    String formattedDateTime = now.format(DateTimeFormatter.ofPattern("dd MMMM yyyy, hh:mm a"));
%>

<div style="text-align: right; color: #555; font-size: 14px;">
    <strong>Date  Time:</strong> <%= formattedDateTime %>
</div>

            
        </div>

        <div class="cards">
            <div class="card">
                <h3>Total Stations</h3>
                <p><%= request.getAttribute("totalStations") %></p>
            </div>
            <div class="card" 
     style="cursor:pointer;" 
     onclick="window.location.href='${pageContext.request.contextPath}/FuelTypeServlet';">
    <h3>Fuel Types</h3>
    <p><%= request.getAttribute("totalFuelTypes") %></p>
</div>
            
            <div class="card" style="cursor:pointer;" onclick="window.location.href='${pageContext.request.contextPath}/SalesServlet';">>
                <h3>Today's Sales</h3>
                <p>₹ <%= request.getAttribute("todaySales") %></p>
            </div>
            <div class="card">
                <h3>Low Fuel Alerts</h3>
                <p><%= request.getAttribute("lowStockCount") %></p>
            </div>
        </div>
    </div>
</body>
</html>

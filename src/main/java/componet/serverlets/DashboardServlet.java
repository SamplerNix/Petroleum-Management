package componet.serverlets;

import componet.config.DBConnection;
import componet.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Verify session
        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            response.sendRedirect("JSP/login.jsp");
            return;
        }

        // Database queries
        try (Connection conn = DBConnection.getConnection()) {

            // 1️⃣ Total Stations
            int totalStations = 0;
            try (PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM fuel_station");
                 ResultSet rs = ps.executeQuery()) {
                if (rs.next()) totalStations = rs.getInt(1);
            }

            // 2️⃣ Total Fuel Types
            int totalFuelTypes = 0;
            try (PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM fueltype");
                 ResultSet rs = ps.executeQuery()) {
                if (rs.next()) totalFuelTypes = rs.getInt(1);
            }

            // 3️⃣ Today's Total Sales
            double todaySales = 0.0;
            try (PreparedStatement ps = conn.prepareStatement(
                    "SELECT COALESCE(SUM(total_price), 0) FROM sales_table WHERE DATE(timestamp) = ?")) {
                ps.setDate(1, Date.valueOf(LocalDate.now()));
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) todaySales = rs.getDouble(1);
                }
            }

            // 4️⃣ Low Stock Alert
            int lowStockCount = 0;
            try (PreparedStatement ps = conn.prepareStatement(
                    "SELECT COUNT(*) FROM fuel_inventory WHERE quantity_liters < 500");
                 ResultSet rs = ps.executeQuery()) {
                if (rs.next()) lowStockCount = rs.getInt(1);
            }

            // Pass all attributes to JSP
            request.setAttribute("user", user);
            request.setAttribute("totalStations", totalStations);
            request.setAttribute("totalFuelTypes", totalFuelTypes);
            request.setAttribute("todaySales", todaySales);
            request.setAttribute("lowStockCount", lowStockCount);

            // Forward to dashboard
            request.getRequestDispatcher("JSP/dashboard.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMsg", "Database Error: " + e.getMessage());
            request.getRequestDispatcher("JSP/error.jsp").forward(request, response);
        }
    }
}

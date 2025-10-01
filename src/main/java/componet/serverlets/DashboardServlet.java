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
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            response.sendRedirect("JSP/login.jsp");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            // 1. Total Stations
            PreparedStatement ps1 = conn.prepareStatement("SELECT COUNT(*) FROM fuel_stations");
            ResultSet rs1 = ps1.executeQuery();
            int totalStations = rs1.next() ? rs1.getInt(1) : 0;

            // 2. Total Fuel Types
            PreparedStatement ps2 = conn.prepareStatement("SELECT COUNT(*) FROM fueltypes");
            ResultSet rs2 = ps2.executeQuery();
            int totalFuelTypes = rs2.next() ? rs2.getInt(1) : 0;

            // 3. Today's Sales Total
            PreparedStatement ps3 = conn.prepareStatement(
                    "SELECT COALESCE(SUM(total_price), 0) FROM sales_table WHERE DATE(timestamp) = ?");
            ps3.setDate(1, Date.valueOf(LocalDate.now()));
            ResultSet rs3 = ps3.executeQuery();
            double todaySales = rs3.next() ? rs3.getDouble(1) : 0.0;

            // 4. Low Fuel Inventory
            PreparedStatement ps4 = conn.prepareStatement(
                    "SELECT COUNT(*) FROM fuel_inventory WHERE quantity_liters < 500");
            ResultSet rs4 = ps4.executeQuery();
            int lowStockCount = rs4.next() ? rs4.getInt(1) : 0;

            // Pass data to JSP
            request.setAttribute("user", user);
            request.setAttribute("totalStations", totalStations);
            request.setAttribute("totalFuelTypes", totalFuelTypes);
            request.setAttribute("todaySales", todaySales);
            request.setAttribute("lowStockCount", lowStockCount);

            request.getRequestDispatcher("JSP/dashboard.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMsg", "Database Error: " + e.getMessage());
            request.getRequestDispatcher("JSP/error.jsp").forward(request, response);
        }
    }
}

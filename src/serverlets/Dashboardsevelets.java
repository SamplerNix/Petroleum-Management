package serverlets;

import dao.FuelinventoryDAO;
import dao.SalesDAO;
import dao.FuelstationDAO;
import model.sales;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Dashboardsevelets extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("jsp/login.jsp");
            return;
        }

        String role = (String) session.getAttribute("role");

        try {
            // Instantiate DAO classes
            FuelinventoryDAO inventoryDAO = new FuelinventoryDAO();
            SalesDAO salesDAO = new SalesDAO();
            FuelstationDAO stationDAO = new FuelstationDAO();

            // Fetch data
            double totalInventory = inventoryDAO.getTotalFuelQuantity();   // You'll add this method below
            int totalStations = stationDAO.getAllStations().size();       // List size
            List<sales> recentSales = salesDAO.getRecentSales(5);         // You'll add this method below

            // Add to request scope
            request.setAttribute("totalInventory", totalInventory);
            request.setAttribute("totalStations", totalStations);
            request.setAttribute("recentSales", recentSales);

            // Forward to dashboard.jsp
            request.getRequestDispatcher("jsp/dashboard.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to load dashboard data.");
            request.getRequestDispatcher("jsp/error.jsp").forward(request, response);
        }
    }
}

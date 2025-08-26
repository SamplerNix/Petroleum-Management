package serverlets;

import dao.SupplyDAO;
import model.Fuel_supply;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class Supplyserverlets extends HttpServlet {

    private SupplyDAO supplyDAO;

    @Override
    public void init() throws ServletException {
        supplyDAO = new SupplyDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            if (action == null || action.equals("list")) {
                listSupplies(request, response);
            } else {
                // If future delete/edit action needed
                response.sendRedirect("jsp/supply.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to retrieve fuel supply data.");
            request.getRequestDispatcher("jsp/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int stationId = Integer.parseInt(request.getParameter("station_id"));
            int fuelTypeId = Integer.parseInt(request.getParameter("fuel_type_id"));
            double quantity = Double.parseDouble(request.getParameter("quantity_liters"));
            String supplier = request.getParameter("supplier_name");
            String deliveryDateStr = request.getParameter("delivery_date");

            // Parse delivery date string into Timestamp
            Timestamp deliveryDate = Timestamp.valueOf(deliveryDateStr + " 00:00:00");

            Fuel_supply supply = new Fuel_supply();
            supply.setStationid(stationId);
            supply.setFueltypeid(fuelTypeId);
            supply.setQuantity_liters(quantity);
            supply.setSuppliername(supplier);
            supply.setDeliverydate(deliveryDate);

            boolean inserted = supplyDAO.insertSupply(supply);

            if (inserted) {
                response.sendRedirect("SupplyServlet?action=list");
            } else {
                request.setAttribute("error", "Failed to record supply.");
                request.getRequestDispatcher("jsp/error.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Invalid input for supply.");
            request.getRequestDispatcher("jsp/error.jsp").forward(request, response);
        }
    }

    private void listSupplies(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        List<Fuel_supply> supplies = supplyDAO.getAllSupplies();
        request.setAttribute("supplyList", supplies);
        request.getRequestDispatcher("jsp/supply.jsp").forward(request, response);
    }
}

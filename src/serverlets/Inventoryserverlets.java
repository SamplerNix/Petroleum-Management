package serverlets;

import dao.FuelinventoryDAO;
import model.Fuel_inventory;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class Inventoryserverlets extends HttpServlet {

    private FuelinventoryDAO inventoryDAO;

    @Override
    public void init() throws ServletException {
        inventoryDAO = new FuelinventoryDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            if (action == null || action.equals("list")) {
                listInventories(request, response);
            } else if (action.equals("delete")) {
                deleteInventory(request, response);
            } else {
                response.sendRedirect("jsp/inventory.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error loading inventory.");
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

            Fuel_inventory inventory = new Fuel_inventory();
            inventory.setStationid(stationId);
            inventory.setFueltypeid(fuelTypeId);
            inventory.setQuantityliters(quantity);
            inventory.setLastupdate(new Timestamp(System.currentTimeMillis()));

            boolean inserted = inventoryDAO.insertInventory(inventory);

            if (inserted) {
                response.sendRedirect("InventoryServlet?action=list");
            } else {
                request.setAttribute("error", "Failed to add inventory.");
                request.getRequestDispatcher("jsp/error.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Invalid input.");
            request.getRequestDispatcher("jsp/error.jsp").forward(request, response);
        }
    }

    private void listInventories(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        List<Fuel_inventory> inventories = inventoryDAO.getAllInventories();
        request.setAttribute("inventories", inventories);
        request.getRequestDispatcher("jsp/inventory.jsp").forward(request, response);
    }

    private void deleteInventory(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        inventoryDAO.deleteInventoryById(id);
        response.sendRedirect("InventoryServlet?action=list");
    }
}

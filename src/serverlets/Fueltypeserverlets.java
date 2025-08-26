package serverlets;

import dao.Fuel_typeDAO;
import model.Fuel_type;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Fueltypeserverlets extends HttpServlet {

    private Fuel_typeDAO fuelTypeDAO;

    @Override
    public void init() throws ServletException {
        fuelTypeDAO = new Fuel_typeDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            if (action == null || action.equals("list")) {
                listFuelTypes(request, response);
            } else if (action.equals("delete")) {
                deleteFuelType(request, response);
            } else {
                response.sendRedirect("jsp/fuelTypes.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error processing fuel types.");
            request.getRequestDispatcher("jsp/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fuelName = request.getParameter("fuel_name");
        double price = Double.parseDouble(request.getParameter("price_per_liter"));

        Fuel_type fuelType = new Fuel_type();
        fuelType.setFuel_name(fuelName);
        fuelType.setPrice_per_liter(price);

        try {
            fuelTypeDAO.insertFuelType(fuelType);
            response.sendRedirect("Fueltypeserverlets?action=list");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to add fuel type.");
            request.getRequestDispatcher("jsp/error.jsp").forward(request, response);
        }
    }

    private void listFuelTypes(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        List<Fuel_type> fuelTypes = fuelTypeDAO.getAllFuelTypes();
        request.setAttribute("fuelTypes", fuelTypes);
        request.getRequestDispatcher("jsp/fuelTypes.jsp").forward(request, response);
    }

    private void deleteFuelType(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        fuelTypeDAO.deleteFuelType(id);
        response.sendRedirect("Fueltypeserverlets?action=list");
    }
}

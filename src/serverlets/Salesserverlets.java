package serverlets;

import dao.SalesDAO;
import model.sales;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class Salesserverlets extends HttpServlet {

    private SalesDAO salesDAO;

    @Override
    public void init() throws ServletException {
        salesDAO = new SalesDAO();
    }

    // Handle GET request (e.g., list sales or delete sale)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            if (action == null || action.equals("list")) {
                listSales(request, response);
            } else if (action.equals("delete")) {
                deleteSale(request, response);
            } else {
                response.sendRedirect("jsp/sales.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error processing sales.");
            request.getRequestDispatcher("jsp/error.jsp").forward(request, response);
        }
    }

    // Handle POST request (create a new sale)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int stationId = Integer.parseInt(request.getParameter("station_id"));
            int fuelTypeId = Integer.parseInt(request.getParameter("fuel_type_id"));
            double quantity = Double.parseDouble(request.getParameter("quantity_liters"));
            double totalPrice = Double.parseDouble(request.getParameter("total_price"));
            String soldBy = request.getParameter("sold_by");

            sales sale = new sales();
            sale.setStationid(stationId);
            sale.setFueltypeid(fuelTypeId);
            sale.setQuantityliters(quantity);
            sale.setTotalprice(totalPrice);
            sale.setSoldby(soldBy);
            sale.setTimestamp(new Timestamp(System.currentTimeMillis()));

            boolean inserted = salesDAO.insertSale(sale);

            if (inserted) {
                response.sendRedirect("SalesServlet?action=list");
            } else {
                request.setAttribute("error", "Failed to record sale.");
                request.getRequestDispatcher("jsp/error.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Invalid input data.");
            request.getRequestDispatcher("jsp/error.jsp").forward(request, response);
        }
    }

    private void listSales(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        List<sales> salesList = salesDAO.getAllSales();
        request.setAttribute("salesList", salesList);
        request.getRequestDispatcher("jsp/sales.jsp").forward(request, response);
    }

    private void deleteSale(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int saleId = Integer.parseInt(request.getParameter("id"));
        // You may implement delete logic in DAO if needed
        // salesDAO.deleteSaleById(saleId);
        response.sendRedirect("SalesServlet?action=list");
    }
}

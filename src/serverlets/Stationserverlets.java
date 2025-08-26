package serverlets;

import dao.FuelstationDAO;
import model.Fuel_station;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Stationserverlets extends HttpServlet {

    private FuelstationDAO stationDAO;

    @Override
    public void init() throws ServletException {
        stationDAO = new FuelstationDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            if (action == null || action.equals("list")) {
                listStations(request, response);
            } else if (action.equals("delete")) {
                deleteStation(request, response);
            } else {
                response.sendRedirect("jsp/station.jsp"); // fallback page
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error processing station request.");
            request.getRequestDispatcher("jsp/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String stationName = request.getParameter("station_name");
            String location = request.getParameter("location");
            int managerId = Integer.parseInt(request.getParameter("manager_id"));
            String contactInfo = request.getParameter("contact_info");

            Fuel_station station = new Fuel_station();
            station.setStattion_name(stationName);
            station.setLocation(location);
            station.setManagerid(managerId);
            station.setContactinfo(contactInfo);

            boolean success = stationDAO.insertStation(station);

            if (success) {
                response.sendRedirect("StationServlet?action=list");
            } else {
                request.setAttribute("error", "Failed to add station.");
                request.getRequestDispatcher("jsp/error.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Invalid station input.");
            request.getRequestDispatcher("jsp/error.jsp").forward(request, response);
        }
    }

    private void listStations(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        List<Fuel_station> stations = stationDAO.getAllStations();
        request.setAttribute("stationList", stations);
        request.getRequestDispatcher("jsp/station.jsp").forward(request, response);
    }

    private void deleteStation(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int stationId = Integer.parseInt(request.getParameter("id"));
        stationDAO.deleteStation(stationId);
        response.sendRedirect("StationServlet?action=list");
    }
}

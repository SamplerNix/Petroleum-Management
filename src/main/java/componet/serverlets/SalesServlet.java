package componet.serverlets;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import componet.Dao.FuelTypeDAO;
import componet.Dao.SalesDAO;
import componet.Dao.StationDAO;
import componet.model.FuelType;
import componet.model.Station;

@WebServlet("/SalesServlet")
public class SalesServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int fuelTypeId = Integer.parseInt(request.getParameter("fuelTypeId"));
        int stationId = Integer.parseInt(request.getParameter("stationId"));
        double quantity = Double.parseDouble(request.getParameter("quantity"));

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user_id") == null) {
            response.sendRedirect(request.getContextPath() + "/JSP/login.jsp");
            return;
        }
        int userId = (int) session.getAttribute("user_id");


        // Call DAO to insert the sale
        SalesDAO dao = new SalesDAO();
        boolean success = dao.recordSale(fuelTypeId, stationId, quantity, userId);

        if (success) {
            response.sendRedirect("/JSP/dashboard.jsp");
        } else {
            request.setAttribute("error", "Sale could not be recorded.");
            request.getRequestDispatcher("/JSP/sales.jsp").forward(request, response); // Correct path
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FuelTypeDAO fuelDao = new FuelTypeDAO();
        List<FuelType> fuelTypes = fuelDao.getAllFuelTypes();

        StationDAO stationDao = new StationDAO();
        List<Station> stations = stationDao.getAllStations();

        request.setAttribute("fuelTypes", fuelTypes);
        request.setAttribute("stations", stations);
        request.getRequestDispatcher("/JSP/sales.jsp").forward(request, response);  // Correct path

        System.out.println("FuelTypes: " + fuelTypes.size());
        System.out.println("Stations: " + stations.size());
    }
}

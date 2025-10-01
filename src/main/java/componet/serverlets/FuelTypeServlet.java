package componet.serverlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import componet.Dao.FuelTypeDAO;
import componet.model.FuelType;	

@WebServlet("/fueltype")
public class FuelTypeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private FuelTypeDAO fuelTypeDAO;

    public void init() {
        fuelTypeDAO = new FuelTypeDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<FuelType> fuelTypes = fuelTypeDAO.getAllFuelTypes();
        request.setAttribute("fuelTypes", fuelTypes); // ✅ Use plural
        request.getRequestDispatcher("/JSP/fueltype.jsp").forward(request, response); // ✅ Correct path
    }

//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String action = request.getParameter("action");
//
//        if ("add".equals(action)) {
//            String name = request.getParameter("fuelName");
//            double price = Double.parseDouble(request.getParameter("pricePerLiter"));
//            FuelType ft = new FuelType(0, name, price);
//            fuelTypeDAO.addFuelType(ft);
//        } else if ("update".equals(action)) {
//            int id = Integer.parseInt(request.getParameter("id"));
//            String name = request.getParameter("fuelName");
//            double price = Double.parseDouble(request.getParameter("pricePerLiter"));
//            FuelType ft = new FuelType(id, name, price);
//            fuelTypeDAO.updateFuelType(ft);
//        } else if ("delete".equals(action)) {
//            int id = Integer.parseInt(request.getParameter("id"));
//            fuelTypeDAO.deleteFuelType(id);
//        }
//
//        // ✅ Fix redirect with full context path
//        response.sendRedirect(request.getContextPath() + "/fueltype");
//    }
//}
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    String action = request.getParameter("action");
    System.out.println("🚀 Action: " + action);

    try {
        if ("add".equals(action)) {
            String name = request.getParameter("fuelName");
            String priceStr = request.getParameter("pricePerLiter");

            System.out.println("🟢 Add: name=" + name + ", price=" + priceStr);

            double price = Double.parseDouble(priceStr);
            FuelType ft = new FuelType(0, name, price);
            fuelTypeDAO.addFuelType(ft);

        } else if ("update".equals(action)) {
            String idStr = request.getParameter("id");
            String name = request.getParameter("fuelName");
            String priceStr = request.getParameter("pricePerLiter");

            System.out.println("🟡 Update: id=" + idStr + ", name=" + name + ", price=" + priceStr);

            int id = Integer.parseInt(idStr);
            double price = Double.parseDouble(priceStr);
            FuelType ft = new FuelType(id, name, price);
            fuelTypeDAO.updateFuelType(ft);

        } else if ("delete".equals(action)) {
            String idStr = request.getParameter("id");
            System.out.println("🔴 Delete: id=" + idStr);

            int id = Integer.parseInt(idStr);
            fuelTypeDAO.deleteFuelType(id);
        }

    } catch (Exception e) {
        System.out.println("⚠️ Exception in doPost:");
        e.printStackTrace();
    }

    response.sendRedirect(request.getContextPath() + "/fueltype");
}
}


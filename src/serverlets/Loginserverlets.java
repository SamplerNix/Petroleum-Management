package serverlets;

import dao.UserDAO;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class Loginserverlets extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();  // Your DAO uses DButil internally
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            User user = userDAO.getUserByUsername(username);

            if (user != null && user.getPassword().equals(password)) {
                // Create session
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("role", user.getRole());
                session.setAttribute("userId", user.getUserId());

                // Redirect based on role
                switch (user.getRole()) {
                    case "Admin":
                        response.sendRedirect("jsp/adminDashboard.jsp");
                        break;
                    case "Manager":
                        response.sendRedirect("jsp/managerDashboard.jsp");
                        break;
                    case "Staff":
                        response.sendRedirect("jsp/staffDashboard.jsp");
                        break;
                    default:
                        session.invalidate();
                        request.setAttribute("error", "Unauthorized role.");
                        request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
                }
            } else {
                // Invalid login
                request.setAttribute("error", "Invalid username or password.");
                request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error.");
            request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("jsp/login.jsp");
    }
}

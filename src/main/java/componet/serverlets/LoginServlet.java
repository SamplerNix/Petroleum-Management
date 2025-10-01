package componet.serverlets;

import componet.Dao.Userdao;
import componet.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        Userdao dao = new Userdao();
        User user = dao.validateUser(username, password);

        System.out.println("Checking user in database...");

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("JSP/dashboard.jsp"); // Redirect to dashboard page
        } else {
            request.setAttribute("errorMsg", "Invalid username or password.");
            request.getRequestDispatcher("JSP/login.jsp").forward(request, response);
        }
    }
}

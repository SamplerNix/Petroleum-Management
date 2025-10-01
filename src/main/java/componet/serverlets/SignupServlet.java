package componet.serverlets;

import componet.Dao.Userdao;
import componet.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get form values
        String fullName = request.getParameter("full_name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        // Build User object
        User user = new User();
        user.setFullName(fullName);
        user.setUsername(username);
        user.setPassword(password); // Consider hashing
        user.setRole(role);
        user.setCreatedAt(Timestamp.from(Instant.now()));

        Userdao dao = new Userdao();
        boolean success = dao.registerUser(user);

        if (success) {
            // Redirect to login on success
            response.sendRedirect("JSP/login.jsp");
        } else {
            // On failure, show error on signup page
            request.setAttribute("errorMsg", "Signup failed. Try again.");
            request.getRequestDispatcher("JSP/signup.jsp").forward(request, response);
        }
    }
}

package dao;
import java.sql.*;
import model.User;
import utils.DButil;

// dao/UserDAO.java
public class UserDAO {
    public User getUserByUsername(String username) throws SQLException {
        Connection conn = DButil.getconnection();
        String sql = "SELECT * FROM users WHERE username=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            User user = new User();
            user.setUserId(rs.getInt("user_id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getString("role"));
            user.setFullName(rs.getString("full_name"));
            return user;
        }
        return null;
    }
}


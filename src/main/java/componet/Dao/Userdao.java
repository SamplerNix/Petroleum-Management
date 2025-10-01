package componet.Dao;

import componet.config.DBConnection;
import componet.model.User;

import java.sql.*;

public class Userdao {
    public User validateUser(String username, String password) {
        String query = "SELECT * FROM user_table WHERE username = ? AND password = ?";
        Connection conn = DBConnection.getConnection();

        if (conn == null) {
            System.err.println("❌ DB Connection failed in Userdao. Exiting method.");
            return null;
        }

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username.trim());
            ps.setString(2, password.trim());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setFullName(rs.getString("full_name"));
                user.setRole(rs.getString("role"));
                return user;
            }

        } catch (SQLException e) {
            System.err.println("❌ SQL Error in validateUser: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public boolean registerUser(User user) {
        String query = "INSERT INTO user_table (username, password, role, full_name, created_at) VALUES (?, ?, ?, ?, ?)";
        Connection conn = DBConnection.getConnection();

        if (conn == null) {
            System.err.println("❌ DB Connection failed in registerUser. Exiting method.");
            return false;
        }

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, user.getUsername().trim());
            ps.setString(2, user.getPassword().trim()); // ⚠️ Consider hashing this in future
            ps.setString(3, user.getRole().trim());
            ps.setString(4, user.getFullName().trim());
            ps.setTimestamp(5, user.getCreatedAt());

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.err.println("❌ SQL Error in registerUser: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

}


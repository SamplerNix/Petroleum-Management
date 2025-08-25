package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.User;
import utils.DButil;

public class UserDAO {

    // Get user by username (already exists)
    public User getUserByUsername(String username) throws SQLException {
        Connection conn = DButil.getconnection();
        String sql = "SELECT * FROM user_table WHERE username=?";
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

    // 🔹 Get all users
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        Connection conn = DButil.getconnection();
        String sql = "SELECT * FROM user_table ORDER BY user_id";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            User user = new User();
            user.setUserId(rs.getInt("user_id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getString("role"));
            user.setFullName(rs.getString("full_name"));
            users.add(user);
        }
        return users;
    }

    // 🔹 Get user by ID
    public User getUserById(int userId) throws SQLException {
        Connection conn = DButil.getconnection();
        String sql = "SELECT * FROM user_table WHERE user_id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);
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

    // 🔹 Insert user
    public void insertUser(User user) throws SQLException {
        Connection conn = DButil.getconnection();
        String sql = "INSERT INTO user_table (username, password, role, full_name, created_at) VALUES (?, ?, ?, ?, NOW())";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getRole());
        ps.setString(4, user.getFullName());
        ps.executeUpdate();
    }

    // 🔹 Update user
    public void updateUser(User user) throws SQLException {
        Connection conn = DButil.getconnection();
        String sql = "UPDATE user_table SET username=?, password=?, role=?, full_name=? WHERE user_id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getRole());
        ps.setString(4, user.getFullName());
        ps.setInt(5, user.getUserId());
        ps.executeUpdate();
    }

    // 🔹 Delete user
    public void deleteUser(int userId) throws SQLException {
        Connection conn = DButil.getconnection();
        String sql = "DELETE FROM user_table WHERE user_id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);
        ps.executeUpdate();
    }
}

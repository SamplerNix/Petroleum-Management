package Component;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbconnection {
    private static final String URL = "jdbc:mysql://localhost:3306/petrolium_management?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root"; // 🔁 change to your MySQL user
    private static final String PASSWORD = "your_password"; // 🔁 change to your password

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connected to MySQL database.");
            return conn;
        } catch (Exception e) {
            System.out.println("❌ Database connection failed: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}

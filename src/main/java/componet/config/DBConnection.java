package componet.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/petrolium_management";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Nikhil@123";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            System.out.println("🟡 Loading JDBC Driver...");
            Class.forName("org.postgresql.Driver");
            System.out.println("🟢 Driver loaded successfully.");

            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connection established: " + conn);
        } catch (ClassNotFoundException e) {
            System.err.println("❌ PostgreSQL Driver not found.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("❌ Exception while connecting to DB: " + e.getMessage());
            e.printStackTrace();
        }

        return conn;
    }
}

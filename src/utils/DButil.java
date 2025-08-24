package utils;

import jdk.dynalink.beans.StaticClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DButil {
   private static final String Url="jdbc:postgresql://localhost:5432/petrolium_management";
   private static final String Username="postgres";
   private static final String Password="Nikhil@123";
    public static Connection getconnection()throws SQLException {
        return DriverManager.getConnection(Url,Username,Password);
    }
}

package componet.Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import componet.config.DBConnection;

public class SalesDAO {
    public boolean recordSale(int fuelTypeId, int stationId, double quantity, int userId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();

            // Get price per liter
            String priceQuery = "SELECT price_per_liter FROM fueltype WHERE fuel_type_id = ?";
            ps = con.prepareStatement(priceQuery);
            ps.setInt(1, fuelTypeId);
            rs = ps.executeQuery();

            if (rs.next()) {
                double pricePerLiter = rs.getDouble("price_per_liter");
                double totalPrice = quantity * pricePerLiter;

                // Insert sale
                String insertQuery = "INSERT INTO sales_table (station_id, fuel_type_id, quantity_liters, total_price, sold_by, timestamp) VALUES (?, ?, ?, ?, ?, NOW())";
                ps = con.prepareStatement(insertQuery);
                ps.setInt(1, stationId);
                ps.setInt(2, fuelTypeId);
                ps.setDouble(3, quantity);
                ps.setDouble(4, totalPrice);
                ps.setInt(5, userId);

                int rows = ps.executeUpdate();
                return rows > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(con, ps, rs);
        }
        return false;
    }
}

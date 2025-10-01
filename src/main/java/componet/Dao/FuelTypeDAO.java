package componet.Dao;
import java.sql.*;
import java.util.*;
import componet.model.*;
import componet.config.*;

public class FuelTypeDAO {

    public List<FuelType> getAllFuelTypes() {
        List<FuelType> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM fueltype";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                FuelType ft = new FuelType(
                    rs.getInt("fuel_type_id"),
                    rs.getString("fuel_name"),
                    rs.getDouble("price_per_liter")
                );
                list.add(ft);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addFuelType(FuelType fuelType) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO fueltype (fuel_name, price_per_liter) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, fuelType.getFuelName());
            stmt.setDouble(2, fuelType.getPricePerLiter());

            int rows = stmt.executeUpdate();
            System.out.println("✅ Added rows: " + rows);

        } catch (Exception e) {
            System.out.println("❌ Error in addFuelType()");
            e.printStackTrace();
        }
    }



    public void updateFuelType(FuelType fuelType) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE fueltype SET fuel_name = ?, price_per_liter = ? WHERE fuel_type_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, fuelType.getFuelName());
            stmt.setDouble(2, fuelType.getPricePerLiter());
            stmt.setInt(3, fuelType.getFuelTypeId());

            int rows = stmt.executeUpdate();
            System.out.println("🛠️ Updated rows: " + rows);

        } catch (Exception e) {
            System.out.println("❌ Error in updateFuelType()");
            e.printStackTrace();
        }
    }


    public void deleteFuelType(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM fueltype WHERE fuel_type_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

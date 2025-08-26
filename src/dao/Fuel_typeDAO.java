package dao;

import model.Fuel_type;
import utils.DButil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Fuel_typeDAO {

    public Fuel_type getFuelTypeByName(String fuelName) throws SQLException {
        Connection conn = DButil.getconnection();
        String sql = "SELECT * FROM fuel_types WHERE fuel_name = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, fuelName);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Fuel_type ft = new Fuel_type();
            ft.setFuel_type_id(rs.getInt("fuel_type_id"));
            ft.setFuel_name(rs.getString("fuel_name"));
            ft.setPrice_per_liter(rs.getDouble("price_per_liter"));
            return ft;
        }
        return null;
    }

    public boolean insertFuelType(Fuel_type fuelType) throws SQLException {
        String sql = "INSERT INTO fuel_types (fuel_name, price_per_liter) VALUES (?, ?)";

        try (Connection conn = DButil.getconnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, fuelType.getFuel_name());
            ps.setDouble(2, fuelType.getPrice_per_liter());

            return ps.executeUpdate() == 1;
        }
    }

    public List<Fuel_type> getAllFuelTypes() throws SQLException {
        List<Fuel_type> list = new ArrayList<>();
        String sql = "SELECT * FROM fuel_types ORDER BY fuel_type_id ASC";

        try (Connection conn = DButil.getconnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Fuel_type fuel = new Fuel_type();
                fuel.setFuel_type_id(rs.getInt("fuel_type_id"));
                fuel.setFuel_name(rs.getString("fuel_name"));
                fuel.setPrice_per_liter(rs.getDouble("price_per_liter"));
                list.add(fuel);
            }
        }
        return list;
    }

    public boolean deleteFuelType(int id) throws SQLException {
        String sql = "DELETE FROM fuel_types WHERE fuel_type_id = ?";

        try (Connection conn = DButil.getconnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        }
    }

    public boolean updateFuelType(Fuel_type fuelType) throws SQLException {
        String sql = "UPDATE fuel_types SET fuel_name = ?, price_per_liter = ? WHERE fuel_type_id = ?";

        try (Connection conn = DButil.getconnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, fuelType.getFuel_name());
            ps.setDouble(2, fuelType.getPrice_per_liter());
            ps.setInt(3, fuelType.getFuel_type_id());

            return ps.executeUpdate() == 1;
        }
    }
}

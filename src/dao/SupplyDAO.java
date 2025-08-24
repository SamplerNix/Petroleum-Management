package dao;

import model.Fuel_supply;
import utils.DButil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplyDAO {

    // Insert a new supply record
    public boolean insertSupply(Fuel_supply supply) throws SQLException {
        String sql = "INSERT INTO fuel_supply (station_id, fuel_type_id, quantity_liters, supplier_name, delivery_date) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DButil.getconnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, supply.getStationid());
            ps.setInt(2, supply.getFueltypeid());
            ps.setDouble(3, supply.getQuantity_liters());
            ps.setString(4, supply.getSuppliername());
            ps.setTimestamp(5, supply.getDeliverydate());

            return ps.executeUpdate() == 1;
        }
    }

    // Get all supply records
    public List<Fuel_supply> getAllSupplies() throws SQLException {
        List<Fuel_supply> supplies = new ArrayList<>();
        String sql = "SELECT * FROM fuel_supply ORDER BY delivery_date DESC";

        try (Connection conn = DButil.getconnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Fuel_supply supply = new Fuel_supply();
                supply.setSupplyid(rs.getInt("supply_id"));
                supply.setStationid(rs.getInt("station_id"));
                supply.setFueltypeid(rs.getInt("fuel_type_id"));
                supply.setQuantity_liters(rs.getDouble("quantity_liters"));
                supply.setSuppliername(rs.getString("supplier_name"));
                supply.setDeliverydate(rs.getTimestamp("delivery_date"));

                supplies.add(supply);
            }
        }
        return supplies;
    }

    // Optional: Get supplies by station
    public List<Fuel_supply> getSuppliesByStation(int stationId) throws SQLException {
        List<Fuel_supply> supplies = new ArrayList<>();
        String sql = "SELECT * FROM fuel_supply WHERE station_id = ? ORDER BY delivery_date DESC";

        try (Connection conn = DButil.getconnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, stationId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Fuel_supply supply = new Fuel_supply();
                supply.setSupplyid(rs.getInt("supply_id"));
                supply.setStationid(rs.getInt("station_id"));
                supply.setFueltypeid(rs.getInt("fuel_type_id"));
                supply.setQuantity_liters(rs.getDouble("quantity_liters"));
                supply.setSuppliername(rs.getString("supplier_name"));
                supply.setDeliverydate(rs.getTimestamp("delivery_date"));

                supplies.add(supply);
            }
        }
        return supplies;
    }
}

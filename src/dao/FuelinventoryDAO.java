package dao;

import model.Fuel_inventory;
import utils.DButil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuelinventoryDAO {

    // Insert a new fuel inventory record
    public boolean insertInventory(Fuel_inventory inventory) throws SQLException {
        String sql = "INSERT INTO fuel_inventory (station_id, fuel_type_id, quantity_liters, last_updated) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection conn = DButil.getconnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, inventory.getStationid());
            ps.setInt(2, inventory.getFueltypeid());
            ps.setDouble(3, inventory.getQuantityliters());
            ps.setTimestamp(4, inventory.getLastupdate());

            return ps.executeUpdate() == 1;
        }
    }

    // Get all fuel inventory records
    public List<Fuel_inventory> getAllInventories() throws SQLException {
        List<Fuel_inventory> inventories = new ArrayList<>();
        String sql = "SELECT * FROM fuel_inventory ORDER BY last_updated DESC";

        try (Connection conn = DButil.getconnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Fuel_inventory inventory = new Fuel_inventory();
                inventory.setInventoryid(rs.getInt("inventory_id"));
                inventory.setStationid(rs.getInt("station_id"));
                inventory.setFueltypeid(rs.getInt("fuel_type_id"));
                inventory.setQuantityliters(rs.getDouble("quantity_liters"));
                inventory.setLastupdate(rs.getTimestamp("last_updated"));

                inventories.add(inventory);
            }
        }

        return inventories;
    }

    // Get inventory by station and fuel type
    public Fuel_inventory getInventoryByStationAndFuel(int stationId, int fuelTypeId) throws SQLException {
        String sql = "SELECT * FROM fuel_inventory WHERE station_id = ? AND fuel_type_id = ?";

        try (Connection conn = DButil.getconnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, stationId);
            ps.setInt(2, fuelTypeId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Fuel_inventory inventory = new Fuel_inventory();
                inventory.setInventoryid(rs.getInt("inventory_id"));
                inventory.setStationid(rs.getInt("station_id"));
                inventory.setFueltypeid(rs.getInt("fuel_type_id"));
                inventory.setQuantityliters(rs.getDouble("quantity_liters"));
                inventory.setLastupdate(rs.getTimestamp("last_updated"));

                return inventory;
            }
        }
        return null;
    }

    // Update inventory quantity and timestamp
    public boolean updateInventoryQuantity(int stationId, int fuelTypeId, double newQuantity) throws SQLException {
        String sql = "UPDATE fuel_inventory SET quantity_liters = ?, last_updated = ? " +
                "WHERE station_id = ? AND fuel_type_id = ?";

        try (Connection conn = DButil.getconnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, newQuantity);
            ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            ps.setInt(3, stationId);
            ps.setInt(4, fuelTypeId);

            return ps.executeUpdate() == 1;
        }
    }

    // Delete inventory by ID
    public boolean deleteInventoryById(int inventoryId) throws SQLException {
        String sql = "DELETE FROM fuel_inventory WHERE inventory_id = ?";

        try (Connection conn = DButil.getconnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, inventoryId);
            return ps.executeUpdate() == 1;
        }
    }
}

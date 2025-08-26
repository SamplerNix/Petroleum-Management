package dao;
import model.sales;
import utils.DButil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesDAO {

    // Insert a new sale into the database
    public boolean insertSale(sales sale) throws SQLException {
        String sql = "INSERT INTO sales_table (station_id, fuel_type_id, quantity_liters, total_price, sold_by) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DButil.getconnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, sale.getStationid());
            ps.setInt(2, sale.getFueltypeid());
            ps.setDouble(3, sale.getQuantityliters());
            ps.setDouble(4, sale.getTotalprice());
            ps.setString(5, sale.getSoldby());
            return ps.executeUpdate() == 1;
        }
    }

    // Get all sales from the database
    public List<sales> getAllSales() throws SQLException {
        List<sales> salesList = new ArrayList<>();
        String sql = "SELECT * FROM sales ORDER BY timestamp DESC";

        try (Connection conn = DButil.getconnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                sales sale = new sales();
                sale.setSalesid(rs.getInt("sale_id"));
                sale.setStationid(rs.getInt("station_id"));
                sale.setFueltypeid(rs.getInt("fuel_type_id"));
                sale.setQuantityliters(rs.getDouble("quantity_liters"));
                sale.setTotalprice(rs.getDouble("total_price"));
                sale.setSoldby(rs.getString("sold_by"));
                salesList.add(sale);
            }
        }
        return salesList;
    }

    // Get sales by station ID
    public List<sales> getSalesByStation(int stationId) throws SQLException {
        List<sales> salesList = new ArrayList<>();
        String sql = "SELECT * FROM sales_table WHERE station_id = ?;";

        try (Connection conn = DButil.getconnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, stationId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                sales sale = new sales();
                sale.setSalesid(rs.getInt("sale_id"));
                sale.setStationid(rs.getInt("station_id"));
                sale.setFueltypeid(rs.getInt("fuel_type_id"));
                sale.setQuantityliters(rs.getDouble("quantity_liters"));
                sale.setTotalprice(rs.getDouble("total_price"));
                sale.setSoldby(rs.getString("sold_by"));
                salesList.add(sale);
            }
        }
        return salesList;
    }
    public List<sales> getRecentSales(int limit) throws SQLException {
        List<sales> salesList = new ArrayList<>();
        String sql = "SELECT * FROM sales_table ORDER BY timestamp DESC LIMIT ?";

        try (Connection conn = DButil.getconnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, limit);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                sales sale = new sales();
                sale.setSalesid(rs.getInt("sale_id"));
                sale.setStationid(rs.getInt("station_id"));
                sale.setFueltypeid(rs.getInt("fuel_type_id"));
                sale.setQuantityliters(rs.getDouble("quantity_liters"));
                sale.setTotalprice(rs.getDouble("total_price"));
                sale.setSoldby(rs.getString("sold_by"));
                salesList.add(sale);
            }
        }
        return salesList;
    }

}

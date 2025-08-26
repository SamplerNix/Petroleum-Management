package dao;

import model.Fuel_station;
import utils.DButil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuelstationDAO {

    // Insert a new fuel station
    public boolean insertStation(Fuel_station station) throws SQLException {
        String sql = "INSERT INTO fuel_station (station_name, location, manager_id, contact_info) VALUES (?, ?, ?, ?)";

        try (Connection conn = DButil.getconnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, station.getStattion_name());
            ps.setString(2, station.getLocation());
            ps.setInt(3, station.getManagerid());
            ps.setString(4, station.getContactinfo());

            return ps.executeUpdate() == 1;
        }
    }

    // Get all fuel stations
    public List<Fuel_station> getAllStations() throws SQLException {
        List<Fuel_station> stations = new ArrayList<>();
        String sql = "SELECT * FROM fuel_station ORDER BY station_id ASC";

        try (Connection conn = DButil.getconnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Fuel_station station = new Fuel_station();
                station.setStationid(rs.getInt("station_id"));
                station.setStattion_name(rs.getString("station_name"));
                station.setLocation(rs.getString("location"));
                station.setManagerid(rs.getInt("manager_id"));
                station.setContactinfo(rs.getString("contact_info"));

                stations.add(station);
            }
        }
        return stations;
    }

    // Get a single station by ID
    public Fuel_station getStationById(int id) throws SQLException {
        String sql = "SELECT * FROM fuel_station WHERE station_id = ?";
        try (Connection conn = DButil.getconnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Fuel_station station = new Fuel_station();
                station.setStationid(rs.getInt("station_id"));
                station.setStattion_name(rs.getString("station_name"));
                station.setLocation(rs.getString("location"));
                station.setManagerid(rs.getInt("manager_id"));
                station.setContactinfo(rs.getString("contact_info"));
                return station;
            }
        }
        return null;
    }

    // Optional: Update a station (can be expanded)
    public boolean updateStation(Fuel_station station) throws SQLException {
        String sql = "UPDATE fuel_station SET station_name = ?, location = ?, manager_id = ?, contact_info = ? WHERE station_id = ?";

        try (Connection conn = DButil.getconnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, station.getStattion_name());
            ps.setString(2, station.getLocation());
            ps.setInt(3, station.getManagerid());
            ps.setString(4, station.getContactinfo());
            ps.setInt(5, station.getStationid());

            return ps.executeUpdate() == 1;
        }
    }
    // Delete a station by ID
    public boolean deleteStation(int stationId) throws SQLException {
        String sql = "DELETE FROM fuel_station WHERE station_id = ?";
        try (Connection conn = DButil.getconnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, stationId);
            return ps.executeUpdate() == 1;
        }
    }

}
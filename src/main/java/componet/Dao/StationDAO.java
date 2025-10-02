package componet.Dao;

import componet.model.Station;
import componet.config.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StationDAO {

    public List<Station> getAllStations() {
        List<Station> stations = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT station_id, station_name, location, contact_info FROM fuel_station";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Station station = new Station();
                station.setId(rs.getInt("station_id"));
                station.setName(rs.getString("station_name"));
                station.setLocation(rs.getString("location"));
                station.setContactInfo(rs.getString("contact_info"));
                stations.add(station);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(conn, ps, rs);
        }

        return stations;
    }
}

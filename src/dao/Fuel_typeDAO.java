package dao;

import model.Fuel_type;
import model.Fuel_type;
import utils.DButil;

import java.sql.*;

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
}

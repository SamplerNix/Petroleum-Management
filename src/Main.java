import java.sql.*;
public class Main {
    public static void main(String[] args) {
        String Url="jdbc:postgresql://localhost:5432/petrolium_management";
        String Username="postgres";
        String Password="Nikhil@123";
        String Show="Select* from User_table";
        try{
            Connection conn=DriverManager.getConnection(Url,Username,Password);
            System.out.println("Connected");
            Statement stm= conn.createStatement();
            ResultSet rs= stm.executeQuery(Show);
            while(rs.next()){
                int id= rs.getInt("user_id");
                String username=rs.getString("username");
                String password=rs.getString("Password");
                System.out.println(id+username+password);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
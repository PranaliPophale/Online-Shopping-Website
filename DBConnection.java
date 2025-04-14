import java.sql.*;
public class DBConnection {
        public static Connection getConnection() {
            Connection conn = null;
            try {
                String url = "jdbc:mysql://localhost:3306/products";
                String user = "root";
                String password = "root"; //

                conn = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                System.out.println("Database connection error: " + e);
            }
            return conn;
        }
    }

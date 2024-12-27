package realestate;

import java.sql.*;

public class ConnectDB {

    private static final String DB_URL = "jdbc:sqlite:C:/Users/Hakan/Desktop/database.db";

    // Database connection method
    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            // Connect to database
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Database connection succesful.");
        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
            throw e;
        }
        return conn;
    }

}

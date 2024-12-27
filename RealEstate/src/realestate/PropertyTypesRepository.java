package realestate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hakan
 */
public class PropertyTypesRepository {

    // ADD PROPERTY TYPES TO THE SQL
    public static void addPropertyType(String name) {
        String sql = "INSERT INTO PropertyTypes (name) VALUES (?)";
        try (Connection conn = ConnectDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error adding Property Type: " + ex.getMessage(), ex);
        }
    }

    // UPDATES PROPERTY TYPES FROM SQL
    public static void updatePropertyType(String oldName, String newName) {
        String sql = "UPDATE PropertyTypes SET name = ? WHERE name = ?";
        try (Connection conn = ConnectDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newName);
            pstmt.setString(2, oldName);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error updating Property Type: " + ex.getMessage(), ex);
        }
    }

    // DELETES PROPERTY TYPES FROM SQL
    public static void removePropertyType(String name) {
        String sql = "DELETE FROM PropertyTypes WHERE name = ?";
        try (Connection conn = ConnectDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error removing Property Type: " + ex.getMessage(), ex);
        }
    }

    // GIVES ALL PROPERTY TYPES FROM SQL
    public static List<String> getAllPropertyTypes() {
        List<String> propertyTypes = new ArrayList<>();
        String sql = "SELECT name FROM PropertyTypes";
        try (Connection conn = ConnectDB.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                propertyTypes.add(rs.getString("name"));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error fetching Property Types: " + ex.getMessage(), ex);
        }
        return propertyTypes;
    }

}

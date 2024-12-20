package realestate;

import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class PropertyRepository {

    private static DefaultTableModel propertyModel = new DefaultTableModel(
            new String[]{"ID", "Type", "Owner ID", "Square Feet", "Price", "Bedrooms", "Bathrooms", "Rooms", "Floor", "Balcony", "Pool", "Backyard", "Garage", "Lift"}, 0
    );

    public static DefaultTableModel getPropertyModel() {
        return propertyModel;
    }

    public static DefaultTableModel setPropertiesTableModel() {
        propertyModel.setRowCount(0);

        String sql = "SELECT * FROM Properties";

        try (Connection conn = ConnectDB.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id"),
                    rs.getString("type"),
                    rs.getString("ownerId"),
                    rs.getDouble("squareFeet"),
                    rs.getDouble("price"),
                    rs.getInt("bedrooms"),
                    rs.getInt("bathrooms"),
                    rs.getInt("rooms"),
                    rs.getInt("floor"),
                    rs.getBoolean("balcony"),
                    rs.getBoolean("pool"),
                    rs.getBoolean("backyard"),
                    rs.getBoolean("garage"),
                    rs.getBoolean("lift")
                };
                propertyModel.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return propertyModel;
    }
}

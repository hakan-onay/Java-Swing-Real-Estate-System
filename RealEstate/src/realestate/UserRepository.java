package realestate;

import java.sql.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hakan
 */
public class UserRepository {

    private static DefaultTableModel userModel = new DefaultTableModel(
            new String[]{"ID", "Name", "Surname", "Telephone No", "E-Mail", "Password"}, 0
    );

    public static DefaultTableModel getUserModel() {
        return userModel;
    }

    public static DefaultTableModel setUsersTableModel() {
        userModel.setRowCount(0);

        String sql = "SELECT * FROM Users";

        try (Connection conn = ConnectDB.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("surname"),
                    rs.getInt("telephoneNo"),
                    rs.getString("email"),
                    rs.getString("password")};

                userModel.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userModel;
    }
}

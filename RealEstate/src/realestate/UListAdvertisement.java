package realestate;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UListAdvertisement extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> propertyTypeComboBox;
    private JButton searchButton;

    public UListAdvertisement() {
        JFrame listAdvertisementFrame = new JFrame("Advertisements");
        listAdvertisementFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        listAdvertisementFrame.setSize(1200, 700);
        listAdvertisementFrame.setLayout(null);
        listAdvertisementFrame.setLocationRelativeTo(null);

        JLabel propertyTypesLabel = new JLabel("ADVERTISEMENTS");
        propertyTypesLabel.setBounds(400, 50, 500, 40);
        propertyTypesLabel.setForeground(Color.DARK_GRAY);
        propertyTypesLabel.setFont(new Font("", Font.BOLD, 35));
        listAdvertisementFrame.add(propertyTypesLabel);

        // Initialize the ComboBox and populate it with data from the database
        propertyTypeComboBox = new JComboBox<>();
        propertyTypeComboBox.setBounds(50, 60, 200, 30);
        listAdvertisementFrame.add(propertyTypeComboBox);

        searchButton = new JButton("Search");
        searchButton.setBounds(270, 60, 100, 30);
        listAdvertisementFrame.add(searchButton);

        // Populate the ComboBox with property types from the database
        populatePropertyTypes();

        // Initialize the table with all properties by default
        tableModel = PropertyRepository.getPropertyModel();
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 110, 1075, 400);
        listAdvertisementFrame.add(scrollPane);

        JButton getButton = new JButton("GET");
        getButton.setBounds(350, 525, 100, 30);
        listAdvertisementFrame.add(getButton);

        JButton backButton = new JButton("BACK");
        backButton.setBounds(650, 525, 100, 30);
        listAdvertisementFrame.add(backButton);

        getButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected row from the table
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    try {
                        // Retrieve the propertyID and ownerID from the selected row
                        String propertyIdStr = table.getValueAt(selectedRow, 0).toString(); // propertyID is in the first column
                        String ownerIdStr = table.getValueAt(selectedRow, 2).toString(); // ownerID is in the second column

                        // Convert the String values to Integer
                        int propertyId = Integer.parseInt(propertyIdStr);
                        int ownerId = Integer.parseInt(ownerIdStr);

                        // Get the userID from the user
                        String userIdStr = JOptionPane.showInputDialog("Enter your User ID:");
                        if (userIdStr != null && !userIdStr.isEmpty()) {
                            try {
                                int userId = Integer.parseInt(userIdStr);

                                // Insert the data into the Got table
                                insertIntoGotTable(userId, propertyId, ownerId);

                                // Delete the selected property from the Properties table
                                deletePropertyFromDatabase(propertyId);

                                // Remove the selected row from the table in the UI
                                ((DefaultTableModel) table.getModel()).removeRow(selectedRow);

                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Invalid User ID. Please enter a valid number.");
                            }
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid Property ID or Owner ID. Please enter a valid number.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a property.");
                }
            }
        });

        // to back user view page
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserView();
                listAdvertisementFrame.dispose();
            }
        });

        // Search button action listener
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedType = (String) propertyTypeComboBox.getSelectedItem();
                updateTableWithFilteredData(selectedType);
            }
        });

        listAdvertisementFrame.setVisible(true);
    }

    // Populate the ComboBox with property types from the database using ConnectDB
    private void populatePropertyTypes() {
        try (Connection conn = ConnectDB.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT name FROM PropertyTypes")) {

            while (rs.next()) {
                propertyTypeComboBox.addItem(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update the table with filtered data based on the selected property type using ConnectDB
    private void updateTableWithFilteredData(String propertyType) {
        try (Connection conn = ConnectDB.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Properties WHERE type = ?")) {

            stmt.setString(1, propertyType);
            ResultSet rs = stmt.executeQuery();

            // Create a new table model with the filtered data
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            Object[] columnNames = new Object[columnCount];

            for (int i = 1; i <= columnCount; i++) {
                columnNames[i - 1] = metaData.getColumnName(i);
            }

            // Create a new table model with data
            DefaultTableModel filteredTableModel = new DefaultTableModel(columnNames, 0);
            while (rs.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = rs.getObject(i);
                }
                filteredTableModel.addRow(rowData);
            }

            // Set the new table model to the JTable
            table.setModel(filteredTableModel);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Insert the selected property into the Got table
    private void insertIntoGotTable(int userId, int propertyId, int ownerId) {
        String query = "INSERT INTO Got (userId, propertyId, ownerId) VALUES (?, ?, ?)";
        try (Connection conn = ConnectDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, propertyId);
            stmt.setInt(3, ownerId);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Property successfully marked as 'Got'.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error inserting into Got table.");
        }
    }

    private void deletePropertyFromDatabase(int propertyId) {
        String sql = "DELETE FROM Properties WHERE id = ?";
        try (Connection conn = ConnectDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, propertyId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

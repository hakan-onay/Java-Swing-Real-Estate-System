package realestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hakan
 */
public class APropertyView extends JFrame {

    private JFrame propertyFrame;
    private JLabel propertyTypesLabel, idLabel, propertyTypeLabel, ownerIdLabel, squareFeetLabel, priceLabel, bedroomLabel, bathroomLabel, ageOfHouseLabel,
            roomsLabel, floorLabel;
    private JTextField idTextField, ownerIdTextField, squareFeetTextField, priceTextField;
    private JComboBox<String> propertyTypesComboBox;
    private JSpinner bedroomSpinner, bathroomSpinner, ageOfHouseSpinner, roomsSpinner, floorSpinner;
    private JCheckBox balconyCheckBox, poolCheckBox, backyardCheckBox, garageCheckBox, liftCheckBox;
    private JButton addButton, editButton, removeButton, backButton, showButton;

    public APropertyView() {

        propertyFrame = new JFrame("Properties");
        propertyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        propertyFrame.setSize(1000, 700);
        propertyFrame.setLayout(null);
        propertyFrame.setLocationRelativeTo(null);

        propertyTypesLabel = new JLabel("PROPERTIES");
        propertyTypesLabel.setBounds(360, 50, 500, 40);
        propertyTypesLabel.setForeground(Color.DARK_GRAY);
        propertyTypesLabel.setFont(new Font("", Font.BOLD, 35));
        propertyFrame.add(propertyTypesLabel);

        idLabel = new JLabel("ID: ");
        idLabel.setBounds(70, 140, 200, 40);
        idLabel.setFont(new Font("", Font.BOLD, 15));
        propertyFrame.add(idLabel);

        idTextField = new JTextField();
        idTextField.setBounds(170, 145, 100, 30);
        propertyFrame.add(idTextField);

        propertyTypeLabel = new JLabel("Type: ");
        propertyTypeLabel.setBounds(70, 200, 200, 40);
        propertyTypeLabel.setFont(new Font("", Font.BOLD, 15));
        propertyFrame.add(propertyTypeLabel);

        propertyTypesComboBox = new JComboBox<>();
        propertyTypesComboBox.setBounds(170, 205, 150, 30);
        propertyFrame.add(propertyTypesComboBox);

        // LOADS COMBOBOX DATAS FROM SQL
        loadPropertyTypesIntoComboBox(propertyTypesComboBox);

        ownerIdLabel = new JLabel("Owner ID: ");
        ownerIdLabel.setBounds(70, 260, 200, 40);
        ownerIdLabel.setFont(new Font("", Font.BOLD, 15));
        propertyFrame.add(ownerIdLabel);

        ownerIdTextField = new JTextField();
        ownerIdTextField.setBounds(170, 265, 200, 30);
        propertyFrame.add(ownerIdTextField);

        squareFeetLabel = new JLabel("Square Feet: ");
        squareFeetLabel.setBounds(70, 320, 200, 40);
        squareFeetLabel.setFont(new Font("", Font.BOLD, 15));
        propertyFrame.add(squareFeetLabel);

        squareFeetTextField = new JTextField();
        squareFeetTextField.setBounds(170, 325, 200, 30);
        propertyFrame.add(squareFeetTextField);

        priceLabel = new JLabel("Price: ");
        priceLabel.setBounds(70, 380, 200, 40);
        priceLabel.setFont(new Font("", Font.BOLD, 15));
        propertyFrame.add(priceLabel);

        priceTextField = new JTextField();
        priceTextField.setBounds(170, 385, 200, 30);
        propertyFrame.add(priceTextField);

        bedroomLabel = new JLabel("Bedrooms: ");
        bedroomLabel.setBounds(420, 140, 200, 40);
        bedroomLabel.setFont(new Font("", Font.BOLD, 15));
        propertyFrame.add(bedroomLabel);

        bedroomSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        bedroomSpinner.setBounds(530, 145, 50, 30);
        propertyFrame.add(bedroomSpinner);

        bathroomLabel = new JLabel("Bathrooms: ");
        bathroomLabel.setBounds(420, 200, 200, 40);
        bathroomLabel.setFont(new Font("", Font.BOLD, 15));
        propertyFrame.add(bathroomLabel);

        bathroomSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        bathroomSpinner.setBounds(530, 205, 50, 30);
        propertyFrame.add(bathroomSpinner);

        roomsLabel = new JLabel("Rooms: ");
        roomsLabel.setBounds(420, 260, 200, 40);
        roomsLabel.setFont(new Font("", Font.BOLD, 15));
        propertyFrame.add(roomsLabel);

        roomsSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 50, 1));
        roomsSpinner.setBounds(530, 265, 50, 30);
        propertyFrame.add(roomsSpinner);

        floorLabel = new JLabel("Floor: ");
        floorLabel.setBounds(420, 320, 200, 40);
        floorLabel.setFont(new Font("", Font.BOLD, 15));
        propertyFrame.add(floorLabel);

        floorSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        floorSpinner.setBounds(530, 325, 50, 30);
        propertyFrame.add(floorSpinner);

        balconyCheckBox = new JCheckBox("Balcony");
        balconyCheckBox.setBounds(650, 140, 150, 30);
        balconyCheckBox.setFont(new Font("", Font.BOLD, 15));
        propertyFrame.add(balconyCheckBox);

        poolCheckBox = new JCheckBox("Pool");
        poolCheckBox.setBounds(650, 200, 150, 30);
        poolCheckBox.setFont(new Font("", Font.BOLD, 15));
        propertyFrame.add(poolCheckBox);

        backyardCheckBox = new JCheckBox("Backyard");
        backyardCheckBox.setBounds(650, 260, 150, 30);
        backyardCheckBox.setFont(new Font("", Font.BOLD, 15));
        propertyFrame.add(backyardCheckBox);

        garageCheckBox = new JCheckBox("Garage");
        garageCheckBox.setBounds(650, 320, 150, 30);
        garageCheckBox.setFont(new Font("", Font.BOLD, 15));
        propertyFrame.add(garageCheckBox);

        liftCheckBox = new JCheckBox("Lift");
        liftCheckBox.setBounds(650, 380, 150, 30);
        liftCheckBox.setFont(new Font("", Font.BOLD, 15));
        propertyFrame.add(liftCheckBox);

        showButton = new JButton("SHOW");
        showButton.setBounds(150, 485, 100, 30);
        propertyFrame.add(showButton);

        addButton = new JButton("ADD");
        addButton.setBounds(300, 485, 100, 30);
        propertyFrame.add(addButton);

        editButton = new JButton("EDIT");
        editButton.setBounds(450, 485, 100, 30);
        propertyFrame.add(editButton);

        backButton = new JButton("BACK");
        backButton.setBounds(600, 485, 100, 30);
        propertyFrame.add(backButton);

        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // GETS PROPERTY ID
                int id = Integer.parseInt(idTextField.getText());

                // GETS RELEVANT ID'S PROPERTY INFOS
                String sql = "SELECT * FROM Properties WHERE id = ?";
                try (Connection conn = ConnectDB.getConnection()) {
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, id);
                    ResultSet rs = pstmt.executeQuery();

                    // IF RELEVANT PROPERTY FOUND
                    if (rs.next()) {
                        // DATAS THAT ARE SQL ENTERS JTEXTFİELDS AND SPINNERS
                        idTextField.setText(String.valueOf(rs.getInt("id")));
                        propertyTypesComboBox.setSelectedItem(rs.getString("type"));
                        ownerIdTextField.setText(String.valueOf(rs.getInt("ownerId")));
                        squareFeetTextField.setText(String.valueOf(rs.getDouble("squareFeet")));
                        priceTextField.setText(String.valueOf(rs.getDouble("price")));
                        bedroomSpinner.setValue(rs.getInt("bedrooms"));
                        bathroomSpinner.setValue(rs.getInt("bathrooms"));
                        roomsSpinner.setValue(rs.getInt("rooms"));
                        floorSpinner.setValue(rs.getInt("floor"));
                        balconyCheckBox.setSelected(rs.getBoolean("balcony"));
                        poolCheckBox.setSelected(rs.getBoolean("pool"));
                        backyardCheckBox.setSelected(rs.getBoolean("backyard"));
                        garageCheckBox.setSelected(rs.getBoolean("garage"));
                        liftCheckBox.setSelected(rs.getBoolean("lift"));
                    } else {
                        JOptionPane.showMessageDialog(null, "Property not found!");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error retrieving property: " + ex.getMessage());
                }

            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idTextField.getText());
                String type = propertyTypesComboBox.getSelectedItem().toString();
                int ownerId = Integer.parseInt(ownerIdTextField.getText());
                double squareFeet = Double.parseDouble(squareFeetTextField.getText());
                double price = Double.parseDouble(priceTextField.getText());
                int bedrooms = (Integer) bedroomSpinner.getValue();
                int bathrooms = (Integer) bathroomSpinner.getValue();
                int rooms = (Integer) roomsSpinner.getValue();
                int floor = (Integer) floorSpinner.getValue();
                boolean balcony = balconyCheckBox.isSelected();
                boolean pool = poolCheckBox.isSelected();
                boolean backyard = backyardCheckBox.isSelected();
                boolean garage = garageCheckBox.isSelected();
                boolean lift = liftCheckBox.isSelected();

                // UPDATES SQL
                String sql = "UPDATE Properties SET type = ?, ownerId = ?, squareFeet = ?, price = ?, bedrooms = ?, bathrooms = ?, rooms = ?, floor = ?, balcony = ?, pool = ?, backyard = ?, garage = ?, lift = ? WHERE id = ?";

                try (Connection conn = ConnectDB.getConnection()) {
                    PreparedStatement pstmt = conn.prepareStatement(sql);

                    // SETS PARAMETERS
                    pstmt.setString(1, type);
                    pstmt.setInt(2, ownerId);
                    pstmt.setDouble(3, squareFeet);
                    pstmt.setDouble(4, price);
                    pstmt.setInt(5, bedrooms);
                    pstmt.setInt(6, bathrooms);
                    pstmt.setInt(7, rooms);
                    pstmt.setInt(8, floor);
                    pstmt.setBoolean(9, balcony);
                    pstmt.setBoolean(10, pool);
                    pstmt.setBoolean(11, backyard);
                    pstmt.setBoolean(12, garage);
                    pstmt.setBoolean(13, lift);
                    pstmt.setInt(14, id);

                    // EXECUTES QUERY
                    int updated = pstmt.executeUpdate();

                    if (updated > 0) {
                        JOptionPane.showMessageDialog(null, "Property updated successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Property not found!");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error updating property: " + ex.getMessage());
                }
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idTextField.getText());
                String type = propertyTypesComboBox.getSelectedItem().toString();
                int ownerId = Integer.parseInt(ownerIdTextField.getText());
                double squareFeet = Double.parseDouble(squareFeetTextField.getText());
                double price = Double.parseDouble(priceTextField.getText());
                int bedrooms = (Integer) bedroomSpinner.getValue();
                int bathrooms = (Integer) bathroomSpinner.getValue();
                int rooms = (Integer) roomsSpinner.getValue();
                int floor = (Integer) floorSpinner.getValue();
                boolean balcony = balconyCheckBox.isSelected();
                boolean pool = poolCheckBox.isSelected();
                boolean backyard = backyardCheckBox.isSelected();
                boolean garage = garageCheckBox.isSelected();
                boolean lift = liftCheckBox.isSelected();

                addProperty(id, type, ownerId, squareFeet, price, bedrooms, bathrooms, rooms, floor, balcony, pool, backyard, garage, lift);
                PropertyRepository.setPropertiesTableModel();

            }
        });

        //to back admin view page
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminView();
                propertyFrame.dispose();
            }
        });

        propertyFrame.setVisible(true);

    }

    public void addProperty(int id, String type, int ownerId, double squareFeet, double price, int bedrooms, int bathrooms,
            int rooms, int floor, boolean balcony, boolean pool, boolean backyard,
            boolean garage, boolean lift) {
        String sql = "INSERT INTO Properties (id, type, ownerId, squareFeet, price, bedrooms, bathrooms, rooms, floor, balcony, pool, backyard, garage, lift) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectDB.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // Parametreleri set et
            pstmt.setInt(1, id);
            pstmt.setString(2, type);
            pstmt.setInt(3, ownerId);
            pstmt.setDouble(4, squareFeet);
            pstmt.setDouble(5, price);
            pstmt.setInt(6, bedrooms);
            pstmt.setInt(7, bathrooms);
            pstmt.setInt(8, rooms);
            pstmt.setInt(9, floor);
            pstmt.setBoolean(10, balcony);
            pstmt.setBoolean(11, pool);
            pstmt.setBoolean(12, backyard);
            pstmt.setBoolean(13, garage);
            pstmt.setBoolean(14, lift);

            // Sorguyu çalıştır
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Property added successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error adding property: " + e.getMessage());
        }
    }

    private void loadPropertyTypesIntoComboBox(JComboBox<String> comboBox) {
        List<String> propertyTypes = PropertyTypesRepository.getAllPropertyTypes();
        comboBox.removeAllItems(); // CLEARS OLD DATAS
        for (String type : propertyTypes) {
            comboBox.addItem(type); // ADDS NEW DATAS
        }
    }

}

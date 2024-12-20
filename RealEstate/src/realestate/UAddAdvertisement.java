package realestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author Hakan
 */
public class UAddAdvertisement extends JFrame {

    public UAddAdvertisement() {

        JFrame addAdvertisementFrame = new JFrame("Add Advertisement");
        addAdvertisementFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addAdvertisementFrame.setSize(1000, 700);
        addAdvertisementFrame.setLayout(null);
        addAdvertisementFrame.setLocationRelativeTo(null);

        JLabel propertyTypesLabel = new JLabel("ADD ADVERTISEMENT");
        propertyTypesLabel.setBounds(310, 50, 500, 40);
        propertyTypesLabel.setForeground(Color.DARK_GRAY);
        propertyTypesLabel.setFont(new Font("", Font.BOLD, 35));
        addAdvertisementFrame.add(propertyTypesLabel);

        JLabel ownerIdLabel = new JLabel("Owner ID: ");
        ownerIdLabel.setBounds(70, 140, 200, 40);
        ownerIdLabel.setFont(new Font("", Font.BOLD, 15));
        addAdvertisementFrame.add(ownerIdLabel);

        JTextField ownerIdTextField = new JTextField();
        ownerIdTextField.setBounds(170, 145, 100, 30);
        addAdvertisementFrame.add(ownerIdTextField);

        JLabel propertyTypeLabel = new JLabel("Type: ");
        propertyTypeLabel.setBounds(70, 200, 200, 40);
        propertyTypeLabel.setFont(new Font("", Font.BOLD, 15));
        addAdvertisementFrame.add(propertyTypeLabel);

        JComboBox propertyTypesComboBox = new JComboBox<>();
        propertyTypesComboBox.setBounds(170, 205, 150, 30);
        addAdvertisementFrame.add(propertyTypesComboBox);

        // SQL'den ComboBox verilerini yükle
        loadPropertyTypesIntoComboBox(propertyTypesComboBox);

        JLabel squareFeetLabel = new JLabel("Square Feet: ");
        squareFeetLabel.setBounds(70, 260, 200, 40);
        squareFeetLabel.setFont(new Font("", Font.BOLD, 15));
        addAdvertisementFrame.add(squareFeetLabel);

        JTextField squareFeetTextField = new JTextField();
        squareFeetTextField.setBounds(170, 265, 200, 30);
        addAdvertisementFrame.add(squareFeetTextField);

        JLabel priceLabel = new JLabel("Price: ");
        priceLabel.setBounds(70, 320, 200, 40);
        priceLabel.setFont(new Font("", Font.BOLD, 15));
        addAdvertisementFrame.add(priceLabel);

        JTextField priceTextField = new JTextField();
        priceTextField.setBounds(170, 325, 200, 30);
        addAdvertisementFrame.add(priceTextField);

        JLabel bedroomLabel = new JLabel("Bedrooms: ");
        bedroomLabel.setBounds(420, 140, 200, 40);
        bedroomLabel.setFont(new Font("", Font.BOLD, 15));
        addAdvertisementFrame.add(bedroomLabel);

        JSpinner bedroomSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        bedroomSpinner.setBounds(530, 145, 50, 30);
        addAdvertisementFrame.add(bedroomSpinner);

        JLabel bathroomLabel = new JLabel("Bathrooms: ");
        bathroomLabel.setBounds(420, 200, 200, 40);
        bathroomLabel.setFont(new Font("", Font.BOLD, 15));
        addAdvertisementFrame.add(bathroomLabel);

        JSpinner bathroomSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        bathroomSpinner.setBounds(530, 205, 50, 30);
        addAdvertisementFrame.add(bathroomSpinner);

        JLabel roomsLabel = new JLabel("Rooms: ");
        roomsLabel.setBounds(420, 260, 200, 40);
        roomsLabel.setFont(new Font("", Font.BOLD, 15));
        addAdvertisementFrame.add(roomsLabel);

        JSpinner roomsSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 50, 1));
        roomsSpinner.setBounds(530, 265, 50, 30);
        addAdvertisementFrame.add(roomsSpinner);

        JLabel floorLabel = new JLabel("Floor: ");
        floorLabel.setBounds(420, 320, 200, 40);
        floorLabel.setFont(new Font("", Font.BOLD, 15));
        addAdvertisementFrame.add(floorLabel);

        JSpinner floorSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        floorSpinner.setBounds(530, 325, 50, 30);
        addAdvertisementFrame.add(floorSpinner);

        JCheckBox balconyCheckBox = new JCheckBox("Balcony");
        balconyCheckBox.setBounds(650, 140, 150, 30);
        balconyCheckBox.setFont(new Font("", Font.BOLD, 15));
        addAdvertisementFrame.add(balconyCheckBox);

        JCheckBox poolCheckBox = new JCheckBox("Pool");
        poolCheckBox.setBounds(650, 200, 150, 30);
        poolCheckBox.setFont(new Font("", Font.BOLD, 15));
        addAdvertisementFrame.add(poolCheckBox);

        JCheckBox backyardCheckBox = new JCheckBox("Backyard");
        backyardCheckBox.setBounds(650, 260, 150, 30);
        backyardCheckBox.setFont(new Font("", Font.BOLD, 15));
        addAdvertisementFrame.add(backyardCheckBox);

        JCheckBox garageCheckBox = new JCheckBox("Garage");
        garageCheckBox.setBounds(650, 320, 150, 30);
        garageCheckBox.setFont(new Font("", Font.BOLD, 15));
        addAdvertisementFrame.add(garageCheckBox);

        JCheckBox liftCheckBox = new JCheckBox("Lift");
        liftCheckBox.setBounds(650, 380, 150, 30);
        liftCheckBox.setFont(new Font("", Font.BOLD, 15));
        addAdvertisementFrame.add(liftCheckBox);

        JButton addButton = new JButton("ADD");
        addButton.setBounds(250, 485, 100, 30);
        addAdvertisementFrame.add(addButton);

        JButton removeButton = new JButton("REMOVE");
        removeButton.setBounds(400, 485, 100, 30);
        addAdvertisementFrame.add(removeButton);

        JButton backButton = new JButton("BACK");
        backButton.setBounds(550, 485, 100, 30);
        addAdvertisementFrame.add(backButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
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

                addProperty(type, ownerId, squareFeet, price, bedrooms, bathrooms, rooms, floor, balcony, pool, backyard, garage, lift);
                PropertyRepository.setPropertiesTableModel();

            }
        });

        //to back user view page
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserView();
                addAdvertisementFrame.dispose();

            }
        });

        addAdvertisementFrame.setVisible(true);
    }

    public void addProperty(String type, int ownerId, double squareFeet, double price, int bedrooms, int bathrooms,
            int rooms, int floor, boolean balcony, boolean pool, boolean backyard,
            boolean garage, boolean lift) {
        String sql = "INSERT INTO Properties (type, ownerId, squareFeet, price, bedrooms, bathrooms, rooms, floor, balcony, pool, backyard, garage, lift) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectDB.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // Parametreleri set et
            pstmt.setInt(2, ownerId);
            pstmt.setString(1, type);
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
        comboBox.removeAllItems(); // DELETES OLD DATAS
        for (String type : propertyTypes) {
            comboBox.addItem(type); // ADDS NEW DATAS
        }
    }

}

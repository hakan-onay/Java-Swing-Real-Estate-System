package realestatesystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        JLabel propertyTypesLabel = new JLabel("ADD ADVERTISEMENT");
        propertyTypesLabel.setBounds(310, 50, 500, 40);
        propertyTypesLabel.setForeground(Color.DARK_GRAY);
        propertyTypesLabel.setFont(new Font("", Font.BOLD, 35));
        addAdvertisementFrame.add(propertyTypesLabel);

        JLabel idLabel = new JLabel("ID: ");
        idLabel.setBounds(70, 140, 200, 40);
        idLabel.setFont(new Font("", Font.BOLD, 15));
        addAdvertisementFrame.add(idLabel);

        JTextField idTextField = new JTextField();
        idTextField.setBounds(170, 145, 100, 30);
        addAdvertisementFrame.add(idTextField);

        JLabel propertyTypeLabel = new JLabel("Type: ");
        propertyTypeLabel.setBounds(70, 200, 200, 40);
        propertyTypeLabel.setFont(new Font("", Font.BOLD, 15));
        addAdvertisementFrame.add(propertyTypeLabel);

        JComboBox<String> propertyTypesComboBox = new JComboBox<>(new String[]{"Sale", "Rental", "Daily Rental"});
        propertyTypesComboBox.setBounds(170, 205, 150, 30);
        addAdvertisementFrame.add(propertyTypesComboBox);

        JLabel ownerIdLabel = new JLabel("Owner ID: ");
        ownerIdLabel.setBounds(70, 260, 200, 40);
        ownerIdLabel.setFont(new Font("", Font.BOLD, 15));
        addAdvertisementFrame.add(ownerIdLabel);

        JTextField ownerIdTextField = new JTextField();
        ownerIdTextField.setBounds(170, 265, 200, 30);
        addAdvertisementFrame.add(ownerIdTextField);

        JLabel squareFeetLabel = new JLabel("Square Feet: ");
        squareFeetLabel.setBounds(70, 320, 200, 40);
        squareFeetLabel.setFont(new Font("", Font.BOLD, 15));
        addAdvertisementFrame.add(squareFeetLabel);

        JTextField squareFeetTextField = new JTextField();
        squareFeetTextField.setBounds(170, 325, 200, 30);
        addAdvertisementFrame.add(squareFeetTextField);

        JLabel priceLabel = new JLabel("Price: ");
        priceLabel.setBounds(70, 380, 200, 40);
        priceLabel.setFont(new Font("", Font.BOLD, 15));
        addAdvertisementFrame.add(priceLabel);

        JTextField priceLabelTextField = new JTextField();
        priceLabelTextField.setBounds(170, 385, 200, 30);
        addAdvertisementFrame.add(priceLabelTextField);

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

        JLabel ageOfHouseLabel = new JLabel("Age of House: ");
        ageOfHouseLabel.setBounds(420, 260, 200, 40);
        ageOfHouseLabel.setFont(new Font("", Font.BOLD, 15));
        addAdvertisementFrame.add(ageOfHouseLabel);

        JSpinner ageOfHouseSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 50, 1));
        ageOfHouseSpinner.setBounds(530, 265, 50, 30);
        addAdvertisementFrame.add(ageOfHouseSpinner);

        JLabel roomsLabel = new JLabel("Rooms: ");
        roomsLabel.setBounds(420, 320, 200, 40);
        roomsLabel.setFont(new Font("", Font.BOLD, 15));
        addAdvertisementFrame.add(roomsLabel);

        JSpinner roomsSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        roomsSpinner.setBounds(530, 325, 50, 30);
        addAdvertisementFrame.add(roomsSpinner);

        JLabel floorLabel = new JLabel("Floor: ");
        floorLabel.setBounds(420, 380, 200, 40);
        floorLabel.setFont(new Font("", Font.BOLD, 15));
        addAdvertisementFrame.add(floorLabel);

        JSpinner floorSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        floorSpinner.setBounds(530, 385, 50, 30);
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
                int id = Integer.parseInt(idTextField.getText());
                String type = propertyTypesComboBox.getSelectedItem().toString();
                int ownerId = Integer.parseInt(ownerIdTextField.getText());
                double squareFeet = Double.parseDouble(squareFeetTextField.getText());
                double price = Double.parseDouble(priceLabelTextField.getText());
                int bedrooms = (Integer) bedroomSpinner.getValue();
                int bathrooms = (Integer) bathroomSpinner.getValue();
                int ageOfHouse = (Integer) ageOfHouseSpinner.getValue();
                int rooms = (Integer) roomsSpinner.getValue();
                int floor = (Integer) floorSpinner.getValue();
                boolean balcony = balconyCheckBox.isSelected();
                boolean pool = poolCheckBox.isSelected();
                boolean backyard = backyardCheckBox.isSelected();
                boolean garage = garageCheckBox.isSelected();
                boolean lift = liftCheckBox.isSelected();
                
                PropertyRepository.addProperty(id, type, ownerId, squareFeet, price,
                        bedrooms, bathrooms, ageOfHouse, rooms, floor, balcony, pool, backyard, garage, lift);


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

}

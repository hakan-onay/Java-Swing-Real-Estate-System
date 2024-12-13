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
public class APropertyView extends JFrame {

    private JFrame propertyFrame;
    private JLabel propertyTypesLabel, idLabel, propertyTypeLabel, ownerIdLabel, squareFeetLabel, priceLabel, bedroomLabel, bathroomLabel, ageOfHouseLabel,
            roomsLabel, floorLabel;
    private JTextField idTextField, ownerIdTextField, squareFeetTextField, priceTextField;
    private JComboBox<String> propertyTypesComboBox;
    private JSpinner bedroomSpinner, bathroomSpinner, ageOfHouseSpinner, roomsSpinner, floorSpinner;
    private JCheckBox balconyCheckBox, poolCheckBox, backyardCheckBox, garageCheckBox, liftCheckBox;
    private JButton addButton,editButton,removeButton,backButton;

    public APropertyView() {

        propertyFrame = new JFrame("Properties");
        propertyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        propertyFrame.setSize(1000, 700);
        propertyFrame.setLayout(null);

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

        propertyTypesComboBox = new JComboBox<>(new String[]{"Sale", "Rental", "Daily Rental"});
        propertyTypesComboBox.setBounds(170, 205, 150, 30);
        propertyFrame.add(propertyTypesComboBox);

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

        ageOfHouseLabel = new JLabel("Age of House: ");
        ageOfHouseLabel.setBounds(420, 260, 200, 40);
        ageOfHouseLabel.setFont(new Font("", Font.BOLD, 15));
        propertyFrame.add(ageOfHouseLabel);

        ageOfHouseSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 50, 1));
        ageOfHouseSpinner.setBounds(530, 265, 50, 30);
        propertyFrame.add(ageOfHouseSpinner);

        roomsLabel = new JLabel("Rooms: ");
        roomsLabel.setBounds(420, 320, 200, 40);
        roomsLabel.setFont(new Font("", Font.BOLD, 15));
        propertyFrame.add(roomsLabel);

        roomsSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        roomsSpinner.setBounds(530, 325, 50, 30);
        propertyFrame.add(roomsSpinner);

        floorLabel = new JLabel("Floor: ");
        floorLabel.setBounds(420, 380, 200, 40);
        floorLabel.setFont(new Font("", Font.BOLD, 15));
        propertyFrame.add(floorLabel);

        floorSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        floorSpinner.setBounds(530, 385, 50, 30);
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

        addButton = new JButton("ADD");
        addButton.setBounds(100, 485, 100, 30);
        propertyFrame.add(addButton);

        editButton = new JButton("EDIT");
        editButton.setBounds(250, 485, 100, 30);
        propertyFrame.add(editButton);

        removeButton = new JButton("REMOVE");
        removeButton.setBounds(400, 485, 100, 30);
        propertyFrame.add(removeButton);

        backButton = new JButton("BACK");
        backButton.setBounds(550, 485, 100, 30);
        propertyFrame.add(backButton);

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

}

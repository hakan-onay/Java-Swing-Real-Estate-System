package realestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hakan
 */
public class ASalesView extends JFrame {

    private JFrame salesFrame;
    private JTable salesTable;
    private DefaultTableModel tableModel;
    private JLabel propertyTypesLabel;
    private JButton backButton, removeButton, editButton;

    public ASalesView() {
        salesFrame = new JFrame("Sales");
        salesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        salesFrame.setSize(1200, 700);
        salesFrame.setLayout(null);
        salesFrame.setLocationRelativeTo(null);

        propertyTypesLabel = new JLabel("SALES");
        propertyTypesLabel.setBounds(600, 50, 500, 40);
        propertyTypesLabel.setForeground(Color.DARK_GRAY);
        propertyTypesLabel.setFont(new Font("", Font.BOLD, 35));
        salesFrame.add(propertyTypesLabel);

        tableModel = PropertyRepository.getPropertyModel();
        salesTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(salesTable);
        scrollPane.setBounds(50, 110, 1075, 400);
        salesFrame.add(scrollPane);

        removeButton = new JButton("REMOVE");
        removeButton.setBounds(250, 525, 100, 30);
        salesFrame.add(removeButton);

        editButton = new JButton("EDIT");
        editButton.setBounds(405, 525, 100, 30);
        salesFrame.add(editButton);

        backButton = new JButton("BACK");
        backButton.setBounds(560, 525, 100, 30);
        salesFrame.add(backButton);

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = salesTable.getSelectedRow();
                if (selectedRow != -1) {
                    // Seçili satır varsa, o satırı siler
                    tableModel.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(salesFrame, "Please select a row to remove.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // SETS THE TABLE WHEN CLICK EDIT BUTTON
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = salesTable.getSelectedRow();
                if (selectedRow != -1) {

                    String id = salesTable.getValueAt(selectedRow, 0).toString();
                    String type = salesTable.getValueAt(selectedRow, 1).toString();
                    String ownerId = salesTable.getValueAt(selectedRow, 2).toString();
                    String squareFeet = salesTable.getValueAt(selectedRow, 3).toString();
                    String price = salesTable.getValueAt(selectedRow, 4).toString();
                    String bedrooms = salesTable.getValueAt(selectedRow, 5).toString();
                    String bathrooms = salesTable.getValueAt(selectedRow, 6).toString();
                    String age = salesTable.getValueAt(selectedRow, 7).toString();
                    String rooms = salesTable.getValueAt(selectedRow, 8).toString();
                    String floor = salesTable.getValueAt(selectedRow, 9).toString();
                    String balcony = salesTable.getValueAt(selectedRow, 10).toString();
                    String pool = salesTable.getValueAt(selectedRow, 11).toString();
                    String backyard = salesTable.getValueAt(selectedRow, 12).toString();
                    String garage = salesTable.getValueAt(selectedRow, 13).toString();
                    String lift = salesTable.getValueAt(selectedRow, 14).toString();

                    // OPENS EDIT FRAME
                    String newId = JOptionPane.showInputDialog(salesFrame, "Edit ID:", id);
                    if (newId != null) {
                        id = newId;
                    }

                    String newType = JOptionPane.showInputDialog(salesFrame, "Edit Type:", type);
                    if (newType != null) {
                        type = newType;
                    }

                    String newOwnerId = JOptionPane.showInputDialog(salesFrame, "Edit Owner ID:", ownerId);
                    if (newOwnerId != null) {
                        ownerId = newOwnerId;
                    }

                    String newSquareFeet = JOptionPane.showInputDialog(salesFrame, "Edit Square Feet:", squareFeet);
                    if (newSquareFeet != null) {
                        squareFeet = newSquareFeet;
                    }

                    String newPrice = JOptionPane.showInputDialog(salesFrame, "Edit Price:", price);
                    if (newPrice != null) {
                        price = newPrice;
                    }

                    String newBedrooms = JOptionPane.showInputDialog(salesFrame, "Edit Bedrooms:", bedrooms);
                    if (newBedrooms != null) {
                        bedrooms = newBedrooms;
                    }

                    String newBathrooms = JOptionPane.showInputDialog(salesFrame, "Edit Bathrooms:", bathrooms);
                    if (newBathrooms != null) {
                        bathrooms = newBathrooms;
                    }

                    String newAge = JOptionPane.showInputDialog(salesFrame, "Edit Age:", age);
                    if (newAge != null) {
                        age = newAge;
                    }

                    String newRooms = JOptionPane.showInputDialog(salesFrame, "Edit Rooms:", rooms);
                    if (newRooms != null) {
                        rooms = newRooms;
                    }

                    String newFloor = JOptionPane.showInputDialog(salesFrame, "Edit Floor:", floor);
                    if (newFloor != null) {
                        floor = newFloor;
                    }

                    String newBalcony = JOptionPane.showInputDialog(salesFrame, "Edit Balcony:", balcony);
                    if (newBalcony != null) {
                        balcony = newBalcony;
                    }

                    String newPool = JOptionPane.showInputDialog(salesFrame, "Edit Pool:", pool);
                    if (newPool != null) {
                        pool = newPool;
                    }

                    String newBackyard = JOptionPane.showInputDialog(salesFrame, "Edit Backyard:", backyard);
                    if (newBackyard != null) {
                        backyard = newBackyard;
                    }

                    String newGarage = JOptionPane.showInputDialog(salesFrame, "Edit Garage:", garage);
                    if (newGarage != null) {
                        garage = newGarage;
                    }

                    String newLift = JOptionPane.showInputDialog(salesFrame, "Edit Lift:", lift);
                    if (newLift != null) {
                        lift = newLift;
                    }
                    //Adding new values to the table
                    salesTable.setValueAt(id, selectedRow, 0);
                    salesTable.setValueAt(type, selectedRow, 1);
                    salesTable.setValueAt(ownerId, selectedRow, 2);
                    salesTable.setValueAt(squareFeet, selectedRow, 3);
                    salesTable.setValueAt(price, selectedRow, 4);
                    salesTable.setValueAt(bedrooms, selectedRow, 5);
                    salesTable.setValueAt(bathrooms, selectedRow, 6);
                    salesTable.setValueAt(age, selectedRow, 7);
                    salesTable.setValueAt(rooms, selectedRow, 8);
                    salesTable.setValueAt(floor, selectedRow, 9);
                    salesTable.setValueAt(balcony, selectedRow, 10);
                    salesTable.setValueAt(pool, selectedRow, 11);
                    salesTable.setValueAt(backyard, selectedRow, 12);
                    salesTable.setValueAt(garage, selectedRow, 13);
                    salesTable.setValueAt(lift, selectedRow, 14);

                } else {
                    JOptionPane.showMessageDialog(salesFrame, "Please select a row to edit.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //to back admin view page
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminView();
                salesFrame.dispose();
            }
        });

        salesFrame.setVisible(true);
    }

}

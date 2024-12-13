package realestatesystem;

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
        salesFrame.setSize(1000, 700);
        salesFrame.setLayout(null);

        propertyTypesLabel = new JLabel("SALES");
        propertyTypesLabel.setBounds(400, 50, 500, 40);
        propertyTypesLabel.setForeground(Color.DARK_GRAY);
        propertyTypesLabel.setFont(new Font("", Font.BOLD, 35));
        salesFrame.add(propertyTypesLabel);

        tableModel = new DefaultTableModel(new String[]{"ID", "Type", "Owner ID", "Square Feet", "Price", "Bedrooms", "Bathrooms"
            + "Age", "Rooms", "Balcony", "Pool", "Backyard", "Garage", "Lift"}, 0);
        salesTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(salesTable);
        scrollPane.setBounds(50, 110, 875, 400);
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
                    JOptionPane.showMessageDialog(salesFrame, "Please select a row to remove.","ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // "EDIT" butonuna tıklandığında tabloyu düzenler
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = salesTable.getSelectedRow();
                if (selectedRow != -1) {
                    // Seçili satır varsa, her bir hücreyi alır ve düzenler
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

                    // Düzenleme işlemi için yeni bir pencere açabilirsiniz (JTextField veya başka komponentler ile)
                    String newPrice = JOptionPane.showInputDialog(salesFrame, "Edit Price:", price);
                    if (newPrice != null) {
                        salesTable.setValueAt(newPrice, selectedRow, 4);  // Fiyatı günceller
                    }
                } else {
                    JOptionPane.showMessageDialog(salesFrame, "Please select a row to edit.","ERROR",JOptionPane.ERROR_MESSAGE);
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

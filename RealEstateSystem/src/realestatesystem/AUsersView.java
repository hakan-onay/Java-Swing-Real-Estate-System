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
public class AUsersView extends JFrame {

    private JFrame usersFrame;
    private JTable usersTable;
    private DefaultTableModel tableModel;
    private JLabel propertyTypesLabel;
    private JButton backButton, removeButton, editButton;

    public AUsersView() {
        usersFrame = new JFrame("Users");
        usersFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        usersFrame.setSize(1000, 700);
        usersFrame.setLayout(null);

        propertyTypesLabel = new JLabel("USERS");
        propertyTypesLabel.setBounds(400, 50, 500, 40);
        propertyTypesLabel.setForeground(Color.DARK_GRAY);
        propertyTypesLabel.setFont(new Font("", Font.BOLD, 35));
        usersFrame.add(propertyTypesLabel);

        tableModel = UserRepository.getUserModel();
        usersTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(usersTable);
        scrollPane.setBounds(50, 110, 875, 400);
        usersFrame.add(scrollPane);

        removeButton = new JButton("REMOVE");
        removeButton.setBounds(250, 525, 100, 30);
        usersFrame.add(removeButton);

        editButton = new JButton("EDIT");
        editButton.setBounds(405, 525, 100, 30);
        usersFrame.add(editButton);

        backButton = new JButton("BACK");
        backButton.setBounds(560, 525, 100, 30);
        usersFrame.add(backButton);
        
        
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = usersTable.getSelectedRow();
                if (selectedRow != -1) {
                    // Seçili satır varsa, o satır silinir
                    tableModel.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(usersFrame, "Please select a row to remove.","ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // "EDIT" butonuna tıklanıldığında seçili satırda düzenleme yapılır
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = usersTable.getSelectedRow();
                if (selectedRow != -1) {
                    // Seçili satırdaki veriler alınır
                    String name = usersTable.getValueAt(selectedRow, 0).toString();
                    String surname = usersTable.getValueAt(selectedRow, 1).toString();
                    String email = usersTable.getValueAt(selectedRow, 2).toString();
                    String phoneNumber = usersTable.getValueAt(selectedRow, 3).toString();
                    String password = usersTable.getValueAt(selectedRow, 4).toString();

                    // Düzenleme penceresi açılır
                    String newName = JOptionPane.showInputDialog(usersFrame, "Edit Name:", name);
                    if (newName != null) name = newName;

                    String newSurname = JOptionPane.showInputDialog(usersFrame, "Edit Surname:", surname);
                    if (newSurname != null) surname = newSurname;

                    String newEmail = JOptionPane.showInputDialog(usersFrame, "Edit Email:", email);
                    if (newEmail != null) email = newEmail;

                    String newPhoneNumber = JOptionPane.showInputDialog(usersFrame, "Edit Phone Number:", phoneNumber);
                    if (newPhoneNumber != null) phoneNumber = newPhoneNumber;

                    String newPassword = JOptionPane.showInputDialog(usersFrame, "Edit Password:", password);
                    if (newPassword != null) password = newPassword;

                    // Düzenlenen veriler tabloya güncellenir
                    usersTable.setValueAt(name, selectedRow, 0);
                    usersTable.setValueAt(surname, selectedRow, 1);
                    usersTable.setValueAt(email, selectedRow, 2);
                    usersTable.setValueAt(phoneNumber, selectedRow, 3);
                    usersTable.setValueAt(password, selectedRow, 4);
                } else {
                    JOptionPane.showMessageDialog(usersFrame, "Please select a row to edit.","ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        

        //to back admin view page
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminView();
                usersFrame.dispose();
            }
        });

        usersFrame.setVisible(true);

    }
    
    private void addSampleData() {
        // Bu fonksiyon örnek veri ekler. Gerçek uygulamada, bu veriler veritabanından alınmalıdır.
        tableModel.addRow(new Object[]{"John", "Doe", "john.doe@example.com", "1234567890", "password123"});
        tableModel.addRow(new Object[]{"Jane", "Smith", "jane.smith@example.com", "0987654321", "securePass"});
    }

}

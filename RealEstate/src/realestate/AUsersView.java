package realestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

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
        usersFrame.setLocationRelativeTo(null);

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
                    // GET USER ID THAT IS SELECTED
                    String userId = usersTable.getValueAt(selectedRow, 0).toString();  // ID sütunu burada varsayılıyor

                    // DELETING DATA QUERY
                    String sql = "DELETE FROM Users WHERE id = ?";

                    try (Connection conn = ConnectDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

                        // ADDING ID TO THE QUERY
                        pstmt.setString(1, userId);

                        // DELETING PROCESS
                        int rowsDeleted = pstmt.executeUpdate();
                        if (rowsDeleted > 0) {
                            // UPDATE TABLE
                            tableModel.removeRow(selectedRow);
                            JOptionPane.showMessageDialog(usersFrame, "User deleted successfully.", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(usersFrame, "Failed to delete user from database.", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(usersFrame, "An error occurred while deleting the user.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(usersFrame, "Please select a row to remove.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // IF EDIT BUTTON IS CLICKED YOU CAN EDIT THE ROW THAT IS SELECTED
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = usersTable.getSelectedRow();
                if (selectedRow != -1) {
                    // GIVES SELECTED ROWS DATAS
                    String userId = usersTable.getValueAt(selectedRow, 0).toString();
                    String name = usersTable.getValueAt(selectedRow, 1).toString();
                    String surname = usersTable.getValueAt(selectedRow, 2).toString();
                    String telephoneNo = usersTable.getValueAt(selectedRow, 3).toString();
                    String email = usersTable.getValueAt(selectedRow, 4).toString();
                    String password = usersTable.getValueAt(selectedRow, 5).toString();

                    // OPENING EDIT FRAME
                    String newName = JOptionPane.showInputDialog(usersFrame, "Edit Name:", name);
                    if (newName != null) {
                        name = newName;
                    }

                    String newSurname = JOptionPane.showInputDialog(usersFrame, "Edit Surname:", surname);
                    if (newSurname != null) {
                        surname = newSurname;
                    }

                    String newEmail = JOptionPane.showInputDialog(usersFrame, "Edit Email:", email);
                    if (newEmail != null) {
                        email = newEmail;
                    }

                    String newTelephoneNo = JOptionPane.showInputDialog(usersFrame, "Edit Phone Number:", telephoneNo);
                    if (telephoneNo != null) {
                        telephoneNo = newTelephoneNo;
                    }

                    String newPassword = JOptionPane.showInputDialog(usersFrame, "Edit Password:", password);
                    if (newPassword != null) {
                        password = newPassword;
                    }

                    // SETTING NEW DATAS TO THE TABLE
                    usersTable.setValueAt(name, selectedRow, 1);
                    usersTable.setValueAt(surname, selectedRow, 2);
                    usersTable.setValueAt(telephoneNo, selectedRow, 3);
                    usersTable.setValueAt(email, selectedRow, 4);
                    usersTable.setValueAt(password, selectedRow, 5);

                    // SETTING SQL TABLE FOR NEW DATAS
                    String sql = "UPDATE Users SET name = ?, surname = ?, telephoneNo = ?, email = ?, password = ? WHERE id = ?";

                    try (Connection conn = ConnectDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

                        // SETTING QUERY PARAMETERS
                        pstmt.setString(6, userId); // UPDATING THE DATAS USING ID 
                        pstmt.setString(1, name);
                        pstmt.setString(2, surname);
                        pstmt.setString(3, telephoneNo);
                        pstmt.setString(4, email);
                        pstmt.setString(5, password);

                        // EXECUTE QUERY
                        int rowsUpdated = pstmt.executeUpdate();
                        if (rowsUpdated > 0) {
                            JOptionPane.showMessageDialog(usersFrame, "User updated successfully.", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(usersFrame, "Failed to update user.", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(usersFrame, "An error occurred while updating the user.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(usersFrame, "Please select a row to edit.", "ERROR", JOptionPane.ERROR_MESSAGE);
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

}

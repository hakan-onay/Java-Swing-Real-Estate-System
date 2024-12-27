package realestate;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author Hakan
 */
public class RegisterView extends JFrame {

    public RegisterView() {

        JFrame registerFrame = new JFrame("Register");
        registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registerFrame.setSize(700, 600);
        registerFrame.setLayout(null);
        registerFrame.setLocationRelativeTo(null);

        JLabel registerNameLabel = new JLabel("Name: ");
        registerNameLabel.setBounds(100, 20, 160, 40);
        registerNameLabel.setFont(new Font("", Font.BOLD, 17));
        registerFrame.add(registerNameLabel);

        JTextField resgisterNameTextField = new JTextField();
        resgisterNameTextField.setBounds(230, 25, 300, 30);
        registerFrame.add(resgisterNameTextField);

        JLabel registerSurnameLabel = new JLabel("Surname: ");
        registerSurnameLabel.setBounds(100, 80, 160, 40);
        registerSurnameLabel.setFont(new Font("", Font.BOLD, 17));
        registerFrame.add(registerSurnameLabel);

        JTextField registerSurnameField = new JTextField();
        registerSurnameField.setBounds(230, 85, 300, 30);
        registerFrame.add(registerSurnameField);

        JLabel registerEmailLabel = new JLabel("E-mail: ");
        registerEmailLabel.setBounds(100, 140, 160, 40);
        registerEmailLabel.setFont(new Font("", Font.BOLD, 17));
        registerFrame.add(registerEmailLabel);

        JTextField resgisterEmailTextField = new JTextField();
        resgisterEmailTextField.setBounds(230, 145, 300, 30);
        registerFrame.add(resgisterEmailTextField);

        JLabel phoneNumberLabel = new JLabel("Phone Number: ");
        phoneNumberLabel.setBounds(100, 200, 160, 40);
        phoneNumberLabel.setFont(new Font("", Font.BOLD, 17));
        registerFrame.add(phoneNumberLabel);

        JTextField phoneNumberTextField = new JTextField();
        phoneNumberTextField.setBounds(230, 205, 300, 30);
        registerFrame.add(phoneNumberTextField);

        JLabel registerPasswordLabel = new JLabel("Password: ");
        registerPasswordLabel.setBounds(100, 260, 160, 40);
        registerPasswordLabel.setFont(new Font("", Font.BOLD, 17));
        registerFrame.add(registerPasswordLabel);

        JPasswordField resgisterPasswordTextField = new JPasswordField();
        resgisterPasswordTextField.setBounds(230, 265, 300, 30);
        registerFrame.add(resgisterPasswordTextField);

        JLabel registerPasswordLabel2 = new JLabel("Password: ");
        registerPasswordLabel2.setBounds(100, 320, 160, 40);
        registerPasswordLabel2.setFont(new Font("", Font.BOLD, 17));
        registerFrame.add(registerPasswordLabel2);

        JPasswordField registerPasswordTextField2 = new JPasswordField();
        registerPasswordTextField2.setBounds(230, 325, 300, 30);
        registerFrame.add(registerPasswordTextField2);

        JButton registerButton = new JButton("REGISTER");
        registerButton.setBounds(160, 385, 100, 30);
        registerFrame.add(registerButton);

        JButton backButton = new JButton("BACK");
        backButton.setBounds(370, 385, 100, 30);
        registerFrame.add(backButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = resgisterNameTextField.getText();
                String surname = registerSurnameField.getText();
                String email = resgisterEmailTextField.getText();
                int telephoneNo = Integer.parseInt(phoneNumberTextField.getText());
                String password = resgisterPasswordTextField.getText();
                String confirmPassword = registerPasswordTextField2.getText();

                if (password.equals(confirmPassword)) {
                    if (!name.isEmpty() && !surname.isEmpty() && !email.isEmpty() && !password.isEmpty() && (telephoneNo != 0)) {
                        saveToDatabase(name, surname, telephoneNo, email, password);
                        JOptionPane.showMessageDialog(registerFrame, "Registration Successful!");
                        new LogInView();
                        registerFrame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(registerFrame, "Please fill all the fields!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(registerFrame, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //to back sign in page
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LogInView();
                registerFrame.dispose();
            }
        });

        registerFrame.setVisible(true);
    }

    private void saveToDatabase(String name, String surname, int telephoneNo, String email, String password) {
        String sqlInsert = "INSERT INTO Users (name, surname, telephoneNo, email, password) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectDB.getConnection()) {
            try (PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {
                pstmt.setString(1, name);
                pstmt.setString(2, surname);
                pstmt.setInt(3, telephoneNo);
                pstmt.setString(4, email);
                pstmt.setString(5, password);
                pstmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}

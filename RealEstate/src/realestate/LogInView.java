package realestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author Hakan
 */
public class LogInView extends JFrame {

    public LogInView() {

        JFrame signInFrame = new JFrame("Logn In");
        signInFrame.setSize(1000, 700);
        signInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        signInFrame.setLayout(null);
        signInFrame.setLocationRelativeTo(null);

        JLabel welcomeLabel = new JLabel("--REAL ESTATE SYSTEM--");
        welcomeLabel.setBounds(270, 50, 500, 40);
        welcomeLabel.setForeground(Color.DARK_GRAY);
        welcomeLabel.setFont(new Font("", Font.BOLD, 35));
        signInFrame.add(welcomeLabel);

        JLabel logoLabel = new JLabel(new ImageIcon(LogInView.class.getResource("/images/house.png")));
        logoLabel.setBounds(440, 82, 80, 80);
        signInFrame.add(logoLabel);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(50, 140, 880, 400);
        tabbedPane.setBackground(Color.WHITE);
        signInFrame.add(tabbedPane);

        JPanel userPanel = new JPanel();
        userPanel.setBackground(Color.WHITE);
        tabbedPane.addTab("User", null, userPanel, null);
        userPanel.setLayout(null);

        JPanel adminPanel = new JPanel();
        adminPanel.setBackground(Color.WHITE);
        tabbedPane.addTab("Admin", null, adminPanel, null);
        adminPanel.setLayout(null);

        JLabel adminEmailLabel = new JLabel("E-MAIL ADDRESS: ");
        adminEmailLabel.setBounds(70, 50, 200, 40);
        adminEmailLabel.setFont(new Font("", Font.BOLD, 20));
        adminPanel.add(adminEmailLabel);

        JTextField adminEmailTextField = new JTextField();
        adminEmailTextField.setBounds(270, 55, 300, 30);
        adminPanel.add(adminEmailTextField);

        JLabel adminPasswordLabel = new JLabel("PASSWORD: ");
        adminPasswordLabel.setBounds(70, 130, 160, 40);
        adminPasswordLabel.setFont(new Font("", Font.BOLD, 20));
        adminPanel.add(adminPasswordLabel);

        JPasswordField adminPasswordField = new JPasswordField();
        adminPasswordField.setBounds(270, 135, 300, 30);
        adminPanel.add(adminPasswordField);

        JButton adminLoginButton = new JButton("LOG IN");
        adminLoginButton.setBounds(200, 220, 150, 30);
        adminPanel.add(adminLoginButton);

        JLabel userEmailLabel = new JLabel("E-MAIL ADDRESS: ");
        userEmailLabel.setBounds(70, 50, 200, 40);
        userEmailLabel.setFont(new Font("", Font.BOLD, 20));
        userPanel.add(userEmailLabel);

        JTextField userEmailTextField = new JTextField();
        userEmailTextField.setBounds(270, 55, 300, 30);
        userPanel.add(userEmailTextField);

        JLabel userPasswordLabel = new JLabel("PASSWORD: ");
        userPasswordLabel.setBounds(70, 130, 160, 40);
        userPasswordLabel.setFont(new Font("", Font.BOLD, 20));
        userPanel.add(userPasswordLabel);

        JPasswordField userPasswordField = new JPasswordField();
        userPasswordField.setBounds(270, 135, 300, 30);
        userPanel.add(userPasswordField);

        JButton userLoginButton = new JButton("LOG IN");
        userLoginButton.setBounds(200, 220, 150, 30);
        userPanel.add(userLoginButton);

        JButton userRegisterButton = new JButton("REGISTER");
        userRegisterButton.setBounds(450, 220, 150, 30);
        userPanel.add(userRegisterButton);

        //to go user view page
        userLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = userEmailTextField.getText();
                String password = userPasswordField.getText();

                if (email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(signInFrame, "You have to enter your e-mail and password!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    int userId = authenticateUser(email, password); // GETTING USER ID

                    if (userId != -1) {
                        JOptionPane.showMessageDialog(signInFrame, "Login Successful! User ID: " + userId);
                        new UserView();
                        signInFrame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(signInFrame, "Invalid email or password.");
                    }
                }
            }
        });
        //to go register page
        userRegisterButton.addActionListener(e -> {
            new RegisterView();
            signInFrame.dispose();

        });
        //to go admin view page
        adminLoginButton.addActionListener(e -> {

            String email = adminEmailTextField.getText();
            String password = adminPasswordField.getText();

            if (adminEmailTextField.getText().isEmpty() || adminPasswordField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(signInFrame, "You have to enter your e-mail and password!", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (authenticateAdmin(email, password)) {
                JOptionPane.showMessageDialog(signInFrame, "Login Successful!");
                new AdminView();
                signInFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(signInFrame, "Invalid email or password.");
            }

        });

        signInFrame.setVisible(true);

    }

    public int authenticateUser(String email, String password) {
        String sql = "SELECT id FROM Users WHERE email = ? AND password = ?";
        try (Connection conn = ConnectDB.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id"); // RETURNING USER ID
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // IF USER DOESNT EXIST RETURN -1
    }

    private static boolean authenticateAdmin(String email, String password) {
        String query = "SELECT * FROM Admins WHERE email = ? AND password = ?";
        try (Connection conn = ConnectDB.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            return rs.next(); // If admin exist login success
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

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
public class LogInView extends JFrame {

    public LogInView() {

        JFrame signInFrame = new JFrame("Logn In");
        signInFrame.setSize(1000, 700);
        signInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        signInFrame.setLayout(null);

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

                if (userEmailTextField.getText().isEmpty() || userPasswordField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(signInFrame, "You have to enter your e-mail and password!", "Error", JOptionPane.ERROR_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(signInFrame, "Login successfully.", "Message", JOptionPane.INFORMATION_MESSAGE);
                    new UserView();
                    signInFrame.dispose();
                }
            }
        });
        //to go register page
        userRegisterButton.addActionListener(e -> {
            RegisterView register = new RegisterView();
            signInFrame.dispose();

        });
        //to go admin view page
        adminLoginButton.addActionListener(e -> {

            new AdminView();
            signInFrame.dispose();

        });

        signInFrame.setVisible(true);

    }
}

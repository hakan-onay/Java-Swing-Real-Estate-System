package realestatesystem;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
                String phoneNumber = phoneNumberTextField.getText();
                String password = new String(resgisterPasswordTextField.getPassword());
                String confirmPassword = new String(registerPasswordTextField2.getPassword());

                if (password.equals(confirmPassword)) {
                    if (!name.isEmpty() && !surname.isEmpty() && !email.isEmpty() && !phoneNumber.isEmpty() && !password.isEmpty()) {
                        UserRepository.addUser(name, surname, email, phoneNumber, password); // Ara depoya ekleme
                        JOptionPane.showMessageDialog(registerFrame, "Registration Successful!");
                        new LogInView(); // Kayıt başarılıysa giriş sayfasına dön
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

}

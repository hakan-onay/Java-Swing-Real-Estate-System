package realestate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Hakan
 */
public class UserView extends JFrame {

    public UserView() {
        JFrame userFrame = new JFrame("User");
        userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userFrame.setSize(700, 600);
        userFrame.setLayout(null);
        userFrame.setLocationRelativeTo(null);

        JButton listAdvertisementButton = new JButton("List Advertisement");
        listAdvertisementButton.setBounds(220, 50, 200, 100);
        userFrame.add(listAdvertisementButton);

        JButton addAdvertisementButton = new JButton("Add Advertisement");
        addAdvertisementButton.setBounds(220, 250, 200, 100);
        userFrame.add(addAdvertisementButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(420, 420, 150, 40);
        userFrame.add(backButton);

        //to back login page
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LogInView();
                userFrame.dispose();

            }
        });
        //to go list ad page
        listAdvertisementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PropertyRepository.setPropertiesTableModel();
                new UListAdvertisement();
                userFrame.dispose();

            }
        });
        //to go add ad page
        addAdvertisementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new UAddAdvertisement();
                userFrame.dispose();
            }
        });

        userFrame.setVisible(true);
    }

}

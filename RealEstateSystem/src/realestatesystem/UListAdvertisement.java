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
public class UListAdvertisement extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;

    

    public UListAdvertisement() {
        JFrame listAdvertisementFrame = new JFrame("Advertisements");
        listAdvertisementFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        listAdvertisementFrame.setSize(1000, 700);
        listAdvertisementFrame.setLayout(null);

        JLabel propertyTypesLabel = new JLabel("ADVERTISEMENTS");
        propertyTypesLabel.setBounds(350, 50, 500, 40);
        propertyTypesLabel.setForeground(Color.DARK_GRAY);
        propertyTypesLabel.setFont(new Font("", Font.BOLD, 35));
        listAdvertisementFrame.add(propertyTypesLabel);

        tableModel = PropertyRepository.getPropertyModel();
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 110, 875, 400);
        listAdvertisementFrame.add(scrollPane);

        JButton buyButton = new JButton("BUY");
        buyButton.setBounds(250, 525, 100, 30);
        listAdvertisementFrame.add(buyButton);

        JButton rentButton = new JButton("RENT");
        rentButton.setBounds(405, 525, 100, 30);
        listAdvertisementFrame.add(rentButton);

        JButton backButton = new JButton("BACK");
        backButton.setBounds(560, 525, 100, 30);
        listAdvertisementFrame.add(backButton);

        //to back user view page
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserView();
                listAdvertisementFrame.dispose();
            }
        });

        listAdvertisementFrame.setVisible(true);
    }

}

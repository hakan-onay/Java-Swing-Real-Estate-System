package realestate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Hakan
 */
public class AdminView extends JFrame {
    
    private JFrame adminFrame;
    private JButton propertiesButton, propertyTypesButton, usersButton, advertisementsButton, backButton, reportButton;
    
    public AdminView() {
        adminFrame = new JFrame("Admin");
        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminFrame.setSize(700, 600);
        adminFrame.setLayout(null);
        adminFrame.setLocationRelativeTo(null);
        
        propertiesButton = new JButton("Properties");
        propertiesButton.setBounds(50, 50, 200, 100);
        adminFrame.add(propertiesButton);
        
        propertyTypesButton = new JButton("Property-Types");
        propertyTypesButton.setBounds(50, 250, 200, 100);
        adminFrame.add(propertyTypesButton);
        
        usersButton = new JButton("Users");
        usersButton.setBounds(400, 50, 200, 100);
        adminFrame.add(usersButton);
        
        advertisementsButton = new JButton("Advertisements");
        advertisementsButton.setBounds(400, 250, 200, 100);
        adminFrame.add(advertisementsButton);
        
        reportButton = new JButton("Report");
        reportButton.setBounds(50, 420, 150, 40);
        adminFrame.add(reportButton);
        
        backButton = new JButton("Back");
        backButton.setBounds(420, 420, 150, 40);
        adminFrame.add(backButton);
        
        // Create report as a pdf to desktop
        reportButton.addActionListener((e) -> {
            
            PDFReportGenerator.generateDailyReport("C:\\Users\\Hakan\\Desktop\\DailyReport.pdf");
            
        });

        //to back login page
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LogInView();
                adminFrame.dispose();
                
            }
        });

        //to go property view page
        propertiesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new APropertyView();
                adminFrame.dispose();
                
            }
        });

        //to go property types page
        propertyTypesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new APropertyTypesView();
                adminFrame.dispose();
            }
        });

        //to go users page
        usersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserRepository.setUsersTableModel();
                new AUsersView();
                adminFrame.dispose();
                
            }
        });

        //to go Advertisements page
        advertisementsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PropertyRepository.setPropertiesTableModel();
                new AListAdvertisementFrame();
                adminFrame.dispose();
            }
        });
        
        adminFrame.setVisible(true);
    }
    
}

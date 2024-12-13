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
public class APropertyTypesView extends JFrame {

    private JFrame propertyTypesFrame;
    private JLabel propertyTypesLabel;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel descriptionLabel;
    private JTextField descriptionTextField;
    private JButton addButton;
    private JButton editButton;
    private JButton removeButton;
    private JButton backButton;
    private DefaultListModel<String> listModel;
    private JList<String> propertyTypesList;

    public APropertyTypesView() {

        propertyTypesFrame = new JFrame("Property Types");
        propertyTypesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        propertyTypesFrame.setSize(1000, 700);
        propertyTypesFrame.setLayout(null);

        propertyTypesLabel = new JLabel("PROPERTY TYPES");
        propertyTypesLabel.setBounds(280, 50, 500, 40);
        propertyTypesLabel.setForeground(Color.DARK_GRAY);
        propertyTypesLabel.setFont(new Font("", Font.BOLD, 35));
        propertyTypesFrame.add(propertyTypesLabel);

        nameLabel = new JLabel("Name: ");
        nameLabel.setBounds(70, 200, 200, 40);
        nameLabel.setFont(new Font("", Font.BOLD, 15));
        propertyTypesFrame.add(nameLabel);

        nameTextField = new JTextField();
        nameTextField.setBounds(200, 205, 200, 30);
        propertyTypesFrame.add(nameTextField);

        descriptionLabel = new JLabel("Description: ");
        descriptionLabel.setBounds(70, 260, 200, 40);
        descriptionLabel.setFont(new Font("", Font.BOLD, 15));
        propertyTypesFrame.add(descriptionLabel);

        descriptionTextField = new JTextField();
        descriptionTextField.setBounds(200, 265, 200, 60);
        propertyTypesFrame.add(descriptionTextField);

        addButton = new JButton("ADD");
        addButton.setBounds(100, 485, 100, 30);
        propertyTypesFrame.add(addButton);

        editButton = new JButton("EDIT");
        editButton.setBounds(250, 485, 100, 30);
        propertyTypesFrame.add(editButton);

        removeButton = new JButton("REMOVE");
        removeButton.setBounds(400, 485, 100, 30);
        propertyTypesFrame.add(removeButton);

        backButton = new JButton("BACK");
        backButton.setBounds(550, 485, 100, 30);
        propertyTypesFrame.add(backButton);

        listModel = new DefaultListModel<>();
        propertyTypesList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(propertyTypesList);
        propertyTypesList.setBounds(500, 140, 300, 300);
        propertyTypesFrame.add(propertyTypesList);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText().trim();
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(propertyTypesFrame, "Please enter the property type name to add!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    listModel.addElement(name);
                    nameTextField.setText(""); // Alanı temizle
                }

            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedIndex = propertyTypesList.getSelectedIndex(); // Seçilen öğeyi al
                if (selectedIndex != -1) { // Eğer bir öğe seçildiyse
                    String currentText = listModel.getElementAt(selectedIndex); // Seçilen öğenin mevcut değerini al
                    String newText = JOptionPane.showInputDialog(propertyTypesFrame, "Edit item:", currentText); // Yeni değeri al

                    if (newText != null && !newText.trim().isEmpty()) { // Geçerli bir değer kontrolü
                        listModel.set(selectedIndex, newText.trim()); // Yeni değeri modele ata
                    }
                } else {
                    JOptionPane.showMessageDialog(propertyTypesFrame, "Please select an item to edit!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedIndex = propertyTypesList.getSelectedIndex(); // Seçilen öğenin indeksi
                if (selectedIndex != -1) { // Eğer bir öğe seçildiyse
                    listModel.removeElementAt(selectedIndex); // Modelden öğeyi kaldır
                } else {
                    JOptionPane.showMessageDialog(propertyTypesFrame, "Please select an item to remove!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        //to back admin view page
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new AdminView();
                propertyTypesFrame.dispose();
            }
        });

        propertyTypesFrame.setVisible(true);

    }

}

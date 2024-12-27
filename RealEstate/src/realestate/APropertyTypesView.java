package realestate;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
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

    private String propertyTypeId;
    private String propertyTypeName;

    public APropertyTypesView() {

        propertyTypesFrame = new JFrame("Property Types");
        propertyTypesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        propertyTypesFrame.setSize(1000, 700);
        propertyTypesFrame.setLayout(null);
        propertyTypesFrame.setLocationRelativeTo(null);

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

        addButton = new JButton("ADD");
        addButton.setBounds(150, 485, 100, 30);
        propertyTypesFrame.add(addButton);

        editButton = new JButton("EDIT");
        editButton.setBounds(300, 485, 100, 30);
        propertyTypesFrame.add(editButton);

        removeButton = new JButton("REMOVE");
        removeButton.setBounds(450, 485, 100, 30);
        propertyTypesFrame.add(removeButton);

        backButton = new JButton("BACK");
        backButton.setBounds(600, 485, 100, 30);
        propertyTypesFrame.add(backButton);

        listModel = new DefaultListModel<>();
        propertyTypesList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(propertyTypesList);
        scrollPane.setBounds(500, 140, 300, 300);
        propertyTypesFrame.add(scrollPane);

        loadPropertyTypes(); // UPLOADS PROPERTY TYPES DATAS FROM SQL

        // ADD BUTTON ACTION
        addButton.addActionListener(e -> {
            String name = nameTextField.getText().trim();
            if (!name.isEmpty()) {
                PropertyTypesRepository.addPropertyType(name);
                listModel.addElement(name);
                nameTextField.setText(""); // CLEARS NAME TEXT FIELD
            } else {
                JOptionPane.showMessageDialog(propertyTypesFrame, "Please enter a name!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // EDIT BUTTON ACTION
        editButton.addActionListener(e -> {
            int selectedIndex = propertyTypesList.getSelectedIndex();
            if (selectedIndex != -1) {
                String currentName = listModel.getElementAt(selectedIndex);
                String newName = JOptionPane.showInputDialog(propertyTypesFrame, "Edit Property Type:", currentName);
                if (newName != null && !newName.trim().isEmpty()) {
                    PropertyTypesRepository.updatePropertyType(currentName, newName);
                    listModel.set(selectedIndex, newName);
                }
            } else {
                JOptionPane.showMessageDialog(propertyTypesFrame, "Please select an item to edit!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // REMOVE BUTTON ACTION
        removeButton.addActionListener(e -> {
            int selectedIndex = propertyTypesList.getSelectedIndex();
            if (selectedIndex != -1) {
                String name = listModel.getElementAt(selectedIndex);
                PropertyTypesRepository.removePropertyType(name);
                listModel.removeElementAt(selectedIndex);
            } else {
                JOptionPane.showMessageDialog(propertyTypesFrame, "Please select an item to remove!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // BACK BUTTON ACTION
        backButton.addActionListener(e -> {
            new AdminView();
            propertyTypesFrame.dispose();
        });

        propertyTypesFrame.setVisible(true);

    }
    //LOADS PROPERTY TYPES NAMES FROM SQL

    private void loadPropertyTypes() {
        List<String> propertyTypes = PropertyTypesRepository.getAllPropertyTypes();
        for (String type : propertyTypes) {
            listModel.addElement(type);
        }
    }

}

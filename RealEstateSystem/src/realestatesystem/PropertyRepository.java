package realestatesystem;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hakan
 */
public class PropertyRepository {

    private static DefaultTableModel propertyModel = new DefaultTableModel(new String[]{"ID", "Type", "Owner ID", "Square Feet", "Price", "Bedrooms", "Bathrooms"
            + "Age", "Rooms", "Balcony", "Pool", "Backyard", "Garage", "Lift"}, 0);

    // Tablo modeline erişim sağlayan metot
    public static DefaultTableModel getPropertyModel() {
        return propertyModel;
    }

    // Yeni kullanıcı ekleyen metot
    public static void addProperty(int id, String type, int ownerId, double squareFeet, 
            double price, int bedrooms, int bathrooms, int ageOfHouse, int rooms, int floor, boolean balcony, 
            boolean pool, boolean backyard, boolean garage, boolean lift) {
        propertyModel.addRow(new Object[]{id, type, ownerId, squareFeet,
                price, bedrooms, bathrooms, ageOfHouse, rooms, floor, balcony,
                        pool, backyard, garage, lift});
    }

}

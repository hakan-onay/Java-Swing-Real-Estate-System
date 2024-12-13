package realestatesystem;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hakan
 */
public class UserRepository {

    private static DefaultTableModel userModel = new DefaultTableModel(new String[]{"Name", "Surname", "E-Mail", "Phone Number", "Password"}, 0);

    // Tablo modeline erişim sağlayan metot
    public static DefaultTableModel getUserModel() {
        return userModel;
    }

    // Yeni kullanıcı ekleyen metot
    public static void addUser(String name, String surname, String email, String phoneNumber, String password) {
        userModel.addRow(new Object[]{name, surname, email, phoneNumber, password});
    }
}

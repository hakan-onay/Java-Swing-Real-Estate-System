package realestate;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import javax.swing.table.DefaultTableModel;
import java.io.FileOutputStream;
import javax.swing.JOptionPane;

public class PDFReportGenerator {

    public static void generateDailyReport(String filePath) {
        Document document = new Document(PageSize.A4.rotate());
        boolean isSuccessful = false;

        try {
            // Load a font that supports Turkish characters
            BaseFont baseFont = BaseFont.createFont("c:\\windows\\fonts\\arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font titleFont = new Font(baseFont, 18, Font.BOLD);
            Font normalFont = new Font(baseFont, 12, Font.NORMAL);
            Font headerFont = new Font(baseFont, 14, Font.BOLD);

            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // Add title
            Paragraph title = new Paragraph("Daily Real Estate System Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph("Generated on: " + java.time.LocalDate.now(), normalFont));
            document.add(new Paragraph(" ")); // Empty space

            // Add system name and description
            Paragraph systemName = new Paragraph("System: Real Estate System", headerFont);
            document.add(systemName);

            Paragraph description = new Paragraph(
                    "This report provides an overview of the properties and users managed by the system.\n"
                    + "The data includes property details and user information stored in the database.",
                    normalFont);
            description.setAlignment(Element.ALIGN_LEFT);
            document.add(description);
            document.add(new Paragraph(" ")); // Empty space

            // Property Table: Set column widths
            float[] propertyColumnWidths = {2f, 2f, 3f, 3f, 3f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f};
            PdfPTable propertyTable = new PdfPTable(14); // 14 columns
            propertyTable.setWidths(propertyColumnWidths);
            propertyTable.setWidthPercentage(100);
            propertyTable.setSpacingBefore(10f);
            propertyTable.setSpacingAfter(10f);

            // Property table headers
            String[] propertyHeaders = {
                "ID", "Type", "Owner ID", "Square Feet", "Price", "Bedrooms",
                "Bathrooms", "Rooms", "Floor", "Balcony", "Pool",
                "Backyard", "Garage", "Lift"
            };
            for (String header : propertyHeaders) {
                PdfPCell cell = new PdfPCell(new Phrase(header, headerFont));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPadding(5f);
                propertyTable.addCell(cell);
            }

            // Add property data
            DefaultTableModel propertyModel = PropertyRepository.setPropertiesTableModel();
            for (int i = 0; i < propertyModel.getRowCount(); i++) {
                for (int j = 0; j < propertyModel.getColumnCount(); j++) {
                    PdfPCell cell = new PdfPCell(new Phrase(String.valueOf(propertyModel.getValueAt(i, j)), normalFont));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setPadding(5f);
                    propertyTable.addCell(cell);
                }
            }

            document.add(propertyTable);

            // User Table: Set column widths
            float[] userColumnWidths = {2f, 3f, 3f, 4f, 4f, 4f};
            PdfPTable userTable = new PdfPTable(6); // 6 columns
            userTable.setWidths(userColumnWidths);
            userTable.setWidthPercentage(100);
            userTable.setSpacingBefore(20f);
            userTable.setSpacingAfter(10f);

            // User table headers
            String[] userHeaders = {"ID", "Name", "Surname", "Phone", "Email", "Password"};
            for (String header : userHeaders) {
                PdfPCell cell = new PdfPCell(new Phrase(header, headerFont));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPadding(5f);
                userTable.addCell(cell);
            }

            // Add user data
            DefaultTableModel userModel = UserRepository.setUsersTableModel();
            for (int i = 0; i < userModel.getRowCount(); i++) {
                for (int j = 0; j < userModel.getColumnCount(); j++) {
                    PdfPCell cell = new PdfPCell(new Phrase(String.valueOf(userModel.getValueAt(i, j)), normalFont));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setPadding(5f);
                    userTable.addCell(cell);
                }
            }

            document.add(userTable);

            // Footer
            Paragraph footer = new Paragraph("Generated by Real Estate System", normalFont);
            footer.setAlignment(Element.ALIGN_CENTER);
            document.add(footer);

            isSuccessful = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
            if (isSuccessful) {
                JOptionPane.showMessageDialog(null, "Report successfully generated: " + filePath,
                        "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to generate the report.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

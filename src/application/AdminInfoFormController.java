package application;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class AdminInfoFormController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField OrganizationField;
    @FXML
    private CheckBox ReviewerCheckbox;
    @FXML
    private CheckBox SewardCheckbox;
    @FXML
    private TextArea AffiliatedScholarshipsField;
    @FXML
    private CheckBox AdminCheckbox;
    @FXML
    private TextField idField;
    @FXML
    private TextField emailField;
    @FXML
   
    private TextField userNameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Main mainApp;
    public void setMain(Main mainApp) {
        this.mainApp = mainApp;
    }
    public void submitForm() {
        String name = nameField.getText();
        String Organization = OrganizationField.getText();
        boolean isReviewer = ReviewerCheckbox.isSelected();
        boolean isSeward = SewardCheckbox.isSelected();
        String Affiliation = AffiliatedScholarshipsField.getText();
        boolean isAdmin = AdminCheckbox.isSelected();
        String Id = idField.getText();
        String email = emailField.getText();
        String userName = userNameField.getText();
        String password = passwordField.getText();
        String infoText = String.format("Name: %s\nUser Name: %s\nPassword: %s\nOrganization: %s\nReviewer: %b\nSeward: %b\nAffiliated Scholarships: %s\n" +
                "Admin: %b\nID Number: %s\nEmail: %s\n",
                name, userName, password, Organization, isReviewer, isSeward, Affiliation, isAdmin, Id, email);
        saveToFile(userName, password , infoText);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Submission Successful");
        alert.setHeaderText(null);
        alert.setContentText("Form submitted and saved to a file.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Handle OK button action if needed
        }
        mainApp.switchToAdminLoginScene();
    }

    private void saveToFile(String userName, String password, String data) {
        // Construct the file path with the format "password_username.txt"
        String folderPath = System.getProperty("user.dir") + File.separator + "AdminInfo";
        String filePath = folderPath + File.separator + userName + "_" + password + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Error saving to file: " + e.getMessage());
        }
    }


    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
   
}

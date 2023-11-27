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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class StudentInfoFormController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField genderField;
    @FXML
    private TextField majorField;
    @FXML
    private TextField minorField;
    @FXML
    private TextField yearField;
    @FXML
    private TextField graduationDateField;
    @FXML
    private TextField gpaField;
    @FXML
    private CheckBox transferCheckbox;
    @FXML
    private TextField idField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField unitsField;
    @FXML
    private TextField citizenshipField;
    @FXML
    private TextArea statementArea;
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
        String gender = genderField.getText();
        String major = majorField.getText();
        String minor = minorField.getText();
        String year = yearField.getText();
        String graduationDate = graduationDateField.getText();
        String gpa = gpaField.getText();
        boolean isTransfer = transferCheckbox.isSelected();
        String studentId = idField.getText();
        String email = emailField.getText();
        String units = unitsField.getText();
        String citizenship = citizenshipField.getText();
        String personalStatement = statementArea.getText();
        String userName = userNameField.getText();
        String password = passwordField.getText();
        String infoText = String.format("Name: %s\nUser Name: %s\nPassword: %s\nGender: %s\nMajor: %s\nMinor: %s\nYear of Study: %s\n" +
                "Graduation Date: %s\nGPA: %s\nTransfer Student: %s\nStudent ID: %s\nEmail: %s\n" +
                "Enrolled Units: %s\nCitizenship: %s\nPersonal Statement: %s",
                name, userName, password, gender, major, minor, year, graduationDate, gpa, isTransfer, studentId, email,
                units, citizenship, personalStatement);

        saveToFile(userName, password , infoText);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Submission Successful");
        alert.setHeaderText(null);
        alert.setContentText("Form submitted and saved to a file.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Handle OK button action if needed
        }
        mainApp.switchToLoginScene();
    }

    private void saveToFile(String userName, String password, String data) {
        String folderPath = System.getProperty("user.dir") + File.separator + "StudentInfo";
        File folder = new File(folderPath);
        folder.mkdirs();

        // Construct the file path with the format "password_username.txt"
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

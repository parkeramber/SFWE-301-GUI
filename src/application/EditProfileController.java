package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import javafx.scene.control.Alert;
public class EditProfileController {

    @FXML
    private TextArea profileTextArea;

    @FXML
    private Button saveButton;

    @FXML
    private Main mainApp;

    public void setMain(Main mainApp) {
        this.mainApp = mainApp;
        loadUserProfile();  // Load the user profile when setting the main app
    }

    @FXML
    private String readFile(String filePath) throws IOException {
        
        return Files.readString(Paths.get(filePath));
    }

    @FXML
    private void loadUserProfile() {
        UserCredentials currentUserCredentials = (UserCredentials) SharedData.getInstance().getData("currentUserCredentials");
        if (currentUserCredentials != null) {
            String username = currentUserCredentials.getUsername();
            String password = currentUserCredentials.getPassword();

            // Update the code to read the data from the user's specific file
            String filePath = "C:\\Users\\ryanm\\eclipse-workspace\\Student_Form\\StudentInfo\\" + username + "_" + password + ".txt";

            try {
                String userProfileData = readFile(filePath);
                profileTextArea.setText(userProfileData);
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception 
            }
        }
    }

    @FXML
    private void initialize() {
        
        profileTextArea.setText("Load user's profile data here...");
    }

    @FXML
    private void handleSave() {
        // Save the edited profile data to the user's specific file
        
        UserCredentials currentUserCredentials = (UserCredentials) SharedData.getInstance().getData("currentUserCredentials");
        if (currentUserCredentials != null) {
            String username = currentUserCredentials.getUsername();
            String password = currentUserCredentials.getPassword();

            String filePath = "C:\\Users\\ryanm\\eclipse-workspace\\Student_Form\\StudentInfo\\" + username + "_" + password + ".txt";

            try {
                Files.writeString(Paths.get(filePath), profileTextArea.getText(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
                showInfoDialog("Profile saved successfully.");
                mainApp.switchToScholarshipScene();
            } catch (IOException e) {
                e.printStackTrace();
                
            }
        }
    }
    private void showInfoDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


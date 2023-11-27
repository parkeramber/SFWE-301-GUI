package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.File;

public class LoginController extends BaseController{

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    void login(ActionEvent event) {
        if (!usernameField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (performLogin(username, password)) {
            	
            	SharedData.getInstance().setData("currentUserCredentials", new UserCredentials(username, password));
	
                // Login successful, switch to the next scene
                Main mainApp = (Main) SharedData.getInstance().getData("mainInstance");
                mainApp.switchToScholarshipScene();
            } else {
                // Login failed, show an error message or take appropriate action
                showErrorDialog("Invalid username or password");
            }
        }
    }
    @FXML
    void backToLanding(ActionEvent event) {
        
        Main mainApp = (Main) SharedData.getInstance().getData("mainInstance");
        mainApp.switchToLandingScene();
    }
    @FXML
    void register(ActionEvent event) {
        
        User newUser = new User(usernameField.getText(), passwordField.getText());

        // Store the user data 
        UserDataStore.storeUser(newUser);

        // Switch to the next scene (StudentInfoFormScene)
        Main mainApp = (Main) SharedData.getInstance().getData("mainInstance");
        mainApp.switchToStudentInfoFormScene();
    }

    private boolean performLogin(String username, String password) {
        
        String folderPath = "C:\\Users\\ryanm\\eclipse-workspace\\Student_Form\\StudentInfo";
        File folder = new File(folderPath);

        // List all files in the directory
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    // Extract username and password from the file name
                    String fileName = file.getName();
                    String[] fileNameParts = fileName.split("_");

                    if (fileNameParts.length == 2) {
                        String storedUsername = fileNameParts[0].trim();
                        String storedPassword = fileNameParts[1].replace(".txt", "").trim();

                        System.out.println(storedUsername);
                        System.out.println(username);

                        // Check for a match
                        if (storedUsername.equals(username) && storedPassword.equals(password)) {
                            // Username and password match, authentication successful
                            System.out.println("Authentication Successful");
                            return true;
                        } else {
                            // Debug print statement for non-matching credentials
                            System.out.println("Credentials did not match");
                        }
                    }
                }
            }
        }

        // No matching username and password found, authentication failed
        System.out.println("Authentication Failed");
        return false;
    }

    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


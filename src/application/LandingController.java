package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LandingController {

    @FXML
    private Button studentLoginButton;

    @FXML
    private Button adminLoginButton;
    

    @FXML
    public void switchToStudentLogin() {
        // Load and switch to the Student Login scene
    	Main mainApp = (Main) SharedData.getInstance().getData("mainInstance");
        mainApp.switchToLoginScene();    }

    @FXML
    public void switchToAdminLogin() {
        // Load and switch to the Admin Login scene 
 
        Main mainApp = (Main) SharedData.getInstance().getData("mainInstance");
        mainApp.switchToAdminLoginScene();
    }
    private Main mainApp;

    // Add a setMain method or a constructor that accepts a Main instance
    public void setMain(Main mainApp) {
        this.mainApp = mainApp;
    }

    // Add the necessary event handler for the backToLandingButton
    @FXML
    private void handleBackToLanding() {
        // Implement the logic to go back to the Landing Scene
        mainApp.switchToLandingScene();
    }
}

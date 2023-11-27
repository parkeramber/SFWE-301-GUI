// StatusSceneController.java
package application;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import java.util.List;
import java.util.Arrays;
public class StatusSceneController {

    @FXML
    private ListView<String> applicationsListView;
    private Main mainApp; 
    public void setMain(Main mainApp) {
        this.mainApp = mainApp;
    }
    public void initialize() {
    	if (mainApp != null) {
            System.out.println("Using mainApp in StatusSceneController");
        }
        // Initialize the ListView with user applications
        String username = getUserUsername(); // Implement this method to get the logged-in user's username
        List<String> userApplications = getUserApplications(username); // Implement this method to get user applications
        applicationsListView.getItems().addAll(userApplications);
    }

    // Method to get the logged-in user's username 
    private String getUserUsername() {
        
        return "SampleUser";
    }

    // Method to get user applications 
    private List<String> getUserApplications(String username) {
        List<String> sampleApplications = Arrays.asList("Application 1", "Application 2", "Application 3");
        return sampleApplications;
    }
    @FXML
    private void goBack() {
        // Navigate back to the previous scene 
        Main mainApp = (Main) SharedData.getInstance().getData("mainInstance");
        mainApp.switchToScholarshipScene();
    }
}

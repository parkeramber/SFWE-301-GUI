package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ScholarshipController extends BaseController{

    @FXML
    private Button searchButton;

    @FXML
    private Button statusButton;

    @FXML
    private Button applyButton;

    @FXML
    private ImageView imageView;

    private Main mainApp;

    public void setMain(Main mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void initialize() {
        // Set the image in the ImageView
        imageView.setImage(new Image("file:C:/Users/ryanm/eclipse-workspace/Student_Form/Arizona-Wildcats-Logo.png"));
        imageView.setFitWidth(80); 
        imageView.setFitHeight(60); 
    }

    @FXML
    private void handleSearch(ActionEvent event) {
    	Main mainApp = (Main) SharedData.getInstance().getData("mainInstance");
        mainApp.switchToViewScholarshipsScene();
        // Handle Search button action
        System.out.println("Search Scholarships clicked");
    }

    @FXML
    private void handleStatus(ActionEvent event) {
        // Handle Scholarship Status button action
    	Main mainApp = (Main) SharedData.getInstance().getData("mainInstance");
        mainApp.switchToStatusScene();
        System.out.println("Scholarship Status clicked");
    }

    @FXML
    private void handleApply(ActionEvent event) {
        // Handle Apply button action
        System.out.println("Apply clicked");
    }
    @FXML
    private void handleEditProfile(ActionEvent event) {
        // Handle Edit Profile button action
        System.out.println("Edit Profile clicked");
        Main mainApp = (Main) SharedData.getInstance().getData("mainInstance");
        mainApp.switchToEditProfileScene();
        
    }

@FXML
private void handleLogOff(ActionEvent event) {
    // Handle Log Off button action
    System.out.println("Log Off clicked");
    mainApp.switchToLoginScene();
}

}
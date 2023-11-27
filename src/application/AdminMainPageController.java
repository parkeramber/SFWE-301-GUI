// AdminMainPageController.java
package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AdminMainPageController {

	@FXML
    private Main mainApp;
    public void setMain(Main mainApp) {
        this.mainApp = mainApp;
    }
    @FXML
    private void customizeScholarships() {
        // Add logic to open Customize Scholarships page
        System.out.println("Customize Scholarships clicked");
    }

    @FXML
    private void manageScholarships() {
        // Add logic to open Manage Scholarships page
        System.out.println("Manage Scholarships clicked");
        mainApp.switchToEditScholarshipScene();
    }

    @FXML
    private void scoreApplicants() {
        // Add logic to open Score Applicants page
        System.out.println("Score Applicants clicked");
        mainApp.switchToRateApplicantsScene();
    }

    @FXML
    private void createScholarships() {
        // Add logic to open Create Scholarships page
        System.out.println("Create Scholarships clicked");
        mainApp.switchToFrontScene();
    }

    @FXML
    private void viewApplicantList() {
        // Add logic to open View Applicant List page
        System.out.println("View Applicant List clicked");
        mainApp.switchToViewAppliedScene();

    }
    @FXML
    private void handleLogOff(ActionEvent event) {
        // Handle Log Off button action
        System.out.println("Log Off clicked");
                mainApp.switchToLoginScene();
    }
}

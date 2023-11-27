package application;

import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
public class FrontController {
	private Main mainApp;
	public void setMain(Main mainApp) {
        this.mainApp = mainApp;
    }

	@FXML
	private TableView<Scholarship> tableView;

	@FXML
	private TableColumn<Scholarship, String> scholarshipName;


    @FXML
    private TableColumn<Scholarship, String> scholarshipAwardAmount;

    @FXML
    private TableColumn<Scholarship, String> scholarshipSponsorName;

    @FXML
    private TableColumn<Scholarship, String> emailAdd;

    @FXML
    private TableColumn<Scholarship, String> scholarshipMinGPA;

    @FXML
    private TableColumn<Scholarship, String> scholarshipAwardFreq;

    @FXML
    private Button bttnBack;

    @FXML
    private Button bttnSave;

    @FXML
    private Button bttnAdd;

    @FXML
    private Button bttnDelete;

    @FXML
    private TextField scholarshipNameField;

    @FXML
    private TextField scholarshipAmountField;

    @FXML
    private TextField sponsorNameField;

    @FXML
    private TextField commentsField;

    @FXML
    private TextField emailAddressField;

    @FXML
    private TextField minGPAField;

    @FXML
    private TextField distributionField;
    @FXML
    private void initialize() {
        // Initialize the TableView columns
        scholarshipName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        scholarshipAwardAmount.setCellValueFactory(cellData -> cellData.getValue().amountProperty());
        scholarshipSponsorName.setCellValueFactory(cellData -> cellData.getValue().sponsorProperty());
        emailAdd.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        scholarshipMinGPA.setCellValueFactory(cellData -> cellData.getValue().minGPAProperty());
        scholarshipAwardFreq.setCellValueFactory(cellData -> cellData.getValue().awardFreqProperty());
        // If you have predefined data, you can set it to the TableView here
        ObservableList<Scholarship> scholarships = FXCollections.observableArrayList();
        tableView.setItems(scholarships);
    }

    
    @FXML
    private void bttnAddPress() {
        // Handle the Add button press
        System.out.println("Add button pressed");

        if (tableView != null) {
            // Create a new Scholarship object with the input values
            Scholarship newScholarship = new Scholarship(
                    scholarshipNameField.getText(),
                    scholarshipAmountField.getText(),
                    sponsorNameField.getText(),
                    emailAddressField.getText(),
                    minGPAField.getText(),
                    distributionField.getText()
            );

            // Add the new scholarship to the TableView
            tableView.getItems().add(newScholarship);

            // Clear the input fields
            clearInputFields();
        } else {
            System.out.println("TableView is null. Check FXML initialization.");
        }
    }

    private void clearInputFields() {
        scholarshipNameField.clear();
        scholarshipAmountField.clear();
        sponsorNameField.clear();
        emailAddressField.clear();
        minGPAField.clear();
        distributionField.clear();
    }

    @FXML
    private void bttnBackPress(ActionEvent event) {
    	// Handle the Back button press
        System.out.println("Back button pressed");
        Main mainApp = (Main) SharedData.getInstance().getData("mainInstance");
        if (mainApp != null) {
            mainApp.switchToAdminMainScene();
        } else {
            System.out.println("mainApp is null. Check if it's properly set in SharedData.");
        }
    }

    @FXML
    private void handleSave(ActionEvent event) {
        System.out.println("Save button pressed");

        // Specify the directory where individual files will be saved
        String scholarshipsDirectoryPath = "C:\\Users\\ryanm\\eclipse-workspace\\Student_Form\\Scholarships"; // Change this to your desired directory

        // Specify the base directory where individual scholarship folders will be created
        String appliedDirectoryPath = "C:\\Users\\ryanm\\eclipse-workspace\\Student_Form\\Applied"; // Change this to your desired directory

        // Iterate through each row in the TableView
        for (Scholarship scholarship : tableView.getItems()) {
            // Construct the file name based on some property of the row (e.g., scholarship name)
            String fileName = scholarship.getName() + "_data.txt"; 

            // Create the file object
            File file = new File(scholarshipsDirectoryPath, fileName);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                // Write the data from the current row to the file using the custom toString method
                writer.write(scholarship.toString());

                System.out.println("Data saved to file: " + file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception appropriately 
            }

            // Construct the directory path based on some property of the row (e.g., scholarship name)
            String appliedDirectory = appliedDirectoryPath + File.separator + scholarship.getName();

            // Create the directory
            File directory = new File(appliedDirectory);
            if (!directory.exists()) {
                if (directory.mkdirs()) {
                    System.out.println("Directory created: " + directory.getAbsolutePath());
                } else {
                    System.out.println("Failed to create directory: " + directory.getAbsolutePath());
                    // Handle the failure appropriately
                }
            }
        }

        // Show an alert indicating that applications are saved
        showInfoAlert("Applications saved successfully!");
    }

    // Method to show an information alert
    private void showInfoAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }




    

    @FXML
    private void handleDelete(ActionEvent event) {
        // Handle the Delete button press
        System.out.println("Delete button pressed");
    }
}

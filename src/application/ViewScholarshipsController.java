// ViewScholarshipsController.java
package application;

import javafx.fxml.FXML;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;
public class ViewScholarshipsController {

	@FXML
	private ListView<String> scholarshipListView;
	@FXML
    private TableView<Scholarship> tableView;

    @FXML
    private TableColumn<Scholarship, String> columnName;

    @FXML
    private TableColumn<Scholarship, String> columnAmount;

    @FXML
    private TableColumn<Scholarship, String> columnSponsor;

    @FXML
    private TableColumn<Scholarship, String> columnEmail;

    @FXML
    private TableColumn<Scholarship, String> columnMinGPA;

    @FXML
    private TableColumn<Scholarship, String> columnAwardFreq;

    @FXML
    private Button applyButton;

    private ObservableList<Scholarship> scholarships = FXCollections.observableList(new ArrayList<>());

    @FXML
    public void initialize() {
        
        List<String> scholarshipFiles = getScholarshipFiles("C:\\Users\\ryanm\\eclipse-workspace\\Student_Form\\Scholarships");

        // Parse the scholarship files into a list of Scholarship objects
        scholarships.addAll(parseScholarshipFiles(scholarshipFiles, "C:\\Users\\ryanm\\eclipse-workspace\\Student_Form\\Scholarships"));

        // Set up the TableView columns
        columnName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        columnAmount.setCellValueFactory(cellData -> cellData.getValue().amountProperty());
        columnSponsor.setCellValueFactory(cellData -> cellData.getValue().sponsorProperty());
        columnEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        columnMinGPA.setCellValueFactory(cellData -> cellData.getValue().minGPAProperty());
        columnAwardFreq.setCellValueFactory(cellData -> cellData.getValue().awardFreqProperty());

        // Set the items of the TableView
        tableView.setItems(scholarships);
    }
    
 // Method to get the list of scholarship files in the specified directory
    private List<String> getScholarshipFiles(String directoryPath) {
        List<String> scholarshipFiles = new ArrayList<>();

        try {
            File directory = new File(directoryPath);

            // Check if the directory exists
            if (directory.exists() && directory.isDirectory()) {
                // Get the list of files in the directory
                File[] files = directory.listFiles();

                // Check if there are any files
                if (files != null) {
                    for (File file : files) {
                        // Check if the file is a text file
                        if (file.isFile() && file.getName().toLowerCase().endsWith(".txt")) {
                            scholarshipFiles.add(file.getName());
                        }
                    }
                }
            } else {
                System.out.println("Directory not found: " + directoryPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return scholarshipFiles;
    }

    // Method to parse scholarship files into a list of Scholarship objects
    private List<Scholarship> parseScholarshipFiles(List<String> scholarshipFiles, String directoryPath) {
        List<Scholarship> scholarships = new ArrayList<>();

        for (String fileName : scholarshipFiles) {
            try (BufferedReader reader = new BufferedReader(new FileReader(directoryPath + File.separator + fileName))) {
                // Read each line from the file and create a Scholarship object
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 6) {
                        scholarships.add(new Scholarship(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return scholarships;
    }
    private void updateApplicationFile(String selectedScholarship) {
        // Get the username of the logged-in user 
        String username = getUserUsername(); // Implement this method

        // Construct the directory path based on the scholarship name
        String directoryPath = "C:\\Users\\ryanm\\eclipse-workspace\\Student_Form\\Applied\\" + selectedScholarship;

        // Create the directory if it doesn't exist
        createDirectoryIfNotExists(directoryPath);

        // Construct the file path within the directory
        String applicationFilePath = directoryPath + "\\" + username + ".txt";

        
        appendUsernameToFile(username, applicationFilePath);
    }
 // Method to get the username of the logged-in user 
    private String getUserUsername() {
        
        return "SampleUser";
    }
 // Method to append the username to the file 
    private void appendUsernameToFile(String username, String filePath) {
    	appendToFile(username, filePath);
    }
   

    @FXML
    private void goBack() {
        // Navigate back to the previous scene 
        Main mainApp = (Main) SharedData.getInstance().getData("mainInstance");
        mainApp.switchToScholarshipScene();
    }
    

    @FXML
    private void applyForScholarship() {
        // Prompt the user to log in
        Optional<String> result = promptLogin();

        if (result.isPresent()) {
            // Parse the result (format: "username_password")
            String[] loginInfo = result.get().split("_");

            // Get the selected scholarship from the TableView
            Scholarship selectedScholarship = tableView.getSelectionModel().getSelectedItem();

            if (selectedScholarship != null) {
                // Get the scholarship name
                String scholarshipName = selectedScholarship.getName();

                // Construct the directory path for student info
                String studentInfoDirectory = "C:\\Users\\ryanm\\eclipse-workspace\\Student_Form\\StudentInfo";

                // Construct the file path for the student info file
                String studentInfoFilePath = studentInfoDirectory + "\\" + loginInfo[0] + "_" + loginInfo[1] + ".txt";

                // Check if the student info file exists
                if (Files.exists(Paths.get(studentInfoFilePath))) {
                    // Read the content of the student info file
                    String studentInfoContent = readFileContent(studentInfoFilePath);

                    // Construct the directory path based on the scholarship name
                    String directoryPath = "C:\\Users\\ryanm\\eclipse-workspace\\Student_Form\\Applied\\" + scholarshipName;

                    // Check if the directory exists
                    File scholarshipDirectory = new File(directoryPath);
                    if (scholarshipDirectory.exists() && scholarshipDirectory.isDirectory()) {
                        // Create the file inside the directory
                        String applicationFilePath = directoryPath + "\\" + extractFileNameLine(studentInfoContent) + ".txt";
                        System.out.println(applicationFilePath);
                        // Append the student information to the application file
                        appendToFile(studentInfoContent, applicationFilePath);

                        // Display an alert confirming the application
                        showInfoAlert("Application for " + scholarshipName + " has been submitted.");
                    } else {
                        // If the directory doesn't exist, display an error alert
                        showErrorAlert("Directory not found: " + directoryPath);
                    }
                } else {
                    // If the student info file doesn't exist, display an error alert
                    showErrorAlert("Student info file not found: " + studentInfoFilePath);
                }
            } else {
                // If no scholarship is selected, display an error alert
                showErrorAlert("Please select a scholarship to apply.");
            }
        }
    }

 // Method to extract the line following "Name:" from studentInfoContent
    private String extractFileNameLine(String studentInfoContent) {
        // Split the content by lines
        String[] lines = studentInfoContent.split("\n");

        // Iterate through the lines to find "Name:" and extract the following line
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].contains("Name:") && i + 1 < lines.length) {
                // Extract the part after "Name:" and remove leading/trailing whitespaces and "User"
                return lines[i + 1].replace("Name:", "").replace("User", "").trim();
            }
        }


        // If "Name:" is not found, return a default value
        return "DefaultFileName";
    }
 /// Method to append the username to the file
    private void appendToFile(String username, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(username);  // Append the username to the file
            writer.newLine();  // Add a new line for better readability or separation
            System.out.println("Username appended to file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error appending username to file: " + e.getMessage());
            // Handle the exception appropriately
        }
    }

    
 // Method to show an information alert
    private void showInfoAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
 // Method to show an error alert
    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    // ... (other methods)

    // Method to prompt the user to log in
    private Optional<String> promptLogin() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Login");
        dialog.setHeaderText("Enter your username and password");
        dialog.setContentText("Username_Password:");

        return dialog.showAndWait();
    }

    // Method to read the content of a file
    private String readFileContent(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error reading file: " + e.getMessage());
            
        }
        return content.toString();
    }

    // Method to create a file
    private void createFile(String filePath) {
        try {
            File file = new File(filePath);

            // Check if the file already exists
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getAbsolutePath());
            } else {
                System.out.println("File already exists: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error creating file: " + e.getMessage());
            
        }
    }

 // Method to create a directory if it doesn't exist
    private void createDirectoryIfNotExists(String directoryPath) {
        try {
            Path path = Paths.get(directoryPath);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
                System.out.println("Directory created: " + directoryPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}

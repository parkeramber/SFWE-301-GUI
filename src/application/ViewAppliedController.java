package application;

import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;

import java.io.File;
import java.io.IOException;

public class ViewAppliedController {
	private String currentFolderPath = "C:\\Users\\ryanm\\eclipse-workspace\\Student_Form\\Applied\\";
    @FXML
    private ListView<String> appliedListView;

    private ObservableList<String> fileNames = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // Load file names from the specified directory
        String directoryPath = "C:\\Users\\ryanm\\eclipse-workspace\\Student_Form\\Applied";
        loadFileNames(directoryPath);

        // Set the file names to the ListView
        appliedListView.setItems(fileNames);

        // Set up a click event handler for the ListView
        appliedListView.setOnMouseClicked(event -> handleItemClick());
    }

    private void loadFileNames(String directoryPath) {
        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        // Add folder names to the list
                        fileNames.add("[" + file.getName() + "]");
                    } else if (file.isFile()) {
                        // Add file names to the list
                        fileNames.add(file.getName());
                    }
                    // Print file/folder information for debugging
                    System.out.println("File/Folder: " + file.getAbsolutePath());
                }
            }
        } else {
            showErrorAlert("Directory not found: " + directoryPath);
        }
    }

    @FXML
    private void handleItemClick() {
        String selectedItem = appliedListView.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            if (selectedItem.startsWith("[") && selectedItem.endsWith("]")) {
                // Clicked on a folder, update the list with folder contents
                updateListForFolder(selectedItem.substring(1, selectedItem.length() - 1));
                System.out.println("Out");
            } else {
                // Clicked on a file
                handleFileClick();
                System.out.println("Look");
            }
        }
    }

    private void updateListForFolder(String folderName) {
        // Clear the current list
        fileNames.clear();

        // Construct the path for the selected folder
        String folderPath = currentFolderPath + folderName;

        // Load the file names for the selected folder
        loadFileNames(folderPath);
        currentFolderPath = folderPath +"\\";
    }

    private void handleFileClick() {
        String selectedFileName = appliedListView.getSelectionModel().getSelectedItem();

        if (selectedFileName != null && !selectedFileName.startsWith("[") && !selectedFileName.endsWith("]")) {
            // Use the current folder path to construct the file path
            String filePath = currentFolderPath + selectedFileName;
            System.out.println("File Path: " + filePath);

            Runnable fileClickTask = () -> {
                try {
                    String fileContent = FileUtils.readFileContent(filePath);
                    showInfoAlert("File Content", fileContent);
                } catch (IOException e) {
                    showErrorAlert("Error reading file: " + filePath);
                    e.printStackTrace(); // Print the stack trace for debugging purposes
                }
            };

            // Run the task on a separate thread to handle the exception
            new Thread(fileClickTask).start();
        } else {
            showErrorAlert("Please select a valid file to view.");
        }
    }
    @FXML
    private void handleBack(ActionEvent event) {
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
    private void viewFileContent(ActionEvent event) {
        String selectedFileName = appliedListView.getSelectionModel().getSelectedItem();

        if (selectedFileName != null) {
            String filePath = "C:\\Users\\ryanm\\eclipse-workspace\\Student_Form\\Applied\\" + selectedFileName;

            try {
                String fileContent = FileUtils.readFileContent(filePath);
                showInfoAlert("File Content", fileContent);
            } catch (IOException e) {
                showErrorAlert("Error reading file: " + filePath);
                e.printStackTrace(); // Print the stack trace for debugging purposes
            }
        } else {
            showErrorAlert("Please select a file to view.");
        }
    }





    private void showInfoAlert(String title, String content) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(content);
            alert.showAndWait();
        });
    }


    private void showErrorAlert(String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }

}

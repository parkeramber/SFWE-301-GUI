package application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import java.io.File;
import java.io.IOException;

public class EditScholarshipController {
    private String currentFolderPath = "C:\\Users\\ryanm\\eclipse-workspace\\Student_Form\\Scholarships\\";

    @FXML
    private ListView<String> scholarshipListView;

    @FXML
    private TextArea fileContentTextArea;

    @FXML
    private void initialize() {
        loadFileNames(currentFolderPath);
    }

    private void loadFileNames(String directoryPath) {
        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().endsWith(".txt")) {
                        scholarshipListView.getItems().add(file.getName());
                    }
                }
            }
        } else {
            showErrorAlert("Directory not found: " + directoryPath);
        }
    }

    @FXML
    private void handleItemClick() {
        String selectedFileName = scholarshipListView.getSelectionModel().getSelectedItem();

        if (selectedFileName != null && !selectedFileName.startsWith("[") && !selectedFileName.endsWith("]")) {
            // Use the current folder path to construct the file path
            String filePath = currentFolderPath + selectedFileName;

            Runnable fileClickTask = () -> {
                try {
                    String fileContent = FileUtils.readFileContent(filePath);
                    Platform.runLater(() -> fileContentTextArea.setText(fileContent));
                } catch (IOException e) {
                    showErrorAlert("Error reading file: " + filePath);
                    e.printStackTrace();
                }
            };

            new Thread(fileClickTask).start();
        }
    }

    @FXML
    private void handleEditFile() {
    	String selectedFileName = scholarshipListView.getSelectionModel().getSelectedItem();

        if (selectedFileName != null && !selectedFileName.startsWith("[") && !selectedFileName.endsWith("]")) {
            // Use the current folder path to construct the file path
            String filePath = currentFolderPath + selectedFileName;

            // Get the new content from the text area
            String newContent = fileContentTextArea.getText();

            // Write the new content to the file
            try {
                FileUtils.writeFileContent(filePath, newContent);
                showInfoAlert("File Edited", "Changes saved successfully.");
            } catch (IOException e) {
                showErrorAlert("Error writing to file: " + filePath);
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleBack() {
    	// Handle the Back button press
        System.out.println("Back button pressed");
        Main mainApp = (Main) SharedData.getInstance().getData("mainInstance");
        if (mainApp != null) {
            mainApp.switchToAdminMainScene();
        } else {
            System.out.println("mainApp is null. Check if it's properly set in SharedData.");
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


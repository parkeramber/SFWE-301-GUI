package application;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RateApplicantsController {
    private String currentFolderPath = "C:\\Users\\ryanm\\eclipse-workspace\\Student_Form\\Applied\\";
    
    
    @FXML
    private void handleSubmitSelections() {
        // Implement the logic to handle the submission of selections

        // Show success alert
        showSuccessAlert("Selections Submitted Successfully");
    }
    @FXML
    private ListView<HBox> appliedFoldersListView;

    @FXML
    private void initialize() {
        loadApplicants(currentFolderPath);
    }

    private void loadApplicants(String directoryPath) {
        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            File[] folders = directory.listFiles(File::isDirectory);

            if (folders != null) {
                ObservableList<HBox> items = FXCollections.observableArrayList();

                for (File folder : folders) {
                    List<String> filesInFolder = getFilesInFolder(folder);
                    items.add(createHeaderItem(folder.getName()));
                    items.addAll(createFileItems(filesInFolder));
                }

                appliedFoldersListView.setItems(items);
            }
        } else {
            showErrorAlert("Directory not found: " + directoryPath);
        }
    }

    private List<String> getFilesInFolder(File folder) {
        List<String> fileNames = new ArrayList<>();
        File[] files = folder.listFiles(File::isFile);

        if (files != null) {
            for (File file : files) {
                fileNames.add(file.getName());
            }
        }

        return fileNames;
    }

    private HBox createHeaderItem(String headerText) {
        HBox headerItem = new HBox();
        // Header text without checkbox, bold
        headerItem.getChildren().add(createLabel(headerText, true));
        return headerItem;
    }
    private Label createLabel(String text, boolean isBold) {
        Label label = new Label(text);
        label.setFont(Font.font("System", isBold ? FontWeight.BOLD : FontWeight.NORMAL, 12));
        return label;
    }
    private List<HBox> createFileItems(List<String> fileNames) {
        List<HBox> fileItems = new ArrayList<>();

        for (String fileName : fileNames) {
            HBox fileItem = new HBox(20); 
            fileItem.setAlignment(Pos.CENTER_LEFT);

            Label label = createLabel(fileName, false);
            CheckBox acceptedCheckBox = new CheckBox("Accepted");
            CheckBox deniedCheckBox = new CheckBox("Denied");

            fileItem.getChildren().addAll(label, acceptedCheckBox, deniedCheckBox);
            fileItems.add(fileItem);
        }

        return fileItems;
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
    private void showSuccessAlert(String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }
}

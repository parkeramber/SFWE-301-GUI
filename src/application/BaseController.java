package application;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class BaseController implements Initializable {

    private Main mainApp;

    public void setMain(Main mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    

    public void switchScene(String ScholarshipScene) {
       // mainApp.switchScene(ScholarshipScene);
    }
}

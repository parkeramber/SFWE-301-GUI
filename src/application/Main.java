// Main.java
package application;

import javafx.application.Application;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
	
    private Stage primaryStage;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        // Set the instance in SharedData when Main is created
        SharedData.getInstance().setData("mainInstance", this);

        // Switch to the initial scene 
        switchToSignUpScene();

        // Set the title
        primaryStage.setTitle("Sign Up Page");

        // Show the stage
        primaryStage.show();
    }
   
    public void switchToAdminLoginScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AdminLogin.fxml"));
            Parent adminLoginRoot = loader.load();

            AdminLoginController adminLoginController = loader.getController();
            adminLoginController.setMain(this);

            Scene adminLoginScene = new Scene(adminLoginRoot);
            primaryStage.setScene(adminLoginScene);  

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToSignUpScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Landing.fxml"));
            Parent root = loader.load();

            primaryStage.setScene(new Scene(root, 600, 400));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToLoginScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Login.fxml"));
            Parent root = loader.load();

            primaryStage.setScene(new Scene(root, 600, 400));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToStudentInfoFormScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/StudentInfoForm.fxml"));
            Parent root = loader.load();
         // Get the controller from the loader
            StudentInfoFormController studentInfoFormController = loader.getController();

            // Set the Main instance in the controller
            studentInfoFormController.setMain(this);
            primaryStage.setScene(new Scene(root, 600, 800));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void switchToAdminInfoFormScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AdminInfoForm.fxml"));
            Parent root = loader.load();
         // Get the controller from the loader
            AdminInfoFormController AdminInfoFormController = loader.getController();

            // Set the Main instance in the controller
            AdminInfoFormController.setMain(this);
            primaryStage.setScene(new Scene(root, 600, 800));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void switchToScholarshipScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/ScholarshipScene.fxml"));
            Parent root = loader.load();
            
            // Debug print to check if the controller is loaded
            System.out.println("Controller instance: " + loader.getController());

            ScholarshipController scholarshipController = loader.getController();
            scholarshipController.setMain(this);
            primaryStage.setScene(new Scene(root, 600, 600));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void switchToStatusScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/StatusScene.fxml"));
            Parent root = loader.load();
            StatusSceneController controller = loader.getController();
            controller.initialize(); // Initialize the status scene
            primaryStage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void switchToFrontScene() {
    	try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Front.fxml"));
            AnchorPane frontPage = loader.load();

            // Set the controller
            FrontController controller = loader.getController();
            controller.setMain(this);

            // Set the scene
            Scene scene = new Scene(frontPage);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }    public void switchToAdminMainScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AdminMainPage.fxml"));
            Parent root = loader.load();
            
            // Debug print to check if the controller is loaded
            System.out.println("Controller instance: " + loader.getController());

            AdminMainPageController AdminMainPageController = loader.getController();
            AdminMainPageController.setMain(this);
            primaryStage.setScene(new Scene(root, 500, 500));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void switchToEditProfileScene() {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/EditProfile.fxml"));
            Parent root = loader.load();

            // Get the controller from the loader
            EditProfileController editProfileController = loader.getController();

            // Set the Main instance in the controller
            editProfileController.setMain(this);

            primaryStage.setScene(new Scene(root, 600, 400));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void switchToLandingScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Landing.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 600, 400);
            primaryStage.setScene(scene);

            // Set the controller if needed
            LandingController landingController = loader.getController();
            landingController.setMain(this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void switchToViewScholarshipsScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewScholarships.fxml"));
            Parent root = loader.load();

            ViewScholarshipsController controller = loader.getController();
            

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void switchToRateApplicantsScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RateApplicantsScene.fxml"));
            Parent root = loader.load();

            RateApplicantsController controller = loader.getController();
            

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Rate Applicants");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void switchToEditScholarshipScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditScholarshipScene.fxml"));
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void switchToViewAppliedScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewAppliedScene.fxml"));
            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}


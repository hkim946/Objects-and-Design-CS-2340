package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Configuration controller for Configuration screen
 */
public class Configuration extends Application implements Initializable {
    @FXML
    private Button nextBtn;
    @FXML
    private Button level1;
    @FXML
    private Button level2;
    @FXML
    private Button level3;
    @FXML
    private Button kaleConfig;
    @FXML
    private Button tomatoConfig;
    @FXML
    private Button peanutsConfig;
    @FXML
    private TextField nameInput;

    /**
     *
     * @param theStage stage
     * @throws Exception handler
     */
    @Override
    public void start(Stage theStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../templates/InitialConfig.fxml"));
        Scene scene = new Scene(root);

        theStage.setScene(scene);
        theStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes the Configuration Screen
     * @param url url
     * @param rb rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        level1.setStyle("-fx-background-color: #E0E0E0");
        level2.setStyle("-fx-background-color: #E0E0E0");
        level3.setStyle("-fx-background-color: #E0E0E0");
        kaleConfig.setStyle("-fx-background-color: #E0E0E0");
        tomatoConfig.setStyle("-fx-background-color: #E0E0E0");
        peanutsConfig.setStyle("-fx-background-color: #E0E0E0");
    }

    /**
     * Action Handler for 'Next' Button
     * @param event button click
     * @return confirmation :1 error :-1
     * @throws Exception handler
     */
    @FXML
    private int pressNextButton(ActionEvent event) throws Exception {
        Stage stage;
        String difficultyInput;

        JsonHandler farmDataJSON = new JsonHandler();
        farmDataJSON.setFarmDataField("Name", nameInput.getText());
        difficultyInput = farmDataJSON.getFarmDataField("Difficulty");


        if (nameInput.getText() == null || nameInput.getText().equals("")) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Invalid Name");
            errorAlert.setContentText("Please input name");
            errorAlert.showAndWait();
            return -1;
        }

        if (difficultyInput.equals("none")) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Incomplete");
            errorAlert.setContentText("Please fill in all fields");
            errorAlert.showAndWait();
            return -1;
        } else {
            stage = (Stage) nextBtn.getScene().getWindow();
            InitialFarmUI farmUI = new InitialFarmUI();
            farmUI.start(stage);

            return 1;
        }

    }

    /**
     * Writes difficulty level selected to farmData.json when button is clicked
     * @param event button click
     * @throws Exception handler
     */
    @FXML
    private void difficultyInitializer(ActionEvent event) throws Exception {

        JsonHandler farmDataJSON = new JsonHandler();
        if (event.getSource() == level1) {
            level1.setStyle("-fx-background-color: Yellow");
            level2.setStyle("-fx-background-color: #E0E0E0");
            level3.setStyle("-fx-background-color: #E0E0E0");

            farmDataJSON.initializeFarmDataJSON("Noob Farmer");
        }
        if (event.getSource() == level2) {
            level2.setStyle("-fx-background-color: Yellow");
            level1.setStyle("-fx-background-color: #E0E0E0");
            level3.setStyle("-fx-background-color: #E0E0E0");

            farmDataJSON.initializeFarmDataJSON("Instagram Plant Girl");
        }
        if (event.getSource() == level3) {
            level3.setStyle("-fx-background-color: Yellow");
            level2.setStyle("-fx-background-color: #E0E0E0");
            level1.setStyle("-fx-background-color: #E0E0E0");

            farmDataJSON.initializeFarmDataJSON("G.W. Carver");
        }
        if (event.getSource() == kaleConfig) {
            kaleConfig.setStyle("-fx-background-color: Yellow");
            peanutsConfig.setStyle("-fx-background-color: #E0E0E0");
            tomatoConfig.setStyle("-fx-background-color: #E0E0E0");
            farmDataJSON.setFarmDataField("Type", "Kale");
        }
        if (event.getSource() == tomatoConfig) {
            tomatoConfig.setStyle("-fx-background-color: Yellow");
            peanutsConfig.setStyle("-fx-background-color: #E0E0E0");
            kaleConfig.setStyle("-fx-background-color: #E0E0E0");
            farmDataJSON.setFarmDataField("Type", "Tomato");
        }
        if (event.getSource() == peanutsConfig) {
            peanutsConfig.setStyle("-fx-background-color: Yellow");
            tomatoConfig.setStyle("-fx-background-color: #E0E0E0");
            kaleConfig.setStyle("-fx-background-color: #E0E0E0");
            farmDataJSON.setFarmDataField("Type", "Peanut");
        }
    }
}

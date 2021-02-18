package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Paths;

/**
 * Welcome controller for welcome UI
 */
public class Welcome extends Application {
    @FXML
    private Button startBtn;

    /**
     * Start for javafx screen
     * @param stage stage
     * @throws Exception handler
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../templates/welcome.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void pressStartButton(ActionEvent event) throws Exception {
        Stage stage;

        stage = (Stage) startBtn.getScene().getWindow();
        Configuration configScreen = new Configuration();
        configScreen.start(stage);


        Object obj = new JSONParser().parse(new FileReader(Paths.get("").toAbsolutePath().toString()
                + "/data/farmData.json"));
        JSONObject json = (JSONObject) obj;

        json.put("Difficulty", "none");
        json.put("Money", "0.00");
        json.put("Day", 0);
        FileWriter jsonFile = new FileWriter(Paths.get("").toAbsolutePath().toString()
                + "/data/farmData.json");
        jsonFile.write(json.toString());
        jsonFile.flush();

    }
    public static void main(String[] args) {
        launch(args); }
}

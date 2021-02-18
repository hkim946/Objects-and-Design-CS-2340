package application;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Market Controller for Market UI
 */
public class Market extends Application implements Initializable {
    private final int maxCapacity = 50;
    private double currMoney;
    private double seedPrice;
    private double harvestPrice;
    private int currSeeds;
    private int currHarvestPlants;
    private int seedNumber;
    private int plantNumber;
    private String difficulty;

    @FXML
    private Button buyBtn;
    @FXML
    private Button sellPlants;
    @FXML
    private Button sellSeeds;
    @FXML
    private MenuButton seedAmount;
    @FXML
    private MenuItem seed1;
    @FXML
    private MenuItem seed2;
    @FXML
    private MenuItem seed3;
    @FXML
    private MenuItem seed4;
    @FXML
    private MenuItem seed5;
    @FXML
    private MenuItem seed6;
    @FXML
    private MenuItem seed7;
    @FXML
    private MenuItem seed8;
    @FXML
    private MenuItem seed9;
    @FXML
    private MenuItem seed10;
    @FXML
    private MenuItem plant1;
    @FXML
    private MenuItem plant2;
    @FXML
    private MenuItem plant3;
    @FXML
    private MenuItem plant4;
    @FXML
    private MenuItem plant5;
    @FXML
    private MenuItem plant6;
    @FXML
    private MenuItem plant7;
    @FXML
    private MenuItem plant8;
    @FXML
    private MenuItem plant9;
    @FXML
    private MenuItem plant10;
    @FXML
    private MenuButton harvestPlantsAmount;
    @FXML
    private Button returnBtn;
    @FXML
    private Label seedLabel;
    @FXML
    private Label plantLabel;
    @FXML
    private Label moneyLabel;
    @FXML
    private Label seedPriceLabel;
    @FXML
    private Label plantPriceLabel;
    @FXML
    private Label seedOrSeeds;
    @FXML
    private Label plantOrPlants;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../templates/market.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Sets the new data if item can be bought or sold
     * and shows errors if there is not enough money or space in the inventory
     * @param event button being pushed
     *
     */
    public void buy(ActionEvent event) {

        if (currMoney - calculateSeedPrice() >= 0 && currSeeds + seedNumber <= maxCapacity) {
            currSeeds = currSeeds + seedNumber;
            currMoney = currMoney - calculateSeedPrice();
            moneyLabel.setText(String.format("%.2f", currMoney));
            seedLabel.setText(currSeeds + "");
        } else if (currMoney - calculateSeedPrice() < 0) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("MONEY ERROR");
            errorAlert.setContentText("Not enough money!");
            errorAlert.showAndWait();
        } else { // NOT ENOUGH SPACE FOR SEEDS ERROR
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("INVENTORY ERROR");
            errorAlert.setContentText("Not enough space in inventory!");
            errorAlert.showAndWait();
        }
    }
    public void sell(ActionEvent event) {
        if (event.getSource() == sellSeeds) {
            if (seedNumber <= currSeeds) {
                currMoney = currMoney + calculateSeedPrice();
                moneyLabel.setText(currMoney + "");
                currSeeds = currSeeds - seedNumber;
                seedLabel.setText(currSeeds + "");
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("INVENTORY ERROR");
                errorAlert.setContentText("Not enough seeds in inventory!");
                errorAlert.showAndWait();
            }
        } else { //EVENT SOURCE FROM SELL PLANT
            if (plantNumber <= currHarvestPlants) {
                currMoney = currMoney + calculatePlantPrice();
                moneyLabel.setText(currMoney + "");
                currHarvestPlants = currHarvestPlants - plantNumber;
                plantLabel.setText(currHarvestPlants + "");
            } else { //NOT ENOUGH PLANTS ERROR
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("INVENTORY ERROR");
                errorAlert.setContentText("Not enough plants in inventory!");
                errorAlert.showAndWait();
            }
        }

    }

    /**
     * calculates the seed price
     * @return seed price
     */
    private double calculateSeedPrice() {
        if (seedNumber == 0) {
            return 0;
        }
        double num = seedNumber * 10;
        switch (difficulty) {
        case "Noob Farmer": num += 1;
            break;
        case "Instagram\nPlant Girl": num += 3;
            break;
        case "G. W. Carver": num += 5;
            break;
        default: num = 0;
            break;
        }
        return num;
    }

    /**
     * calculates the plant price
     * @return plant price
     */
    private double calculatePlantPrice() {
        if (plantNumber == 0) {
            return 0;
        }
        double num = plantNumber * 20;
        switch (difficulty) {
        case "Noob Farmer": num -= 1;
            break;
        case "Instagram\nPlant Girl": num -= 2;
            break;
        case "G. W. Carver": num -= 3;
            break;
        default: num = 0;
            break;
        }
        return num;
    }

    /**
     * determines how many seeds user wants to buy
     * @param event button being pushed
     * @throws Exception handler
     */
    @FXML
    private void seedSelection(ActionEvent event) throws Exception {
        if (event.getSource() == "none") {
            seedNumber = 0;
        }
        if (event.getSource() == seed1) {
            seedAmount.setText("1");
            seedOrSeeds.setText("seed");
            seedNumber = 1;
        } else {
            seedOrSeeds.setText("seeds");
        }
        if (event.getSource() == seed2) {
            seedAmount.setText("2");
            seedNumber = 2;
        }
        if (event.getSource() == seed3) {
            seedAmount.setText("3");
            seedNumber = 3;
        }
        if (event.getSource() == seed4) {
            seedAmount.setText("4");
            seedNumber = 4;
        }
        if (event.getSource() == seed5) {
            seedAmount.setText("5");
            seedNumber = 5;
        }
        if (event.getSource() == seed6) {
            seedAmount.setText("6");
            seedNumber = 6;
        }
        if (event.getSource() == seed7) {
            seedAmount.setText("7");
            seedNumber = 7;
        }
        if (event.getSource() == seed8) {
            seedAmount.setText("8");
            seedNumber = 8;
        }
        if (event.getSource() == seed9) {
            seedAmount.setText("9");
            seedNumber = 9;
        }
        if (event.getSource() == seed10) {
            seedAmount.setText("10");
            seedNumber = 10;
        }
        double price = calculateSeedPrice();
        seedPriceLabel.setText(price + "");
    }

    /**
     * determines how many plants user wants to sell
     * @param event how many plant button pressed
     * @throws Exception handler
     */
    @FXML
    private void harvestPlantSelection(ActionEvent event) throws Exception {
        if (event.getSource() == "none") {
            plantNumber = 0;
        }
        if (event.getSource() == plant1) {
            harvestPlantsAmount.setText("1");
            plantOrPlants.setText("plant");
            plantNumber = 1;
        } else {
            plantOrPlants.setText("plants");
        }
        if (event.getSource() == plant2) {
            harvestPlantsAmount.setText("2");
            plantNumber = 2;
        }
        if (event.getSource() == plant3) {
            harvestPlantsAmount.setText("3");
            plantNumber = 3;
        }
        if (event.getSource() == plant4) {
            harvestPlantsAmount.setText("4");
            plantNumber = 4;
        }
        if (event.getSource() == plant5) {
            harvestPlantsAmount.setText("5");
            plantNumber = 5;
        }
        if (event.getSource() == plant6) {
            harvestPlantsAmount.setText("6");
            plantNumber = 6;
        }
        if (event.getSource() == plant7) {
            harvestPlantsAmount.setText("7");
            plantNumber = 7;
        }
        if (event.getSource() == plant8) {
            harvestPlantsAmount.setText("8");
            plantNumber = 8;
        }
        if (event.getSource() == plant9) {
            harvestPlantsAmount.setText("9");
            plantNumber = 9;
        }
        if (event.getSource() == plant10) {
            harvestPlantsAmount.setText("10");
            plantNumber = 10;
        }
        double price = calculatePlantPrice();
        plantPriceLabel.setText(price + "");
    }

    /**
     * returns to farm once "return to farm" button is clicked
     * @param event return button being pushed
     * @throws Exception handling
     */
    @FXML
     private void pressReturnToFarmBtn(ActionEvent event) throws Exception {
        //hide the window
        setInventoryData(currSeeds, currHarvestPlants);
        setMoney(currMoney);
        Stage stage;
        stage = (Stage) returnBtn.getScene().getWindow();
        stage.close();
        InitialFarmUI controller = InitialFarmUI.getFarmUIController();
        controller.updateInventory();
        controller.updateMoney();



    }

    /**
     * initializes seed, plant, and money labels
     * @param url needed to initialize
     * @param resourceBundle needed to initialize
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            seedLabel.setText(getInventoryData(1, 0));
            currSeeds = Integer.parseInt(getInventoryData(1, 0));

            plantLabel.setText(getInventoryData(0, 1));
            currHarvestPlants = Integer.parseInt(getInventoryData(0, 1));

            moneyLabel.setText(getMoneyandDiff() + "");
            currMoney = getMoneyandDiff();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * setter for seeds and plants data
     * @param seeds number of seeds
     * @param plants number of plants
     * @throws Exception handling
     */
    private void setInventoryData(int seeds, int plants) throws Exception {
        JsonHandler inventoryHandler = new JsonHandler();

        if (seeds >= 0) {
            inventoryHandler.setInventoryField("Seeds", seeds + "");
        }
        if (plants >= 0) {
            inventoryHandler.setInventoryField("Harvest", plants + "");
        }
    }

    /**
     * getter for seeds and plants data
     * @param seeds indicator
     * @param plants indicator
     * @return seeds or plants depending on indicator
     * @throws Exception handler
     */
    private String getInventoryData(int seeds, int plants) throws Exception {
        JsonHandler inventoryHandler = new JsonHandler();

        if (seeds > 0) {
            return inventoryHandler.getInventoryField("Seeds");
        }
        if (plants > 0) {
            return inventoryHandler.getInventoryField("Harvest");
        }
        return "0";
    }

    /**
     * setter for current money data
     * @param money amount of money
     * @throws Exception handler
     */
    private void setMoney(double money) throws Exception {
        JsonHandler farmDataHandler = new JsonHandler();
        if (money >= 0) {
            farmDataHandler.setFarmDataField("Money", String.valueOf(money));
        }
    }

    /**
     * getter for current money data
     * @return money in json
     * @throws Exception handler
     */
    private double getMoneyandDiff() throws Exception {
        JsonHandler farmDataHandler = new JsonHandler();

        difficulty = farmDataHandler.getFarmDataField("Difficulty");
        String monStr = farmDataHandler.getFarmDataField("Money");
        return Double.parseDouble(monStr);
    }
}



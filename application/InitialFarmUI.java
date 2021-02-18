package application;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * InitialFarmUI Controller for BasicUI
 */
public class InitialFarmUI extends Application implements Initializable {

    private int currSeeds;
    private int currHarvest;

    @FXML
    private Label difficultyLabel;
    @FXML
    private Label moneyLabel;
    @FXML
    private Label dayLabel;
    @FXML
    private Button waterButton;
    @FXML
    private Button harvestButton;
    @FXML
    private Label waterInventory;
    @FXML
    private Button marketButton;
    @FXML
    private Label seedInventory;
    @FXML
    private Label harvestInventory;
    @FXML
    private Button plantButton;
    @FXML
    private Button nextDayBtn;

    private Inventory inventory;
    private String dayStr;
    private int day;

    @FXML
    private ImageView plot00;
    @FXML
    private ImageView plot01;
    @FXML
    private ImageView plot02;
    @FXML
    private ImageView plot03;
    @FXML
    private ImageView plot04;
    @FXML
    private ImageView plot05;
    @FXML
    private ImageView plot06;
    @FXML
    private ImageView plot07;
    @FXML
    private ImageView plot08;
    @FXML
    private ImageView plot09;
    @FXML
    private ImageView plot10;
    @FXML
    private ImageView plot11;
    @FXML
    private ImageView plan00;
    @FXML
    private ImageView plan01;
    @FXML
    private ImageView plan02;
    @FXML
    private ImageView plan03;
    @FXML
    private ImageView plan04;
    @FXML
    private ImageView plan05;
    @FXML
    private ImageView plan06;
    @FXML
    private ImageView plan07;
    @FXML
    private ImageView plan08;
    @FXML
    private ImageView plan09;
    @FXML
    private ImageView plan10;
    @FXML
    private ImageView plan11;
    @FXML
    private ImageView displayPlant;
    @FXML
    private Label plantName;
    @FXML
    private ProgressBar waterProgress;


    private Plot[] plotArray;

    private ImageView[] plotImageViewArr;
    private ImageView[] plantImageViewArr;

    private static InitialFarmUI farmUIController;


    public void start(Stage stage) throws Exception {
        // Parent root = FXMLLoader.load(getClass().getResource("../templates/BasicUI.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../templates/BasicUI.fxml"));
        Parent root = loader.load();
        farmUIController = loader.getController();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Gets the InitialFarmUI controller to be used in other controllers
     * @return InitialFarmUI controller
     */
    static InitialFarmUI getFarmUIController() {
        return farmUIController;
    }

    /**
     * Initializes the array of plots on the InitialFarmUI screen
     */
    private void initializePlots() {
        plotArray = new Plot[12];
        Random rand = new Random();
        for (int i = 0; i < 12; i++) {
            Plant newPlant = generateRandomPlant(rand);
            newPlant.setPlot(i);
            plotArray[i] = new Plot(newPlant, i);
            System.out.println("Plot: " + i + " Plant: " + newPlant.getGrowthStage());
            addPlantToPlot(newPlant.getGrowthStage(), i);
        }
    }

    /**
     * Initializes the inventory class and stores the data in Inventory.json
     * @throws Exception handler
     */
    private void initializeInventory() throws Exception {
        JsonHandler inventoryHandler = new JsonHandler();

        inventory = new Inventory(10, 0, 0);
        waterInventory.setText("10");
        seedInventory.setText("0");
        harvestInventory.setText("0");

        inventoryHandler.initializeInventoryJSON();
    }

    /**
     * Updates the inventory when changes are made
     * @throws Exception handler
     */
    public void updateInventory() throws Exception {
        JsonHandler inventoryHandler = new JsonHandler();

        String newWater = inventoryHandler.getInventoryField("Water");
        String newHarvest = inventoryHandler.getInventoryField("Harvest");
        String newSeeds = inventoryHandler.getInventoryField("Seeds");

        waterInventory.setText(newWater);
        harvestInventory.setText(newHarvest);
        seedInventory.setText(newSeeds);

        inventory.setWater(Integer.parseInt(newWater));
        inventory.setHarvestedPlants(Integer.parseInt(newHarvest));
        inventory.setSeeds(Integer.parseInt(newSeeds));
    }

    /**
     * Updates the Money Label on InitialFarmUI
     * @throws Exception handler
     */
    public void updateMoney() throws Exception {
        JsonHandler farmDataHandler = new JsonHandler();
        moneyLabel.setText(farmDataHandler.getFarmDataField("Money"));
    }

    /**
     * Generates random order of plants on initialization of InitialFarmUI
     * @param rand object for random generation
     * @return Plant
     */
    private Plant generateRandomPlant(Random rand) {
        int select = rand.nextInt(3);
        switch (select) {
        case 0:
            return new Plant("seed", 0);
        case 1:
            return new Plant("Immature Plant", 0);
        case 2:
            return new Plant("Mature Plant", 0);
        default:
            return new Plant("seed", 0);
        }
    }

    /**
     * GUI Animation that highlights a plot when the user's mouse hovers over it
     * @param event from mouse hover
     */
    @FXML
    public void plotHighlighter(MouseEvent event) {
        ImageView img = (ImageView) event.getSource();
        Glow glow = new Glow();
        int plotIndex = Integer.parseInt(img.getId().substring(4));
        if (!plotArray[plotIndex].getSelected()) {
            ImageView plot = plotImageViewArr[plotIndex];
            if (event.getEventType().toString().equals("MOUSE_ENTERED")) {
                glow.setLevel(0.5);
                plot.setEffect(glow);
            }
            if (event.getEventType().toString().equals("MOUSE_EXITED")) {
                glow.setLevel(0);
                plot.setEffect(glow);
            }
        }
    }

    /**
     * Action event for when a plot is clicked by the user
     * @param event from mouse click
     * @throws Exception handler
     */

    @FXML
    public void selectPlot(MouseEvent event) throws Exception {
        JsonHandler inventoryHandler = new JsonHandler();
        harvestButton.setDisable(true);
        waterButton.setDisable(true);
        plantButton.setDisable(true);
        Glow oldglow = new Glow();
        oldglow.setLevel(0);

        for (int i = 0; i < 12; i++) {
            plotArray[i].setSelected(false);
        }

        for (int i = 0; i < 12; i++) {
            plotImageViewArr[i].setEffect(oldglow);
        }
        Glow newGlow = new Glow();
        newGlow.setLevel(0.5);

        ImageView img = (ImageView) event.getSource();
        int plotIndex = Integer.parseInt(img.getId().substring(4));
        plotImageViewArr[plotIndex].setEffect(newGlow);
        Plant plant = plotArray[plotIndex].getPlant();

        if (plant != null) {
            if (plant.getGrowthStage().equals("Mature Plant")) {
                harvestButton.setDisable(false);
            }
            if (!plant.getGrowthStage().equals("Dead Plant")) {
                waterButton.setDisable(false);
            }
        } else if (Integer.parseInt(inventoryHandler.getInventoryField("Seeds")) > 0
                && Integer.parseInt(inventoryHandler.getInventoryField("Water")) > 0) {
            plantButton.setDisable(false);
        }

        plotArray[plotIndex].setSelected(true);
        displayPlantStats(plotArray[plotIndex].getPlant());

    }

    /**
     * Action event for harvesting a plant when the harvest button is clicked
     * @param event from mouse click
     * @throws Exception handler
     */
    @FXML
    public void harvestPlant(MouseEvent event) throws Exception {
        Button button = (Button) event.getSource();
        button.setTranslateY(4);
        JsonHandler inventoryHandler = new JsonHandler();
        for (int i = 0; i < 12; i++) {
            if (plotArray[i].getPlant() != null && plotArray[i].getSelected()) {
                plotArray[i].harvestPlot();
                inventory.addHarvestedPlant();
                harvestInventory.setText(inventory.getHarvestedPlants() + "");
                removePlantFromPlot(i);
                inventoryHandler.setInventoryField("Harvest",
                        String.valueOf(inventory.getHarvestedPlants()));
                displayPlantStats(plotArray[i].getPlant());

                if (Integer.parseInt(inventoryHandler.getInventoryField("Seeds")) > 0) {
                    plantButton.setDisable(false);
                } else {
                    plotArray[i].setSelected(false);
                }
            }
        }
        button.setDisable(true);
    }

    /**
     * GUI Animation for clicking a button
     * @param event from mouse release
     */
    public void buttonAnimation(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setTranslateY(0);

    }

    /**
     * Adds a specified plant to a specified plot
     * @param plant to go on plot
     * @param plotIndex Index to plant
     */
    public void addPlantToPlot(String plant, int plotIndex) {
        Image image;
        if (plant.equals("seed")) {
            image = new Image(getClass().getResourceAsStream("../images/seed.png"));
            plantImageViewArr[plotIndex].setImage(image);
        }

        if (plant.equals("Immature Plant")) {
            image = new Image(getClass().getResourceAsStream("../images/little-plant.png"));
            plantImageViewArr[plotIndex].setImage(image);
        }

        if (plant.equals("Mature Plant")) {
            image = new Image(getClass().getResourceAsStream("../images/grown-plant.png"));
            plantImageViewArr[plotIndex].setImage(image);
        }

        if (plant.equals("Dead Plant")) {
            image = new Image(getClass().getResourceAsStream("../images/deadplant.jpg"));
            plantImageViewArr[plotIndex].setImage(image);
        }

        DropShadow shadow = new DropShadow();
        plantImageViewArr[plotIndex].setEffect(shadow);
        plantImageViewArr[plotIndex].setOpacity(1.0);
    }

    /**
     * displays selected plant
     * @param plant plant selected for display
     * @throws Exception handler
     */
    public void displayPlantStats(Plant plant) throws Exception {
        if (plant == null) {
            displayPlant.setOpacity(0);
            plantName.setText("Plant: ");
            waterProgress.setProgress(0);
        } else {
            JsonHandler farmDataHandler = new JsonHandler();
            String stage = plant.getGrowthStage();
            if (stage.equals("seed")) {
                Image image =
                        new Image(getClass().getResourceAsStream("../images/seed.png"));
                displayPlant.setImage(image);
            }

            if (stage.equals("Immature Plant")) {
                Image image =
                        new Image(getClass().getResourceAsStream("../images/little-plant.png"));
                displayPlant.setImage(image);
            }

            if (stage.equals("Mature Plant")) {
                Image image =
                        new Image(getClass().getResourceAsStream("../images/grown-plant.png"));
                displayPlant.setImage(image);
            }

            if (stage.equals("Dead Plant")) {
                Image image =
                        new Image(getClass().getResourceAsStream("../images/deadplant.jpg"));
                displayPlant.setImage(image);
            }

            plantName.setText("Plant: " + farmDataHandler.getFarmDataField("Type"));
            waterProgress.setProgress(plant.getWaterLevel());

            DropShadow shadow = new DropShadow();
            displayPlant.setEffect(shadow);
            displayPlant.setOpacity(1.0);
        }
    }


    /**
     * Removed a plant from a plot
     * @param plotIndex Plot to remove plant
     */
    public void removePlantFromPlot(int plotIndex) {
        plantImageViewArr[plotIndex].setOpacity(0);
    }

    /**
     * Action event for pressing the market button
     * @param event mouse click
     * @throws Exception handler
     */
    @FXML
    private void pressMarketBtn(MouseEvent event) throws Exception {
        Button button = (Button) event.getSource();
        button.setTranslateY(4);

        Stage stage = new Stage();

        Market marketScreen = new Market();
        marketScreen.start(stage);
    }

    /**
     * adds seeds to plot when plant button is pressed
     * @param event plant button pressed
     * @throws Exception handler
     */
    public void plantSeed(MouseEvent event) throws Exception {
        Button button = (Button) event.getSource();
        button.setTranslateY(4);
        JsonHandler inventoryHandler = new JsonHandler();

        for (int i = 0; i < 12; i++) {
            if (plotArray[i].getPlant() == null && plotArray[i].getSelected()) {
                plotArray[i].plantSeed(i);
                addPlantToPlot("seed", i);
                displayPlantStats(plotArray[i].getPlant());
            }
        }

        inventory.subtractSeed();
        inventoryHandler.setInventoryField("Seeds", String.valueOf(inventory.getSeeds()));
        updateInventory();
    }

    /**
     * updates plant's water level and inventory when water button is pressed
     * @param event button pressed
     * @throws Exception handler
     */
    public void waterPlant(MouseEvent event) throws Exception {
        Button button = (Button) event.getSource();
        button.setTranslateY(4);
        JsonHandler inventoryHandler = new JsonHandler();
        for (int i = 0; i < 12; i++) {
            if (plotArray[i].getPlant() != null && plotArray[i].getSelected()) {
                plotArray[i].waterPlot();
                displayPlantStats(plotArray[i].getPlant());
            }
        }
        inventory.subtractWater();
        inventoryHandler.setInventoryField("Water", String.valueOf(inventory.getWater()));
        updateInventory();

        if (inventory.getWater() == 0) {
            button.setDisable(true);
        }
    }

    /**
     * actions called when next day button is pressed
     * @throws Exception handler
     */
    @FXML
    private void pressNextDay() throws Exception {
        JsonHandler farmDataHandler = new JsonHandler();
        JsonHandler inventoryJSON = new JsonHandler();
        dayStr = farmDataHandler.getFarmDataField("Day");
        day = Integer.parseInt(dayStr);
        if (day++ < 100) {
            dayStr = Integer.toString(day);
            dayLabel.setText(dayStr);
            farmDataHandler.setFarmDataField("Day", dayStr);
            inventoryJSON.setInventoryField("Water", "10");
            updateInventory();
            boolean flag = randEvent();
            plantGrowth(day, flag);
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("MAX DAY ERROR");
            errorAlert.setContentText("There are no more days!");
            errorAlert.showAndWait();
        }
    }

    /**
     * changes plant stages
     * @param day current day
     * @param flag notifier
     * @throws Exception handler
     */
    private void plantGrowth(int day, boolean flag) throws Exception {
        for (int i = 0; i < 12; i++) {
            if (plotArray[i].getPlant() != null) {
                String currPlantStage = plotArray[i].getPlant().getGrowthStage();
                Plant currPlant = plotArray[i].getPlant();

                if (currPlant.getWaterLevel() >= 0.2 && !flag) {
                    currPlant.setWaterLevel(currPlant.getWaterLevel() - 0.2);
                }

                if (currPlantStage.equals("seed") && (day - currPlant.getCountDay()) % 5 == 0
                        && (currPlant.getWaterLevel() > 0.2 || currPlant.getWaterLevel() < 0.8)) {
                    plotArray[i].getPlant().setGrowthStage("Immature Plant");
                    addPlantToPlot("Immature Plant", i);
                } else if (currPlantStage.equals("Immature Plant")
                        && (day - currPlant.getCountDay()) % 4 == 0
                        && (currPlant.getWaterLevel() > 0.2 || currPlant.getWaterLevel() < 0.8)) {
                    plotArray[i].getPlant().setGrowthStage("Mature Plant");
                    addPlantToPlot("Mature Plant", i);
                } else if (currPlant.getWaterLevel() < 0.2 || currPlant.getWaterLevel() > 0.8) {
                    plotArray[i].getPlant().setGrowthStage("Dead Plant");
                    addPlantToPlot("Dead Plant", i);
                    currPlant.setWaterLevel(0.0);
                }
                if (plotArray[i].getSelected()) {
                    displayPlantStats(currPlant);
                }
            }
        }
    }

    private boolean randEvent() throws Exception {
        JsonHandler farmDataHandler = new JsonHandler();
        String diff = farmDataHandler.getFarmDataField("Difficulty");
        Random rand = new Random();
        boolean event = false;
        double chance = rand.nextDouble();
        chance *= 100;
        switch (diff) {
        case "Instagram Plant Girl":
            chance *= 1.25;
            break;
        case "G. W. Carver":
            chance *= 1.5;
            break;
        default:
            chance *= 1;
            break;
        }

        if (chance >= 120 && chance <= 130) {
            for (int i = 0; i < 12; i++) {
                //locusts
                Plant currPlant = plotArray[i].getPlant();
                int plotChance = rand.nextInt(10);
                if (plotArray[i].getPlant() != null && plotChance <= 2) {
                    plotArray[i].getPlant().setGrowthStage("Dead Plant");
                    addPlantToPlot("Dead Plant", i);
                    currPlant.setWaterLevel(0.0);
                }
            }
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("LOCUSTS");
            errorAlert.setContentText("Oh no! Locusts swarmed your "
                    + "farm last night! You now have some dead plants.");
            errorAlert.showAndWait();
        }
        if (chance >= 100 && chance <= 115) {
            //drought
            event = true;
            for (int i = 0; i < 12; i++) {
                Plant currPlant = plotArray[i].getPlant();
                int plotChance = rand.nextInt(10);
                if (plotArray[i].getPlant() != null && plotChance <= 3) {
                    currPlant.setWaterLevel(currPlant.getWaterLevel() - .4);
                    if (currPlant.getWaterLevel() < 0.2) {
                        plotArray[i].getPlant().setGrowthStage("Dead Plant");
                        addPlantToPlot("Dead Plant", i);
                        currPlant.setWaterLevel(0.0);
                    }
                }
            }
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("DROUGHT");
            errorAlert.setContentText("Oh no! There was a drought "
                    + "throughout the night! You now have some dead plants.");
            errorAlert.showAndWait();
        }
        if (chance >= 75 && chance <= 95) {
            //rain
            event = true;
            for (int i = 0; i < 12; i++) {
                Plant currPlant = plotArray[i].getPlant();
                int plotChance = rand.nextInt(10);
                if (plotArray[i].getPlant() != null && plotChance <= 4) {
                    currPlant.setWaterLevel(currPlant.getWaterLevel() + .4);
                    if (currPlant.getWaterLevel() > 0.8) {
                        plotArray[i].getPlant().setGrowthStage("Dead Plant");
                        addPlantToPlot("Dead Plant", i);
                        currPlant.setWaterLevel(0.0);
                    }
                }
            }
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("RAIN");
            errorAlert.setContentText("Oh no! There was rain "
                    + "throughout the night! You now have some dead plants.");
            errorAlert.showAndWait();
        }
        return event;
    }

    /**
     * Initializes the InitialFarmUI screen
     * @param url url
     * @param rb rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        JsonHandler jsonHandler = new JsonHandler();

        try {
            difficultyLabel.setText(jsonHandler.getFarmDataField("Difficulty"));
            moneyLabel.setText(jsonHandler.getFarmDataField("Money"));
            dayLabel.setText(jsonHandler.getFarmDataField("Day"));
            initializeInventory();
        } catch (Exception e) {
            e.printStackTrace();
        }

        waterButton.setStyle("-fx-background-color: #1F75FE");
        waterButton.setDisable(true);
        harvestButton.setStyle("-fx-background-color: #FB8B23");
        harvestButton.setDisable(true);
        marketButton.setStyle("-fx-background-color: #F51B00");
        plantButton.setDisable(true);
        plantButton.setStyle("-fx-background-color: #32CD32");
        plantName.setText("Plant: ");
        displayPlant.setOpacity(0);
        waterProgress.setProgress(0);

        plotImageViewArr = new ImageView[]{plot00, plot01, plot02, plot03, plot04,
            plot05, plot06, plot07, plot08, plot09, plot10, plot11};
        plantImageViewArr = new ImageView[]{plan00, plan01, plan02, plan03, plan04,
            plan05, plan06, plan07, plan08, plan09, plan10, plan11};

        initializePlots();

    }


}
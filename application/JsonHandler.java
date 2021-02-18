package application;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Paths;

/**
 * Class for handling json reading and writing
 */
public class JsonHandler {

    private String farmDataPath;
    private String inventoryPath;

    /**
     * No args constructor for JsonHandler
     */
    public JsonHandler() {
        farmDataPath = Paths.get("").toAbsolutePath().toString()
                + "/data/farmData.json";
        inventoryPath = Paths.get("").toAbsolutePath().toString()
                + "/data/Inventory.json";
    }

    /**
     * Instantiates a JSONParser for farmData.json
     * @return JSONObject for reading farmData.json
     * @throws Exception handler
     */
    private JSONObject instantiateFarmDataParser() throws Exception {
        Object farmDataParser = new JSONParser().parse(new FileReader(farmDataPath));
        return (JSONObject) farmDataParser;
    }

    /**
     * Instantiates a FileWriter object for farmData.json
     * @return FileWriter Object
     * @throws Exception handler
     */
    private FileWriter instantiateFarmDataWriter() throws Exception {
        return new FileWriter(farmDataPath);
    }

    /**
     * Instantiates a JSONParser object for Inventory.json
     * @return JSONObject
     * @throws Exception handler
     */
    private JSONObject instantiateInventoryParser() throws Exception {
        Object farmDataParser = new JSONParser().parse(new FileReader(inventoryPath));
        return (JSONObject) farmDataParser;
    }

    /**
     * Instantiates a FileWriter Object for Inventory.json
     * @return FileWriter Object
     * @throws Exception handler
     */
    private FileWriter instantiateInventoryWriter() throws Exception {
        return new FileWriter(inventoryPath);
    }

    /**
     * Initializes farmData.json to the default values based on difficulty
     * @param difficulty to set
     * @throws Exception handler
     */
    public void initializeFarmDataJSON(String difficulty) throws Exception {
        JSONObject farmDataJSON = instantiateFarmDataParser();
        FileWriter farmDataWriter = instantiateFarmDataWriter();
        if (difficulty.equals("Noob Farmer")) {
            farmDataJSON.put("Difficulty", "Noob Farmer");
            farmDataJSON.put("Money", "500");
            farmDataJSON.put("Day", "0");
        }

        if (difficulty.equals("Instagram Plant Girl")) {
            farmDataJSON.put("Difficulty", "Instagram\nPlant Girl");
            farmDataJSON.put("Money", "250");
            farmDataJSON.put("Day", "0");
        }

        if (difficulty.equals("G.W. Carver")) {
            farmDataJSON.put("Difficulty", "G. W. Carver");
            farmDataJSON.put("Money", "50");
            farmDataJSON.put("Day", "0");
        }

        farmDataWriter.write(farmDataJSON.toString());
        farmDataWriter.flush();
    }

    /**
     * Initializes Inventory.json to default values
     * @throws Exception handler
     */
    public void initializeInventoryJSON() throws Exception {
        JSONObject inventoryJSON = instantiateInventoryParser();
        FileWriter inventoryWriter = instantiateInventoryWriter();

        inventoryJSON.put("Water", "10");
        inventoryJSON.put("Seeds", "0");
        inventoryJSON.put("Harvest", "0");

        inventoryWriter.write(inventoryJSON.toString());
        inventoryWriter.flush();
    }

    /**
     * Sets a farmData.json field to a specified value
     * @param field desired
     * @param value desired
     * @throws Exception handler
     */
    public void setFarmDataField(String field, String value) throws Exception {
        JSONObject farmDataJSON = instantiateFarmDataParser();
        FileWriter farmDataWriter = instantiateFarmDataWriter();

        farmDataJSON.put(field, value);
        farmDataWriter.write(farmDataJSON.toString());
        farmDataWriter.flush();
    }

    /**
     * Gets a farmData.json value for a specific field
     * @param field to set
     * @return String representing value
     * @throws Exception handler
     */
    public String getFarmDataField(String field) throws Exception {
        JSONObject farmDataJSON = instantiateFarmDataParser();
        return (String) farmDataJSON.get(field);
    }

    /**
     * Sets a Inventory.json field to a specific value
     * @param field to set
     * @param value to replace
     * @throws Exception handler
     */
    public void setInventoryField(String field, String value) throws Exception {
        JSONObject inventoryJSON = instantiateInventoryParser();
        FileWriter inventoryWriter = instantiateInventoryWriter();

        inventoryJSON.put(field, value);
        inventoryWriter.write(inventoryJSON.toString());
        inventoryWriter.flush();
    }

    /**
     * Gets an Inventory.json field
     * @param field to return
     * @return String representing field value
     * @throws Exception handler
     */
    public String getInventoryField(String field) throws Exception {
        JSONObject inventoryJSON = instantiateInventoryParser();
        return (String) inventoryJSON.get(field);
    }

}

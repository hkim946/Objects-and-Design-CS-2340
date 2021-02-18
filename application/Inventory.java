package application;

/**
 * Represents an Inventory Object
 */
public class Inventory {

    private int water;
    private int seeds;
    private int harvestedPlants;

    /**
     * Constructor for Inventory Object
     * @param water amount of water
     * @param seeds amount of seeds
     * @param harvestedPlants amount of plants harvested
     */
    public Inventory(int water, int seeds, int harvestedPlants) {
        this.water = water;
        this.seeds = seeds;
        this.harvestedPlants = harvestedPlants;
    }

    //Setters

    /**
     * Setter for water field
     * @param water setting water
     */
    public void setWater(int water) {
        this.water = water;
    }

    /**
     * Getter for seed field
     * @param seeds setting seeds
     */
    public void setSeeds(int seeds) {
        this.seeds = seeds;
    }

    /**
     * setter for harvestedPlants field
     * @param harvestedPlants setting harvested plants
     */
    public void setHarvestedPlants(int harvestedPlants) {
        this.harvestedPlants = harvestedPlants;
    }

    //Getters

    /**
     * Getter for water field
     * @return water
     */
    public int getWater() {
        return water;
    }

    /**
     * Getter for seed field
     * @return seeds
     */
    public int getSeeds() {
        return seeds;
    }

    /**
     * getter for harvestedPlants field
     * @return harvested plants
     */
    public int getHarvestedPlants() {
        return harvestedPlants;
    }

    /**
     * Adds a harvested plant to the inventory
     */
    public void addHarvestedPlant() {
        harvestedPlants++;
    }

    public void subtractSeed() {
        seeds--;
    }

    public void subtractWater() {
        water--;
    }
}

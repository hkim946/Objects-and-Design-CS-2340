package application;

/**
 * Plot Object
 */
public class Plot {

    private Plant plant;
    private int plotIndex;
    private boolean isSelected;

    /**
     * Constructor for plot object
     * @param plant to go in plot
     * @param plotIndex on Farm screen
     */
    public Plot(Plant plant, int plotIndex) {
        this.plant = plant;
        this.plotIndex = plotIndex;
        this.isSelected = false;
    }

    //Setters

    /**
     * Setter for plant
     * @param plant to set
     */
    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    /**
     * Setter for plotIndex
     * @param plotIndex to set
     */
    public void setPlotIndex(int plotIndex) {
        this.plotIndex = plotIndex;
    }

    /**
     * Setter for isSelected
     * @param isSelected true of false
     */
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    //Getters

    /**
     * Getter for plant
     * @return plant
     */
    public Plant getPlant() {
        return plant;
    }

    /**
     * Getter for plotIndex
     * @return plotIndex
     */
    public int getPlotIndex() {
        return plotIndex;
    }

    /**
     * Getter for isSelected
     * @return isSelected
     */
    public boolean getSelected() {
        return isSelected;
    }

    /**
     * Harvest plot by setting plant to null
     */
    public void harvestPlot() {
        plant = null;
    }

    public void plantSeed(int plotIndex) throws Exception {
        plant = new Plant("seed", plotIndex);
        JsonHandler farmDataHandler = new JsonHandler();
        String dayStr = farmDataHandler.getFarmDataField("Day");
        int day = Integer.parseInt(dayStr);
        plant.setCountDay(day);
    }

    public void waterPlot() {
        plant.waterPlant();
    }

}

package application;

/**
 * Plant Object
 */
public class Plant {

    private double waterLevel;
    private String growthStage;
    private int plot;
    private int countDay;

    /**
     * Constructor for Plant Object
     * @param growthStage what stage the plant is in
     * @param plot plot that plant belongs to
     */
    public Plant(String growthStage, int plot) {
        this.waterLevel = calculateRandomWaterLevel();
        this.growthStage = growthStage;
        this.plot = plot;
        this.countDay = 0;
    }

    public double calculateRandomWaterLevel() {
        double seed = Math.random();
        if (seed < 0.2) {
            return 0.0;
        }
        if (seed < 0.4) {
            return 0.2;
        }
        if (seed < 0.6) {
            return 0.4;
        }
        if (seed < 0.8) {
            return 0.6;
        }
        if (seed <= 1.0) {
            return 0.8;
        } else {
            return  0.0;
        }
    }


    //Setters



    /**
     * Setter for growthStage
     * @param growthStage to set
     */
    public void setGrowthStage(String growthStage) {
        this.growthStage = growthStage;
    }

    /**
     * Setter for plot
     * @param plot to set
     */
    public void setPlot(int plot) {
        this.plot = plot;
    }

    /**
     * Setter for water Level
     * @param waterLevel to set
     */
    public void setWaterLevel(double waterLevel) {
        this.waterLevel = waterLevel;
    }

    //Getters

    /**
     * Getter for growthFactor
     * @return growthFactor
     */
    public double getWaterLevel() {
        return this.waterLevel;
    }

    /**
     * Getter for growthStage
     * @return growthStage
     */
    public String getGrowthStage() {
        return this.growthStage;
    }

    /**
     * Getter for plot
     * @return plot
     */
    public int getPlot() {
        return this.plot;
    }

    public void waterPlant() {
        waterLevel = waterLevel + .20;
    }

    /**
     * Setter for countDay (number of days it has been since seed has been plotted
     * @param countDay counting day
     */
    public void setCountDay(int countDay) {
        this.countDay = countDay;
    }

    /**
     * Getter for countDay (number of days it has been since seed has been plotted)
     * @return countDay
     */
    public int getCountDay() {
        return countDay;
    }

}

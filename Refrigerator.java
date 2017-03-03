public class Refrigerator extends Appliance {
    private static int defaultElectricityUse = 1;
    private static int defaultGasUse = 0;
    private static int defaultWaterUse = 0;


    public Refrigerator(int electricityUse, int gasUse, int waterUse, int timeOn, House myhouse) {
        super(electricityUse > 0 ? electricityUse : defaultElectricityUse,
                gasUse > 0 ? gasUse : defaultGasUse,
                waterUse > 0 ? waterUse : defaultWaterUse,
                timeOn = -1, myhouse);
        /**
         *  It must have a name event if it is never used.
         *  Otherwise it is not possible to assign an appliance to a task.
         */
        taskName = "Refrigerate!";
    }

    protected void setMeter(House myhouse) {
        this.electricMeter = myhouse.getElectricMeter();
    }

    /**
     * as this appliance is always on, the use method will not do anything.
     * still i had to overide it because it is abstract in Appliance.java class.
     */

}



public class WashingMachine extends Appliance {
    private static int defaultElectricityUse = 2;
    private static int defaultGasUse = 0;
    private static int defaultWaterUse = 1;
    private boolean ecousedbefore;


    public WashingMachine(int electricityUse, int gasUse, int waterUse, int timeOn, House myhouse) {
        super(electricityUse > 0 ? electricityUse : defaultElectricityUse,
                gasUse > 0 ? gasUse : defaultGasUse,
                waterUse > 0 ? waterUse : defaultWaterUse,
                timeOn = 8, myhouse);
        taskName = "DoWashing   DoWashingEcoMode";
    }

    protected void setMeter(House myhouse) {
        this.electricMeter = myhouse.getElectricMeter();
        this.waterMeter = myhouse.getWaterMeter();
    }

    /**
     * Use eco is called when the task implies the use of eco mode
     * The machine will be working with less electricity and water consumption
     * */
    protected void useeco(){
        this.turnOn();
        if (!ecousedbefore){
            electricityUse--;
            waterUse--;
        }
        ecousedbefore = true;
    }

    /**
     * Check if the eco mode was used before on this machine
     * If ecousedbefore is true then it will get back to the original values of electricityUse and waterUse
     */
    protected void use(){
        this.turnOn();
        if (ecousedbefore){
            electricityUse++;
            waterUse++;
        }
        ecousedbefore = false;
    }

}

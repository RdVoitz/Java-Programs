public class Boiler extends TurnOnoffAppliance {

    private static int defaultElectricityUse = 0;
    private static int defaultGasUse = 1;
    private static int defaultWaterUse = 0;
    private House myhouse;


    public Boiler(int electricityUse, int gasUse, int waterUse, int timeOn, House myhouse) {
        super(electricityUse > 0 ? electricityUse : defaultElectricityUse,
                gasUse > 0 ? gasUse : defaultGasUse,
                waterUse > 0 ? waterUse : defaultWaterUse,
                timeOn = -2, myhouse);
        taskName = "TurnOnHeating TurnOffHeating";
        this.myhouse = myhouse;
        currentState = true;
    }

    protected void setMeter(House myhouse) {
        this.gasMeter = myhouse.getGasMeter();
    }

    /**
     * It's currentState is true by default, but it can be turned off and then on again by GrownUps
     * It works only is house temperature is too low.
     */
    protected void timePasses() {
        if (currentState) {
            if (myhouse.getTemperature() < 20) {
                myhouse.increaseTempereture();
                if (gasMeter != null) {
                    for (int i = 1; i <= gasUse; i++)
                        gasMeter.incrementConsumed();
                }

            }
        }
    }
}


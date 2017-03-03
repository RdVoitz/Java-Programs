public class NightLight extends TurnOnoffAppliance {

    private static int defaultElectricityUse = 1;
    private static int defaultGasUse = 0;
    private static int defaultWaterUse = 0;


    public NightLight(int electricityUse, int gasUse, int waterUse, int timeOn, House myhouse) {
        super(electricityUse > 0 ? electricityUse : defaultElectricityUse,
                gasUse > 0 ? gasUse : defaultGasUse,
                waterUse > 0 ? waterUse : defaultWaterUse,
                timeOn = -2, myhouse);
        taskName = "TurnOnNightLight TurnOffNightLight";
    }

    protected void setMeter(House myhouse) {
        this.electricMeter = myhouse.getElectricMeter();
    }

}



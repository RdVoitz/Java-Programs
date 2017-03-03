public class Kettle extends Appliance {
    private static int defaultElectricityUse = 20;
    private static int defaultGasUse = 0;
    private static int defaultWaterUse = 1;

    public Kettle(int electricityUse, int gasUse, int waterUse, int timeOn, House myhouse) {
        super(electricityUse > 0 ? electricityUse : defaultElectricityUse,
                gasUse > 0 ? gasUse : defaultGasUse,
                waterUse > 0 ? waterUse : defaultWaterUse,
                timeOn = 1, myhouse);
        taskName = "Boil";
    }

    protected void setMeter(House myhouse) {
        this.electricMeter = myhouse.getElectricMeter();
        this.waterMeter = myhouse.getWaterMeter();
    }
}

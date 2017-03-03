public class ElectricShower extends Shower {
    private static int defaultElectricityUse = 12;
    private static int defaultGasUse = 0;
    private static int defaultWaterUse = 4;


    public ElectricShower(int electricityUse, int gasUse, int waterUse, int timeOn, House myhouse) {
        super(electricityUse > 0 ? electricityUse : defaultElectricityUse,
                gasUse > 0 ? gasUse : defaultGasUse,
                waterUse > 0 ? waterUse : defaultWaterUse,
                timeOn = 1, myhouse);
    }

    protected void setMeter(House myhouse) {
        this.electricMeter = myhouse.getElectricMeter();
        this.waterMeter = myhouse.getWaterMeter();
    }

}

public class PowerShower extends Shower {

    private static int defaultElectricityUse = 0;
    private static int defaultGasUse = 10;
    private static int defaultWaterUse = 5;


    public PowerShower(int electricityUse, int gasUse, int waterUse, int timeOn, House myhouse) {
        super(electricityUse > 0 ? electricityUse : defaultElectricityUse,
                gasUse > 0 ? gasUse : defaultGasUse,
                waterUse > 0 ? waterUse : defaultWaterUse,
                timeOn = 1, myhouse);
    }

    protected void setMeter(House myhouse) {
        this.gasMeter = myhouse.getGasMeter();
        this.waterMeter = myhouse.getWaterMeter();
    }

}

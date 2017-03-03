public class SolarCells extends GeneratorAppliance {
    private static int defaultElectricityUse = 1;
    private static int defaultGasUse = 0;
    private static int defaultWaterUse = 0;

    public SolarCells(int electricityUse, int gasUse, int waterUse, int timeOn, House myhouse) {
        super(electricityUse > 0 ? electricityUse : defaultElectricityUse,
                gasUse > 0 ? gasUse : defaultGasUse,
                waterUse > 0 ? waterUse : defaultWaterUse,
                timeOn = 3, myhouse);
    }
}



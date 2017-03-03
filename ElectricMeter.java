public class ElectricMeter extends Meter {

    public ElectricMeter(int consumed, boolean canGenerate) {
        super(consumed, canGenerate);
    }

    public String getType() {
        return "electric";
    }

}

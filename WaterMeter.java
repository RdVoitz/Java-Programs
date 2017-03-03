public class WaterMeter extends Meter {


    public WaterMeter(int consumed, boolean canGenerate) {
        super(consumed, canGenerate);
    }

    public String getType() {
        return "water";
    }
}


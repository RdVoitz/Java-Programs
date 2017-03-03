public class GasMeter extends Meter {


    public GasMeter(int consumed, boolean canGenerate) {
        super(consumed, canGenerate);
    }

    public String getType() {
        return "gas";
    }
}


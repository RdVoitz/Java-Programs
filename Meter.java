abstract class Meter {
    private int consumed;
    private int generated;
    private boolean canGenerate;

    /**
     * Meter Constructor
     * Takes a start value, or it begins from 0.
     * Specifies if the resource can be generated.
     * Initialises the value generated with 0.
     */
    public Meter(int consumed, boolean canGenerate) {
        this.consumed = consumed;
        this.canGenerate = canGenerate;
        generated = 0;
    }

    public void incrementConsumed() {
        consumed++;
    }

    public void incrementGenerated() {
        generated++;
    }

    boolean canGenerate() {
        return canGenerate;
    }

    public int getConsumed() {
        return consumed;
    }

    int getGenerated() {
        return generated;
    }

    abstract public String getType();


}

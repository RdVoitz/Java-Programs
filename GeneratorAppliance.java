public class GeneratorAppliance extends Appliance {
    private int time;// measures how many times timePasses() has been called

    /**
     * timeOn here shows how many timePasses() calls it takes for GeneratorAppliance to generate electricity
     * The electricityUse represents how much electricity it generates
     */
    public GeneratorAppliance(int electricityUse, int gasUse, int waterUse, int timeOn, House myhouse) {
        super(electricityUse, gasUse, waterUse, timeOn, myhouse);
        taskName = "Generate";
        time = timeOn;

    }

    /**
     * It can work only if the meter can generate electricity.
     * It will appear as an error if there is a GeneratorAppliance but the electric meter cannot generate
     */
    protected void setMeter(House myhouse) {
        if (myhouse.getElectricMeter().canGenerate()) {
            this.electricMeter = myhouse.getElectricMeter();
        } else System.err.println("The meter cannot generate electricity");
    }

    /**
     * It generates electricity when time is 0 .
     * It depends on how many timePasses calls ahve to pass.
     */
    protected void timePasses() {
        time--;
        if (time == 0) {
            if (electricityUse == 1) {
                electricMeter.incrementGenerated();
            } else
                for (int i = 1; i <= electricityUse; i++) {
                    electricMeter.incrementGenerated();
                }
            time = timeOn;
        }
    }
}

abstract class TurnOnoffAppliance extends Appliance {
    public TurnOnoffAppliance(int electricityUse, int gasUse, int waterUse, int timeOn, House myhouse) {
        super(electricityUse, gasUse, waterUse, timeOn, myhouse);
    }

    /**
     * The use method for TurnOn/Off appliances works a bit different.
     * When it is called it checks if the appliance is on or off.
     * It turns on the appliance if current State is false or it turns off if current State is true.
     * For example a TV by default is off.
     * When it is called first it will turn on the TV.
     * The second time it is called it will turn it off.
     * In this case use will be called by TurnOnTV and TurnOffTV tasks.
     */
    protected void use() {
        if (!this.getCurrentState()) {
            this.turnOn();
        } else this.turnOff();

    }
}


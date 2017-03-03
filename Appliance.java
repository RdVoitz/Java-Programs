abstract class Appliance {

    protected int electricityUse;
    protected int gasUse;
    protected int waterUse;
    protected int timeOn;
    private int usetime;
    protected boolean currentState;
    protected String taskName;

    protected Meter electricMeter = null;
    protected Meter gasMeter = null;
    protected Meter waterMeter = null;

    /**
     * Appliance Constructor
     * it sets how much of every resource is used in 15 min
     * sets the amount of time it remains working for when switched on
     * takes a house in order to set the meters using the setMeter method
     * this will make it increment the meters in the house without using any intermediate meters
     * if time is equal to -1 it means that the appliance is on all the time
     * if time is equal to -1 the currentState will be true through the entire simulation
     * this will allow it to work every time timePasses() is called
     */

    public Appliance(int electricityUse, int gasUse, int waterUse, int timeOn, House myhouse) {
        this.electricityUse = electricityUse;
        this.gasUse = gasUse;
        this.waterUse = waterUse;
        this.timeOn = timeOn;
        this.setMeter(myhouse);
        if (this.timeOn == -1) currentState = true;
        usetime = timeOn;


    }

    /**
     * This method is called at every cycle for every appliance
     * If the appliance is turned on, it will increment the specific meters(the one set in the constructor).
     * If the time is 0 it means that the appliance has finished its working process and it will be automatically turned off
     * else, it doesn't do anything
     * It is overrided in Boiler and WindTurbin appliance classes
     */
    protected void timePasses() {
        if (usetime == 0)
            this.turnOff();
        else if (currentState) {
            usetime--;
            if (electricMeter != null) {
                if (electricityUse == 1) {
                    electricMeter.incrementConsumed();
                } else
                    for (int i = 1; i <= electricityUse; i++) {
                        electricMeter.incrementConsumed();
                    }
            }
            if (gasMeter != null) {
                for (int i = 1; i <= gasUse; i++)
                    gasMeter.incrementConsumed();
            }
            if (waterMeter != null) {
                for (int i = 1; i <= waterUse; i++)
                    waterMeter.incrementConsumed();
            }
        }
    }

    /**
     * Simply turns on the appliance by setting true value to currentState
     * automatically gives the value from timeOn to a copy of it
     * this way you can use an appliance more times
     */
    public void turnOn() {
        usetime = timeOn;
        currentState = true;
    }

    /**
     * Turns off by setiing false value to currentState
     * when this is called, time will be equal to 0 ( if it is not a turn on/off appliance or one that is on all the time)
     */
    public void turnOff() {
        currentState = false;
    }


    /**
     * returns the currentState of an appliance
     * if it is true it means the appliance is on
     * else it is off
     */
    protected boolean getCurrentState() {
        return currentState;
    }

    /**
     * Sets the correct meters.
     * It is different for every appliance because they don't use the same meters.
     */
    abstract protected void setMeter(House myhouse);

    /**
     * When it is called, it turns on the appliance.
     * Override in TurnOnoffAppliance class because it works different for that type of appliances.
     */
    protected void use(){
        this.turnOn();
    }
    protected void useeco(){};//for the appliances that have an eco mode

    /**
     * Returns the name of the task that this appliance is doing.
     * This method is used in the Task.java class in order to assign the correct appliance to every task.
     */
    public String getTaskName() {
        return taskName;
    }
}

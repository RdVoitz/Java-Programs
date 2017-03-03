import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.PrintStream;

public class House {
    private int temperature = 20;
    private ArrayList<Appliance> houseAppliance = new ArrayList<Appliance>();
    private ArrayList<Person> housePersons = new ArrayList<Person>();
    private Meter waterMeter;
    private Meter gasMeter;
    private Meter electricMeter;
    private boolean isWaterMeter = false;
    private boolean isGasMeter = false;
    private boolean isElectricMeter = false;
    private PrintStream printer = new PrintStream("Register.txt");

    public House() throws FileNotFoundException {
    }

    protected Meter getWaterMeter() {
        return waterMeter;
    }

    protected Meter getElectricMeter() {
        return electricMeter;
    }

    protected Meter getGasMeter() {
        return gasMeter;
    }

    /**
     * Add a meter to the house if there is no meter of the same type
     */
    protected void addMeter(Meter meter) {
        if (meter.getType().equals("electric") && !isElectricMeter) {
            electricMeter = new ElectricMeter(meter.getConsumed(), meter.canGenerate());
            isElectricMeter = true;
        } else if (meter.getType().equals("gas") && !isGasMeter) {
            gasMeter = new GasMeter(meter.getConsumed(), meter.canGenerate());
            isGasMeter = true;
        } else if (meter.getType().equals("water") && !isWaterMeter) {
            waterMeter = new WaterMeter(meter.getConsumed(), meter.canGenerate());
            isWaterMeter = true;
        } else System.out.println("There is already one " + meter.getType() + " meter in the house");
    }

    protected ArrayList<Appliance> getHouseApplianceList() {
        return houseAppliance;
    }

    /**
     * This method adds a new appliance to the house
     * It won't allow more than 25 appliances
     */
    protected void addAppliance(Appliance appliance) {
        if (houseAppliance.size() < 25) {
            houseAppliance.add(appliance);
        } else System.out.println("The house has 25 appliances already");
    }

    protected void removeAppliance(Appliance appliance) {
        houseAppliance.remove(appliance);
    }

    protected int numAppliances() {
        return houseAppliance.size();
    }


    protected ArrayList<Person> getHousePersons() {
        return housePersons;
    }

    protected void addPerson(Person person) {
        housePersons.add(person);
    }

    protected Person getLastPerson() {
        return housePersons.get(housePersons.size() - 1);
    }


    protected int getTemperature() {
        return temperature;
    }

    protected void increaseTempereture() {
        temperature++;
    }

    /**
     * First it calls timePasses() on every person in the house
     * After that, some appliances are turned on, some not depending on tasks and appliance type
     * Then it iterates over the appliance list and calls timePasses() on every appliance
     */
    private void timePasses() {
        for (Person person : housePersons)
            person.timePasses();
        for (Appliance appliance : houseAppliance)
            appliance.timePasses();
    }

    private void printResults() {
        int quantity; // in this integer the generated amount of energy will be substracted from the consumed one
        if (electricMeter != null) {
            quantity = electricMeter.getConsumed() - electricMeter.getGenerated();
            System.out.println("The electricity consumed by this house in a day was " + quantity);
            printer.println(("The electricity consumed by this house in a day was " + quantity));
        }
        if (gasMeter != null) {
            System.out.println("The gas consumed by this house in a day was " + gasMeter.getConsumed());
            printer.println(("The electricity consumed by this house in a day was " + gasMeter.getConsumed()));
        }

        if (waterMeter != null) {
            System.out.println("The water consumed by this house in a day was " + waterMeter.getConsumed());
            printer.println(("The electricity consumed by this house in a day was " + waterMeter.getConsumed()));
        }
    }

    /**
     * Calls timePasses() in the house for 96 times wich is the equivalent of one day
     * Prints the results using the printResults() method
     * The temperature drops once every 2 hours
     * Considering that the first part of the day is during night, the teperature will drop by 2 degrees
     * Otherwise, if it is not the middle of the day, it drops by 1 degree
     */
    protected void go() {
        for (int i = 1; i < 97; i++) {
            if (i % 8 == 0 && i < 25) {
                temperature = temperature - 2;
            } else if (i % 8 == 0 && i < 49)
                temperature--;
            else if (i % 8 == 0 && i > 72)
                temperature--;
            this.timePasses();
        }
        this.printResults();
    }

    /**
     * Run the program by simply creating a new Simulator
     */
    public static void main(String[] args) {
        try {
            Simulator simulator = new Simulator();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

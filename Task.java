import java.util.ArrayList;
import java.util.Iterator;

public class Task {
    private House myhouse;
    private String name;
    private int time;
    private boolean hasAppliance;
    private ArrayList<Appliance> applianceList;

    /**
     * The Constructor sets the name, time of the task as well as the house where they will be done.
     * Initialise the appliance list.
     * Set the appliance using setAppliance().
     */
    public Task(String name, int time, House myhouse) {
        this.name = name;
        this.time = time;
        this.myhouse = myhouse;
        applianceList = new ArrayList<Appliance>();
        this.setAppliance();
    }

    public int getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    /**
     * Iterates over the Appliance list in the house.
     * Add the appliance that perform the task.
     * If no appliance that can perfomr the task is found, an error is thrown.
     */
    public void setAppliance() {
        for (Appliance appliance : myhouse.getHouseApplianceList()) {
            if (appliance.getTaskName().equals(name))
                applianceList.add(appliance);
            else if (appliance.getTaskName().contains(name))
                applianceList.add(appliance);
        }
    }

    /**
     * When doTask() is called, it calls the use() method of the first appliance in the applianceList.
     * If the task implies using Eco Mode then it will call the right use method.
     */
    public void doTask() {
        try {
            if (name.contains("EcoMode")) {
                applianceList.get(0).useeco();
            } else applianceList.get(0).use();
        } catch (Exception e) {
            System.err.println("There is no available apliance in the house for the following task: " + name);

        }
    }

}

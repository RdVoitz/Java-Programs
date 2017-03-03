import java.util.HashSet;


public class Child extends Person {
    private HashSet<String> forbiddenTask;

    /**
     * In addition to the Person constructor, this initialises a list of forbiddenTasks
     */
    public Child(String name, int age, String gender) {
        super(name, age, gender);
        forbiddenTask = new HashSet();
        this.fillSet();

    }

    /**
     * Set of forbidden tasks .
     * The list may be updated if new appliances are created.
     */
    private void fillSet() {
        forbiddenTask.add("TurnOffTV");
        forbiddenTask.add("Boil");
        forbiddenTask.add("Cook");
        forbiddenTask.add("TurnOnHeating");
        forbiddenTask.add("TurnOffHeating");
    }

    /**
     * Check if the task is not in the forbiddenTask set.
     * I chose to print the message as an error because it is easier for the user to see where is the mistake.
     */
    void addTask(Task task) {
        if (!forbiddenTask.contains(task.getName()))
            taskList.add(task);
        else System.err.println("A child cannot perform the following task: " + task.getName());
    }


}

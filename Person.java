import java.util.ArrayList;


abstract class Person {
    protected String name;
    protected int age;
    protected String gender;
    private int time = 0;
    protected ArrayList<Task> taskList;

    /**
     * The Constructor sets the name, age and gender of the person initialises the list of tasks in the constructor
     * Initialises the list of tasks and time
     */
    public Person(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        taskList = new ArrayList<Task>();
        time = 0;
    }

    abstract void addTask(Task task);

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Time passes method witch is called in house
     * Increments time at every call and iterates over the ArrayList of Tasks
     * Calls the method doTask() on tasks that have the same time value with the one in the person
     */
    public void timePasses() {
        time++;
        for (Task task : taskList) {
            if (task.getTime() == time)
                task.doTask();
        }

    }


}

public class GrownUp extends Person{
    public GrownUp(String name, int age, String gender) {
        super(name, age, gender);
    }


    void addTask(Task task) {
        taskList.add(task);
    }

}

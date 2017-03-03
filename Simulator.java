import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.io.PrintStream;


public class Simulator {
    /**
     * it works for multiple houses
     */

    private ArrayList<House> houseList;
    PrintStream printer = new PrintStream("Register.txt");
    /**
     * everything is done in the constructor
     * the main method only has to create a new simulator object and it will work by itself
     */
    public Simulator() throws FileNotFoundException {
        int housenumber = 0;
        houseList = new ArrayList<House>();
        House myhouse = new House();
        String currentLine;
        BufferedReader reader = null;
        int numberline = 0;

        /**
         *  create a new BufferedReader that will read the source file
         *  */
        try {
            reader = new BufferedReader(new FileReader("myHouse.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        /**
         * this is the interpreter
         * based on some patterns in the source file it creates new objects with different values in the constructor
         * */
        try {
            while ((currentLine = reader.readLine()) != null) {
                numberline++;
                if (!currentLine.equals("House:")) {
                    try {
                        if (currentLine.split(":")[0].endsWith("Meter")) {
                            this.retreiveMeter(myhouse, currentLine);
                        } else if (currentLine.split(":")[0].equals("Person")) {
                            this.retreivePerson(currentLine.split(":")[1], myhouse);
                        } else if (myhouse.getHousePersons().isEmpty()) {
                            this.retreiveAppliace(currentLine, myhouse);
                        } else
                            this.retreiveTask(myhouse.getLastPerson(), currentLine, myhouse);
                    } catch (Exception e) {
                        System.err.println("Data not recognised. Please check line " + numberline + " of the input file");
                        // Error is thrown if the input does not correspond to any pattern
                    }
                } else if (currentLine.equals("House:")) {
                    myhouse = new House();
                    houseList.add(myhouse);
                }
            }
        } catch (Exception e) {
            System.err.println("myHouse.txt not found !");
            //Error is thrown if there is no myHouse.txt file
        }
        /**
         * iterates over the list of houses
         * does the simulation for every house
         * */
        for (House house : houseList) {
            housenumber++;
            System.out.println("House number " + housenumber);
            printer.println("House number " + housenumber);
            house.go();
        }
    }

    private void retreiveMeter(House myhouse, String currentLine) {

        int consumed = 0;
        boolean canGenerate = false;
        Meter meter = null; // create a new instance of the appliance
        Class cl = null;
        try {
            cl = Class.forName(currentLine.split(":")[0]);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        /**
         * this means we do not have to enter another switch method just to know what kind of appliance to create
         * */

        Constructor constructor = null; // create a new constructor based on the newly created appliance
        try {
            constructor = cl.getConstructor(int.class, boolean.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        if (currentLine.split(":").length > 1) {
            if (currentLine.split(":")[1].contains(",")) {
                consumed = Integer.parseInt(currentLine.split(":")[1].split(",")[0]);
                if (currentLine.split(":")[1].split(",")[1].equals("true"))
                    canGenerate = true;
            } else if (currentLine.split(":")[1].equals("true") || currentLine.split(":")[1].equals("false")) {
                canGenerate = Boolean.valueOf(currentLine.split(":")[1]);
            } else consumed = Integer.parseInt(currentLine.split(":")[1]);
        }

        try {
            meter = (Meter) constructor.newInstance(consumed, canGenerate);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        myhouse.addMeter(meter); // add meter to the house
    }


    /**
     * Simple method wich creates a new person object
     * If the age parameter is under 18, the object will be Child
     * Otherwise it creates a GrownUp
     */
    private void retreivePerson(String personDetails, House myhouse) {
        Person newPerson;
        int x = Integer.parseInt(personDetails.split(",")[1]);
        if (x > 17) {
            newPerson = new GrownUp(personDetails.split(",")[0], x, personDetails.split(",")[2]);
        } else newPerson = new Child(personDetails.split(",")[0], x, personDetails.split(",")[2]);
        myhouse.addPerson(newPerson);
    }


    /**
     * This method uses reflection in order to create the right type of appliance
     */
    private void retreiveAppliace(String currentLine, House myhouse) {
        int electricityUse = 0;
        int gasUse = 0;
        int waterUse = 0;
        Appliance appliance = null;


        Class cl = null;
        try {
            cl = Class.forName(currentLine.split(":")[0]);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } // cl will represent the class with the name which has been read

        Constructor constructor = null; // create a new constructor for the newly created appliance
        try {
            constructor = cl.getConstructor(int.class, int.class, int.class, int.class, House.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        /**
         * Check if there are any details given about the appliance
         * if there are, they will be used in the constructor
         * else it will have some default values as constructor arguments
         * every Appliance has it's own default values
         * */
        if (currentLine.split(":").length > 1) {
            if (currentLine.split(":")[1].contains(",")) {
                if (currentLine.split(":")[1].split(",").length > 2) {
                    electricityUse = Integer.parseInt(currentLine.split(":")[1].split(",")[0]);
                    gasUse = Integer.parseInt(currentLine.split(":")[1].split(",")[1]);
                    waterUse = Integer.parseInt(currentLine.split(":")[1].split(",")[2]);

                } else {
                    electricityUse = Integer.parseInt(currentLine.split(":")[1].split(",")[0]);
                    gasUse = Integer.parseInt(currentLine.split(":")[1].split(",")[1]);
                }
            } else electricityUse = Integer.parseInt(currentLine.split(":")[1]);
        }
        /**
         * Creating the new appliance
         * */
        try {
            appliance = (Appliance) constructor.newInstance(electricityUse, gasUse, waterUse, 0, myhouse);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        myhouse.addAppliance(appliance); // add appliance to the house
    }

    /**
     * This method creates new Tasks
     * If the input is correct, the constructor receives all his arguments
     * Assign the task to the las person that has been read and added to the house
     */
    private void retreiveTask(Person person, String currentLine, House myhouse) {
        String taskname = currentLine.split(":")[0];
        int time = Integer.parseInt(currentLine.split(":")[1]);
        if (time < 1 || time > 96) System.err.println("Task time out of bounds");
        Task task = new Task(taskname, time, myhouse);
        try {
            person.addTask(task);
        } catch (Exception e) {
            System.err.println("The following task cannot be done by " + person.getName() + " :" + taskname);
        }
    }
}



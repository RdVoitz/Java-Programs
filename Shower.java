abstract class Shower extends Appliance {


    public Shower(int electricityUse, int gasUse, int waterUse, int timeOn, House myhouse) {
        super(electricityUse, gasUse, waterUse, timeOn, myhouse);
        taskName = "Shower";
    }
}




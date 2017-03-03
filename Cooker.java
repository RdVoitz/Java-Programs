
abstract class Cooker extends Appliance {


    public Cooker(int electricityUse, int gasUse, int waterUse, int timeOn, House myhouse) {
        super(electricityUse, gasUse, waterUse, timeOn, myhouse);
        taskName = "Cook";
    }
}

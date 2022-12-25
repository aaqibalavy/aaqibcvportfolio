package GameUtils;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> items; // new ArrayList of Item objects

    public Inventory(){
        items = new ArrayList<>();
        items.add(new HealthPotion()); // health potion object always initialised to first index
    }

    // accessor method
    public HealthPotion getHealthPotion(){return (HealthPotion)(items.get(0));}
}

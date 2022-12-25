package GameUtils;

public class HealthPotion extends Item{
    private int healAmount; // amount health potion heals
    private int quantity; // quantity of health potions

    HealthPotion(){
        this.healAmount = 20;
        this.quantity = 5;
        setName("Health Potion");
        setDescription("This potion heals for 20 additional health.");
        setDropRate(60);
    }

    // accessor and setter methods
    public int getHealAmount(){return this.healAmount;}
    public int getQuantity(){return this.quantity;}
    public void setHealAmount(int amount){this.healAmount = amount;}
    public void setQuantity(int amount){this.quantity = amount;}
    public void decrementQuantity(){ this.quantity--; }

}


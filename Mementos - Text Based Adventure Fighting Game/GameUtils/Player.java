package GameUtils;

import java.io.Serializable;
import java.util.Random;

public class Player implements Serializable {
    private String name; // player name
    private int vitality; // player health stat
    private int health; // player current health
    private double strength; // attack damage multiplier
    private int agility; // dodge chance
    private int bloodVials; // blood vials (used for increasing player stats)
    private static Inventory playerInventory; // player inventory containing ArrayList
    //public int healthPotions;
    private final static int MIN_DAMAGE = 15; // minimum damage from attacks before multipliers
    private final static int MAX_DAMAGE = 20; // maximum damage from attacks before multipliers

    public Player(){
        this.vitality = 150;
        this.health = 150;
        this.strength = 1.0;
        this.agility = 5;
        this.bloodVials = 0;
        playerInventory = new Inventory();
    }

    // constructor that takes a string for player's name
    public Player(String givenName) {
        this.name = givenName;
        this.vitality = 150;
        this.health = 150;
        this.strength = 1.0;
        this.agility = 5;
        this.bloodVials = 0;
        playerInventory = new Inventory();
    }

    // accessor methods
    public String getName(){ return this.name; }
    public int getVitality(){ return this.vitality; }
    public int getHealth(){ return this.health; }
    public double getStrengthMultiplier(){ return this.strength; }
    public int getStrength(){ return (int)(this.strength*10);}
    public int getAgility(){ return this.agility; }
    public int getBloodVials(){ return this.bloodVials; }
    public Inventory getPlayerInventory(){ return playerInventory; }
    //public int getHealthPotions(){return healthPotions; }

    // setter methods
    public void setName(String givenName){
        this.name = givenName;
    }
    public void setVitality(int amount){ this.vitality = amount; }
    public void setHealth(int amount){ this.health = amount; }
    public void setStrength(double amount){ this.strength = amount; }
    public void setAgility(int amount){ this.agility = amount; }
    public void setBloodVials(int amount){ this.bloodVials = amount; }
    //public void setHealthPotions(){ this.healthPotions = this.getPlayerInventory().getHealthPotion().getQuantity(); }

    // stat modifier methods
    public void increaseVitality(){ this.vitality += 10; }
    public void increaseStrength(){ this.strength += 0.5; }
    public void increaseAgility(){
        if (this.agility <= 50){
            this.agility += 2;
        }
        else{
            System.out.println("You have maxed out your agility stat.");
        }
    }

    // calculates damage of player attacks
    public double calcDamage(){
        Random rand = new Random();
        // random value between min and max damage is generated, then added to min damage and multiplied by player strength stat
        double damageDealt = (rand.nextInt(MAX_DAMAGE - MIN_DAMAGE) + 1 + MIN_DAMAGE) * strength;
        return damageDealt;
    }

    // increases player health by given amount
    public void heal(int amountHealed){
        this.health += amountHealed;
    }

    // determines player dodge based on player agility and random integer generation
    public boolean playerDodge(){
        Random rand = new Random();
        boolean dodged = false;
        int diceRoll = rand.nextInt(100) + 1;
        if(diceRoll <= this.agility){
            dodged = true;
        }
        return dodged;
    }

}

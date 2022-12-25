package GameUtils;

import java.util.Random;

public abstract class Enemy {
    private int health; // enemy health
    private int dodge; // enemy dodge chance
    private int minDamage; // base amount of enemy damage
    private int maxDamage; // max amount of enemy damage

    public Enemy(){}

    // calculates enemy health based on random integer generation two passed integers
    public int calcHealth(int min, int max){
        Random rand = new Random();
        int calculatedHealth = rand.nextInt(max-min) + min;
        return calculatedHealth;
    }

    // accessor and setter methods
    public int getHealth(){ return this.health; }
    public void setHealth(int health){ this.health = health; }
    public void setDodge(int dodge){ this.dodge = dodge; }
    public void setMinDamage(int minDamage){ this.minDamage = minDamage; }
    public void setMaxDamage(int maxDamage){ this.maxDamage = maxDamage; }

    // calculates enemy damage inflicted using their specific damage attributes (initialised in subclasses)
    public int calcDamage(){
        Random rand = new Random();
        int damageDealt = rand.nextInt(maxDamage - minDamage) + minDamage;
        return damageDealt;
    }

    // calculates if enemy dodges player attack based on enemy's dodge attribute (initialised in subclasses)
    public boolean enemyDodge(){
        Random rand = new Random();
        boolean dodged = false;
        int diceRoll = rand.nextInt(100) + 1;
        if(diceRoll <= this.dodge){
            dodged = true;
        }
        return dodged;
    }

    // calculates if enemy drops items based on random number generation using a bound argument
    public boolean itemDrop(int bound){
        Random rand = new Random();
        boolean dropped = false;
        int diceRoll = rand.nextInt(bound) + 1;
        if(diceRoll == 1){
            dropped = true;
        }
        return dropped;
    }

    // calculates whether enemy drops a health potion and displays message
    public boolean dropHealthPotion(Inventory playerInventory, String enemyType){
        boolean dropped;
        Random rand = new Random();
        int diceRoll = rand.nextInt(100)+1;
        if(diceRoll <= playerInventory.getHealthPotion().getDropRate()){
            int diceRoll2 = rand.nextInt(2)+1;
            playerInventory.getHealthPotion().setQuantity(playerInventory.getHealthPotion().getQuantity() + diceRoll2);
            dropped = true;
            System.out.println("The " + enemyType + " dropped " + diceRoll2 + " health potion" + Game.isPlural(diceRoll2) + "!");

        }
        else{ dropped = false; }
        return dropped;
    }

    // calculates whether enemy drops blood vials
    public boolean dropBloodVials(GameState game, Player player){
        boolean dropped = false;
        Random rand = new Random();
        int diceRoll = rand.nextInt(3)+1;
        if (diceRoll == 1) {
            dropped = false;
        }
        else {
            dropped = true;
            // floor number alters maximum number of blood vials that can be dropped
            if (game.getCurrentFloor() == 1) {
                player.setBloodVials(player.getBloodVials() + 1);
                System.out.println("The " + game.getCurrentEnemyType() + " dropped a blood vial!");
            } else if (game.getCurrentFloor() == 2) {
                int diceRoll2 = rand.nextInt(2) + 1;
                player.setBloodVials(player.getBloodVials() + diceRoll2);
                System.out.println("The " + game.getCurrentEnemyType() + " dropped " + diceRoll2 + " blood vial" + Game.isPlural(diceRoll2) + "!");
            } else if (game.getCurrentFloor() == 3) {
                int diceRoll3 = rand.nextInt(3) + 1;
                player.setBloodVials(player.getBloodVials() + diceRoll3);
                System.out.println("The " + game.getCurrentEnemyType() + " dropped " + diceRoll3 + " blood vial" + Game.isPlural(diceRoll3) + "!");
            }
        }
        return dropped;
    }

    public boolean enemyHealthPotionDrop(){ //use as template for other drops that extend the itemDrop method
        boolean dropped = itemDrop(2);
        return dropped;
    }

}

package GameUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class GameState implements Serializable {
    private static int currentFloor; // tracks current floor
    private static Enemy currentEnemy; // tracks current enemy
    private static String currentEnemyType; // tracks the type of current enemy
    private static boolean currentEnemyKilled; // holds boolean value for whether current enemy has been killed
    private static int enemyCounter; // tracks number of enemies encountered
    private static ArrayList<Enemy> enemiesEncountered; // ArrayList that holds all enemies encountered
    private static ArrayList<Enemy> enemiesKilled; // ArrayList that holds all enemies killed.
    private boolean continueCurrentFloor; // holds value for whether player has chosen to continue fighting on current floor
    private boolean firstClear; // holds value for whether the first clear of the current program run is in motion

    public GameState() {
        currentFloor = 1;
        enemiesEncountered = new ArrayList<>();
        enemiesKilled = new ArrayList<>();
        currentEnemyKilled = false;
        continueCurrentFloor = true;
        firstClear = true;
    }

    // accessor methods
    public int getCurrentFloor() {return currentFloor;}
    public String getCurrentEnemyType() {return currentEnemyType;}
    public boolean getCurrentEnemyKilled() {return currentEnemyKilled;}
    public int getEnemyCounter() {return enemyCounter;}
    public ArrayList<Enemy> getEnemiesKilled() {return enemiesKilled;}
    public ArrayList<Enemy> getEnemiesEncountered() {return enemiesEncountered;}
    public boolean getContinueCurrentFloor(){ return continueCurrentFloor; }
    public boolean getFirstClear() { return firstClear; }

    // setter methods
    public void setCurrentFloor(int floorNumber) {currentFloor = floorNumber;}
    public void setEnemyCounter(int amount) {enemyCounter = amount;}
    public void setCurrentEnemyKilled(boolean killed) {currentEnemyKilled = killed;}
    public void setContinueCurrentFloor(boolean choice){ continueCurrentFloor = choice; }
    public void setFirstClear(boolean choice){ firstClear = choice; }

    // randomises the type of the enemy to be encountered
    public void randomiseEnemy() {
        Random rand = new Random();
        int diceRoll = rand.nextInt(3) + 1;

        // currentEnemy object is initialised to a specific subclass of Enemy depending on randomised integer, equal 33% chance for each enemy type
        if (currentFloor == 1) {
            if (diceRoll == 1) {
                currentEnemy = new Zombie();
            } else if (diceRoll == 2) {
                currentEnemy = new Skeleton();
            } else {
                currentEnemy = new Assassin();
            }
        }
        //add other floors and enemies
    }

    // checks if Enemy object passed is of the passed class type
    public boolean isInstanceOf(Enemy enemy, Class<?> enemyType) {
        return enemyType.isAssignableFrom(enemy.getClass());
    }

    // checks if the floor is cleared by checking if one of each enemy type has been killed
    public boolean isFloorCleared() {
        boolean floorCleared = false;
        int zombiesKilled = 0;
        int skeletonsKilled = 0;
        int assassinsKilled = 0;

        // iterates through enemiesKilled ArrayList, checking the subclass type of each Enemy object
        for (Enemy enemy : enemiesKilled) {
            if (currentFloor == 1) {
                if (isInstanceOf(enemy, Zombie.class)) {
                    zombiesKilled++;
                } else if (isInstanceOf(enemy, Skeleton.class)) {
                    skeletonsKilled++;
                } else if (isInstanceOf(enemy, Assassin.class)) {
                    assassinsKilled++;
                }
            }
        }
        // checks for whether at least three of each enemy type is found in the ArrayList
        if (zombiesKilled >= 3 & skeletonsKilled >= 3 & assassinsKilled >= 3) {
            floorCleared = true;
        }

        return floorCleared;
    }

    // after randomising Enemy object's subclass type, a new subclass object is initialised and added to the enemy ArrayLists
    public void determineEnemyType(Player player) {
        randomiseEnemy();
        enemyCounter++;
        currentEnemyKilled = false;

        // checks subclass type of currentEnemy object
        if (isInstanceOf(currentEnemy, Zombie.class)) {
            Zombie z = new Zombie();
            enemiesEncountered.add(z);
            currentEnemyType = "Zombie";
        } else if (isInstanceOf(currentEnemy, Skeleton.class)) {
            Skeleton s = new Skeleton();
            enemiesEncountered.add(s);
            currentEnemyType = "Skeleton";
        } else if (isInstanceOf(currentEnemy, Assassin.class)) {
            Assassin a = new Assassin();
            enemiesEncountered.add(a);
            currentEnemyType = "Assassin";
        }
        //add other enemies
    }
}

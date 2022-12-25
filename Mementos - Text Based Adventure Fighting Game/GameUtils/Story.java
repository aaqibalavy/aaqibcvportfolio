package GameUtils;
import GUI.*;

import java.util.Random;

public class Story {
    Player player;
    GameState gameState;
    GameFrame gameFrame;

    public Story(Player givenPlayer, GameState game) {
        this.player = givenPlayer;
        this.gameState = game;
    }

    // start of game description
    public void startDesc() {
        System.out.println("I commend you for venturing down here " + player.getName() + ".");
        System.out.println("\nThis underworld labyrinth is separated into three floors and you are currently" +
                " on the highest floor; the \"safest\" floor.\nEach floor is host to a plethora of demons, so tread carefully.");
    }

    // description for game resumption after loading file
    public void resumeDesc() {
        System.out.println("Welcome back " + player.getName() + "! Looks like you've got some balls after all.\nNow continue on with your venture.");
    }

    // determines if player fights or successfully runs (run success based on random generation - 75% chance)
    public boolean fightOrRun(int choice) {
        boolean fightChosen = false;
        // checks if player chose to fight or run
        if (choice == 1) {
            System.out.println("You prepare to fight.");
            fightChosen = true;
        } else if (choice == 2) {
            // random integer generation
            Random rand = new Random();
            int diceRoll = (rand.nextInt(4)) + 1;

            // determines if escape is successful using random integer generation
            if (diceRoll == 4) {
                System.out.println("You were unable to escape!");
                fightChosen = true;
                Combat combat = new Combat(gameState);
                // enemy attacks player first upon player failing to escape
                combat.enemyFirstAttack(gameState.getEnemiesEncountered().get(gameState.getEnemyCounter() - 1), player);
            } else {
                System.out.println("You successfully escaped.");
                fightChosen = false;
            }
        } else {
            fightChosen = false;
        }
        return fightChosen;
    }

    // displays enemy encounter description
    public void enemyEncounter() {
        System.out.println("\nAs you wander through the darkness, a shadowy figure emerges...");
        gameState.determineEnemyType(player); // determines type of enemy encountered
        System.out.println("A " + gameState.getCurrentEnemyType() + " has appeared! It has " + gameState.getEnemiesEncountered().get(gameState.getEnemyCounter() - 1).getHealth() + " health.");
        System.out.println("Would you like to fight or run? Please select a choice from the options below.");
    }

    // conducts the fight between player and enemy, appropriately responding to player action taken and then providing enemy response
    public void enemyFight(int choice, GameFrame givenGameframe) {
        gameFrame = givenGameframe;
        Combat combat = new Combat(gameState);
        if (choice == 3) { // player chooses to attack enemy
            combat.playerBasicAttack(gameState.getEnemiesEncountered().get(gameState.getEnemyCounter() - 1), player);
        } else if (choice == 4) { // player chooses to heal
            combat.playerHeal(gameState.getEnemiesEncountered().get(gameState.getEnemyCounter() - 1), player);
            gameFrame.getStatsPanel().setHealthPotionLabelAmount(player.getPlayerInventory().getHealthPotion().getQuantity());
        }
        // enemy attacks player if not already dead
        if (gameState.getEnemiesEncountered().get(gameState.getEnemyCounter() - 1).getHealth() > 0) {
            combat.enemyBasicAttack(gameState.getEnemiesEncountered().get(gameState.getEnemyCounter() - 1), player);
        }

        if (gameState.getEnemiesEncountered().get(gameState.getEnemyCounter() - 1).getHealth() <= 0) {
            // game state is update upon enemy defeat
            System.out.println("You have defeated the " + gameState.getCurrentEnemyType() + "!");
            gameState.setCurrentEnemyKilled(true);
            gameState.getEnemiesKilled().add(gameState.getEnemiesEncountered().get(gameState.getEnemyCounter() - 1));
            gameState.getEnemiesEncountered().get(gameState.getEnemyCounter() - 1).dropHealthPotion(player.getPlayerInventory(), gameState.getCurrentEnemyType());
            gameState.getEnemiesEncountered().get(gameState.getEnemyCounter() - 1).dropBloodVials(gameState, player);
        }
    }
}


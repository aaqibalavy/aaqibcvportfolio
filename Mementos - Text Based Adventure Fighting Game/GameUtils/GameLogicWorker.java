package GameUtils;

import FileUtils.GameFileWriter;
import GUI.GameFrame;
import GUI.GameOutputStream;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintStream;

public class GameLogicWorker  {
    private static GameState gameState;
    private static Player player;
    private static Story story;
    private int choice;
    private boolean fightChosen;
    private GameFileWriter gameFileWriter;

    GameLogicWorker(GameState game, Player givenplayer, Story givenstory){
        gameState = game;
        player = givenplayer;
        story = givenstory;
        //System.out.println(Thread.currentThread().getName()); // test message
    }

    // executes and runs the game until game ends upon user choice to do so
    public void runGame(GameFrame gameFrame) throws InterruptedException, IOException {
        executeGameScreen(gameFrame); // updates game screen
        // unique descriptions depending on whether new game started or previous game save was loaded from file
        if(gameFrame.isFileLoaded()){
            story.resumeDesc();
        }
        else {
            story.startDesc();
        }

        // loops game (enemy encounters and fights) until user chooses to end game
        while(gameState.getContinueCurrentFloor()) {
            fightChosen = executeEncounter(gameFrame);
            if (fightChosen) {
                // visible buttons changed upon entering fight
                gameFrame.getActionsPanel2().removeEncounterBtns();
                gameFrame.getActionsPanel2().showFightBtns();
                // loops fight sequence until enemy is dead
                while (gameState.getEnemiesEncountered().get(gameState.getEnemyCounter() - 1).getHealth() > 0) {
                    executeFight(gameFrame);
                }
            }
            if (gameState.isFloorCleared()) { // checks if floor has been cleared
                if (gameState.getFirstClear()) { // checks whether the first clear of the current program run is in motion
                    // player action buttons are hidden
                    gameFrame.getActionsPanel2().removeEncounterBtns();
                    gameFrame.getActionsPanel2().removeFightBtns(gameState.getCurrentEnemyKilled());
                    System.out.println("\nYou have cleared the first floor by defeating at least three of each enemy type!\nThe second floor awaits your descent into the depths...\nHowever, you are not strong enough so you must remain here on the first floor until Mementos deems you fit to descend.");
                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        System.err.println("Error");
                    }
                    // confirm dialog box checks if user wants to end game or not
                    int input = JOptionPane.showConfirmDialog(gameFrame, "Would you like to end the game?", "End Game", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    if (input == 0) {
                        gameState.setContinueCurrentFloor(false);
                    }
                    else{
                        gameState.setFirstClear(false);
                    }
                }
            }
        }

        // all action buttons hidden upon end game
        gameFrame.getActionsPanel2().removeEncounterBtns();
        gameFrame.getActionsPanel2().removeFightBtns(gameState.getCurrentEnemyKilled());
        System.out.println("\nHow disappointing to see you give up so soon, it seems you are just simply of the faint-hearted.\nIn spite of your cowardice, your progress shall be saved, and you can carry on from where you left off should you decide to muster up the courage to return...");
        System.out.println();
        //player.setHealthPotions();

        gameFileWriter = new GameFileWriter(gameState, player, gameFrame);
        Thread.sleep(3000);
        // input dialog box prompts user input for desired file name
        String fileName = JOptionPane.showInputDialog(gameFrame, "Please enter desired filename:", "Filename Dialog", JOptionPane.INFORMATION_MESSAGE);
        // new file is created with Player and GameState objects' data written to it in serialized byte code
        gameFileWriter.createPlayerSaveFile(fileName);
        gameFileWriter.createGameStateFile(fileName);
        System.out.println("\nI'll see you soon " + player.getName() + ".");
    }

    // pauses main thread until submit event is fired, to which the thread is resumed and game sequence continues
    public void executeGameScreen(GameFrame gameFrame) throws InterruptedException {
        synchronized (this) {
            try {
                //System.out.println("waiting");
                wait(); // main thread paused
                if(!gameFrame.isFileLoaded()) {
                    String name = gameFrame.getName();
                    player.setName(name);
                }
                PrintStream printStream = new PrintStream(new GameOutputStream(gameFrame.getMainPanel().getTextArea()));
                PrintStream standardOut = System.out; // reference for standard output stream to console
                System.setOut(printStream);
                //System.out.println("waiting done");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Error");
            }
        }
    }

    // pauses main thread until player chooses to fight or run using action button listeners
    public boolean executeEncounter(GameFrame gameFrame) {
        fightChosen = false;
        story.enemyEncounter();
        gameFrame.getActionsPanel2().showEncounterBtns();
        synchronized (this) {
            try {
                //System.out.println("waiting");
                wait(); // main thread paused
                choice = gameFrame.getActionsPanel2().getPlayerChoice(); // retrieves player choice after event is fired and action is performed in response
                fightChosen = story.fightOrRun(choice);
                //System.out.println("waiting done");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Error");
            }
        }
        return fightChosen;
    }

    // pauses main thread until player chooses to attack or heal using action button listeners
    public void executeFight(GameFrame gameFrame) {
        synchronized (this){
            try {
                //System.out.println("waiting");
                if (fightChosen) {
                    System.out.println("Attack or heal?");
                }
                wait();
                System.out.println();
                choice = GameFrame.getActionsPanel().getPlayerChoice(); // retrieves player choice after event is fired and action is performed in response
                story.enemyFight(choice, gameFrame); // conducts enemy fight
                // health potion and current health labels are updated
                gameFrame.getStatsPanel().setHealthPotionLabelAmount(player.getPlayerInventory().getHealthPotion().getQuantity());
                gameFrame.getStatsPanel().setHealthLabelAmount(player.getHealth());
                // blood vials, health potions and current health labels updated upon enemy death, as well as visibility of player action buttons altered
                if(gameState.getCurrentEnemyKilled()) {
                    gameFrame.getActionsPanel2().showEncounterBtns();
                    gameFrame.getActionsPanel2().removeFightBtns(gameState.getCurrentEnemyKilled());
                    gameFrame.getStatsPanel().getBloodVialsAmountLabel().setText("" + player.getBloodVials());
                    gameFrame.getStatsPanel().setHealthPotionLabelAmount(player.getPlayerInventory().getHealthPotion().getQuantity());
                    gameFrame.getStatsPanel().setHealthLabelAmount(player.getHealth());
                }
                //System.out.println("waiting done");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Error");
            }
        }
    }

    // action listener response for fight button sets player choice and changes button visibility, then notifies main thread to resume
    public void fightButtonListener(Object src, GameFrame gameFrame) {
        synchronized (this) {
            if (src.equals(gameFrame.getActionsPanel2().fightBtn)) {
                gameFrame.getActionsPanel2().removeEncounterBtns();
                gameFrame.getActionsPanel2().showFightBtns();
                gameFrame.getActionsPanel2().setPlayerChoice(1);
            }
            notify();
        }
    }

    // action listener response for run button sets player choice and changes button visibility, then notifies main thread to resume
    public void runButtonListener(Object src, GameFrame gameFrame) {
        synchronized (this) {
            if (src.equals(gameFrame.getActionsPanel2().runBtn)) {
                gameFrame.getActionsPanel2().setPlayerChoice(2);
            }
            notify();
        }
    }

    // action listener response for attack button sets player choice, then notifies main thread to resume
    public void attackButtonListener(Object src, GameFrame gameFrame) {
        synchronized (this) {
            if (src.equals(gameFrame.getActionsPanel2().attackBtn)) {
                gameFrame.getActionsPanel2().setPlayerChoice(3);
            }
            notify();
        }
    }

    // action listener response for heal button sets player choice, then notifies main thread to resume
    public void healButtonListener(Object src, GameFrame gameFrame) {
        synchronized (this) {
            if (src.equals(gameFrame.getActionsPanel2().healBtn)) {
                gameFrame.getActionsPanel2().setPlayerChoice(4);
            }
            notify();
        }
    }

    // action listener response for submit button updates the JFrame, hiding and removing the title screen and creating the game screen, then notifies main thread to resume
    public void submitButtonListener(Object src, GameFrame gameFrame) {
        synchronized (this) {
            if (src.equals(gameFrame.getTitlePanel().getSubmit())) {
                gameFrame.setName(gameFrame.getTitlePanel().getTextField().getText());
                gameFrame.createGameScreen();
                // System.out print stream is redirected to game screen's main panel text area
                PrintStream printStream = new PrintStream(new GameOutputStream(gameFrame.getMainPanel().getTextArea()));
                PrintStream standardOut = System.out; // reference for standard output stream to console
                System.setOut(printStream);
                //System.out.println("Test: " + gameFrame.getName());
            }
            notify();
        }
    }

    // action listener response for vitality stat increase button increases player vitality stat and updates the vitality stat label
    public void vitalityButtonListener(Object src, GameFrame gameFrame) {
        if (src.equals(gameFrame.getStatsPanel().getVitalityBtn())) {
            if (player.getBloodVials() > 0) { // checks if player has available blood vials for stat increase
                player.increaseVitality();
                gameFrame.getStatsPanel().setVitalityLabelAmount(player.getVitality());
                player.setBloodVials(player.getBloodVials() - 1);
                gameFrame.getStatsPanel().setBloodVialLabelAmount(player.getBloodVials());
            }
        }
    }

    // action listener response for strength stat increase button increases player strength stat and updates the strength stat label
    public void strengthButtonListener(Object src, GameFrame gameFrame) {
        if (src.equals(gameFrame.getStatsPanel().getStrengthBtn())) {
            if (player.getBloodVials() > 0) { // checks if player has available blood vials for stat increase
                player.increaseStrength();
                gameFrame.getStatsPanel().setStrengthLabelAmount(player.getStrength());
                player.setBloodVials(player.getBloodVials() - 1);
                gameFrame.getStatsPanel().setBloodVialLabelAmount(player.getBloodVials());
            }
        }
    }

    // action listener response for agility stat increase button increases player agility stat and updates the agility stat label
    public void agilityButtonListener(Object src, GameFrame gameFrame) {
        if (src.equals(gameFrame.getStatsPanel().getAgilityBtn())) {
            if (player.getBloodVials() > 0) { // checks if player has available blood vials for stat increase
                player.increaseAgility();
                gameFrame.getStatsPanel().setAgilityLabelAmount(player.getAgility());
                player.setBloodVials(player.getBloodVials() - 1);
                gameFrame.getStatsPanel().setBloodVialLabelAmount(player.getBloodVials());
            }
        }
    }
}

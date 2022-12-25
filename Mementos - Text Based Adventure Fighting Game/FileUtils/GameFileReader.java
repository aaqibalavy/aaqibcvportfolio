package FileUtils;

import GUI.GameFrame;
import GameUtils.GameState;
import GameUtils.Player;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class GameFileReader {
    private Player player;
    private GameState gameState;
    private String fileName;
    String localDir = System.getProperty("user.dir");

    public GameFileReader(){}

    // reads given file by deserializing byte code into Object and casting said Object to a Player object
    public void readPlayerFile(String passedFileName, GameFrame gameFrame) throws IOException {
        Player readPlayer = new Player();
        //fileName = passedFileName;
        File fileChecker = new File(localDir + "/src/SaveFiles/player_" + passedFileName);
        boolean valid = false;
        // exception handler for if the given file doesn't exist, dialog input box loops until user gives a valid file that exists
        while (!fileChecker.exists()) {
            try {
                int input = JOptionPane.showConfirmDialog(gameFrame, "This file does not exist, please input a file that exists.", "Invalid Filename", JOptionPane.DEFAULT_OPTION);
                fileName = JOptionPane.showInputDialog(gameFrame, "Please enter desired filename:", "Filename Dialog", JOptionPane.INFORMATION_MESSAGE);
                File newFile = new File(localDir + "/src/SaveFiles/player_" + fileName);
                valid = fileChecker.renameTo(newFile);
            } catch (Exception e){
                System.err.println("IOException Occured: " + e.toString());
            }
            if(valid){
                System.out.println("Reading from file..");
            }
        }
        FileInputStream fileIn = new FileInputStream(fileChecker);
        ObjectInputStream playerDetailsIn = new ObjectInputStream(fileIn);
        try {
            Object obj = playerDetailsIn.readObject(); // byte code deserialized into Object
            readPlayer = (Player)obj; // Object cast to Player
        } catch(IOException e) {
            System.err.println("IOException Occured: " + e.toString()); // prints exception error
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        player = readPlayer; // player field assigned to loaded Player object reference
        System.out.println("File read successfully, " + player.getName() + "'s player details were retrieved!");
        // streams closed
        fileIn.close();
        playerDetailsIn.close();
    }

    // reads given file by deserializing byte code into Object and casting said Object to a GameState object
    public void readGameStateFile(String passedFileName, GameFrame gameFrame) throws IOException {
        GameState readGameState = new GameState();
        File file = new File(localDir + "/src/SaveFiles/gameState_" + passedFileName);
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream gameStateDetailsIn = new ObjectInputStream(fileIn);
        try {
            Object obj = gameStateDetailsIn.readObject(); // byte code deserialized into Object
            readGameState = (GameState)obj; // Object cast to GameState
        } catch(IOException e) {
            System.err.println("IOException Occured: " + e.toString()); // prints exception error
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        gameState = readGameState; // gameState field assigned to loaded GameState object reference
        System.out.println("File read successfully, " + player.getName() + "'s game state details were retrieved!");
        // dialog box notifying successful file retrieval
        int input = JOptionPane.showConfirmDialog(gameFrame, "File read successfully, " + player.getName() + "'s details were retrieved!", "Files Retrieved", JOptionPane.DEFAULT_OPTION);
        // streams closed
        fileIn.close();
        gameStateDetailsIn.close();
    }

    // asks player using a confirm dialog box whether they want to load a file
    public boolean askLoadFile(GameFrame gameFrame) {
        boolean loaded;
        int input = JOptionPane.showConfirmDialog(gameFrame, "Would you like to load a previous save file?", "File Load Dialog", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if(input == 0){ loaded = true; }
        else { loaded = false; }
        return loaded;
    }

    // loads file by passing given file name (retrieving using input dialog box) to read file methods
    public void loadFile(GameFrame gameFrame, boolean loaded) throws IOException {
        if(loaded) {
            String fileName = JOptionPane.showInputDialog(gameFrame, "Please enter desired filename:", "Filename Dialog", JOptionPane.INFORMATION_MESSAGE);
            readPlayerFile(fileName, gameFrame);
            readGameStateFile(fileName, gameFrame);
        }
    }

    // accessor methods to get the object references for the loaded Player and GameState objects
    public Player getReadPlayer(){return player;}
    public GameState getReadGameState(){return gameState;}
}

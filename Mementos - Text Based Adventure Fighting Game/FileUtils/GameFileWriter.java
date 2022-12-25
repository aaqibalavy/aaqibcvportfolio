package FileUtils;

import GUI.GameFrame;
import GameUtils.GameState;
import GameUtils.Player;

import javax.swing.*;
import java.io.*;

public class GameFileWriter {
    private GameState gameState;
    private Player player;
    private GameFrame gameFrame;
    private static String newFileName;
    String localDir = System.getProperty("user.dir");

    // fields initialised to passed object references
    public GameFileWriter(GameState givenGameState, Player givenPlayer, GameFrame givenGameFrame) {
        gameState = givenGameState;
        player = givenPlayer;
        gameFrame = givenGameFrame;
    }

    // file is created with serialized byte code representing the passed Player object written to it
    public void createPlayerSaveFile(String fileName) throws IOException, InterruptedException {
        File file = new File(localDir + "/src/SaveFiles/player_" + fileName);
        boolean success = false;
        // exception handler for existing file name, dialog input box loops until a file name that doesn't already exist is entered
        while (file.exists()) {
            System.out.println("\nThis filename is already taken, please choose another name.");
            try {
                Thread.sleep(1000);
                newFileName = JOptionPane.showInputDialog(gameFrame, "Enter Filename:", "Filename Dialog", JOptionPane.INFORMATION_MESSAGE);
                File newFile = new File(localDir + "/src/SaveFiles/player_" + newFileName);
                success = file.renameTo(newFile);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (success) {
                System.out.println("\nCreating player file...");
                Thread.sleep(1500);
            } else {
                System.out.println("File could not be created.");
            }
        }

        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream playerDetailsOut = new ObjectOutputStream(fileOut);
        try {
            playerDetailsOut.writeObject(player); // serialized byte code is written to file
        } catch (IOException e) {
            System.err.println("IOException Occurred: " + e.toString()); // prints exception error message
        }
        // streams closed
        fileOut.close();
        playerDetailsOut.close();
        System.out.println("Successfully created " + player.getName() + "'s player file!");
    }

    // file is created with serialized byte code representing the passed Player and GameState objects written to it
    public void createGameStateFile(String fileName) throws IOException, InterruptedException {
        File file = new File(localDir + "/src/SaveFiles/gameState_" + fileName);
        System.out.println("Creating game file...");
        Thread.sleep(1500);

        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream gameStateDetailsOut = new ObjectOutputStream(fileOut);
        try {
            gameStateDetailsOut.writeObject(gameState); // serialized byte code is written to file
        } catch (IOException e) {
            System.err.println("IOException Occurred: " + e.toString()); // prints exception error message
        }
        // streams closed
        fileOut.close();
        gameStateDetailsOut.close();
        System.out.println("Successfully created " + player.getName() + "'s game file!");
    }



}

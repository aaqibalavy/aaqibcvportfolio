package GameUtils;

import FileUtils.GameFileReader;
import GUI.GameFrame;

import java.io.IOException;
import java.util.Scanner;

public class Game {
    // static variables declared
    static GameState gameState;
    static Player player;
    static GameFrame gameFrame;
    static Story story;
    static GameLogicWorker gameLogicWorker;
    static GameFileReader gameFileReader;

    public static void main(String[] args) throws InterruptedException, IOException {
        Scanner sc = new Scanner(System.in);
        gameState = new GameState(); // new GameState object instantiated
        player = new Player(); // new Player object instantiated
        gameFileReader = new GameFileReader(); // new GameFileReader instantiated
        gameFrame = new GameFrame(player, gameState, gameFileReader); // new GameFrame object instantiated, creating GUI window

        // checks if a file has been loaded and initialises Player and GameState objects to the loaded objects
        if(gameFrame.isFileLoaded()){
            player = gameFileReader.getReadPlayer();
            gameState = gameFileReader.getReadGameState();
            gameState.setFirstClear(true);
            gameState.setContinueCurrentFloor(true);
        }

        story = new Story(player, gameState); // new Story object instantiated
        gameLogicWorker = new GameLogicWorker(gameState, player, story); // new GameLogicWorker instantiated
        gameFrame.setGameLogicWorker(gameLogicWorker); // GameFrame object initialises its GameLogicWorker attribute to point to above instantiated GameFrame object
        gameLogicWorker.runGame(gameFrame); // GameLogicWorker object executes method that runs the game
    }

    // inputString method returns user input
    public static String inputString(String message)
    {
        // new scanner initialised
        Scanner scanner = new Scanner(System.in);
        // message is printed
        System.out.println(message);
        // user input assigned to string
        return scanner.nextLine();
    }

    // isPlural method allows for plural/singular words
    public static String isPlural(int number)
    {
        String plural = "";

        // decision for storing 's' for plural or empty string for singular
        if (number == 1)
        {
            plural = "";
        }
        else
        {
            plural = "s";
        }
        return plural;
    }

}

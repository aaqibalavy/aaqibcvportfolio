package GUI;

import FileUtils.GameFileReader;
import GameUtils.GameLogicWorker;
import GameUtils.GameState;
import GameUtils.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameFrame extends JFrame implements ActionListener {

    private static boolean titleActive; // tracks if title screen is active
    private GridBagConstraints gbc = new GridBagConstraints(); // new GridBagConstraints object for GridBagLayout
    private TitlePanel titlePanel; // TitlePanel variable declared
    private MainPanel mainPanel; // MainPanel variable declared
    private static ActionsPanel actionsPanel; // ActionsPanel variable declared
    private StatsPanel statsPanel; // StatsPanel variable declared
    private Player player; // Player variable declared
    private GameState gameState; // GameState variable declared
    private GameLogicWorker gameLogicWorker; // GameLogicWorker variable declared
    private GameFileReader gameFileReader;
    private boolean fileLoaded;

    public GameFrame(Player givenPlayer, GameState game, GameFileReader givenGameFileReader) throws IOException {
        // title screen is created
        setLayout(new BorderLayout());
        setSize(new Dimension(800, 600));
        setBackground(Color.black);
        setForeground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Mementos Underworld");
        setResizable(false);
        titlePanel = new TitlePanel();
        add(titlePanel, BorderLayout.CENTER);
        setVisible(true);
        titlePanel.getSubmit().addActionListener(this); // ActionListener added to submit button on TitlePanel object

        gameFileReader = givenGameFileReader;
        fileLoaded = gameFileReader.askLoadFile(this); // checks if user wants to load file
        gameFileReader.loadFile(this, fileLoaded); // loads file if user chose to
        // if file is loaded, Player and GameState fields are assigned to the loaded object references, and title panel is altered
        if(fileLoaded){
            this.player = gameFileReader.getReadPlayer();
            this.gameState = gameFileReader.getReadGameState();
            titlePanel.getTextField().setEditable(false);
            titlePanel.getSubmit().setText("Resume Game");
        }
        else{ // if file isn't loaded, Player and GameState fields are assigned to the passed object references
            this.player = givenPlayer;
            this.gameState = game;
        }

    }

    // accessor methods
    public TitlePanel getTitlePanel(){
        return this.titlePanel;
    }
    public MainPanel getMainPanel(){
        return this.mainPanel;
    }
    public static ActionsPanel getActionsPanel(){
        return actionsPanel;
    }
    public ActionsPanel getActionsPanel2(){
        return actionsPanel;
    }
    public StatsPanel getStatsPanel(){ return statsPanel; }
    public boolean isFileLoaded(){ return fileLoaded; }

    // sets GameLogicWorker field to point to the GameLogicWorker object instantiated in the Game class Main method
    public void setGameLogicWorker(GameLogicWorker worker){
        this.gameLogicWorker = worker;
    }

    // creates game screen with main, stat and action panels
    public void createGameScreen(){

        titlePanel.setVisible(false);
        this.remove(titlePanel);
        titleActive = false;

        this.setLayout(new GridBagLayout());
        gbc.weightx = 1;
        gbc.weighty = 1;

        // positioning for mainPanel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.LINE_START;
        mainPanel = new MainPanel();
        add(mainPanel, gbc);

        // positioning for statsPanel
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.LINE_END;
        statsPanel = new StatsPanel(player);
        add(statsPanel, gbc);

        // positioning for actionsPanel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.LINE_START;
        actionsPanel = new ActionsPanel();
        add(actionsPanel, gbc);

        // ActionListeners added to player action buttons
        actionsPanel.fightBtn.addActionListener(this);
        actionsPanel.runBtn.addActionListener(this);
        actionsPanel.attackBtn.addActionListener(this);
        actionsPanel.healBtn.addActionListener(this);

        // ActionListeners added to stat increase buttons
        statsPanel.getVitalityBtn().addActionListener(this);
        statsPanel.getStrengthBtn().addActionListener(this);
        statsPanel.getAgilityBtn().addActionListener(this);

        pack();
        setVisible(true);
    }

    // executes upon events fired from components with ActionListeners
    // each component executes a different method from GameLogicWorker class
    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src.equals(getTitlePanel().getSubmit())){
            gameLogicWorker.submitButtonListener(src, this);
        }
        else if (src.equals(actionsPanel.fightBtn)) {
            gameLogicWorker.fightButtonListener(src, this);
        }
        else if (src.equals(actionsPanel.runBtn)){
            gameLogicWorker.runButtonListener(src, this);
        }
        else if (src.equals(actionsPanel.attackBtn)){
            gameLogicWorker.attackButtonListener(src, this);
        }
        else if (src.equals(actionsPanel.healBtn)){
            gameLogicWorker.healButtonListener(src, this);
        }
        else if (src.equals(statsPanel.getVitalityBtn())){
            gameLogicWorker.vitalityButtonListener(src, this);
        }
        else if (src.equals(statsPanel.getStrengthBtn())){
            gameLogicWorker.strengthButtonListener(src, this);
        }
        else if (src.equals(statsPanel.getAgilityBtn())){
            gameLogicWorker.agilityButtonListener(src, this);
        }
    }
}

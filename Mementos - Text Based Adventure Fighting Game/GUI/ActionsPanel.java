package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ActionsPanel extends JPanel {
    // action buttons declared
    public JButton fightBtn;
    public JButton runBtn;
    public JButton attackBtn;
    public JButton healBtn;
    private static int playerChoice; // tracks player button choice with integers 1-4 assigned to each choice
    // colours
    private Color oldMauve = new Color(91, 35, 51);
    private Color warmWhite = new Color(247, 244, 243);

    ActionsPanel(){
        playerChoice = 0;

        // actions panel created
        setBackground(oldMauve);
        setPreferredSize(new Dimension(800,180));
        GridLayout layout = new GridLayout(1,2);
        layout.setHgap(10);
        setLayout(layout);
        Border outer = BorderFactory.createLineBorder(Color.white, 2,false);
        Border inner = new EmptyBorder(10, 10, 10, 10);
        setBorder(BorderFactory.createCompoundBorder(outer, inner));

        // action buttons initialised and added to panel
        fightBtn = new JButton("F I G H T");
        runBtn = new JButton("R U N");
        attackBtn = new JButton("A T T A C K");
        healBtn = new JButton("H E A L");
        add(fightBtn);
        add(runBtn);
        add(attackBtn);
        add(healBtn);
        // sets button formatting
        setButtonFormatting(oldMauve, warmWhite);
        // buttons hidden
        fightBtn.setVisible(false);
        runBtn.setVisible(false);
        attackBtn.setVisible(false);
        healBtn.setVisible(false);
    }

    // methods to show and hide buttons
    public void showEncounterBtns(){
        fightBtn.setVisible(true);
        runBtn.setVisible(true);
    }

    public void removeEncounterBtns(){
        fightBtn.setVisible(false);
        runBtn.setVisible(false);
    }

    public void showFightBtns(){
        attackBtn.setVisible(true);
        healBtn.setVisible(true);
    }
    // buttons hidden only if enemy has been killed
    public void removeFightBtns(boolean isEnemyKilled){
        if(isEnemyKilled){
            attackBtn.setVisible(false);
            healBtn.setVisible(false);
        }
    }

    public int getPlayerChoice(){ return playerChoice; }
    public void setPlayerChoice(int choice){ playerChoice = choice; }

    public void setButtonFormatting(Color foreColour, Color backColour){
        fightBtn.setForeground(foreColour);
        runBtn.setForeground(foreColour);
        attackBtn.setForeground(foreColour);
        healBtn.setForeground(foreColour);

        fightBtn.setBackground(backColour);
        runBtn.setBackground(backColour);
        attackBtn.setBackground(backColour);
        healBtn.setBackground(backColour);

        fightBtn.setPreferredSize(new Dimension(100,100));

        //fightBtn.setBorderPainted(false);
        fightBtn.setFocusPainted(false);
        //runBtn.setBorderPainted(false);
        runBtn.setFocusPainted(false);
        //attackBtn.setBorderPainted(false);
        attackBtn.setFocusPainted(false);
        //healBtn.setBorderPainted(false);
        healBtn.setFocusPainted(false);

        Font buttonFont = new Font("Helvetica", Font.BOLD, 15);
        fightBtn.setFont(buttonFont);
        runBtn.setFont(buttonFont);
        attackBtn.setFont(buttonFont);
        healBtn.setFont(buttonFont);
        /*Insets buttonInsets = new Insets(1,12,1,12);
        fightBtn.setMargin(buttonInsets);
        runBtn.setMargin(buttonInsets);
        attackBtn.setMargin(buttonInsets);*/
    }
}

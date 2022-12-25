package GUI;

import GameUtils.Player;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StatsPanel extends JPanel {
    GridBagConstraints gbc = new GridBagConstraints();
    // stat button variables declared
    private JButton vitalityBtn;
    private JButton strengthBtn;
    private JButton agilityBtn;
    // stat name label variables declared
    private JLabel vitalityStatName;
    private JLabel strengthStatName;
    private JLabel agilityStatName;
    // stat amount label variables declared
    private JLabel vitalityAmount;
    private JLabel strengthAmount;
    private JLabel agilityAmount;
    private Player player; // player variable declared
    // blood vial, health potion and current health labels declared
    private JLabel bloodVialName;
    private JLabel bloodVialAmount;
    private JLabel healthPotionsName;
    private JLabel healthPotionsAmount;
    private JLabel healthName;
    private JLabel healthAmount;
    private JTextArea statsDescription; // text area for stat description declared
    // colours initialised
    private Color oldMauve = new Color(91, 35, 51);
    private Color warmWhite = new Color(247, 244, 243);

    StatsPanel(Player givenPlayer){
        this.player = givenPlayer;
        // stat panel created
        setBackground(oldMauve);
        setPreferredSize(new Dimension(200,420));
        setLayout(new GridBagLayout());
        Border outer = BorderFactory.createLineBorder(warmWhite, 2,false);
        Border inner = new EmptyBorder(10, 10, 10, 10);
        setBorder(BorderFactory.createCompoundBorder(outer, inner));

        // stat buttons, name labels and amount labels initialised
        vitalityBtn = new JButton(" + ");
        strengthBtn = new JButton(" + ");
        agilityBtn = new JButton(" + ");
        vitalityStatName = new JLabel("Vitality:");
        strengthStatName = new JLabel("Strength:");
        agilityStatName = new JLabel("Agility:");
        vitalityAmount = new JLabel("" + player.getVitality());
        strengthAmount = new JLabel("" + player.getStrength());
        agilityAmount = new JLabel("" + player.getAgility());
        // blood vial labels initialised
        bloodVialName = new JLabel("Blood Vials:");
        bloodVialAmount = new JLabel("" + player.getBloodVials());
        // health potion labels initialised
        healthPotionsName = new JLabel("Health Potions:");
        healthPotionsAmount = new JLabel("" + player.getPlayerInventory().getHealthPotion().getQuantity());
        // current health labels initialised
        healthName = new JLabel("Current Health:");
        healthAmount = new JLabel("" + player.getHealth());

        // textarea for stat description initialised with formatting added
        statsDescription = new JTextArea("--------------------------------------------\n\nVitality accounts for your max health points\n - increases by 10\n\nStrength calculates the amount of damage you do to enemies\n - increases by 2\n\nAgility determines your chance to dodge enemy attacks\n - increases by 1");
        statsDescription.setEditable(false);
        statsDescription.setLineWrap(true);
        statsDescription.setWrapStyleWord(true);
        statsDescription.setBackground(oldMauve);
        statsDescription.setSize(185, 300);

        // all component fonts altered
        setFonts(warmWhite);
        // all buttons altered
        setButtons(oldMauve, warmWhite);

        // standard weightings
        gbc.weightx = 1;
        gbc.weighty = 0.5;

        // positioning for vitality components
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.weighty = 2;
        add(vitalityBtn, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbc.weighty = 0.5;
        add(vitalityStatName, gbc);
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(vitalityAmount, gbc);

        // positioning for strength components
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.weighty = 2;
        add(strengthBtn, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbc.weighty = 0.5;
        add(strengthStatName, gbc);
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(strengthAmount, gbc);

        // positioning for agility components
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.weighty = 2;
        add(agilityBtn, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbc.weighty = 0.5;
        add(agilityStatName, gbc);
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(agilityAmount, gbc);

        // positioning for blood vial components
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weighty = 7;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(bloodVialName, gbc);
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(bloodVialAmount, gbc);

        // positioning for health potion components
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(healthPotionsName, gbc);
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(healthPotionsAmount, gbc);

        // positioning for current health components
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weighty = 7;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(healthName, gbc);
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(healthAmount, gbc);

        // positioning for stat description text area
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weighty = 5;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(statsDescription, gbc);

    }

    // sets font for all components (colour, name, style, size)
    public void setFonts(Color givenColour){
        vitalityStatName.setForeground(givenColour);
        strengthStatName.setForeground(givenColour);
        agilityStatName.setForeground(givenColour);
        vitalityAmount.setForeground(givenColour);
        strengthAmount.setForeground(givenColour);
        agilityAmount.setForeground(givenColour);
        bloodVialName.setForeground(givenColour);
        bloodVialAmount.setForeground(givenColour);
        healthPotionsName.setForeground(givenColour);
        healthPotionsAmount.setForeground(givenColour);
        statsDescription.setForeground(givenColour);
        healthName.setForeground(givenColour);
        healthAmount.setForeground(givenColour);

        Font fontPlain = new Font("Arial", Font.ITALIC, 12);
        Font fontBold = new Font("Arial", Font.BOLD, 13);
        vitalityStatName.setFont(fontBold);
        strengthStatName.setFont(fontBold);
        agilityStatName.setFont(fontBold);
        vitalityAmount.setFont(fontBold);
        strengthAmount.setFont(fontBold);
        agilityAmount.setFont(fontBold);
        bloodVialName.setFont(fontBold);
        bloodVialAmount.setFont(fontBold);
        healthPotionsName.setFont(fontBold);
        healthPotionsAmount.setFont(fontBold);
        statsDescription.setFont(fontPlain);
        healthName.setFont(fontBold);
        healthAmount.setFont(fontBold);
    }

    // sets button styling (font, colours and button formatting)
    public void setButtons(Color foreColour, Color backColour){
        vitalityBtn.setForeground(foreColour);
        strengthBtn.setForeground(foreColour);
        agilityBtn.setForeground(foreColour);

        vitalityBtn.setBackground(backColour);
        strengthBtn.setBackground(backColour);
        agilityBtn.setBackground(backColour);

        //vitalityBtn.setBorderPainted(false);
        vitalityBtn.setFocusPainted(false);
        //strengthBtn.setBorderPainted(false);
        strengthBtn.setFocusPainted(false);
        //agilityBtn.setBorderPainted(false);
        agilityBtn.setFocusPainted(false);

        Font buttonFont = new Font("Arial", Font.BOLD, 15);
        vitalityBtn.setFont(buttonFont);
        strengthBtn.setFont(buttonFont);
        agilityBtn.setFont(buttonFont);
        Insets buttonInsets = new Insets(1,12,1,12);
        vitalityBtn.setMargin(buttonInsets);
        strengthBtn.setMargin(buttonInsets);
        agilityBtn.setMargin(buttonInsets);
    }

    // accessor methods
    public JButton getVitalityBtn(){ return vitalityBtn; }
    public JButton getStrengthBtn(){ return strengthBtn; }
    public JButton getAgilityBtn(){ return agilityBtn; }
    public JLabel getHealthPotionsAmountLabel(){ return healthPotionsAmount; }
    public JLabel getBloodVialsAmountLabel(){ return bloodVialAmount; }

    // setter methods
    public void setVitalityLabelAmount(int amount){ vitalityAmount.setText("" + amount); }
    public void setStrengthLabelAmount(int amount){ strengthAmount.setText("" + amount); }
    public void setAgilityLabelAmount(int amount){ agilityAmount.setText("" + amount); }
    public void setBloodVialLabelAmount(int amount){ bloodVialAmount.setText("" + amount); }
    public void setHealthPotionLabelAmount(int amount){ healthPotionsAmount.setText("" + amount); }
    public void setHealthLabelAmount(int amount){ healthAmount.setText("" + amount); }


}

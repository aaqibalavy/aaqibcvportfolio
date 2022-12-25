package GUI;

import javax.swing.*;
import java.awt.*;

public class TitlePanel extends JPanel {
    private JLabel welcomeMessage; // label for welcome message declared
    private JLabel nameLabel; // label for enter name message declared
    private JTextField nameInput; // textfield for name input declared
    private JButton submit; // button for user submit declared
    GridBagConstraints gbc = new GridBagConstraints(); // new GridBagConstraints object created for GridBagLayout

    TitlePanel(){
        // title panel created with formatting
        setBackground(Color.black);
        setPreferredSize(new Dimension(800,600));
        setLayout(new GridBagLayout());

        // welcome message created with formatting
        welcomeMessage = new JLabel("Welcome to Mementos");
        welcomeMessage.setBackground(Color.black);
        welcomeMessage.setForeground(Color.white);
        welcomeMessage.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        welcomeMessage.setFont(welcomeMessage.getFont().deriveFont(40f));

        // enter name message created with formatting
        nameLabel = new JLabel("Please enter your name:");
        nameLabel.setBackground(Color.black);
        nameLabel.setForeground(Color.white);
        nameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        // text field for name input created with formatting
        nameInput = new JTextField();
        nameInput.setBackground(Color.black);
        nameInput.setForeground(Color.white);
        nameInput.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        nameInput.setPreferredSize(new Dimension(400,30));
        nameInput.setHorizontalAlignment(SwingConstants.CENTER);

        // submit button created with formatting
        submit = new JButton("S U B M I T");
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
        submit.setFont(new Font("Arial Narrow", Font.PLAIN, 14));
        submit.setFocusPainted(false);

        // positioning for welcome message label
        gbc.weightx = 0;
        gbc.weighty = 2.5;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.SOUTH;
        add(welcomeMessage, gbc);

        // positioning for enter name message label
        gbc.gridy = 1;
        gbc.weighty = 0.5;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        add(nameLabel, gbc);

        // positioning for name input text field
        gbc.gridy = 2;
        gbc.weighty = 0.3;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
        add(nameInput, gbc);

        // positioning for submit button
        gbc.gridy = 3;
        gbc.weighty = 3;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
        add(submit, gbc);
    }

    // accessor methods
    public JLabel getNameLabel(){ return nameLabel; }
    public JTextField getTextField(){ return nameInput; }
    public JButton getSubmit(){ return submit; }
}

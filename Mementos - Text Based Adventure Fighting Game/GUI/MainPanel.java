package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;
import java.awt.*;

public class MainPanel extends JPanel {
    private JTextArea textArea; // JTextArea variable declared
    private JScrollPane scrollPane; // JScrollPane variable declared

    public MainPanel(){
        // creates mainPanel
        setBackground(Color.black);
        setPreferredSize(new Dimension(600,420));
        setLayout(new BorderLayout());
        Border outer = BorderFactory.createLineBorder(Color.white, 2,false);
        Border inner = new EmptyBorder(10, 10, 10, 10);
        setBorder(BorderFactory.createCompoundBorder(outer, inner));

        // new text area added to panel
        this.textArea = new JTextArea(10, 10);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setBackground(Color.black);
        textArea.setForeground(Color.white);
        textArea.setMargin( new Insets(10,10,10,10) );
        textArea.setFont(new Font("Dialog", Font.PLAIN, 16));

        // textarea added to scroll pane to allow scrolling
        this.scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        // allow auto-scroll so that when text area is updated, it scrolls to bottom
        DefaultCaret caret = (DefaultCaret)textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    }

    public JTextArea getTextArea(){ return this.textArea; }

}

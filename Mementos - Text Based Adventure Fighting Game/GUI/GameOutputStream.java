package GUI;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;

public class GameOutputStream extends OutputStream {
    private JTextArea textArea; // new textarea declared

    // class textarea initialised to same JTextArea object that's passed
    public GameOutputStream(JTextArea destination){
        this.textArea = destination;
    }

    @Override
    public void write(int b) throws IOException {
        // redirects output to text area
        textArea.append(String.valueOf((char) b));
    }

}

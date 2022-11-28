import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
    final String ROOT_SLANG_WORD_FILE_NAME = "slang.txt";
    final String CURRENT_SLANG_WORD_FILE_NAME = "slangWord.txt";
    final String HISTOTY_FILE_NAME = "history.txt";
    public static void createAndShowGUI(){
        JFrame frame = new JFrame("Slang Word");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MainGUI.gui(frame.getContentPane());

        frame.setSize(1024, 576);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        createAndShowGUI();
    }
}